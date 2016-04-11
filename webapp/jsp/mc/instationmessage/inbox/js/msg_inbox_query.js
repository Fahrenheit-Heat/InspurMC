//上下文根
var context = G3.webPath;

function selectTime(obj){
	$(obj).datetimepicker({format: 'yyyy-mm-dd',minView: "month"});
}

/**
 * 页面初始化
 */
$(function() {
	
	// 表格初始化查询
	initGrid();

	// 增加按钮事件
	$("#addBtn").click("click", function () {
		newMessage();
	});
	
	//回复
	$("#replyBtn").click(function (){
		replayMessage();
	});
	
	//转发
	$("#forwardBtn").click(function (){
		forwardMessage();
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
		$("#receiveState").val("");
		$("#senderName").val("");
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

/**
 * 表格初始化查询
 */
function initGrid(){
	//初始化表格
	var url = "mc/core/instationmessage/instationMsgList";
	grid = new G3.Grid("instationMsgList");
	//设置过滤条件：未读和已读
	var isNotRead = "0";
	var isRead = "1";
	//设置过滤条件：消息
	var messageType = "m";
	//设置数据请求地址
	grid.setAjaxUrl(url);
	grid.setParameter("messageType", messageType);
	grid.setParameter("receiverId", loginId);
	grid.setParameter("isNotRead", isNotRead);
	grid.setParameter("isRead", isRead);
	//初始化
	grid.init();
}

/**
 * 新建消息(跳转)
 */
function newMessage(){
	var url = G3.cmdPath + "mc/core/instationmessage/newMessage";
	window.location = url;
}

/**
 * 回复消息
 */
function replayMessage(){
	var records = grid.getSelectedRow();
	if (records.length == 1) {
		var data = records[0];
		var url = G3.cmdPath + "/mc/core/instationmessage/replayMessage";
		if (data.message.id != undefined && data.message.id != "" && data.message.id != "null") {
			url += "?messageId=" + data.message.id;
		}
		G3.showModalDialog("回复消息", url, {
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
}

/**
 * 转发消息
 */
function forwardMessage(){
	var records = grid.getSelectedRow();
	if (records.length == 1) {
		var data = records[0];
		var url = G3.cmdPath + "/mc/core/instationmessage/forwardMessage";
		if (data.message.id != undefined && data.message.id != "" && data.message.id != "null") {
			url += "?messageId=" + data.message.id;
		}
		
		G3.showModalDialog("转发消息", url, {
			width : 800,
			height : 500
		}, function(e, ret) {
			if (ret == "1") {
				grid.reload();
			}
		});
	} else {
		G3.alert("提示", "请选择要转发的邮件！");
	}
}

/**
* 渲染主题连接方法
*/
function messageShowLink(data, type, full){
	var messageId = full.message.id;
	var url = G3.cmdPath + "mc/core/instationmessage/showMessage?messageId="+messageId+"&type=view";
	return '<a href='+url+'>'+data+'</a>';
}

/**
 * 查询数据
 */
function query() {
	//设置过滤条件：未读和已读
	var isNotRead = "0";
	var isRead = "1";
	//设置过滤条件：消息
	var messageType = "m";
	var messageTopic = $("#messageTopic").val();
	var receiveState = $("#receiveState").val();
	var senderName = $("#senderName").val();
	var sendTimeFrom = $("#sendTimeFrom").val();
	var sendTimeTo = $("#sendTimeTo").val();
	if (messageTopic == undefined) {
		messageTopic = "";
	}
	
	var url = "mc/core/instationmessage/query";
	grid.setAjaxUrl(url);
	grid.setParameter("messageTopic", messageTopic);
	grid.setParameter("isNotRead", isNotRead);
	grid.setParameter("isRead", isRead);
	grid.setParameter("messageType", messageType);
	grid.setParameter("receiveState", receiveState);
	grid.setParameter("senderName", senderName);
	grid.setParameter("sendTimeFrom", sendTimeFrom);
	grid.setParameter("sendTimeTo", sendTimeTo);
	grid.load();
}

/**
 * 渲染envelope状态
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function RenderState(data, type, full) {
	if (data != "" || data != null) {
		if (data == "0") {
			data = "未读";
		} else if(data == "1") {
			data = "已读";
		} else {
			data = "";
		}
	}
	return data;
}