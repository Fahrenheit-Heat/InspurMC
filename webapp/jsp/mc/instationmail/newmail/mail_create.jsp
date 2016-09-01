<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="ui" uri="/tags/GCloud-UI"%>

<html>
<head>
<title>新建邮件</title>
<ui:ScriptManager hasList="true"></ui:ScriptManager>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<form class="form-horizontal g3form" id ="form" role="form" onsubmit="return false;">
				<div class="pull-right">
					<button class="btn btn-success" type="submit">
						<i class="fa fa-save fa-fw"></i>发送
					</button>
					<button class="btn btn-success" type="button" onClick = "save();">
						<i class="fa fa-save fa-fw"></i>保存
					</button>
				</div>
			<div class="panel panel-default">
				<div class="panel-body ">
					<table>
						<tr class=>
						<td class="fieldLabel" width="10%">
							<button class="btn btn-primary" type="button"  onclick="queryParty()">收件人:</button>
						</td>
						<td class="fieldInput">
							<input type="text" class="form-control" id="receivername" name="receivername" 
						           value="${envelope.receivername}" placeholder="收件人" ignore="ignore">
							<label class="errorMsg"></label>
						</td>
						</tr>
					<%-- 	<tr>
						<td class="fieldLabel" width="10%">
							<button class="btn btn-primary" type="button"  onclick="queryParty()">抄送:</button>
						</td>
						<td class="fieldInput">
							<input type="text" class="form-control" id="receivernames" name="receivernames" 
						           value="${envelope.receivername}" placeholder="抄送" ignore="ignore">
							<label class="errorMsg"></label>
						</td>
						</tr> --%>
						<tr>
						<td class="fieldLabel" width="10%">邮件主题:</td>
						<td class="fieldInput">
						<input type="text" class="form-control" id="messageTopic" name="messageTopic"
							value="${envelope.messageTopic}" placeholder="邮件主题" datatype="*" ignore="ignore">
						
						</td>
						</tr>
						<tr>
						<td class="fieldLabel" width="10%">发送时间:</td>
						<td class="fieldInput">
							<div class="input-group date Validform_input">
								<input type="text" class="form-control ue-form" id="sendTime" name="sendTime" 
								   value="${envelope.sendTime}" placeholder="发送时间" datatype="*" ignore="ignore" />
								<span class="input-group-addon ue-form-btn date-input-btn">
									<i class="fa fa-calendar"></i>
								</span>
							</div>
						</td>
						</tr>
					</table>
					<div style="width: 100%;">
					<!-- 加载编辑器的容器 -->
					<script id="ueditor" name="message.messageContent" type="text/plain" style="width:100%;height:500px;">${envelope.message.messageContent}</script>
					</div>
					<!-- <div id="ueditor" style="width:100%;height:200px;"></div>
				</div> -->
			</div>
			</form>
		</div>
	</div> 
</div>
<!-- 配置文件 -->
<script type="text/javascript" src="../../../skins/common/plugin/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="../../../skins/common/plugin/ueditor/ueditor.all.js"></script>
 <!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('ueditor',{initialFrameHeight:400 });
    
</script>
<script type="text/javascript" src="<ui:context/>jsp/mc/mailedit/mail_create.js"></script>

</body>
</html>