<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.inspur.gcloud.bsp.BspUtil"%>
<%@ taglib prefix="ui" uri="/tags/GCloud-UI"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看消息</title>
<ui:ScriptManager></ui:ScriptManager>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="main_box g3form_list">
				<form class="form-horizontal g3form" role="form" id="form"  onsubmit="return false;">
					<div class="pull-right">
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
					<div class="bank1"></div>
					<div class="panel-body">
						<div class="form-simple form-simple-edit no-border-bottom form-simple-modal">
							<input type="hidden" id="envelopeId" name="envelopeId" value="${messageView.envelopeId}">
							<input type="hidden" id="messageId" name="messageId" value="${messageView.messageId}">
							<input type="hidden" id="sendType" name="sendType" value="${messageView.sendType }" >
							<input type="hidden" id="sendState" name="sendState" value="${messageView.sendState}">
							<input type="hidden" id="messageContent" name="messageContent" value="${messageView.messageContent}"/>
							<input type="hidden" id="messageType" name="messageType" value="m"/>
							<input type="hidden" id="senderId" name="senderId" value="${messageView.senderId}">
							<input type="hidden" id="receiveState" name="receiveState" value="${messageView.receiveState}">
							<div class="form-hr">
								<div class="fieldLabel">
									<i class="fa fa-user"></i>发件人：
								</div>
								<div class="fieldInput">
									<input type="text" class="form-control" id="senderName" name="senderName" value="${messageView.senderName}" readOnly />
								</div>
							</div>
							<div class="form-hr">
								<div class="fieldLabel">
									<em>*</em><i class="fa fa-user"></i>收件人：
								</div>
								<div class="fieldInput">
									<input type="hidden" id="receiverId" name="receiverId" value="${messageView.receiverId}" />
									<input type="text" class="form-control" id="receiverName" name="receiverName"
										value="${messageView.receiverName}" placeholder="收件人" />
								</div>
							</div>
							<div class="form-hr">
								<div class="fieldLabel">
									<i class="fa fa-pencil-square-o"></i>主&nbsp;&nbsp;&nbsp;&nbsp;题：
								</div>
								<div class="fieldInput">
									<input type="text" class="form-control" id="messageTopic" name="messageTopic"
										value="${messageView.messageTopic}" placeholder="主题" />
								</div>
							</div>
							<div>
								<span id="messageShow"></span>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div> 
</div>
<script type="text/javascript" src="<ui:context/>jsp/mc/instationmessage/newmessage/js/msg_view.js"></script>
</body>
</html>