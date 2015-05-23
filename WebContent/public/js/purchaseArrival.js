$(document).ready(function(){
	$('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0,
        showMeridian: 1,
        language: 'zh-CN'
    });
	
	WarehouseManager.getAllWarehouses(function(warehouses){
		for(var i in warehouses)
		{
			var option='<option value="'+warehouses[i].id+'">'+warehouses[i].name+'</option>';
			$("#warehouse").append(option);
		}
	});
	
	$("#search").click(function(){
		var isStock=$('input[name="isStock"]:checked').val();
		var start=$("#start").val();
		var end=$("#end").val();
		var code=$("#code").val();
		var warehouse=$("#warehouse").val();
		showPopBox("#loading",300);
		PurchaseArrivalManager.getPurchaseArrivals(code,start,end,warehouse,isStock,function(arrivals){
			$("#purchase-arrival tbody").empty();
			$("#item-number").text(arrivals.length);
			for(var i in arrivals)
			{
				var tr='<tr id="'+arrivals[i].id+'">'+
								'<td>'+arrivals[i].warehouse+'</td>'+		
								'<td>'+arrivals[i].code+'</td>'+		
								'<td>'+arrivals[i].madedate.str.split(" ")[0]+'</td>'+		
								'<td class="amount">'+arrivals[i].totalAmount+'</td>'+		
								'<td>'+
									'<button onclick="showDetails(\''+arrivals[i].id+'\')" class="btn btn-default btn-xs">'+
										'<span class="glyphicon glyphicon-eye-open"></span>'+
									'</button>'+
								'</td>'+		
							'</tr>';
				$("#purchase-arrival tbody").append(tr);
			}
			closeAll();
		});
	});
	
	$("#reset").click(function(){
		$("#start").val("");
		$("#end").val("");
		$("#code").val("");
		$("#warehouse").val("");
	});
});

function showDetails(idPurchaseArrival)
{
	$("#purchase-arrival-details tbody").empty();
	$("#money-statistics").text(parseFloat($("#money-statistics").text())+parseFloat($("#"+idPurchaseArrival+" .amount").text()));
	PurchaseArrivalManager.getPurchaseArrivalDetails(idPurchaseArrival,function(details){	
		for(var i in details)
		{
			var present="否";
			if(details[i].isPresent==true)
				present="是";
			var tr='<tr>'+
				           '<td>'+details[i].iventory+'</td>'+
				           '<td>'+details[i].unit+'</td>'+
				           '<td>'+details[i].price+'</td>'+
				           '<td>'+details[i].quantity+'</td>'+
				           '<td>'+details[i].amount+'</td>'+
				           '<td>'+details[i].warehouse+'</td>'+
				           '<td>'+present+'</td>'+
					   '<tr>';
			$("#purchase-arrival-details tbody").append(tr);
		}		
	});
	$("#purchase-arrival-details-modal").modal("show");
}

function clearStatistics()
{
	$("#money-statistics").text(0);
}