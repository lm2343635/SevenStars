<%@page import="com.xwkj.sevenstars.dao.UserDao"%>
<%@page import="com.xwkj.sevenstars.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%
	ArrayList<User> providers=UserDao.getByPrivilege(UserDao.PROVIDER);
%>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>供货商管理</title>
	<link rel="stylesheet" href="public/css/userMgr.css">
</head>
<body>
	<p class="title">
		<span class="glyphicon glyphicon-leaf" style="margin-right: 5px;"></span>  <span style="color: #bbb;">\</span> 供货商管理
		<span class="dtT">
			<button id="add-user" class="btn btn-default btn-xs" style="float: right;">
				<span class="glyphicon glyphicon-plus"></span>
			</button>
			<span style="float: right;">共<span id="user-number"><%=providers.size()%></span>位</span>
		</span>			
	</p>
	<div class="showInfo show">
		<table class="table table-hover">
			<thead>
				<tr class="head-tr">
					<th>供货商编号</th>
					<th>供货商姓名</th>
					<th>供货商密码</th>		
					<th>删除</th>		
					<th>修改</th>
				</tr>
			</thead>
		</table>	
	</div>	
	<div class="showInfo show showInfo-scroll">
		<table class="table table-hover" >	
			<tbody>
			<%
			for(User provider:providers)
			{
				%>
				<tr id="<%=provider.getUid() %>" class="scroll-tr">
					<td><%=provider.getUid() %></td>
					<td class="uname"><%=provider.getUname() %></td>
					<td class="password"><%=provider.getPassword() %></td>
					<td><span class="glyphicon glyphicon-trash" onclick="deleteUser('<%=provider.getUid()%>')"></span></td>
					<td><span class="glyphicon glyphicon-tags" onclick="modifyOpen('<%=provider.getUid()%>')"></span></td>
				</tr>
				<%
			}
			%>
			</tbody>
		</table>	
	</div>
	<div class="pop-box" id="new-user">
		<div class="pop-title"><p>添加供货商</p></div>
		<div class="pop-content">
			<form id="add-new-user" >
				<table>
					<tr>
						<td><span>供货商编号：</span></td>
						<td><input name="uid" type="text" class="form-control" style="width:380px;margin: 5px 0;"></td>
					</tr>
					<tr>
						<td><span>供货商姓名：</span></td>
			            <td><input name="uname"  type="text" class="form-control" style="width:380px;margin: 5px 0;"></td>
					</tr>
					<tr>
						<td><span>登录密码：</span></td>
						<td><input name="password" type="text" class="form-control" style="width:380px;margin: 5px 0;"></td>
					</tr>	
				</table>
				<input type="hidden" id="privilege" value="3">
				<input type="submit" class="btn btn-default" id="add-user-sbt" style="width: 224px;">
				<input type="reset" class="btn btn-warning" style="width: 224px;">
			</form>	
		</div>
	</div>	
	<div class="pop-box" id="modify-user">
		<div class="pop-title"><p>修改供货商</p></div>
		<div class="pop-content">
			<table>
				<tr>
					<td><span>供货商编号：</span></td>
		            <td><span id="old-uid"></span></td>
				</tr>
				<tr>
					<td><span>原供货商姓名：</span></td>
		            <td><span id="old-uname"></span></td>
				</tr>
				<tr>
					<td><span>修改供货商姓名：</span></td>
		            <td><input id="modi-uname"  type="text" class="form-control" style="width:300px;margin: 5px ;"></td>
		            <td><button class="btn btn-primary"  onclick="modifyUser('uname')">确认修改</button></td>
				</tr>
				<tr>
					<td><span>原登录密码：</span></td>
					<td><span id="old-password"></span></td>
				</tr>
				<tr>
					<td><span>修改登录密码：</span></td>
					<td><input id="modi-password" type="text" class="form-control" style="width:300px;margin: 5px ;"></td>
					<td><button class="btn btn-primary" onclick="modifyUser('password')">确认修改</button></td>
				</tr>		
			</table>
		</div>
	</div>	
	<script src="public/js/userMgr.js"></script>
</body>
</html>


