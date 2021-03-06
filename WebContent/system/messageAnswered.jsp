<%@page import="com.xwkj.sevenstars.servlet.SessionServlet"%>
<%@page import="com.xwkj.sevenstars.dao.MessageDao"%>
<%@page import="com.xwkj.sevenstars.bean.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
boolean isAdmin=SessionServlet.isAdmin(request); 
String uid=null;
if(!isAdmin)
	uid=SessionServlet.get(request).getUid();
ArrayList<Message> messages=MessageDao.getAnswered(uid);
%>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>留言板（已回答）</title>
	<link rel="stylesheet" href="public/css/message.css">
</head>
<body>
	<p class="title">
		<span class="glyphicon glyphicon-leaf" style="margin-right: 5px;"></span>  <span style="color: #bbb;">\</span> 留言板（已回答）
	</p>
	<div class="showInfo show">
		<table class="table table-hover">
			<thead>
				<tr>
					<th colspan="6">已回答留言</th>			
				</tr>
			</thead>
			<tbody>
				<%
				for(Message message:messages)
				{
					%>
					<tr id="<%=message.getMid()%>">
						<th>留言编号：<%=message.getMid()%></th>
						<td colspan="3"><%=message.getQuestion(40) %></td>
						<td>留言用户：<%=message.getUid()%>号用户<%=message.getUname()%></td>	
						<td>			
							<%
							if(isAdmin)
							{
								%>
									<button class="btn btn-danger btn-xs" onclick="deleteMsg(<%=message.getMid()%>)">
										<span class="glyphicon glyphicon-trash"></span>
									</button>
								<%
							}
							%>	
							<button class="btn btn-xs" onclick="showMsgContent(<%=message.getMid()%>)">
								<span class="glyphicon glyphicon-chevron-down" id="showBt<%=message.getMid()%>"></span>
							</button>
						</td>	
					</tr>		
					<tr class="msg-content <%=message.getMid()%>">
						<td colspan="5">留言内容</td>
						<td>留言时间：<%=message.getQtime().getDBString()%></td>
					</tr>
					<tr class="msg-content <%=message.getMid()%>">		
						<td colspan="6"><pre><%=message.getQuestion()%></pre></td>
					</tr>
					<tr class="msg-content <%=message.getMid()%>">
						<td colspan="5">回答内容</td>	
						<td>
							回答时间：
							<%
								if(message.getAtime()!=null)
								{
									 %>
									 	<span><%=message.getAtime().getDBString()%></span>
									 <% 
								}										
								else
								{
									 %>
									 	<span>管理员未回答</span>
									 <% 
								}						
							%>
						</td>
					</tr>
					<tr class="msg-content <%=message.getMid()%>">
						<td colspan="6">
							<%
								if(message.getAnswer()!=null)
								{
									 %>
									 	<pre><%=message.getAnswer() %></pre>
									 <% 
								}										
								else
								{
									 %>
									 	<span>管理员未回答</span>
									 <% 
								}						
							%>
						</td>		
					</tr>
					<%
				}
				%>
			</tbody>
		</table>
	</div>
	<div id="add-new-message"></div>
	<script src="public/js/message.js"></script>
</body>
</html>
