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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看消息</title>
<ui:ScriptManager></ui:ScriptManager>
<script type="text/javascript">
	var loginName = '<%=loginName%>';
	var loginId = '<%=loginId%>';
</script>
<style type="text/css">
.panel-body{
	margin-top:5px;
}
.main_operation{
	z-index: 1000;
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
							<input type="hidden" id="messageId" name="messageId" value="${messageId }">
							<input type="hidden" id="operType" name="operType" value="${operType }">
							<input type="hidden" id="boxType" name="boxType" value="${boxType }">
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
									<i class="fa fa-pencil-square-o"></i>主&nbsp;&nbsp;&nbsp;&nbsp;题：
								</div>
								<div class="fieldInput" style="width:85%;">
									<input type="text" class="form-control" id="messageTopic" name="messageTopic" placeholder="主题" readOnly />
								</div>
							</div>
							<div id="editor" style="width:100%; "></div>
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
<script type="text/javascript" src="<ui:context/>jsp/mc/instationmessage/newmessage/js/msg_view.js"></script>
</body>
</html>