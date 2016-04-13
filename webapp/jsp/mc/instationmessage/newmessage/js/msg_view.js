/**
 * 页面初始化
 */
$(function(){
	// 构建跳转地址
	var requestUrl = G3.cmdPath+"mc/core/instationmessage/viewMessage/"
		+$("#messageId").val()
		+"/"
		+loginId
		+"/"
		+boxType;
	// 初始化UE编辑器
	var ue = initUESettings("editor", "show");
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
	
	// 绑定返回按钮触发事件
	$("#backBtn").click(function(){
		goBack($("#boxType").val());
		return false;
	});
	
});

/**
 * 初始化页面元素展示方法
 * @param ue 编辑器对象
 * @param data 数据
 */
function initData(ue, data){
	$("#senderName").val(data.senderName);
	$("#receiverName").val(data.receiverName);
	$("#messageTopic").val(data.messageTopic);
	$("#messageId").val(data.messageId);
	// 初始化UE编辑器数据
	ue.ready(function(){
		ue.setContent(data.messageContent);
	});
}

/**
 * 返回方法
 */
function goBack(boxType){
	var url = G3.cmdPath + "mc/core/instationmessage/forward/"+boxType;
	window.location = url;
}

