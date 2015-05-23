$(document).ready(function(){
	closeAll();
	setScroll(220);
});

// 显示添加用户窗口
$('#add-user').click(function(){
	showPopBox("#new-user",550);
});

// 提交添加信息
$('#add-user-sbt').click(function(){
	var input=$("#add-new-user :text");
	var uid=input[0].value;
	var uname=input[1].value;
	var password=input[2].value;
	var privilege=$("#privilege").val();
	if(uid==""||uname==""||password=="")
		alert("请填写完整并填写合法的用户id（数字）后在提交！");
	else
	{
		var data=
			{
				"uid":uid,
				"uname":uname,
				"password":password,
				"privilege":privilege
			};
		$.post("UserServlet?task=add",data,function(data){
			if(data == 'true')
			{
				alert('提交成功!!');
				closeAll();
				if(privilege==1)
					$('#content').load('user/multiple.jsp');
				else if(privilege==2)
					$('#content').load('user/franchisee.jsp');
				else if(privilege==3)
					$('#content').load('user/provider.jsp');
				else if(privilege==4)
					$('#content').load('user/wholesale.jsp');
				else if(privilege==5)
					$('#content').load('user/direct.jsp');
			}
			else
				alert('提交失败，请检查用户id是否重复，且合法！');
		});		
	}
	return false;
});

// 删除某条记录
function deleteUser(uid)
{
	var str = confirm('是否确定删除'+uid+'号用户的信息?');
	if(str)
	{
		$.get('UserServlet?task=delete&uid='+uid, function(data){
			if(data == 'true')
				$("#"+uid).remove();
			else
				alert('操作失败,请重试!');
		});		
	}
}

function modifyOpen(uid)
{
	$("#old-uid").text(uid);
	$("#old-uname").text($("#"+uid+" .uname").text());
	$("#old-password").text($("#"+uid+" .password").text());
	showPopBox("#modify-user",550);
}

function modifyUser(type)
{
	var uid=$("#old-uid").text();
	var value=$("#modi-"+type).val();
	var data=
		{
			"uid":uid,
			"value":value,
			"type":type
		};
	$.post("UserServlet?task=modifyUser",data,function(data){
		if(data=="true")
		{
			closeAll();
			$("#"+uid+" ."+type).text(value);
			$("#modi-"+type).val("");
			tableWidth();
		}
		else
			alert("修改失败，请重试！");
	});
}