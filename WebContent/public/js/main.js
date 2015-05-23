window.onload=checkSession();

$(document).ready(function(){//文档就绪
	$("#content").load("welcome.jsp");	
	setName();
	setClick();
});

function setClick()
{
	$('#menu > li > a').click(function(){
		$(this).next().slideToggle();
		return false;
	});	
	$('.menu-list > li > a').click(function(){
		checkSession();
		if($(this).attr('href') != '#'){
			$('#content').load( $(this).attr('href') );
		}
			return false;
	});
	$("#title").click(function(){
		location.reload(); 
	});
}

function setName()
{
	$.get("SessionServlet?task=get",null,function(data){
		if(data!="")
			$("#uname").html(data.split(",")[1]);
	});
}

//显示管理员资料
function userInfo()
{
	showPopBox("#userInfo",300);
}

function addNewMessage()
{
	$("#add-new-message").load("system/addMessage.html");
}