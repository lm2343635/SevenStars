$(document).ready(function(){
	setWelcomeStyle();
});

$("#add-message").click(function(){
	$("#add-new-message").load("system/addMessage.html");
});

$("#answered-message").click(function(){
	$("#content").load("system/messageAnswered.jsp");
});

$("#unanswered-message").click(function(){
	$("#content").load("system/messageUnAnswered.jsp");
});

function setWelcomeStyle()
{
	var iWidth = $(window).width()*0.844;
	var iHeight = $(window).height()-112;
	var iPartWidth = (iWidth-60)/2 + "px";
	var iPartHeight = (iHeight-40)  + "px";
	var iPartWidth1 = (iWidth-60)/2+10 + "px";
	var iTop = (iHeight-40)/2 -45 + "px";
	var iLeft = (iWidth-60)/2 -45 + "px";
	var cssObj = 
	{
	  'width' : iPartWidth,
	  'height' : iPartHeight
	};
	$('.part').css(cssObj);	
	$('.part1').css({'position':'absolute','top':0,'left':0});
	$('.part2').css({'position':'absolute','top':0,'left':iPartWidth1});
	$('.box').css({'position':'absolute','top':iTop,'left':iLeft});
}

function showNotification(nid)
{
	$.get("NotificationServlet?task=getNotification&nid="+nid,null,function(data){
		var buffer=data.split("$&$&$");
		$("#notification .pop-title").text(buffer[0]);
		$("#notification .pop-content pre").html(buffer[1]);
		showPopBox("#notification",700); 
	});
}

function doAffair(){
	$('#content').load('teacher/doAffair.jsp');
}

function showMessage(mid)
{
	$.get("MessageServlet?task=get&mid="+mid,null,function(data){
		var buffer=data.split("$&$");
		$("#msg-mid").text(buffer[0]);
		$("#msg-question").text(buffer[1]);
		$("#msg-qtime").text(buffer[2]);
		$("#msg-uname").text(buffer[4]);
		if(buffer[5]==null||buffer[5]=="null")
		{
			$("#msg-atime").text("未回答");
			$("#msg-answer").text("未回答！");
		}
		else
		{
			$("#msg-atime").text(buffer[6]);
			$("#msg-answer").text(buffer[5]);
		}
		showPopBox("#message",700); 
	});
}