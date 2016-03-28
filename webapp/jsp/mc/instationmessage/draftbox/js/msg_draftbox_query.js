//上下文根
var context = G3.webPath;

$('#sendTimeFrom').datetimepicker({
	minView: "month", //选择日期后，不会再跳转去选择时分秒 　　
	format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 　　
	language: 'zh-CN', //汉化 　
	autoclose:true //选择日期后自动关闭
});

/***
*选择日期
**/
function selectCalendar(obj){
    $(obj).prev().datetimepicker('show');
}

$('#sendTimeTo').datetimepicker({
	minView: "month", //选择日期后，不会再跳转去选择时分秒 　　
	format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 　　
	language: 'zh-CN', //汉化 　
	autoclose:true //选择日期后自动关闭
});

/**
 * 页面初始化
 */
$(function() {
	//初始化表格
	var url = "mc/core/instationmessage/instationMsgList";
	grid = new G3.Grid("inEnvelopeList");
	//设置过滤条件：草稿
	var sendState = "0";
	//设置过滤条件：消息
	var messageType = "m";
	//设置数据请求地址
	grid.setAjaxUrl(url);
	grid.setParameter("sendState", sendState);
	grid.setParameter("messageType", messageType);
	//初始化
	grid.init();

	// 增加
	$("#editBtn").bind("click", function () {
		edit();
	});
	
	//回复
	$("#replyBtn").click(function (){
		var records = grid.getSelectedRow();
		if (records.length == 1) {
			var data = records[0];
			var url = G3.cmdPath + "demo/user/edit";
			if (data.id != undefined && data.id != "" && data.id != "null") {
				url += "?id=" + data.id;
			}
			
			G3.showModalDialog("回复邮件", url, {
				width : 800,
				height : 500
			}, function(e, ret) {
				if (ret == "1") {
					grid.reload();
				}
			});
		} else {
			G3.alert("提示", "请选择要回复的邮件！");
		}
	});
	
	//转发
	$("#forwardBtn").click(function (){
		var records = grid.getSelectedRow();
		if (records.length == 1) {
			var data = records[0];
			var url = G3.cmdPath + "mc/core/forward";
			if (data.id != undefined && data.id != "" && data.id != "null") {
				url += "?id=" + data.id;
			}
			
			G3.showModalDialog("回复邮件", url, {
				width : 800,
				height : 500
			}, function(e, ret) {
				if (ret == "1") {
					grid.reload();
				}
			});
		} else {
			G3.alert("提示", "请选择要回复的邮件！");
		}
	});
	
	//增加（跳转）
	$("#addLink").click(function (){
		//页面跳转
		var url = G3.cmdPath + "mc/core/forward_edit";
		G3.forward(url);
	});
	
	//修改（跳转）
	$("#editLink").click(function (){
		var records = grid.getSelectedRow();
		if (records.length == 1) {
			var data = records[0];
			var url = G3.cmdPath + "demo/user/forward_edit";
			if (data.id != undefined && data.id != "" && data.id != "null") {
				url += "?id=" + data.id;
			}
			
			G3.forward(url);
		} else {
			G3.alert("提示", "请选择一个用户！");
		}
	});
	
	

	// 条件查询
	$("#queryBtn").click(query);
	
	// 查询更多查询条件初始化
	$("#moresearch").morequery({
		"title" : "",
		"content" : template('mypopover', {})
	});
	
	// 更多查询
	$("body").on("click", "#moreQueryBtn", query);
	//重置
	$("body").on("click", "#resetBtn", function(){
		$("#messageTopic").val("");
		$("#sendState").val("");
		$("#receiverName").val("");
		$("#sendTimeFrom").val("");
		$("#sendTimeTo").val("");
		
	});
	
	//绑定回车事件
	$("#userId").bind('keypress', function(event) {
		if (event.keyCode == "13") {
			query();
		}
		event.stopPropagation();
	});

});

/**
 * 编辑
 */
function edit(){
	var records = grid.getSelectedRow();
	if (records.length == 1) {
		var data = records[0];
		var url = G3.cmdPath + "mc/core/instationmessage/editdraft";
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
		G3.alert("提示", "请选择一条消息！");
	}
}

/**
 * 渲染账户状态
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function renderstatus(data, type, full) {
	if (data != "" || data != null) {
		if (data == "N") {
			data = "启用";
		}
		if (data == "X") {
			data = "停用";
		}
	}
	return data;
}

/**
 * 查询数据
 */
function query() {
	var messageTopic = $("#messageTopic").val();
	var sendState = $("#sendState").val();
	var receiverName = $("#receiverName").val();
	var sendTimeFrom = $("#sendTimeFrom").val();
	var sendTimeTo = $("#sendTimeTo").val();
	//设置过滤条件：草稿
	if(sendState == undefined || sendState == ""){
		var sendState = "0";
	}
	//设置过滤条件：消息
	var messageType = "m";
	if (messageTopic == undefined) {
		messageTopic = "";
	}
	
	var url = "mc/core/instationmessage/query";
	grid.setAjaxUrl(url);
	grid.setParameter("messageTopic", messageTopic);
	grid.setParameter("sendState", sendState);
	grid.setParameter("receiverName", receiverName);
	grid.setParameter("sendState", sendState);
	grid.setParameter("messageType", messageType);
	grid.load();
}
