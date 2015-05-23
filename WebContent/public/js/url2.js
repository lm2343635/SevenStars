var disps=["ip-disp","port-disp","dbName-disp","userName-disp","password-disp"];
var inputs=["ip-input","port-input","dbName-input","userName-input","password-input"]

$(document).ready(function(){
	setUrl();
});

function setUrl()
{
	$.get("UrlServlet?task=get2",null,function(data){
		var buffer=data.split("$&$");
		for(var i=0;i<disps.length;i++)
			$("#"+disps[i]).text(buffer[i]);
	});
}

function modifyUrl(name)
{
	var value=$("#"+inputs[name-5]).val();
	if(value=="")
		alert("填写完再提交！");
	else
		$.get("UrlServlet?task=set&name="+name+"&value="+value,null,function(data){
			if(data=="true")
			{
				alert("修改成功！");
				$("#"+disps[name-5]).text(value);
				$("#"+inputs[name-5]).val("");
			}
			else
				alert("修改失败，请重试！");
		});
}