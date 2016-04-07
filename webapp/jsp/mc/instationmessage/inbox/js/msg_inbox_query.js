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
	$("#addBtn").bind("click", function () {
		newMessage();
	});
	
	// 删除
	$("#delBtn").click(
		function() {
			del();
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
	//设置过滤条件：收件
	var isInmsg = "y";
	//设置过滤条件：消息
	var messageType = "m";
	//设置数据请求地址
	grid.setAjaxUrl(url);
	grid.setParameter("isInmsg", isInmsg);
	grid.setParameter("messageType", messageType);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	//初始化
	grid.init();
}

/**
 * 新建消息(跳转)
 */
function newMessage(){
	//var url = G3.cmdPath + "mc/core/instationmessage/newMessage";
	G3.forward("command/mc/core/instationmessage/newMessage");
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
	var isInmsg = "y";
	var messageType = "m";
	var messageTopic = $("#messageTopic").val();
	var receiveState = $("#receiveState").val();
	var sendTimeFrom = $("#sendTimeFrom").val();
	var sendTimeTo = $("#sendTimeTo").val();
	if (messageTopic == undefined) {
		messageTopic = "";
	}
	
	var url = "mc/core/instationmessage/query";
	grid.setAjaxUrl(url);
	grid.setParameter("messageTopic", messageTopic);
	grid.setParameter("isInmsg", isInmsg);
	grid.setParameter("messageType", messageType);
	grid.setParameter("receiveState", receiveState);
	grid.setParameter("senderName", loginName);
	grid.setParameter("senderId", loginId);
	grid.setParameter("sendTimeFrom", sendTimeFrom);
	grid.setParameter("sendTimeTo", sendTimeTo);
	grid.load();
}

function del(){
	var records = grid.getSelectedRow();
	if (records.length != 0) {
		
		var recordIds = [];
		//循环遍历获取ID 
		$.each(records, function(index, item){
			recordIds.push(item.id);
		});
		//草稿箱类型
		var boxType = "inbox";
		
		//删除警告框
		G3.confirm("提示", "确认删除记录？",
			function() {
				var requestUrl = G3.cmdPath+"mc/core/instationmessage/delete/"+recordIds+"/type/"+boxType;
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