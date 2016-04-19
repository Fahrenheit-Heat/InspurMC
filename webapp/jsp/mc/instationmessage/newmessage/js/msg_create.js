var context = G3.cmdPath;

// 页面初始化
$(function() {
	
	var messageId = $("#messageId").val();
	var operType = $("#operType").val();
	var boxType = $("#boxType").val();
	
	$("#sendType").val("0");// 设置发送类型为原邮件
	$("#sendState").val("1");// 设置发送状态为已发送
	$("#receiveState").val("0");// 设置接受状态为未读
	$("#receiveType").val("0");// 设置消息类型为一对一
	
	var requestUrl;
	
	if(messageId != null && messageId != undefined && messageId != ""){
		// 编辑
		// 构建跳转地址
		requestUrl = G3.cmdPath+"mc/core/instationmessage/";
		if(operType == "forward"){
			requestUrl += "forwardMessage"
		}else if(operType == "reply"){
			requestUrl += "replyMessage"
		}else if(operType == "edit"){
			requestUrl += "editMessage"
		}else{
			requestUrl += ""
		}
		requestUrl += "/" + messageId + "/"+ loginId + "/" + boxType;
		
		// 初始化UE编辑器
		var ue = initUESettings("editor", "edit");
		// ajax请求表单数据
		$.ajax({
			type : "post",
			async : false,
			dataType : "json",
			url: requestUrl,
			error:function(data){
				G3.alert("错误","数据加载失败！");
			},
			success:function(data){
				// 绑定页面数据元素
				initData(ue, data);
			}
		});
	}else{
		// 新建
		// 初始化UE编辑器
		var ue = initUESettings("editor", "edit");
	}
	
	$("#senderName").val(loginName);
	$("#senderId").val(loginId);
	
	// 通讯录
	$("#bookBtn").click(function(){
		addReceiver();
		return false;
	});
	
	// 保存
	$("#saveBtn").click(function(){
		saveCheck(ue)
		return false;
	});
	
	// 发送
	$("#sendBtn").click(function(){
		sendCheck(ue)
		return false;
	});
	
	// 返回
	$("#backBtn").click(function(){
		goBack($("#boxType").val());
		return false;
	});
});

/**
 * 初始化页面数据方法
 * @param ue
 * @param data
 */
function initData(ue, data){
	$("#senderId").val(data.senderId);// 设置发送人ID
	$("#senderName").val(data.senderName);// 设置发送人名
	$("#receiverId").val(data.receiverId);// 设置接收人ID
	$("#receiverName").val(data.receiverName);// 设置接收人名
	$("#messageTopic").val(data.messageTopic);// 设置消息主题
	$("#messageId").val(data.messageId);// 设置消息ID
	$("#sendType").val(data.sendType);// 设置发送类型为原邮件
	$("#receiveType").val(data.receiveType);// 设置消息类型为一对一
	$("#relatedMessageId").val(data.relatedMessageId)// 设置回复消息ID

	// 初始化UE编辑器数据
	ue.ready(function(){
		ue.setContent(data.messageContent);
	});
}

/**
 * 保存检测方法
 * @param ue
 */
function saveCheck(ue){
	if(G3.isEmpty($("#receiverName").val())){
		G3.confirm("提示", "未选择接收人，是否继续保存？", function(){
			save(ue);
		});
	}else if(G3.isEmpty($("#messageTopic").val())){
		G3.confirm("提示", "确定不填写主题吗？", function(){
			save(ue);
		});
	}else{
		save(ue);
	}
}

/**
 * 发送检测方法
 * @param ue
 */
function sendCheck(ue){
	if(G3.isEmpty($("#receiverName").val())){
		G3.alert("提示", "请选择发送人！");
	}else if(G3.isEmpty($("#messageTopic").val())){
		G3.confirm("提示", "确定真的不填写主题吗？", function(){
			send(ue);
		});
	}else{
		send(ue);
	}
}

/**
 * 通讯录通用帮助
 */
function addReceiver(){
	var url = G3.frontStatic.bspUrl + "/jsp/public/help/help.jsp?helpCode=bsp_contact";
	G3.showModalDialog("站内通讯录", url, {width:900, height:500}, function(e,ret){
		// 设置联系人
		var returnValue = ret.split(";");
		var organId;
		var organName;
		if(returnValue.length > 0){
			organId = returnValue[0];
			organName = returnValue[1]; 
		}
		$("#receiverId").val(organId);
		$("#receiverName").val(organName);
	});

}

/**
 * 保存方法
 * @param ue
 */
function save(ue){
	var boxType = $("#boxType").val();
	var requestUrl = context + "mc/core/instationmessage/ajaxsave/" + boxType;
	$("#messageContent").val(ue.getContent());
	$("#sendType").val("0");
	$("#sendState").val("0");
	$("#receiveState").val("");
	//表单的异步提交
	$("#form").ajaxSubmit({
		type : "post",
		dataType : "json",
		url: requestUrl,
		error:function(data){
			G3.alert("提示","保存失败");
		},
		success:function(data){
			//弹框方式
			G3.alert("提示","保存成功",function(){
				G3.closeModalDialog("1");
				goBack($("#boxType").val());
			},"success");
		}
	});
}

/**
 * 发送方法
 * @param ue
 */
function send(ue){
	$("#messageContent").val(ue.getContent());
	var requestUrl = context + "/mc/core/instationmessage/send";
	//表单的异步提交
	$("#form").ajaxSubmit({
		type : "post",
		dataType : "json",
		url: requestUrl,
		error:function(data){
			G3.alert("提示","发送失败");
		},
		success:function(data){
			//弹框方式
			G3.alert("提示","发送成功",function(){
				G3.closeModalDialog("1");
				goBack($("#boxType").val());
			},"success");
		}
	});
}

/**
 * 返回方法
 */
function goBack(boxType){
	var url = G3.cmdPath + "mc/core/instationmessage/forward/"+boxType;
	window.location = url;
}
