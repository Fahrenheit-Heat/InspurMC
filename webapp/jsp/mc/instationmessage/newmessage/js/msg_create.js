// 初始化UE编辑器
initUESettings("editor", "");

// 页面初始化
$(function() {
	var context = G3.cmdPath;
	// 校验表单数据
	formCheck($("#form"), function() {
		var requestUrl = context + "demo/user/ajaxsave";
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
		return false;
	});
});
