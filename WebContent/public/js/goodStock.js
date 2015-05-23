var PRIVILEGE_FRANCHISEE=2;
var PRIVILEGE_WHOLESALE=4;
var PRIVILEGE_DIRECT=5;

var FRANCHISEE_TH=["商品条码","商品编号","商品名称","规格","库存","单价","批发价","备注"];
var WHOLESALE_TH=["商品条码","商品编号","商品名称","规格","库存","单价","备注"];
var DIRECT_TH=["商品条码","商品编号","商品名称","规格","库存","单价","加盟价","连锁价","批发价","备注"];

$(document).ready(function(){
	GoodStockManager.getFirstLeveals(function(leveals){
		for(var i in leveals)
		{
			var option='<option value="'+leveals[i].id+'">'+leveals[i].name+'</option>';
			$("#first-leveal").append(option);
		}	
	});
	
	UserManager.getUsingUser(function(user){
		var _tr="#good-stock thead .head-tr";
		switch (user.privilege) 
		{
		case PRIVILEGE_FRANCHISEE:
			for(var i in FRANCHISEE_TH)
				$(_tr).append("<th>"+FRANCHISEE_TH[i]+"</th>");
			break;
		case PRIVILEGE_WHOLESALE:
			for(var i in WHOLESALE_TH)
				$(_tr).append("<th>"+WHOLESALE_TH[i]+"</th>");
			break;
		case PRIVILEGE_DIRECT:
			for(var i in DIRECT_TH)
				$(_tr).append("<th>"+DIRECT_TH[i]+"</th>");
			break;
		default:
			break;
		}
	});
	
	$("#search").click(function() {
		var name=$("#name").val();
		var firstLeveal=$("#first-leveal").val();
		showPopBox("#loading",300);
		UserManager.getUsingUser(function(user){
			GoodStockManager.getGoodStocks(name,firstLeveal,function(stocks){
				$("#good-stock tbody").empty();
				//alert(privilege);
				for(var i in stocks)
				{
					var tr;
					switch (user.privilege) 
					{
					case PRIVILEGE_FRANCHISEE:
						tr='<tr>'+
				           '<td>'+stocks[i].code+'</td>'+
				           '<td>'+stocks[i].leveal+'</td>'+
						   '<td>'+stocks[i].name+'</td>'+
						   '<td>'+stocks[i].standard+'</td>'+
						   '<td>'+stocks[i].quantity+'</td>'+
						   '<td>'+stocks[i].shareholderPrice+'</td>'+
						   '<td>'+stocks[i].retailPrice+'</td>'+
						   '<td>'+stocks[i].comment+'</td>'+
					   '</tr>';
						break;
					case PRIVILEGE_WHOLESALE:
						tr='<tr>'+
				           '<td>'+stocks[i].code+'</td>'+
				           '<td>'+stocks[i].leveal+'</td>'+
						   '<td>'+stocks[i].name+'</td>'+
						   '<td>'+stocks[i].standard+'</td>'+
						   '<td>'+stocks[i].quantity+'</td>'+
						   '<td>'+stocks[i].wholesalePrice+'</td>'+
						   '<td>'+stocks[i].comment+'</td>'+
					    '</tr>';
						break;
					case PRIVILEGE_DIRECT:
						tr='<tr>'+
				           '<td>'+stocks[i].code+'</td>'+
				           '<td>'+stocks[i].leveal+'</td>'+
						   '<td>'+stocks[i].name+'</td>'+
						   '<td>'+stocks[i].standard+'</td>'+
						   '<td>'+stocks[i].quantity+'</td>'+
						   '<td>'+stocks[i].preBuyPrice+'</td>'+
						   '<td>'+stocks[i].shareholderPrice+'</td>'+
						   '<td>'+stocks[i].retailPrice+'</td>'+
						   '<td>'+stocks[i].wholesalePrice+'</td>'+
						   '<td>'+stocks[i].comment+'</td>'+
					   '</tr>';
						break;
					default:
						break;
					}
					$("#good-stock tbody").append(tr);		
				}
				closeAll();
			});
		});
	});
	
	$("#reset").click(function() {
		$("#name").val("");
		$("#first-leveal").val("");
	});
});