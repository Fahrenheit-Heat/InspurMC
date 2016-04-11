//上下文根
var context = G3.webPath;
$(function(){
	
	// 表格初始化查询
	initGrid();
	
	//
	$("#queryBtn").click(query);
	
	//
	$("body").on("click","#moreQueryBtn",query);
	
	$("#delBtn").click(del);
	
	//更多查询
	$("#moresearch").morequery({
		"title" : "",
		"content" : template('mypopover',{})
	});
});

/**
 * 表格初始化查询
 */
function initGrid(){
	//初始化表格
	var url = "mc/core/instationmessage/instationMsgList";
	grid = new G3.Grid("instationMsgList");
	//设置过滤条件：废件箱
	var isScrap = "2";
	//设置信封过滤：多条只显示一条
	var groupfield = "y";
	//设置过滤条件：消息
	var messageType = "m";
	//设置数据请求地址
	grid.setAjaxUrl(url);
	grid.setParameter("messageType", messageType);
	grid.setParameter("groupfield", groupfield);
	grid.setParameter("isScrap", isScrap);
	//初始化
	grid.init();
}

function query(){
	
	var url = "mc/core/instationmessage/query";
	
	//设置过滤条件：草稿箱
	var isScrap = "2";
	//设置过滤条件：消息
	var messageType = "m";
	
	var groupfield = "y";
	
	var messageTopic = $("#messageTopic").val();
	var receiverName = $("#receiverName").val();
	var senderName = $("#senderName").val();
	var sendTimeFrom = $("#sendTimeFrom").val();
	var sendTimeTo = $("#sendTimeTo").val();
	
	grid.setAjaxUrl(url);
	grid.setParameter("isScrap", isScrap);
	grid.setParameter("messageType", messageType);
	grid.setParameter("messageTopic", messageTopic);
	grid.setParameter("senderName",senderName);
	grid.setParameter("receiverName", receiverName);
	grid.setParameter("sendTimeFrom", sendTimeFrom);
	grid.setParameter("sendTimeTo", sendTimeTo);
	grid.setParameter("groupfield",groupfield);
	grid.load();
}

//
function del(){
	var records = grid.getSelectedRow();
	if (records.length != 0) {
		var recordIds = [];
		//循环遍历获取ID 
		$.each(records, function(index, item){
			recordIds.push(item.id);
		});
		//草稿箱类型
		var boxType = "scrapbox";
		
		//删除警告框
		G3.confirm("提示", "确认删除记录？",
			function() {
				var requestUrl = G3.cmdPath+"mc/core/instationmessage/delete/"+recordIds+"/type/"+boxType;
				$.ajax({
					type : "post",
					dataType : "json",
					url: requestUrl,
					error:function(data){
						G3.alert("提示","删除失败！");
					},
					success:function(data){
						//弹框方式
						G3.alert("提示","删除成功！",function(){
							grid.reload();
						},"success");
					}
				});
			}
		);
	} else {
		G3.alert("提示", "请选择用户！");
	}
}

function selectTime(obj){
	$(obj).datetimepicker({format: 'yyyy-mm-dd',minView: "month"});
}