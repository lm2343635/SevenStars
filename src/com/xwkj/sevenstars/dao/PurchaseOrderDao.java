package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xwkj.sevenstars.bean.DateTime;
import com.xwkj.sevenstars.bean.PurchaseOrder;
import com.xwkj.sevenstars.bean.PurchaseOrderDetail;
import com.xwkj.sevenstars.util.SQLServer;

public class PurchaseOrderDao 
{
	public static List<PurchaseOrder> find(String start,String end,String uid)
	{
		String idpartner=UserDao.getPartnerID(uid);
		String select="select PU_PurchaseOrder.id,PU_PurchaseOrder.code,PU_PurchaseOrder.createdtime,"+
			" PU_PurchaseOrder.origTotalAmount,AA_Warehouse.name as person"+
			" from PU_PurchaseOrder,AA_Warehouse"+
			" where PU_PurchaseOrder.code not in(select sourceVoucherCode from PU_PurchaseArrival  where sourceVoucherCode!='')"+
			" and PU_PurchaseOrder.idwarehouse = AA_Warehouse.id and PU_PurchaseOrder.idpartner='"+idpartner+"'";
		if(!end.equals(""))
			select+=" and PU_PurchaseOrder.createdtime>='"+start+" 00:00:00'";
		if(!end.equals(""))
			select+=" and PU_PurchaseOrder.createdtime<='"+end+" 23:59:59'";
		SQLServer db=new SQLServer();
		List<PurchaseOrder> orders=new ArrayList<PurchaseOrder>();
		ResultSet rs=db.exeQuery(select);
		try 
		{
			while(rs.next())
			{
				PurchaseOrder order=new PurchaseOrder();
				order.setAmount(Double.parseDouble(rs.getString("origTotalAmount")));
				order.setCode(rs.getString("code"));
				order.setCreatedTime(new DateTime(rs.getString("createdtime")));
				order.setId(rs.getString("id"));
				order.setPerson(rs.getString("person"));
				orders.add(order);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		db.close();
		return orders;
	}
	
	public static List<PurchaseOrderDetail> finDetails(String idPurchaseOrder)
	{
		String select="select PU_PurchaseOrder_b.inventoryBarCode as code,AA_Inventory.name as inventory,PU_PurchaseOrder_b.quantity,AA_Inventory.specification,"+
			" PU_PurchaseOrder_b.discountPrice as price,PU_PurchaseOrder_b.discountAmount as amount"+
			" from PU_PurchaseOrder_b,AA_Inventory "+
			" where PU_PurchaseOrder_b.idinventory=AA_Inventory.id "+
			" and idPurchaseOrderDTO='"+idPurchaseOrder+"' ";
		List<PurchaseOrderDetail> details=new ArrayList<PurchaseOrderDetail>();
		SQLServer db=new SQLServer();
		ResultSet rs=db.exeQuery(select);
		try 
		{
			while(rs.next())
			{
				PurchaseOrderDetail detail=new PurchaseOrderDetail();
				detail.setAmount(Double.parseDouble(rs.getString("amount")));
				detail.setCode(rs.getString("code"));
				detail.setInventory(rs.getString("inventory"));
				detail.setPrice(Double.parseDouble(rs.getString("price")));
				detail.setQuantity(Double.parseDouble(rs.getString("quantity")));
				detail.setSpecification(rs.getString("specification"));
				details.add(detail);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		db.close();
		return details;
	}
}
