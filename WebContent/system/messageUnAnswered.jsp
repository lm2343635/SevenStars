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
	ArrayList<Message> messages=MessageDao.getUnAnswered(uid);
%>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>留言板（未回答）</title>
	<link rel="stylesheet" href="public/css/message.css">
</head>
<body>
	<p class="title">
		<span class="glyphicon glyphicon-leaf" style="margin-right: 5px;"></span>  <span style="color: #bbb;">\</span> 留言板（未回答）
	</p>
	<div class="showInfo show">
		<table class="table table-hover">
			<thead>
				<tr>
					<th colspan="6">未回答留言</th>			
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
									<button class="btn btn-xs" onclick="answer(<%=message.getMid()%>)" title="回答留言">
										<span class="glyphicon glyphicon-tags" ></span>
									</button>	
									<button class="btn btn-danger btn-xs" onclick="deleteMsg(<%=message.getMid()%>)" title="删除留言">
										<span class="glyphicon glyphicon-trash"></span>
									</button>
								<%
							}
							%>	
							<button class="btn btn-xs" onclick="showMsgContent(<%=message.getMid()%>)" title="查看详细内容">
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
					<%
				}
				%>
			</tbody>
		</table>
	</div>
	<div id="add-new-message"></div>
	<div class="pop-box" id="answer-message">
		<div class="pop-title">回答留言</div>
		<div class="pop-content" style="padding: 10px;">
			<pre></pre>
			<textarea id="answer" class="form-control" style="width:600px;margin: 5px 0;height:200px;"></textarea>
			<input type="hidden" id="answer-msg-mid">
			<input type="button" class="btn btn-default" style="width: 300px;" onclick="submitAnswer()" value="提交">
			<input type="reset" class="btn btn-warning" style="width: 300px;" onclick="reset()">
		</div>
	</div>
	<script src="public/js/message.js"></script>
</body>
</html>
