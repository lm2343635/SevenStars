var aCheckbox = document.getElementsByTagName('span');
for(var i = 0; i < aCheckbox.length; i++){
	aCheckbox[i].onclick = function(){
		for(var j = 0; j < aCheckbox.length; j++){
			aCheckbox[j].className = '';
		}
		this.className = 'checked';
	};
}

$("#uid").blur(function(){
	var uid=$("#uid").val();
	$.get("UserServlet?task=checkUid&uid="+uid,null,function(data){
		if(data=="false")
		{
			$('.username').addClass('new');
			$('.first-enter-item').addClass('new1');
		}
		else
		{
			$('.username').removeClass('new');
			$('.first-enter-item').removeClass('new1');
		}
	});
});

$("#uid").focus(function(){
	$('.username').removeClass('new');
	$('.username').addClass('old');
	$('.first-enter-item').removeClass('new1');
	$('.first-enter-item').addClass('old1');
});

$("#login").click(function(){
	var ar=[];
	$("input[type=password]").each(function(){
		ar.push($(this).val());
	});
	var uid=ar[0];
	var password=ar[1];
	$.get("UserServlet?task=checkPassword&password="+password+"&uid="+uid,null,function(data){
		if(data=="false")
		{
			$('.pwd').addClass('new');
			$('.last-enter-item').addClass('new1');
		}
		else
		{
			$.get("SessionServlet?task=set&uid="+uid,null,function(data){
				if(data=="true")
				{
					$.get("SessionServlet?task=get",null,function(data){
						if(data!="")
						{
							var privilege=parseInt(data.split(",")[3]);
							switch (privilege) 
							{
							case 0:
								location.href="main.html";
								break;
							case 1:
								location.href="multiple.html";
								break;
							case 2:
								location.href="franchisee.html";
								break;
							case 3:
								location.href="provider.html";
								break;
							case 4:
								location.href="wholesale.html";
								break;	
							case 5:
								location.href="direct.html";
								break;	
							default:
								break;
							}
						}
					});
				}
			});	
		}		
	});
});

$(".last-enter-item").focus(function(){
	$('.pwd').removeClass('new');
	$('.pwd').addClass('old');
	$('.last-enter-item').removeClass('new1');
	$('.last-enter-item').addClass('old1');
});