function modifyPassword()
{
	$("#new-password").val('');
	$('#repeat-new-password').val('');
	modifyPassword();
}

$.get("SessionServlet?task=get",null,function(data){
	if(data!="")
	{
		var buffer=data.split(",");
		$("#userInfo .pop-title").text(buffer[1]+"的个人资料");
		$("#userInfo .pop-content .uid .userInfo-content").text(buffer[0]);
		$("#userInfo .pop-content .uname .userInfo-content").text(buffer[1]);
		$("#userInfo .pop-content .password .userInfo-content").text(buffer[2]);
	}	
});	

//修改密码
function modifyPassword()
{
	$("#modiPass-btn").css("display","none");
	$("#modiPass-input").css("display","inherit");
	$("#modiPass-cancel").on('click', function(){
		$("#modiPass-btn").css("display","inherit");
		$("#modiPass-input").css("display","none");
	});
	$("#modiPass-submit").on('click', function(){
		var password=$("#new-password").val();
		var rePwd = $('#repeat-new-password').val();
		if(password == rePwd){
			var data={"password":password};
			$.post("UserServlet?task=modifyPassword",data,function(data, status){
				if(data=="sessionError")
					location.href="sessionError.html";
				else if(data=="true")
				{
					$("#modiPass-input").hide();
					$("#modiPass-success").show();
					$("#new-password").val('');
					$('#repeat-new-password').val('');
					$("#userInfo .pop-content .password .userInfo-content").text(password);					
				}		
				else
				{
					$("#modiPass-input").hide();
					$("#modiPass-fail").show();
				}		
				setTimeout(function(){
					$("#modiPass-btn").show();
					$("#modiPass-fail").hide();
					$("#modiPass-success").hide();
				}, 2000);
			});	
		}else{
			alert('两次输入的密码不匹配');
		}
		
	});
}