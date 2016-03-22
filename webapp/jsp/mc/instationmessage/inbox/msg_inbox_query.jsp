<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="ui" uri="/tags/GCloud-UI"%>
<!DOCTYPE html>
<html>
<head>
<title>收件箱</title>
<ui:ScriptManager hasList="true"></ui:ScriptManager>
</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form class="form-inline queryForm" role="form" onsubmit="return false;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="主题"
								id="userId">
							<div class="input-group-addon" id="query">
								<span class="fa fa-search"></span>
							</div>
						</div>
						
						<a class="btn btn-moresearch" id="moresearch"><i
							class="fa fa-angle-double-down"></i>更多查询</a>
						<div class="pull-right">
							<button class="btn btn-primary" type="button" id="addBtn">
								<i class="fa fa-plus fa-fw"></i>新建
							</button>
							<button class="btn btn-primary" type="button" id="replyBtn">
								<i class="fa fa-share "></i>回复
							</button>
							<button class="btn btn-primary" type="button" id="forwardBtn">
								<i class="fa fa-share "></i>转发
							</button>
							<button class="btn btn-danger" type="button" id="delBtn">
								<i class="fa fa-trash-o fa-fw"></i>删除
							</button>
						</div>
					</form>
					<div class="panel panel-default">
						<div class="panel-body">
							<table id="instationMsgList" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th data-number="true">
										<th data-checkbox="true">
										<th width="10%" data-field="senderId" data-sortable="false">发件人</th>
										<th width="60%" data-field="messageTopic">主题</th>
										<th width="20%" data-field="sendTime">日期</th>
										<th width="10%" data-field="receiveState">状态</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="<ui:context/>jsp/mc/instationmessage/inbox/js/msg_inbox_query.js"></script>
		<script id="mypopover" type="text/html">
		<table class="table table-moresearch">
			<tr>
				<td class="fieldInput"><label>状态</label>
					<input type="text" id="receiveState" class="form-control" placeholder="状态">
				</td>
				<td class="fieldInput"><label>发件人</label>
					<input type="text" id="senderName" class="form-control" placeholder="发件人">
				</td>
			</tr>
			<tr>
				<td class="fieldInput">
					<label>开始于</label>
					<div class="input-group" style="display:inline-table">
					     <input type="text" class="form-control"  id="sendTimeFrom" data-bind="value:sendTime" readOnly style="display:inline-table;width: 100%;"></input>
						 <span class="input-group-addon " onclick="selectCalendar(this);"><i class="fa fa-calendar"></i></span>
					</div>
				</td>
				<td class="fieldInput">
					<label>结束于</label>
					<div class="input-group" style="display:inline-table">
						 <input type="text" class="form-control "  id="sendTimeTo" data-bind="value:sendTime" readOnly style="display:inline-table;width: 100%;"></input>
						 <span class="input-group-addon " onclick="selectCalendar(this);"><i class="fa fa-calendar"></i></span>
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