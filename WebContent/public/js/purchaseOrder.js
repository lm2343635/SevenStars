$(document).ready(function() {
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
	
	$("#reset").click(function(){
		$("#start").val("");
		$("#end").val("");
		$("#code").val("");
	});
	
	$("#search").click(function(){
		var start=$("#start").val();
		var end=$("#end").val();
		var code=$("#code").val();
		showPopBox("#loading",300);
		PurchaseOrderManager.getPurchaseOrders(start,end,function(orders){
			$("#purchase-order tbody").empty();
			$("#item-number").text(orders.length);
			for(var i in orders)
			{
				var tr='<tr id="'+orders[i].id+'">'+
								'<td>'+orders[i].code+'</td>'+		
								'<td>'+orders[i].createdTime.str.split(" ")[0]+'</td>'+		
								'<td>'+orders[i].amount+'</td>'+		
								'<td>'+orders[i].person+'</td>'+		
								'<td>'+
									'<button onclick="showOrderDetails(\''+orders[i].id+'\')" class="btn btn-default btn-xs">'+
										'<span class="glyphicon glyphicon-eye-open"></span>'+
									'</button>'+
								'</td>'+		
							'</tr>';
				$("#purchase-order tbody").append(tr);
			}
			closeAll();
		});
	});
});

function showOrderDetails(oid) 
{
	$("#purchase-order-details tbody").empty();
	PurchaseOrderManager.getPurchaseOrderDetails(oid,function(details){
		for(var i in details)
		{
			var tr='<tr>'+
						   '<td>'+details[i].code+'</td>'+
				           '<td>'+details[i].inventory+'</td>'+
				           '<td>'+details[i].specification+'</td>'+
				           '<td>'+details[i].price+'</td>'+
				           '<td>'+details[i].quantity+'</td>'+
				           '<td>'+details[i].amount+'</td>'+
					   '<tr>';
			$("#purchase-order-details tbody").append(tr);
		}
		$("#purchase-order-details-modal").modal("show");
	});
}