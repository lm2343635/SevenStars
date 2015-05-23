$(document).ready(function(){
	closeAll();
	$(".panel-body").css("height",(getClientHeight()-250)+"px");
	$("textarea").css("height",(getClientHeight()-300)+"px");
});

$("#submit").click(function(){
	checkSession();
	var title=$("#title-input").val();
	var content=$("#content-input").val();
	var data=
		{
			"title":title,
			"content":content
		};
	$.post("DocumentServlet?task=insert",data,function(data){
		if(data==-1||data==null||data=="")
			alert("提交失败，请重试！！");
		else
		{
			$("#submit").hide();
			$("#finished").show();
			$('#file_upload').uploadify({
				'swf'      : 'public/imgs/uploadify.swf',
				'uploader' : 'DocumentServlet?task=upload&did='+data,
				'buttonText' : '单击选择上传文件'
			});
			$('#file_upload').show();
		}
	});
});