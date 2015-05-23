$(document).ready(function(){
	showPopBox("#new-message",430);
});

$("#new-msg-rst").click(function(){
	$("#question").val("");
});

$("#new-msg-sbt").click(function(){
	var question={"question":$("#question").val()};
	$.post("MessageServlet?task=add",question,function(data){
		if(data=="true")
		{
			alert("新增留言成功！");
			closeAll();
			$("#content").load("welcome.jsp");
		}
		else if(data=="sessionError")
		{
			alert("用户信息已过期，请重新登录！");
			location.href="sessionError.html";
		}		
		else
			alert("新增留言失败，请重试！");
	});
});