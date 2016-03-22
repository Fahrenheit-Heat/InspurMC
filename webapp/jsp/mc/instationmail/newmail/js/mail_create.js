// 初始化ueditor编辑器实例
//var editor = UE.getEditor('ueditor',{initialFrameHeight:400 });

var context = G3.cmdPath;
// 校验表单数据
formCheck($("#form"), function() {
	var requestUrl = context + "mc/core/ajaxsave";
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
	
	return false;
});


/**
 * 后台获取数据方法
 */
function queryParty(){
}

$(function(){
	//绑定时间选择
	$("#sendTime").datetimepicker({
		minView : "month", //选择日期后，不会再跳转去选择时分秒 　　
		format : "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 　　
		language : 'zh-CN', //汉化 　
		autoclose : true //选择日期后自动关闭
	});
});
//var content = ue.getContent();


/*function save(){
	var content = editor.getContent();
	var receiveName = $("#receivername").val();
	var receiverNames = $("#receivernames").val();
	var messageTopic = $("#messageTopic").val();
	var sendTime = $("#sendTime").val();
}*/








