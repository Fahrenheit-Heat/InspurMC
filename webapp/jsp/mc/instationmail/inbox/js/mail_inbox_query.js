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
	initGrid();

	// 增加
	$("#addBtn").bind("click", function () {
		
	});
	
	//回复
	$("#replyBtn").click(function (){
		
	});
	
	//回复
	$("#replyAllBtn").click(function (){
		
	});
	
	
	//转发
	$("#forwardBtn").click(function (){
		
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
 * 列表初始化查询
 */
function initGird(){
	//初始化表格
	var url = "mc/core/instationmail/instationMailList";
	grid = new G3.Grid("instationMailList");
	//设置过滤条件：未读和已读
	var isNotRead = "0";
	var isRead = "1";
	//设置过滤条件：消息
	var messageType = "w";
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
 * 查询数据
 */
function query() {
	var userId = $("#userId").val();
	var userName = $("#userName").val();
	var nickname = $("#nickname").val();
	if (userId == undefined) {
		userId = "";
	}
	
	var url = "demo/user/query";
	grid.setAjaxUrl(url);
	grid.setParameter("userId", userId);
	grid.setParameter("userName", userName);
	grid.setParameter("nickname", nickname);
	grid.load();
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
