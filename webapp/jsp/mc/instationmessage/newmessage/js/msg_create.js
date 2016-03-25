var context = G3.cmdPath;

// 页面初始化
$(function() {
	
	// 初始化UE编辑器
	var ue = initUESettings("editor", "");
	
	// 通讯录
	$("#bookBtn").click(function(){
		
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
});

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
		G3.confirm("提示", "确定真的不填写主题吗？", function(){
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
 * 保存方法
 * @param ue
 */
function save(ue){
	var requestUrl = context + "/mc/core/instationmessage/send";
	$("#messageContent").val(ue.getContent());
	$("#sendType").val("0");
	$("#receiverId").val('1234');
	$("#sendId").val("senderId");
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
	$("#sendType").val("1");
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
			},"success");
		}
	});
}

/**
 * 通讯录方法
 */
function book(){
	
}
