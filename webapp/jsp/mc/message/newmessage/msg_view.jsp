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
<!DOCTYPE html>
<html>
<head>
<title>查看短信</title>
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
							<input type="hidden" id="senderId" name="senderId">
							<input type="hidden" id="receiveState" name="receiveState">
							<div class="form-hr">
								<div class="fieldLabel">
									<i class="fa fa-user"></i>发件人：
								</div>
								<div class="fieldInput" style="width:85%;">
									<input type="text" class="form-control" id="senderName" name="senderName" value="${messageView.senderName}" readOnly />
								</div>
							</div>
							<div class="form-hr"> 
								<div class="fieldLabel">
									<em>*</em><i class="fa fa-user"></i>收件人：
								</div>
								<div class="fieldInput" style="width:85%;">
									<input type="hidden" id="receiverId" name="receiverId"/>
									<input type="text" class="form-control" id="receiverName" name="receiverName" placeholder="收件人" readOnly />
								</div>
							</div>
							<div class="form-hr">
								<div class="fieldLabel">
									<i class="fa fa-pencil-square-o"></i>短信内容：
								</div>
								<div class="fieldInput" style="width:85%;">
									<textarea class="form-control" id="messageContent" name="messageContent"
										placeholder="短信内容" readOnly >${messageView.messageContent}</textarea>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div> 
</div>
<script type="text/javascript" src="<ui:context/>jsp/mc/message/newmessage/js/msg_view.js"></script>
</body>
</html>