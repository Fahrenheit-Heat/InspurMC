<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.inspur.gcloud.bsp.BspUtil"%>
<%@taglib prefix="ui" uri="/tags/GCloud-UI"%>
<%
	// 登录用户的用户名
	String loginName = BspUtil.getInstance().getLoginUserName();
	// 获取当前登录用户OrganId
	String loginId = BspUtil.getInstance().getLoginUserOrganId();
	// 获取当前登录用户userId
	String userId = BspUtil.getInstance().getLoginUserId();
	// 获取当前登录人用户权限
	boolean isSuper = BspUtil.getInstance().isHasRole(userId, "SUPERADMIN");
%>
<html>
<head>
<title>新建消息</title>
<ui:ScriptManager></ui:ScriptManager>
<script type="text/javascript">
	var loginName = '<%=loginName%>';
	var loginId = '<%=loginId%>';
	var isSuper = <%=isSuper%>;
</script>
<style type="text/css">
.panel-body{
	margin-top:5px;
}
.main_operation{
	z-index: 1000;
}
#receiverName{
	height: 35px;
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
							<input type="hidden" id="envelopeId" name="envelopeId" value="${messageView.envelopeId}">
							<input type="hidden" id="sendType" name="sendType" value="${messageView.sendType}" >
							<input type="hidden" id="sendState" name="sendState" value="${messageView.sendState}">
							<input type="hidden" id="receiveState" name="receiveState" value="${messageView.receiveState}">
							<input type="hidden" id="relatedMessageId" name="relatedMessageId" value="${messageView.relatedMessageId}">
							<input type="hidden" id="messageContent" name="messageContent" value="${messageView.messageContent}">
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
									<i class="fa fa-user"></i>收件人
								</div>
								<div class="fieldInput" style="width:85%;">
									<input type="hidden" id="receiverId" name="receiverId" value="${messageView.receiverId}" />
									<textarea class="form-control" id="receiverName" name="receiverName" placeholder="收件人" 
										text="${messageView.receiverName}" readOnly></textarea>
								</div>
							</div>
							<div class="form-hr">
								<div class="fieldLabel">
									<i class="fa fa-pencil-square-o"></i>主&nbsp;&nbsp;&nbsp;&nbsp;题
								</div>
								<div class="fieldInput" style="width:85%;">
									<input type="text" class="form-control" id="messageTopic" name="messageTopic" 
										value="${messageView.messageTopic}" placeholder="主题" />
								</div>
							</div>
							<div class="form-group"" id="receiverTypeDiv" style="display:none;">
								<div class="fieldLabel">
									<label>消息类型</label>
								</div>
								<div class="fieldInput" style="width:85%;">
									<label class="radio-inline">
										<input type="radio" id="generalMessage" name="receiverType" value="1" checked />普通消息
									</label>
									<label class="radio-inline">
										<input type="radio" id="systemMessage" name="receiverType" value="3" />系统消息
									</label>
								</div>
							</div>
							<div id="editor" style="width:100%;"></div>
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