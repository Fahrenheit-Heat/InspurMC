<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="ui" uri="/tags/GCloud-UI"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/tags/loushang-web" prefix="l"%>
<!DOCTYPE html>
<html>
	<head>
		<title>消息订阅设置</title>
		<ui:ScriptManager></ui:ScriptManager>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form class="form-horizontal g3form" id="form" role="form"
					onsubmit="return false">
						<legend>
							<div class="pull-right">
								<button class="btn btn-success" type="submit">
									<i class="fa fa-save fa-fw"></i>保存
								</button>
								<button class="btn btn-default" type="button" id="returnBtn">
									<i class="fa fa-reply fa-fw"></i>返回
								</button>
							</div>
						</legend>
						<input type="hidden" value="${subscribe.id}" name="id" />
						<div class="form-group">
							<label class="col-xs-3 col-md-3 control-label"><em>*</em>订阅模块：</label>
							<div class="col-sm-6">
								<select class="form-control input-sm" name="subscribeModule" datatype="s"
									nullmsg="请选择订阅模块">
									<option value=""
										<c:if test="${subscribe.subscribeModule eq ''}" >selected='selected'</c:if>>请选择</option>
									<option value="A"
										<c:if test="${subscribe.subscribeModule eq 'A'}" >selected='selected'</c:if>>模块1</option>
									<option value="B"
										<c:if test="${subscribe.subscribeModule eq 'B'}" >selected='selected'</c:if>>模块2</option>
									<option value="C"
										<c:if test="${subscribe.subscribeModule eq 'C'}" >selected='selected'</c:if>>模块3</option>
									<option value="D"
										<c:if test="${subscribe.subscribeModule eq 'D'}" >selected='selected'</c:if>>模块4</option>
								</select>
								<div class="errorMsg"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 col-md-3 control-label"><em>*</em>提醒方式：</label>
							<div class="col-sm-6">
								<select class="form-control input-sm" name="warnType" datatype="s"
									nullmsg="请选择通知方式">
									<option value=""
										<c:if test="${subscribe.warnType eq ''}" >selected='selected'</c:if>>请选择</option>
									<option value="A"
										<c:if test="${subscribe.warnType eq 'A'}" >selected='selected'</c:if>>全部方式</option>
									<option value="M"
										<c:if test="${subscribe.warnType eq 'M'}" >selected='selected'</c:if>>站内消息</option>
									<option value="W"
										<c:if test="${subscribe.warnType eq 'W'}" >selected='selected'</c:if>>站内邮件</option>
									<option value="S"
										<c:if test="${subscribe.warnType eq 'S'}" >selected='selected'</c:if>>短信</option>
									<option value="E"
										<c:if test="${subscribe.warnType eq 'E'}" >selected='selected'</c:if>>电子邮件</option>
								</select>
								<div class="errorMsg"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 col-md-3 control-label"><em>*</em>是否开启订阅：</label>
							<div class="col-sm-6 text-left">
								<label class="radio-inline"><input type="radio"
									name="subscribeOpen" value="1"
									<c:if test="${subscribe.subscribeOpen=='1'}">checked="checked"</c:if> />是
								</label> 
								<label class="radio-inline"><input type="radio"
									name="subscribeOpen" value="0"
									<c:if test="${subscribe.subscribeOpen=='0'}">checked="checked"</c:if> />否
								</label> 
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 col-md-3 control-label">备注：</label>
							<div class="col-sm-6">
								<textarea rows="8" class="form-conrtol col-sm-12"
									id="remark" name="remark" >${subscribe.remark}</textarea>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="<ui:context/>jsp/mc/messagesubscribe/js/subscribe_edit.js"></script>
	</body>
</html>