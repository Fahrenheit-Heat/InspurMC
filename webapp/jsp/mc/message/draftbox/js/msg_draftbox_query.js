//上下文根
var context = G3.webPath;

//页面初始化
$(function() {
	
	// 表格初始化查询
	initGrid();


	// 修改
	$("#editBtn").bind("click", function () {
		edit();
	});
	
	//回复
	$("#replyBtn").click(function (){
		reply();
	});

	// 删除
	$("#delBtn").click(
		function() {
			del();
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
		$("#messageContent").val("");
		$("#sendState").val("");
		$("#receiverName").val("");
		$("#sendTimeFrom").val("");
		$("#sendTimeTo").val("");
		
	});
	
	//绑定回车事件
	$("#receiverName").bind('keypress', function(event) {
		if (event.keyCode == "13") {
			query();
		}
		event.stopPropagation();
	});
	
	/**
	 * 开始日期
	 */
	$('#sendTimeFrom').datetimepicker({
		minView: "month", //选择日期后，不会再跳转去选择时分秒
		format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 　　
		language: 'zh-CN', //汉化 　
		autoclose:true //选择日期后自动关闭
	});

	/**
	 * 截止日期
	 */
	$('#sendTimeTo').datetimepicker({
		minView: "month", //选择日期后，不会再跳转去选择时分秒 　　
		format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 　　
		language: 'zh-CN', //汉化 　
		autoclose:true //选择日期后自动关闭
	});
	
});

function initGrid(){
	//初始化表格
	var url = "mc/core/message/MessageList";
	grid = new G3.Grid("messageList");
	//设置过滤条件：未发送、发送失败
	var isNotSended = "0";
	var sendFailed = "5"
	//设置信封过滤：多条只显示一条
	var groupfield = "y";
	//设置数据请求地址
	grid.setAjaxUrl(url);
	grid.setParameter("messageType", messageType);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	grid.setParameter("groupfield", groupfield);
	grid.setParameter("isNotSended",isNotSended);
	grid.setParameter("sendFailed",sendFailed);
	//初始化
	grid.init();
}

/**
*选择日期
**
*/
function selectTime(obj){
	$(obj).datetimepicker({format: 'yyyy-mm-dd',minView: "month"});
}

/**
 * 删除
 */
function del(){
	var records = grid.getSelectedRow();
	if (records.length != 0) {
		
		var recordIds = [];
		//循环遍历获取ID 
		$.each(records, function(index, item){
			recordIds.push(item.messageId);
		});
		
		//删除警告框
		G3.confirm("提示", "确认删除记录？",
			function() {
				var requestUrl = G3.cmdPath+"mc/core/message/delete/"+recordIds+"/"+loginId+"/"+boxType;
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
 * 查询数据
 */
function query() {
	var messageContent = $("#messageContent").val();
	var receiverName = $("#receiverName").val();
	var sendTimeFrom = $("#sendTimeFrom").val();
	var sendTimeTo = $("#sendTimeTo").val();
	var sendState = $("#sendState").val();
	
	//设置过滤条件：未发送、发送失败
	var isNotSended = "0";
	var sendFailed = "5"
	//设置信封过滤：多条只显示一条
	var groupfield = "y";
	
	var url = "mc/core/message/query";
	grid.setAjaxUrl(url);
	grid.setParameter("messageContent", messageContent);
	grid.setParameter("isNotSended", isNotSended);
	grid.setParameter("sendFailed",sendFailed);
	grid.setParameter("receiverName", receiverName);
	grid.setParameter("sendState",sendState);
	grid.setParameter("messageType", messageType);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	grid.setParameter("sendTimeFrom",sendTimeFrom);
	grid.setParameter("sendTimeTo",sendTimeTo);
	grid.setParameter("groupfield",groupfield);
	grid.load();
}

/**
 * 编辑
 */
function edit(){
	var records = grid.getSelectedRow();
	if (records.length == 1) {
		var data = records[0];
		var url = "command/mc/core/message/showMessage";
		if (data.messageId != undefined && data.messageId != "" && data.messageId != "null") {
			url += "?messageId=" + data.messageId;
		}
		url += "&operType=edit&boxType="+boxType;
		G3.forward(url);
	} else {
		G3.alert("提示", "请选择一条消息！");
	}
}

//渲染查看链接
function messageShowLink(data, type, full){
	var messageId = full.messageId;
	var url = G3.cmdPath + "mc/core/message/showMessage?messageId="+messageId+"&operType=view&boxType="+boxType;
	return '<a href='+url+'>'+data+'</a>';
}

/**
 * 渲染envelope状态
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function renderstatus(data, type, full) {
	if (data != "" || data != null) {
		if (data == "0") {
			data = "未发送";
		} else if(data == "5"){
			data = "发送失败";
		} else {
			data = "";
		}
	}
	return data;
}
