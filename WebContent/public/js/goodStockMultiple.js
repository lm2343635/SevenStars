$(document).ready(function(){
	GoodStockManager.getFirstLeveals(function(leveals){
		for(var i in leveals)
		{
			var option='<option value="'+leveals[i].id+'">'+leveals[i].name+'</option>';
			$("#first-leveal").append(option);
		}	
	});
	
	$("#search").click(function() {
		var name=$("#name").val();
		var firstLeveal=$("#first-leveal").val();
		showPopBox("#loading",300);
		GoodStockManager.getGoodStocks(name,firstLeveal,function(stocks){
			$("#good-stock tbody").empty();
			for(var i in stocks)
			{
				var tr='<tr>'+
					           '<td>'+stocks[i].code+'</td>'+
					           '<td>'+stocks[i].leveal+'</td>'+
							   '<td>'+stocks[i].name+'</td>'+
							   '<td>'+stocks[i].standard+'</td>'+
							   '<td>'+stocks[i].retailPrice+'</td>'+
							   '<td>'+stocks[i].quantity+'</td>'+
							   '<td>'+stocks[i].comment+'</td>'+
						   '</tr>';
				$("#good-stock tbody").append(tr);		
			}
			closeAll();
		});
	});
	
	$("#reset").click(function() {
		$("#name").val("");
		$("#first-leveal").val("");
	});
});