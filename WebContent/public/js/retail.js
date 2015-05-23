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
		var start=$("#start").val();
		var end=$("#end").val();
		var warehouse=$("#warehouse").val();
		if(warehouse==""||warehouse==null)
			alert("请选择仓库，仓库为必选条件！");
		else
		{
			showPopBox("#loading",300);
			RetailManager.getRetails(start,end,warehouse,function(retails){
				$("#retail tbody").empty();
				for(var i in retails)
				{
					var tr='<tr>'+
					               '<td>'+retails[i].warehouse+'</td>'+
								   '<td>'+retails[i].code+'</td>'+
								   '<td>'+retails[i].inventory+'</td>'+
								   '<td>'+retails[i].specification+'</td>'+
								   '<td>'+retails[i].quantity+'</td>'+
							   '</tr>';
					$("#retail tbody").append(tr);
				}
				closeAll();
			});
		}
	});
	
	$("#reset").click(function(){
		$("#start").val("");
		$("#end").val("");
		$("#warehouse").val("");
	});
});