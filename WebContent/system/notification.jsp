<%@page import="com.xwkj.sevenstars.bean.User"%>
<%@page import="com.xwkj.sevenstars.dao.NotificationDao"%>
<%@page import="com.xwkj.sevenstars.servlet.SessionServlet"%>
<%@page import="com.xwkj.sevenstars.bean.Notification"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
	User user=SessionServlet.get(request);
	ArrayList<Notification> notifications=NotificationDao.getNotification(user.getPrivilege());
	boolean isAdmin=SessionServlet.isAdmin(request); 
	String [] privilege={"所有人可见","连锁店可见","加盟店可见","供货商可见","农资店可见"};
%>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>系统通知</title>
	<link rel="stylesheet" href="public/css/notification.css">
</head>
<body>
	<p class="title">
		<span class="glyphicon glyphicon-leaf" style="margin-right: 5px;"></span>  <span style="color: #bbb;">\</span> 系统通知
		<%
		if(isAdmin)
		{
			%>
				<span class="dtT">
					<button id="addNewNotification" class="btn btn-default btn-xs" style="float: right;">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</span>	
			<%
		}
		%>
	</p>
	<div class="showInfo show">
		<table class="table table-hover">
			<thead>
				<tr class="head-tr">
					<th>通知编号</th>
					<th>通知标题</th>
					<th>通知内容</th>
					<th>发布时间</th>
					<%
					if(isAdmin)
					{
						%>
							<th>查看权限</th>
							<th>删除</th>
						<%
					}
					%>			
				</tr>
			</thead>
		</table>	
	</div>	
	<div class="showInfo show showInfo-scroll">
		<table class="table table-hover"  >	
			<tbody>
				<%
				for(Notification notification:notifications)
				{
					%>
					<tr class="scroll-tr">
						<td><%=notification.getNid() %></td>
						<td><%=notification.getTitle() %></td>
						<td>
							<button onclick="showNotification(<%=notification.getNid()%>)" class="btn btn-default btn-xs">
								<span class="glyphicon glyphicon-eye-open"></span>
							</button>
						</td>
						<td><%=notification.getPublish().getDBString() %></td>
						<%
						if(isAdmin)
						{
							%>
								<td><%=privilege[notification.getPrivilege()] %></td>
								<td><button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span></button></td>
							<%
						}
						%>			
					</tr>
					<%
				}
				%>
			</tbody>
		</table>
	</div>
	<div class="pop-box" id="newNotification">
		<div class="pop-title"><p>添加通知</p></div>
		<div class="pop-content">
			<form id="notificationInfo">
				<table>
					<tr>
						<td><span>通知标题:</span></td>
						<td><input name="title" type="text" class="form-control" style="width:400px;margin: 5px 0;"></td>
					</tr>
					<tr>
						<td><span>通知内容:</span></td>
			            <td><textarea name="content"  class="form-control" style="width:400px;margin: 5px 0;height:90px;"></textarea></td>
					</tr>
					<tr>
						<td><span>查看权限:</span></td>
			            <td>
							<select id="privilege" class="form-control">
								<option value="0">所有人可见</option>
								<option value="1">连锁店可见</option>
								<option value="2">加盟店可见</option>
								<option value="3">供货商可见</option>
								<option value="4">农资店可见</option>
							</select>
						</td>
					</tr>
				</table>
				<input type="submit" class="btn btn-default" id="sbt" style="width: 224px;">
				<input type="reset" class="btn btn-warning" style="width: 224px;">
			</form>	
		</div>
	</div>	
	<script src="public/js/notification.js"></script>
</body>
</html>
