$(function() {
	var context = G3.cmdPath;
		// 校验表单数据
	formCheck($("#form"), function() {
		//form提交方式
		//		var url = context + "mc/core/subscribe/save";
		//		form.action = url;
		//		form.method = "POST";
		//		form.submit();
		//	});
		var requestUrl = context + "mc/core/subscribe/ajaxsave";
		//表单的异步提交
		$("form").ajaxSubmit({
			type:"post",
			dataType:"json",
			url:requestUrl,
			error:function(data){
				G3.alert("提示","保存错误");
			},
			success:function(data){
				G3.alert("提示","保存成功",function(){
				G3.closeModalDialog("1");
				},"success");
			}
		});
		return false;
	});
	$("#returnBtn").click(function() {
		G3.forward(context + "mc/core/subscribe");
	});
});