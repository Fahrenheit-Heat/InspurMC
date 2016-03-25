<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="ui" uri="/tags/GCloud-UI"%>
<%@ taglib uri="/tags/loushang-web" prefix="l"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>消息订阅查询</title>
		<ui:ScriptManager hasList="true"></ui:ScriptManager>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form class="form-inline queryForm" role="form" onsubmit="return false;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="订阅模块"
								id="subscribeModule">
							<div class="input-group-addon" id="queryBtn">
								<span class="fa fa-search"></span>
							</div>
						</div>
						
						<a class="btn btn-moresearch" id="moresearch"><i
							class="fa fa-angle-double-down"></i>更多查询</a>
						<div class="pull-right">
							<button class="btn btn-primary" type="button" id="addBtn">
								<i class="fa fa-plus fa-fw"></i>增加
							</button>
							<button class="btn btn-primary" type="button" id="editBtn">
								<i class="fa fa-edit fa-fw"></i>编辑
							</button>
							<button class="btn btn-danger" type="button" id="delBtn">
								<i class="fa fa-trash-o fa-fw"></i>删除
							</button>
						</div>
					</form>
					<div class="panel panel-default">
						<div class="panel-body">
							<table id="inMassageList" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th data-number="true">
										<th data-checkbox="true">
										<th width="15%" data-field="organId" data-sortable="false">组织代码</th>
										<th width="25%" data-field="subscribeModule" data-render="rendermodule">订阅模块</th>
										<th width="20%" data-field="warnType" data-render="rendertype">提醒方式</th>
										<th width="10%" data-field="subscribeOpen" data-render="renderopen">是否开启订阅</th>
										<th width="25%" data-field="remark">备注</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script type="text/javascript" src="<ui:context/>jsp/mc/messagesubscribe/js/subscribe_query.js"></script>
	<script id="mypopover" type="text/html">
		<table class="table table-moresearch">
			<tr>
				<td class="fieldInput"><label>是否订阅</label>
					<label class="radio-inline"><input type="radio"
						name="subscribeOpen" value="1"
						checked="checked" />是
					</label> 
					<label class="radio-inline"><input type="radio"
						name="subscribeOpen" value="0"
						/>否
					</label> 
				</td>
				<td class="fieldInput"><label>提醒方式</label>
					<select class="form-control input-sm" id="warnType">
						<option value=""
							>请选择</option>
						<option value="A"
							>全部方式</option>
						<option value="M"
							>站内消息</option>
						<option value="W"
							>站内邮件</option>
						<option value="S"
							>短信</option>
						<option value="E"
							>电子邮件</option>
					</select>
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