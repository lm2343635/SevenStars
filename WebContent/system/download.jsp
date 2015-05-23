<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.xwkj.sevenstars.dao.DocumentDao"%>
<%@page import="com.xwkj.sevenstars.bean.Document"%>
<%@page import="java.util.ArrayList"%>

<%
	ArrayList<Document> documents=DocumentDao.get(null);
%>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>上传文件管理</title>
	<link rel="stylesheet" href="public/css/download.css">
	<script type="text/javascript" src="public/js/download.js"></script>
</head>
<body>
	<p class="title">
		<span class="glyphicon glyphicon-leaf" style="margin-right: 5px;"></span>  <span style="color: #bbb;">\</span> 上传文件管理
		<span class="dtT">
			<span style="float: right;">共<span id="user-number"><%=documents.size()%></span>个文件</span>
		</span>			
	</p>
	<div class="showInfo show">
		<table class="table table-hover">
			<thead>
				<tr class="head-tr">
					<th>编号</th>
					<th>用户编号</th>
					<th>用户姓名</th>
					<th>标题</th>		
					<th>内容</th>
					<th>文件名</th>	
					<th>上传时间</th>		
					<th>下载</th>
					<th>删除</th>
				</tr>
			</thead>
		</table>	
	</div>	
	<div class="showInfo show showInfo-scroll">
		<table class="table table-hover" >	
			<tbody>
			<%
			for(Document document:documents)
			{
				%>
				<tr id="<%=document.getDid() %>" class="scroll-tr">
					<td><%=document.getDid() %></td>
					<td><%=document.getUid() %></td>
					<td><%=document.getUname() %></td>
					<td><%=document.getTitle(10) %></td>
					<td title="点击查看详细内容" class="dcontent" onclick="showContent(<%=document.getDid()%>,'<%=document.getTitle(30)%>')"><%=document.getContent(10) %></td>
					<td><%if(document.getFilename()!=null) out.print(document.getFilename());%></td>
					<td><%=document.getTime().getDBString()%></td>
					<td><span class="glyphicon glyphicon-download-alt" onclick="download(<%=document.getDid()%>)"></span></td>
					<td><span class="glyphicon glyphicon-trash" onclick="deleteDocument(<%=document.getDid()%>)"></span></td>
				</tr>
				<%
			}
			%>
			</tbody>
		</table>	
	</div>
</body>
</html>