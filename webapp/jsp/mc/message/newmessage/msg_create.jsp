<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.inspur.gcloud.bsp.BspUtil"%>
<%@ taglib prefix="ui" uri="/tags/GCloud-UI"%>
<%
	//登录用户的用户名
	String loginName = BspUtil.getInstance().getLoginUserName();
	// 获取当前登录用户id
	String loginId = BspUtil.getInstance().getLoginUserOrganId();
%>
<html>
<head>
<title>新建短信</title>
<ui:ScriptManager></ui:ScriptManager>
<script type="text/javascript">
	var loginName = '<%=loginName%>';
	var loginId = '<%=loginId%>';
</script>
<style type="text/css">
.panel-body{
	margin-top:5px;
}
</style>
</head>
<body class="body_style1">
<div class="container container_ban">
	<div class="row">
		<div class="col-md-12">
			<div class="operation_box">
				<div class="main_operation">
					<button class="btn btn-primary" type="button" id="bookBtn">
						<i class="fa fa-book"></i>通讯录
					</button>
					<button class="btn btn-primary" type="button" id="sendBtn">
						<i class="fa fa-share"></i>发送
					</button>
					<button class="btn btn-success" type="button" id="saveBtn">
						<i class="fa fa-save fa-fw"></i>保存
					</button>
					<button class="btn btn-default" type="button" id="backBtn">
						<i class="fa fa-reply fa-fw"></i>返回
					</button>
				</div>
			</div>
			<div class="main_box g3form_list margin_top_15">
				<form class="form-horizontal" role="form" id="form"  onsubmit="return false;">
					<div class="bank1"></div>
					<div class="panel-body">
						<div class="form-simple form-simple-edit no-border-bottom form-simple-modal">
							<input type="hidden" id="messageId" name="messageId" value="${messageId}">
							<input type="hidden" id="operType" name="operType" value="${operType}">
							<input type="hidden" id="boxType" name="boxType" value="${boxType}">
							<input type="hidden" id="messageType" name="messageType" value="${messageView.messageType}">
							<input type="hidden" id="envelopeId" name="envelopeId" value="${messageView.envelopeId}">
							<input type="hidden" id="sendType" name="sendType" value="${messageView.sendType}" >
							<input type="hidden" id="sendState" name="sendState" value="${messageView.sendState}">
							<input type="hidden" id="receiveType" name="receiveType" value="${messageView.receiveType}">
							<input type="hidden" id="receiveState" name="receiveState" value="${messageView.receiveState}">
							<input type="hidden" id="relatedMessageId" name="relatedMessageId" value="${messageView.relatedMessageId}">
							<div class="form-hr">
								<div class="fieldLabel">
									<i class="fa fa-user"></i>发件人
								</div>
								<div class="fieldInput" style="width:85%;">
									<input type="hidden" id="senderId" name="senderId" value="${messageView.senderId}">
									<input type="text" class="form-control" id="senderName" name="senderName"
										value="${messageView.senderName}" readOnly />
								</div>
							</div>
							<div class="form-hr">
								<div class="fieldLabel">
									<em>*</em><i class="fa fa-user"></i>收件人
								</div>
								<div class="fieldInput" style="width:85%;">
									<input type="hidden" id="receiverId" name="receiverId" value="${messageView.receiverId}" />
									<input type="text" class="form-control" id="receiverName" name="receiverName"
										value="${messageView.receiverName}" placeholder="收件人" />
								</div>
							</div>
							<div class="form-hr">
								<div class="fieldLabel">
									<em>*</em><i class="fa fa-pencil-square-o"></i>短信内容
								</div>
								<div class="fieldInput" style="width:85%;">
									<textarea class="form-control" id="messageContent" name="messageContent"
										placeholder="短信内容">${messageView.messageContent}</textarea>
								</div>
							</div>
							<div class="form-hr">
								<div class="fieldLabel">
									<i class="fa fa-calendar"></i>定时发送
								</div>
								<div class="fieldInput">
									<input type="text" class="form-control" id="messageSendTime" name="messageSendTime"
										readOnly onclick="selectTime(this)">
								</div>
								<div>&nbsp;&nbsp;&nbsp;&nbsp;(不填表示立即发送)</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div> 
</div>
<script type="text/javascript" src="<ui:context/>jsp/mc/message/newmessage/js/msg_create.js"></script>
</body>
</html>