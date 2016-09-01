<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ui" uri="/tags/GCloud-UI"%>
<%@ page import="com.inspur.gcloud.bsp.BspUtil" %>
<%@ page import="com.inspur.gcloud.mc.common.*" %>
<!DOCTYPE html>
<%
	//用户名
	String loginName = BspUtil.getInstance().getLoginUserName();
	//用户ID
	String loginId = BspUtil.getInstance().getLoginUserOrganId();
	//设定信箱类型
	String messageType = McDataObjectConstants.MESSAGE_TYPE_INSTATIONMSG;
	String boxType = McSystemConstants.INSTATIONMSG_SCRAP_BOX;
%>
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>废件箱</title>
<ui:ScriptManager hasList="true"></ui:ScriptManager>
<Script type="text/javascript">
	var loginName = '<%=loginName%>';
	var loginId = '<%=loginId%>';
	var boxType = '<%=boxType%>';
	var messageType = '<%=messageType%>';
</Script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form class="form-inline queryForm" role="form" onsubmit="return false;">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="主题"
							id="messageTopic">
						<div class="input-group-addon" id="queryBtn">
							<span class="fa fa-search"></span>
						</div>
					</div>
					
					<a class="btn btn-moresearch" id="moresearch">
						<i class="fa fa-angle-double-down"></i>更多查询
					</a>
					
					<div class="pull-right">
						<button class="btn btn-danger" type="button" id="delBtn">
							<i class="fa fa-trash-o fa-fw"></i>彻底删除
						</button>
					</div>
				</form>	
				<div class="panel panel-default">
					<div class="panel-body">
						<table class="table table-bordered table-hover" id="instationMsgList">
							<thead>
								<tr>
									<th data-number="true">
									<th data-checkbox="true">
									<th width="8%" data-field="senderName" data-sortable="false">发件人</th>
									<th width="8%" data-field="receiverName" data-sortable="false">收件人</th>
									<th width="25%" data-field="messageTopic" data-render="messageShowLink">主题</th>
									<th width="25%" data-field="sendTime">日期</th>
									<th width="25%" data-field="sendState" data-render="renderstatus">状态</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>		
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<ui:context/>jsp/mc/instationmessage/scrapbox/js/msg_scrapbox_query.js"></script>
	<script id="mypopover" type="text/html">
		<table class="table table-moresearch">
			<tr>
				<td class="fieldInput">
					<label>发件人</label>
					<input type="text" id="senderName" class="form-control" placeholder="发件人">
				</td>
				<td class="fieldInput">
					<label>收件人</label>
					<input type="text" id="receiverName" class="form-control" placeholder="收件人">
				</td>
			</tr>
			<tr>
				<td class="fieldInput">
					<label>开始于</label>
					<div class="input-group" style="display:inline-table">
						<input type="text" class="form-control"  id="sendTimeFrom" data-bind="value:startTime" readOnly onclick="selectTime(this)" style="display:inline-table;width: 100%;">
						<span class="input-group-addon "><i class="fa fa-calendar"></i></span>
					</div>
				</td>
				<td class="fieldInput">
					<label>结束于</label>
					<div class="input-group" style="display:inline-table">
						<input type="text" class="form-control "  id="sendTimeTo" data-bind="value:sendTime" readOnly onclick="selectTime(this)" style="display:inline-table;width: 100%;">
						<span class="input-group-addon "><i class="fa fa-calendar"></i></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="pull-left">
					<button type="button" class="btn btn-primary" id="moreQueryBtn"><i class="fa fa-search"></i>查询</button>
					<button type="button" class="btn btn-default" id="resetBtn"><i class="fa fa-reply"></i>重置</button>
				</td>	
			</tr>
		</table>
	</script>

</body>
</html>