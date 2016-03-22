$(function() {
	var context = G3.cmdPath;
	// 校验表单数据
	formCheck($("#form"), function() {
		//form提交方式
		var url = context + "mc/subscribe/save";
		form.action = url;
		form.method = "POST";
		form.submit();
	});
	
	$("#returnBtn").click(function() {
		G3.forward(context + "mc/subscribe");
	});
});