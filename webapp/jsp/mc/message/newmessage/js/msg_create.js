var context = G3.cmdPath;

// 页面初始化
$(function() {
	
	var messageId = $("#messageId").val();
	var operType = $("#operType").val();
	var boxType = $("#boxType").val();
	
	$("#sendType").val("0");// 设置发送类型为原邮件
	$("#sendState").val("4");// 设置发送状态为发送中
	$("#receiveState").val("0");// 设置接受状态为未读
	$("#receiveType").val("0");// 设置消息类型为一对一
	
	var requestUrl;
	
	if(messageId != null && messageId != undefined && messageId != ""){
		// 编辑
		// 构建跳转地址
		requestUrl = G3.cmdPath+"mc/core/message/";
		if(operType == "edit"){
			requestUrl += "editMessage"
		}else{
			requestUrl += ""
		}
		requestUrl += "/" + messageId + "/"+ loginId + "/" + boxType;
		

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
				initData(data);
			}
		});
	}else{
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
		saveCheck();
		return false;
	});
	
	// 发送
	$("#sendBtn").click(function(){
		sendCheck();
		return false;
	});
	
	// 返回
	$("#backBtn").click(function(){
		goBack($("#boxType").val());
		return false;
	});
});

/**
 * 弹出时间选择框
 * @param obj
 */
function selectTime(obj){
	var date = new Date();
	$(obj).datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		startDate: date,
		language : 'zh-CN',
		showMeridian :  true
		});
}

/**
 * 初始化页面数据方法
 * @param ue
 * @param data
 */
function initData(data){
	if(data.envelopeId != "" || data.envelopeId != null){
		$("#envelopeId").val(data.envelopeId);//envelopeId不为空，则初始化其值
	}
	$("#senderId").val(data.senderId);// 设置发送人ID
	$("#senderName").val(data.senderName);// 设置发送人名
	$("#receiverId").val(data.receiverId);// 设置接收人ID
	$("#receiverName").val(data.receiverName);// 设置接收人手机号
	$("#messageContent").val(data.messageContent);// 设置消息主题
	$("#messageId").val(data.messageId);// 设置消息ID
	$("#sendType").val(data.sendType);// 设置发送类型为原邮件
	$("#receiveType").val(data.receiveType);// 设置消息类型为一对一
}

/**
 * 保存检测方法
 * @param ue
 */
function saveCheck(){
	if(G3.isEmpty($("#receiverName").val())){
		G3.confirm("提示", "未选择接收人，是否继续保存？", function(){
			save();
		});
	} else if (G3.isEmpty($("#messageContent").val())){
		G3.confirm("提示", "确定不填写短信内容吗？", function(){
			save();
		});
	} else {
		save();
	}
}

/**
 * 发送检测方法
 * @param ue
 */
function sendCheck(){
	var isSetTime = false;//默认无定时发送
	var isPhoneEmpty = phoneEmptyCheck();//判断手机号是否为空

	//定时发送前检测
	if(!G3.isEmpty($("#messageSendTime").val())){
		//选定发送时间和当前时间进行比较
		var setDate = new Date(makeUpTime($("#messageSendTime").val()));
		var date = new Date();
		test =  setDate <= date ? true : false;
	}
	if(G3.isEmpty($("#receiverName").val())){
		G3.alert("提示", "请选择发送人！");
	} else if (G3.isEmpty($("#messageContent").val())){
		G3.alert("提示", "短信内容为空！");
	} else if(isSetTime) {
		//当前时间已过设定发送时间
		G3.alert("提示", "设定发送时间已过，请重新设定");
	} else if(!isPhoneEmpty){
		G3.alert("提示", "联系人中存在手机号缺失用户，请先维护通讯录！");
	} else {
		send();
	}
}

/**
 * String时间规范方法
 * @param dateAndTime
 * @returns {String}
 */
function makeUpTime(dateAndTime){
	var dateTime = dateAndTime.split(" ");
	var date= dateTime[0].split("-");
	var time = dateTime[1].split(":");
	return dateString = date[0] + "/" + date[1] + "/" + date[2] + " " + time[0] + ":" + time[1];
}

/**
 * 判断用户手机号是否存在空值
 * @returns {Boolean}
 */
function phoneEmptyCheck(){
	var phoneJudgement = true;//默认手机号不为空
	var loginNames = $("#receiverId").val().split(",");
	for(var i = 0; i < loginNames.length; i++){
		if(loginNames[i] == "" || loginNames[i] == undefined || loginNames[i] == "undefined"){
			phoneJudgement = false;
			break;
		}
	}
	return phoneJudgement;
}

/**
 * 通讯录通用帮助
 */
function addReceiver(){
	var url = G3.frontStatic.bspUrl + "/jsp/public/help/help.jsp?helpCode=bsp_contact";
	G3.showModalDialog("站内通讯录", url, {width:900, height:500}, function(e,ret){
		// 设置联系人
		var returnValue = ret.split(";");
		var organPhone;
		var organName;
		if(returnValue.length > 0){
			//organId = returnValue[0];
			organName = returnValue[1]; 
			organPhone = returnValue[3];
		}
		//$("#receiverId").val(organId);
		//receiverId为对方手机号
		$("#receiverId").val(organPhone);
		$("#receiverName").val(organName);
	});

}

/**
 * 保存方法
 * @param ue
 */
function save(){
	var requestUrl = context + "mc/core/message/ajaxsave/" + $("#boxType").val();
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
function send(){
	var requestUrl = context + "mc/core/message/send";
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
	var url = G3.cmdPath + "mc/core/message/forward/"+boxType;
	window.location = url;
}
