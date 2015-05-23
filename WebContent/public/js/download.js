$(document).ready(function(){
	closeAll();
	setScroll(215);
});

function showContent(did,title)
{
	$.get("DocumentServlet?task=getContent&did="+did,null,function(data){
		if(data=="fasle")
			alert("加载失败，请重试！");
		else
		{
			$("#notification .pop-title").text(title);
			$("#notification .pop-content pre").html(data);
			showPopBox("#notification",700); 
		}
	});
}

function download(did)
{
	$.get("DocumentServlet?task=downloadTest&did="+did,null,function(data){
		if(data=="fileExist")
			location.href="DocumentServlet?task=download&did="+did;
		else if(data=="fileNotFound")
			alert("文件不存在！");
		else if(data=="fileNameNotFound")
			alert("文件名不存在！");
		else if(data=="itemNotFound")
			alert("未发现该项！");
		else
			alert("未知错误，请重试！！");
	});
}

function deleteDocument(did)
{
	if(confirm("确认删除第"+did+"号文件记录？"))
		$.get("DocumentServlet?task=delete&did="+did,null,function(data){
			if(data=="true")
			{
				$("#"+did).remove();
				$("#user-number").text(parseInt($("#user-number").text())-1);
			}
			else
				alert("删除失败，请重试！");
		});
}