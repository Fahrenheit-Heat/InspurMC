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
	
	// 表格初始化查询
	initGrid();

	// 增加按钮事件
	$("#addBtn").bind("click", function () {
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

});

/**
 * 表格初始化查询
 */
function initGrid(){
	//初始化表格
	var url = "mc/core/instationmessage/instationMsgList";
	grid = new G3.Grid("instationMsgList");
	//设置数据请求地址
	grid.setAjaxUrl(url);
	//初始化
	grid.init();
}

/**
 * 新建消息
 */
function newMessage(){
	var url = G3.cmdPath + "/mc/core/instationmessage/newMessage";
	G3.showModalDialog("新建消息", url, {
		width : 800,
		height : 500
	}, function(e, ret) {
		if (ret == "1") {
			grid.reload();
		}
	});
}

/**
 * 回复消息
 */
function replayMessage(){
	var records = grid.getSelectedRow();
	if (records.length == 1) {
		var data = records[0];
		var url = G3.cmdPath + "/mc/core/instationmessage/replayMessage";
		if (data.id != undefined && data.id != "" && data.id != "null") {
			url += "?id=" + data.id;
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
		if (data.id != undefined && data.id != "" && data.id != "null") {
			url += "?id=" + data.id;
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
		G3.alert("提示", "请选择要回复的邮件！");
	}
}

/**
 * 查询数据
 */
function query() {
	var userId = $("#userId").val();
	var userName = $("#userName").val();
	var nickname = $("#nickname").val();
	if (userId == undefined) {
		userId = "";
	}
	
	var url = "/mc/core/instationmessage/";
	grid.setAjaxUrl(url);
	grid.setParameter("userId", userId);
	grid.setParameter("userName", userName);
	grid.setParameter("nickname", nickname);
	grid.load();
}