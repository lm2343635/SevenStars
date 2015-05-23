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
		var warehouse=$("#warehouse").val();
		showPopBox("#loading",300);
		CurrentStockManager.getCurrentStocks(	"","",warehouse,function(stocks){
			$("#current-stock tbody").empty();
			for(var i in stocks)
			{
				var tr='<tr>'+
							   '<td>'+stocks[i].code+'</td>'+
							   '<td>'+stocks[i].inventory+'</td>'+
							   '<td>'+stocks[i].specification+'</td>'+
							   '<td>'+stocks[i].quantity+'</td>'+
							   '<td>'+stocks[i].warehouse+'</td>'+
						   '</tr>';
				$("#current-stock tbody").append(tr);
			}
			closeAll();
		});
	});
	
	$("#reset").click(function(){
		$("#start").val("");
		$("#end").val("");
		$("#warehouse").val("");
	});
});