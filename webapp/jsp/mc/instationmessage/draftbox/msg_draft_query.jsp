<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="ui" uri="/tags/GCloud-UI"%>
<!DOCTYPE html>
<html>
<head>
<title>草稿箱</title>
<ui:ScriptManager hasList="true"></ui:ScriptManager>
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
						
						<a class="btn btn-moresearch" id="moresearch"><i
							class="fa fa-angle-double-down"></i>更多查询</a>
						<div class="pull-right">
							<button class="btn btn-primary" type="button" id="editBtn">
								<i class="fa fa-edit fa-fw"></i>编辑
							</button>
							<button class="btn btn-primary" type="button" id="sendBtn">
								<i class="fa fa-share "></i>发送
							</button>
							<button class="btn btn-danger" type="button" id="delBtn">
								<i class="fa fa-trash-o fa-fw"></i>删除
							</button>
						</div>
					</form>
					<div class="panel panel-default">
						<div class="panel-body">
							<table id="inEnvelopeList" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th data-number="true">
										<th data-checkbox="true">
										<th width="15%" data-field="receiverId" data-sortable="false">收件人</th>
										<th width="25%" data-field="message.messageTopic">主题</th>
										<th width="25%" data-field="sendTime">日期</th>
										<th width="25%" data-field="sendState">状态</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="<ui:context/>jsp/mc/instationmessage/draftbox/js/msg_draftbox_query.js"></script>
		<script id="mypopover" type="text/html">
		<table class="table table-moresearch">
			<tr>
				<td class="fieldInput"><label>状态</label>
					<input type="text" id="sendState" class="form-control" placeholder="状态">
				</td>
				<td class="fieldInput"><label>收件人</label>
					<input type="text" id="receiverName" class="form-control" placeholder="收件人">
				</td>
			</tr>
			<tr>
				<td>
					
					<label>从</label>
					<div class="input-group sameline">
					     <input type="text" class="form-control"  id="sendTimeFrom" data-bind="value:sendTime" ></input>
						 <span class="input-group-addon " onclick="selectCalendar(this);"><i class="fa fa-calendar"></i></span>
					     </div>
					<label>至</label>
					<div class="input-group sameline">
						 <input type="text" class="form-control "  id="sendTimeTo" data-bind="value:sendTime" ></input>
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