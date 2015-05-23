$(document).ready(function(){
	closeAll();
});

$("#add-message").click(function(){
	$("#add-new-message").load("system/addMessage.html");
});

function showMsgContent(mid)
{
	if($("."+mid).css("display")=="none")
	{
		$("."+mid).show('height');
		$("#showBt"+mid).removeClass("glyphicon-chevron-down");
		$("#showBt"+mid).addClass("glyphicon-chevron-up");
	}
	else
	{
		$("."+mid).hide('height');
		$("#showBt"+mid).addClass("glyphicon-chevron-down");
		$("#showBt"+mid).removeClass("glyphicon-chevron-up");
	}		
}

function deleteMsg(mid)
{
	if(confirm("是否确定删除"+mid+"号留言？"))
	$.get("MessageServlet?task=delete&mid="+mid,null,function(data){
		if(data=="true")
		{
			$("."+mid).remove();
			$("#"+mid).remove();
		}
		else
			alert("删除失败，请重试！");
	});
}

function answer(mid)
{
	$.get("MessageServlet?task=get&mid="+mid,null,function(data){
		var question=data.split("$&$")[1];
		var mid=data.split("$&$")[0];
		$("#answer-msg-mid").val(mid);
		$("#answer-message .pop-content pre").text(question);
		showPopBox("#answer-message",625);
	});
}

function reset()
{
	$("#answer").val("");
}

function submitAnswer()
{
	var answer=$("#answer").val();
	var mid=$("#answer-msg-mid").val();
	var data=
		{
			"answer":answer,
			"mid":mid
		};
	$.post("MessageServlet?task=answer",data,function(data){
		if(data=="true")
		{
			alert("回答成功！");
			closeAll();
			$("."+mid).remove();
			$("#"+mid).remove();
		}
		else
			alert("回答失败，请重试！！");
	});
}