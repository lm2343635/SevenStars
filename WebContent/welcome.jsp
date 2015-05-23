<%@page import="com.xwkj.sevenstars.bean.User"%>
<%@page import="com.xwkj.sevenstars.dao.MessageDao"%>
<%@page import="com.xwkj.sevenstars.bean.Message"%>
<%@page import="com.xwkj.sevenstars.servlet.SessionServlet"%>
<%@page import="com.xwkj.sevenstars.dao.NotificationDao"%>
<%@page import="com.xwkj.sevenstars.bean.Notification"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%
	boolean isAdmin=SessionServlet.isAdmin(request);
	User user=SessionServlet.get(request);
	String uid=null;
	if(!isAdmin)
		uid=user.getUid();
%>

<html>
	<head>
		<link rel="stylesheet" href="public/css/welcome.css">
		<link rel="stylesheet" href="public/css/pop-box.css">
		<script type="text/javascript" src="public/js/popBox.js"></script>
	</head>
	<body>
		<p class="title">
			<span class="glyphicon glyphicon-home" style="margin-right: 5px;"></span>  <span style="color: #bbb;">\</span> 主页
		</p>
		<div id="main">

			<div class="part1 part">
				<div class="welcome_title">系统通知</div>
				<div class="welcome_content">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>通知内容</th>
								<th>发布时间</th>
							</tr>
						</thead>
						<tbody>
							<%
							for(Notification notification:NotificationDao.getNotification(user.getPrivilege(), 9))
							{
								%>
								<tr>
									<td><%=notification.getNid() %></td>
									<td><a href="javascript:void(0);" onclick="showNotification(<%=notification.getNid()%>)">
										<%
											if(notification.getTitle().length() > 20)
												out.print(notification.getTitle().substring(0, 20) + "...");
											else
												out.print(notification.getTitle());
										%>
									</a></td>
									<td><%=notification.getPublish().getDBString()%></td>
								</tr>
								<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
			<div class="part2 part">
				<div class="welcome_title">
					留言板
					<%
					if(!isAdmin)
					{
						%>
							<button id="add-message" class="btn btn-default btn-xs detail" style="float: right;" title="添加留言">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
						<%
					}
					%>
					<button id="answered-message" class="btn btn-default btn-xs detail" style="float: right;" title="查看已回答留言">
						<span class="glyphicon glyphicon-saved"></span>
					</button>
					<button id="unanswered-message" class="btn btn-default btn-xs detail" style="float: right;" title="查看未回答留言">
						<span class="glyphicon glyphicon-tags"></span>
					</button>
				</div>
				<div class="welcome_content">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>留言内容</th>
								<th>留言用户</th>
							</tr>
						</thead>
						<tbody>
							<%
							for(Message message:MessageDao.getWithLimit(9, uid))
							{
								%>
								<tr>
									<td><%=message.getMid()%></td>
									<td>
										<a href="javascript:void(0);" onclick="showMessage(<%=message.getMid()%>)">
											<%=message.getQuestion(35)%>
										</a>
									</td>
									<td><%=message.getUname()%></td>
								</tr>
								<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="pop-box" id="message">
			<div class="pop-title">留言</div>
			<div class="pop-content" style="padding: 10px;">
				<div>
					<span>留言编号：<span id="msg-mid"></span></span>
					<span style="float: right;">留言者：<span id="msg-uname"></span></span>
				</div>
				<div>
					<span>留言内容：</span>
					<span style="float: right;">留言时间：<span id="msg-qtime"></span></span>
				</div>
				<pre id="msg-question"></pre>
				<div>
					<span>回答内容：</span>
					<span style="float: right;">回答时间：<span id="msg-atime"></span></span>
				</div>
				<pre id="msg-answer"></pre>
			</div>
		</div>
		<script src="public/js/welcome.js"></script> 
	</body>
</html>