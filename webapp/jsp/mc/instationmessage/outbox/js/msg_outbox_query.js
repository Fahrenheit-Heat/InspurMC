//上下文根
var context = G3.webPath;

$(function(){
	// 表格初始化查询
	initGrid();
	
	// 条件查询
	$("#queryBtn").click(query);
	
	// 增加按钮事件
	$("#addBtn").bind("click",function(){
		newMessage();
	});
	
	//多条件查询
	$("body").on("click","#moreQueryBtn",query);
	
	//更多查询
	$("#moresearch").morequery({
		"title" : "",
		"content" : template('mypopover', {})
	});
});

function selectTime(obj){
	$(obj).datetimepicker({format: 'yyyy-mm-dd',minView: "month"});
}

/**
 * 表格初始化查询
 */
function initGrid(){
	//初始化表格
	var url = "mc/core/instationmessage/instationMsgList";
	grid = new G3.Grid("instationMsgList");
	//设置过滤条件：收件
	var isOutmsg = "y";
	//设置过滤条件：消息
	var messageType = "m";
	//设置数据请求地址
	grid.setAjaxUrl(url);
	grid.setParameter("isOutmsg", isOutmsg);
	grid.setParameter("messageType", messageType);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	//初始化
	grid.init();
}

/**
*条件查询
*
*/
function query(){
	
	var url = "mc/core/instationmessage/query";
	
	//设置过滤条件：收件
	var isOutmsg = "y";
	//设置过滤条件：消息
	var messageType = "m";
	
	var messageTopic = $("#messageTopic").val();
	var receiverName = $("#receiverName").val();
	var sendTimeFrom = $("#sendTimeFrom").val();
	var sendTimeTo = $("#sendTimeTo").val();
	
	grid.setAjaxUrl(url);
	grid.setParameter("isOutmsg", isOutmsg);
	grid.setParameter("messageType", messageType);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	grid.setParameter("messageTopic", messageTopic);
	grid.setParameter("receiverName", receiverName);
	grid.setParameter("sendTimeFrom", sendTimeFrom);
	grid.setParameter("sendTimeTo", sendTimeTo);
	grid.load();
}

/**
 * 新建消息(跳转)
 */
function newMessage(){
	//var url = G3.cmdPath + "mc/core/instationmessage/newMessage";
	G3.forward("command/mc/core/instationmessage/newMessage");
}