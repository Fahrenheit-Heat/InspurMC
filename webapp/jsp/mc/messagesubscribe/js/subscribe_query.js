//上下文根
var context = G3.webPath;
$(function(){
	//初始化表格
	var url ="mc/core/subscribe/inSubscribeList";
	grid = new G3.Grid("inMassageList");
	//设置数据请求地址
	grid.setAjaxUrl(url);
	//初始化
	grid.init();
	
	//增加	
	$("#addBtn").bind("click", function () {
		add();
	});
	
	//修改
	$("#editBtn").click(function (){
		edit();
	});
	
	// 删除
	$("#delBtn").click(
		function() {
			del();
		});
	
	// 条件查询
	$("#queryBtn").click(query);
	
	// 更多查询
	$("body").on("click", "#moreQueryBtn", query);
	
	//重置
	$("body").on("click", "#resetBtn", function(){
		$("#subscribeModule").val("");
		$("#warnType").val("");
	});
	
	// 查询更多查询条件初始化
	$("#moresearch").morequery({
		"title" : "",
		"content" : template('mypopover', {})
	});
});

/**
 * 渲染订阅内容
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function rendermodule(data, type, full) {
	if (data != "" || data != null) {
		if (data == "A") {
			data = "模块1";
		}
		if (data == "B") {
			data = "模块2";
		}
		if (data == "C") {
			data = "模块3";
		}
		if (data == "D") {
			data = "模块4";
		}
	}
	return data;
}

/**
 * 渲染订阅内容
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function rendertype(data, type, full) {
	if (data != "" || data != null) {
		if (data == "A") {
			data = "全部方式";
		}
		if (data == "M") {
			data = "站内消息";
		}
		if (data == "W") {
			data = "站内邮件";
		}
		if (data == "S") {
			data = "短信";
		}
		if (data == "E") {
			data = "电子邮件";
		}
	}
	return data;
}
/**
 * 渲染是否开启订阅
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function renderopen(data, type, full) {
	if (data != "" || data != null) {
		if (data == "1") {
			data = "是";
		}
		if (data == "0") {
			data = "否";
		}
	}
	return data;
}

/**
 * 增加
 */
function add(){
	var url = G3.cmdPath + "mc/core/subscribe/edit";
	G3.showModalDialog("维护", url, {
		width : 800,
		height : 500
	}, function(e, ret) {
		if (ret == "1") {
			grid.reload();
		}
	});
}

/**
 * 编辑
 */
function edit(){
	var records = grid.getSelectedRow();
	if (records.length == 1) {
		var data = records[0];
		var url = G3.cmdPath + "mc/core/subscribe/edit";
		if (data.id != undefined && data.id != "" && data.id != "null") {
			url += "?id=" + data.id;
		}
		
		G3.showModalDialog("维护", url, {
			width : 800,
			height : 500
		}, function(e, ret) {
			if (ret == "1") {
				grid.reload();
			}
		});
	} else {
		G3.alert("提示", "请选择一个用户！");
	}
}

/**
 * 删除
 */
function del(){
	var records = grid.getSelectedRow();
	if (records.length != 0) {
		
		var recordIds = [];
		//循环遍历获取订阅ID 
		$.each(records, function(index, item){
			recordIds.push(item.id);
		});
		
		//删除警告框
		G3.confirm("提示", "确认删除记录？",
			function() {
				var requestUrl = G3.cmdPath+"mc/core/subscribe/ajaxdelete/"+recordIds;
				$.ajax({
					type : "post",
					dataType : "json",
					url: requestUrl,
					error:function(data){
						G3.alert("提示","删除失败！");
					},
					success:function(data){
						//弹框方式
						G3.alert("提示","删除成功！",function(){
							grid.reload();
						},"success");
					}
				});
			}
		);
	} else {
		G3.alert("提示", "请选择用户！");
	}
}

/**
 * 查询
 */
function query() {
	var warnType = $("#warnType").find("option:selected").val();
	//var warnType = $("#warnType").val();
	var subscribeModule = $("#subscribeModule").val();
	var subscribeOpen = $("input[name='subscribeOpen']:checked").val();
	
	var url = "mc/core/subscribe/query";
	grid.setAjaxUrl(url);
	grid.setParameter("warnType", warnType);
	grid.setParameter("subscribeModule", subscribeModule);
	grid.setParameter("subscribeOpen", subscribeOpen);
	grid.load();
}