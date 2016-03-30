<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="ui" uri="/tags/GCloud-UI"%>
<html>
<head>
<title>新建消息</title>
<ui:ScriptManager></ui:ScriptManager>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="main_box g3form_list">
				<form class="form-horizontal g3form" role="form" id="form"  onsubmit="return false;">
					<div class="pull-right">
						<button class="btn btn-primary" type="button" id="">
							<i class="fa fa-book"></i>通讯录
						</button>
						<button class="btn btn-primary" type="button" id="sendBtn">
							<i class="fa fa-share"></i>发送
						</button>
						<button class="btn btn-success" type="button" id="saveBtn">
							<i class="fa fa-save fa-fw"></i>保存
						</button>
					</div>
					<div class="bank1"></div>
					<div class="panel-body">
						<div class="form-simple form-simple-edit no-border-bottom form-simple-modal">
							<input type="hidden" id="envelopeId" name="envelopeId" value="${messageView.envelopeId}">
							<input type="hidden" id="messageId" name="messageId" value="${messageView.messageId}">
							<input type="hidden" id="sendType" name="sendType" value="${messageView.sendType }" >
							<input type="hidden" id="messageContent" name="messageContent" value="${messageView.messageContent}"/>
							<input type="hidden" id="messageType" name="messageType" value="m"/>
							<input type="hidden" id="senderId" name="senderId" value="${messageView.senderId}">
							<input type="hidden" id="senderName" name="senderName" value="${messageView.senderName}">
							<input type="hidden" id="sendState" name="sendState" value="${messageView.sendState}">
							<div class="form-hr">
								<div class="fieldLabel" title="发件人">
									<i class="fa fa-user"></i>发件人：
								</div>
								<div class="fieldInput">
									<span id="loginUserName">"${messageView.senderName}"</span>
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
							<div id="editor" style="width:100%; height:120px;"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div> 
</div>
<!-- 配置文件 -->
<script type="text/javascript" src="<ui:context/>skins/common/plugin/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<ui:context/>skins/common/plugin/ueditor/ueditor.all.js"></script>
<!-- 编辑器配置 -->
<script type="text/javascript" src="<ui:context/>jsp/mc/common/js/common_ue_settings.js"></script>
<script type="text/javascript" src="<ui:context/>jsp/mc/instationmessage/newmessage/js/msg_create.js"></script>
</body>
</html>