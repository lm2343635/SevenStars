function checkSession()
{
	$.get("SessionServlet?task=get",null,function(data){
		if(data=="")
			location.href="sessionError.html";
		else
			$("body").show();
	});
}

function isNum(num)
{
     var reNum =/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
     return (reNum.test(num));
}

function isInteger(num)
{
    var reNum =/^-?[1-9]\d*$/;
    return (reNum.test(num));
}

function loading(url)
{
	showPopBox("#loading",300);
	$("#content").load(url);
}

function getClientWidth()
{
	var iWidth = document.documentElement.clientWidth;
	return iWidth;
}

function getClientHeight()
{
	var iHeight = document.documentElement.clientHeight;
	return iHeight;
}

function setScroll(otherHeight)
{
	$(".showInfo-scroll").css("height",(getClientHeight()-otherHeight)+"px");
	tableWidth();
}

function tableWidth()
{
	var widths=[];
	var i=0;
	$(".scroll-tr:visible:first>td").each(function(){
		widths[i]=$(this).css("width");
		i++;
	});
	i=0;
	$(".head-tr:first>th").each(function(){
		$(this).css("width",widths[i]);
		i++;
	});
}
