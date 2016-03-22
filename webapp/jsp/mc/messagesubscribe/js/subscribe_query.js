//上下文根
var context = G3.webPath;
$(function(){
	//初始化表格
	var url ="mc/subscribe/inSubscribeList";
	grid = new G3.Grid("inMassageList");
	//设置数据请求地址
	grid.setAjaxUrl(url);
	//初始化
	grid.init();
	
	// 条件查询
	$("#queryBtn").click(query);
	
	// 更多查询
	$("body").on("click", "#moreQueryBtn", query);
	
	//重置
	$("body").on("click", "#resetBtn", function(){
		$("#subscribeModule").val("");
		$("#subscribeOpen").val("");
		$("#warnType").val("");
		$("#remark").val("");
	});
	
	// 查询更多查询条件初始化
	$("#moresearch").morequery({
		"title" : "",
		"content" : template('mypopover', {})
	});
});

/**
 * 渲染订阅内容
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function rendermodule(data, type, full) {
	if (data != "" || data != null) {
		if (data == "A") {
			data = "模块1";
		}
		if (data == "B") {
			data = "模块2";
		}
		if (data == "C") {
			data = "模块3";
		}
		if (data == "D") {
			data = "模块4";
		}
	}
	return data;
}

/**
 * 渲染订阅内容
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function rendertype(data, type, full) {
	if (data != "" || data != null) {
		if (data == "A") {
			data = "全部方式";
		}
		if (data == "M") {
			data = "站内消息";
		}
		if (data == "W") {
			data = "站内邮件";
		}
		if (data == "S") {
			data = "短信";
		}
		if (data == "E") {
			data = "电子邮件";
		}
	}
	return data;
}
function renderopen(data, type, full) {
	if (data != "" || data != null) {
		if (data == "1") {
			data = "是";
		}
		if (data == "0") {
			data = "否";
		}
	}
	return data;
}

/**
 * 查询数据
 */
function query() {
	var organId = $("#organId").val();
	var warnType = $("#warnType").val();
	var subscribeModule = $("#subscribeModule").val();
	var subscribeOpen = $("#subscribeOpen").val();
	var remark = $("#remark").val();
	if (organId == undefined) {
		organId = "";
	}
	
	var url = "mc/subscribe/query";
	grid.setAjaxUrl(url);
	grid.setParameter("organId", organId);
	grid.setParameter("warnType", warnType);
	grid.setParameter("subscribeModule", subscribeModule);
	grid.setParameter("subscribeOpen", subscribeOpen);
	grid.setParameter("remark", remark);
	grid.load();
}