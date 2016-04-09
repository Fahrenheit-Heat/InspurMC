//上下文根
var context = G3.webPath;

$(function(){
	// 表格初始化查询
	initGrid();
	
	// 修改
	$("#editBtn").bind("click", function(){
		edit();
	});
	
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
	//设置过滤条件：已发送
	var isSended = "1";
	//设置信封过滤：多条只显示一条
	var groupfield = "y";
	//设置过滤条件：消息
	var messageType = "m";
	//设置数据请求地址
	grid.setAjaxUrl(url);
	grid.setParameter("messageType", messageType);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	grid.setParameter("groupfield", groupfield);
	grid.setParameter("isSended", isSended);
	//初始化
	grid.init();
}

/**
 * 编辑
 */
function edit(){
	var records = grid.getSelectedRow();
	if (records.length == 1) {
		var data = records[0];
		var url = "command/mc/core/instationmessage/edit";
		if (data.message.id != undefined && data.message.id != "" && data.message.id != "null" ) {
			url += "?messageId=" + data.message.id;
		}
		G3.forward(url);
	} else {
		G3.alert("提示","请选择一条消息！");
	}
}

/**
*条件查询
*
*/
function query(){
	
	var url = "mc/core/instationmessage/query";
	
	//设置过滤条件：已发送
	var isSended = "1";
	//设置过滤条件：消息
	var messageType = "m";
	
	var groupfield = "y";
	
	var messageTopic = $("#messageTopic").val();
	var receiverName = $("#receiverName").val();
	var sendTimeFrom = $("#sendTimeFrom").val();
	var sendTimeTo = $("#sendTimeTo").val();
	
	grid.setAjaxUrl(url);
	grid.setParameter("isSended", isSended);
	grid.setParameter("messageType", messageType);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	grid.setParameter("messageTopic", messageTopic);
	grid.setParameter("receiverName", receiverName);
	grid.setParameter("sendTimeFrom", sendTimeFrom);
	grid.setParameter("sendTimeTo", sendTimeTo);
	grid.setParameter("groupfield",groupfield);
	grid.load();
}

/**
 * 新建消息(跳转)
 */
function newMessage(){
	//var url = G3.cmdPath + "mc/core/instationmessage/newMessage";
	G3.forward("command/mc/core/instationmessage/newMessage");
}