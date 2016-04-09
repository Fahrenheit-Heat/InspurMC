var context = G3.cmdPath;

$(function() {
	$("#senderName").val(loginName);
	$("#senderId").val(loginId);
	//设置编辑器内容
	$("#messageShow").text($("#messageContent").val())
});

/**
 * 编辑
 */
function edit(){
	var records = grid.getSelectedRow();
	if (records.length == 1) {
		var data = records[0];
		var url = G3.cmdPath + "mc/core/instationmessage/editdraft";
		if (data.id != undefined && data.id != "" && data.id != "null") {
			url += "?id=" + data.id;
		}
		G3.showModalDialog("修改", url, {
			width : 800,
			height : 500
		}, function(e, ret) {
			if (ret == "1") {
				grid.reload();
			}
		});
	} else {
		G3.alert("提示", "请选择一条消息！");
	}
}