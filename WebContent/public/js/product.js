var columns=["u_Code","u_Name","BarCode"];

$(document).ready(function(){
	closeAll();
	setScroll(280);
});

function searchProduct(column,privilege)
{
	showPopBox("#loading",300);
	var value=$("#"+column).val();
	if(column=="BarCode")
		column="Product."+column;
	var url="product/product.jsp?column="+column+"&value="+value+"&privilege="+privilege;
	$("#product-content").load(url);
}

function showSecondLevel(firstLevel)
{
	if($("."+firstLevel).css("display")=="none")
		$("."+firstLevel).show('height');
	else
	{
		$("."+firstLevel).hide('height');
		$(".product"+firstLevel).hide('height');
	}		
}

function showProduct(secondLevel)
{
	if($("."+secondLevel).css("display")=="none")
		$("."+secondLevel).show('height');
	else
		$("."+secondLevel).hide('height');
	tableWidth();
}

function showAll()
{
	$(".second-level").show('height');
	$(".product").show('height');
	tableWidth();
}

function showHasProdNum()
{
	alert("a");
	$(".product").each(function(){
		alert($(this).children()[3].text());
	});
}