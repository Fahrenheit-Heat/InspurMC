var context = G3.cmdPath;

// 页面初始化
$(function() {
	
	var messageId = $("#messageId").val();
	var operType = $("#operType").val();
	var boxType = $("#boxType").val();
	
	// 用户权限检查
	checkAuthorization(operType);
	
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
	
	// 初始化页面赋值
	$("#senderName").val(loginName);
	
	$(":radio").click(function(){
		radioClick($(this).val());
	});
	
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
 * 权限检查
 */
function checkAuthorization(operType){
	if(isSuper && operType != "reply"){
		$("#receiverTypeDiv").show();
	}else{
		$("#receiverTypeDiv").hide();
	}
}

/**
 * 初始化页面数据方法
 * @param ue
 * @param data
 */
function initData(ue, data){
	if(data.envelopeId != "" || data.envelopeId != null){
		$("#envelopeId").val(data.envelopeId);//envelopeId不为空，则初始化其值
	}
	$("#senderId").val(data.senderId);// 设置发送人ID
	$("#senderName").val(data.senderName);// 设置发送人名
	$("#receiverId").val(data.receiverId);// 设置接收人ID
	$("#receiverName").val(data.receiverName);// 设置接收人名
	$("#messageTopic").val(data.messageTopic);// 设置消息主题
	$("#messageId").val(data.messageId);// 设置消息ID
	$("#sendType").val(data.sendType);// 设置发送类型为原邮件
	$("#relatedMessageId").val(data.relatedMessageId)// 设置回复消息ID
	
	if(data.receiverType == '1'){
		$("#generalMessage").attr("checked", "checked");
	}else if(data.receiverType == '3'){
		$("#systemMessage").attr("checked", "checked");
	}

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
 * 点击单选框触发方法
 */
function radioClick(val){
	if(val == "1"){
		$("#bookBtn").removeAttr("disabled");
		$("#receiverName").val("");
	}else if(val == "3"){
		$("#bookBtn").attr("disabled","disabled");
		$("#receiverName").val("全体人员");
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
	var requestUrl = context + "mc/core/instationmessage/ajaxsave/" + $("#boxType").val();
	//表单的异步提交
	$("#form").ajaxSubmit({
		type : "post",
		dataType : "json",
		url : requestUrl,
		beforeSubmit : function(data){
			handleFormDate(data, ue, "save");
		},
		error:function(data){
			G3.alert("提示","保存失败");
		},
		success:function(data){
			
			//弹框方式
			G3.alert("提示","保存成功,请在草稿箱查看",function(){
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
		beforeSubmit : function(data){
			handleFormDate(data, ue, "send");
		},
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
 * 处理表单数据方法
 * @param data 表单数据对象
 * @param ue 富文本编辑器对象
 * @param action 动作集
 */
function handleFormDate(data, ue, action){
	$.each(data, function(index, element){
		
		if(element["name"] == "messageContent"){
			// 设置富消息内容messageContent
			element["value"] = ue.getContent();
		}else if(element["name"] == "sendType"){
			// 设置发送类型sendType
			element["value"] = "0";
		}else if(element["name"] == "sendState"){
			// 设置发送状态sendState
			if(action == "send"){
				element["value"] = "1";
			}else if(action == "save"){
				element["value"] = "0";
			}
		}else if(element["name"] == "senderId"){
			// 设置发送人OrganId
			element["value"] = loginId;
		}else if(element["name"] == "receiveState"){
			// 设置接收状态为未读
			element["value"] = "0";
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
