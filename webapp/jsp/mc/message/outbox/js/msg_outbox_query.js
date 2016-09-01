//上下文根
var context = G3.webPath;

$(function(){
	// 表格初始化查询
	initGrid();
	
	// 修改
	$("#editBtn").click(function(){
		edit();
	});
	
	// 删除
	$("#delBtn").click(function() {
		del();
	});
	
	// 条件查询
	$("#queryBtn").click(query);
	
	// 增加按钮事件
	$("#addBtn").click(function(){
		newMessage();
	});
	
	//多条件查询
	$("body").on("click","#moreQueryBtn",query);
	
	//更多查询
	$("#moresearch").morequery({
		"title" : "",
		"content" : template('mypopover', {})
	});
	
	//重置
	$("body").on("click", "#resetBtn", function(){
		$("#messageContent").val("");
		$("#receiverName").val("");
		$("#sendTimeFrom").val("");
		$("#sendTimeTo").val("");
		$("#sendState").val("");
		
	});
	
	//绑定回车事件
	$("#receiverName").bind('keypress', function(event) {
		if (event.keyCode == "13") {
			query();
		}
		event.stopPropagation();
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
	var url = "mc/core/message/MessageList";
	grid = new G3.Grid("messageList");
	//设置过滤条件：已发送、发送中、发送失败
	var isSended = "1";
	var isSending = "4";
	var sendFailed = "5";
	//设置信封过滤：群发只显示一条
	var groupfield = "y";
	//设置数据请求地址
	grid.setAjaxUrl(url);
	grid.setParameter("messageType", messageType);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	grid.setParameter("groupfield", groupfield);
	grid.setParameter("isSended", isSended);
	grid.setParameter("isSending",isSending);
	grid.setParameter("sendFailed",sendFailed);
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
		var url = "command/mc/core/message/showMessage";
		if (data.messageId != undefined && data.messageId != "" && data.messageId != "null" ) {
			url += "?messageId=" + data.messageId;
		}
		url += "&operType=edit&boxType="+boxType;
		G3.forward(url);
	} else {
		G3.alert("提示","请选择一条消息！");
	}
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
*条件查询
*
*/
function query(){
	
	var url = "mc/core/message/query";
	
	//设置过滤条件：已发送、发送中
	var isSended = "1";
	var isSending = "4";
	var sendFailed = "5";
	
	var groupfield = "y";
	
	var messageContent = $("#messageContent").val();
	var receiverName = $("#receiverName").val();
	var sendTimeFrom = $("#sendTimeFrom").val();
	var sendTimeTo = $("#sendTimeTo").val();
	var sendState = $("#sendState").val();
	
	grid.setAjaxUrl(url);
	grid.setParameter("isSended", isSended);
	grid.setParameter("isSending",isSending);
	grid.setParameter("sendFailed",sendFailed);
	grid.setParameter("sendState",sendState);
	grid.setParameter("messageType", messageType);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	grid.setParameter("messageContent", messageContent);
	grid.setParameter("receiverName", receiverName);
	grid.setParameter("sendTimeFrom", sendTimeFrom);
	grid.setParameter("sendTimeTo", sendTimeTo);
	grid.setParameter("groupfield",groupfield);
	grid.load();
}

/**
 * 新建短信
 */
function newMessage(){
	var url = G3.cmdPath + "mc/core/message/newMessage?boxType="+boxType;
	window.location = url;
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
		if (data == "1") {
			data = "已发送";
		} else if (data == "4"){
			data = "发送中";
		} else if(data == "5"){
			data = "发送失败";
		} else {
			data = "";
		}
	}
	return data;
}