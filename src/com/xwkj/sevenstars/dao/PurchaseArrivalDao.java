package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xwkj.sevenstars.bean.DateTime;
import com.xwkj.sevenstars.bean.PurchaseArrival;
import com.xwkj.sevenstars.bean.PurchaseArrivalDetail;
import com.xwkj.sevenstars.util.SQLServer;

public class PurchaseArrivalDao 
{
	public static List<PurchaseArrival> find(String code,String start,String end,String idwarehouse,String idpartner,boolean isStock)
	{
		String select="select PU_PurchaseArrival.id,PU_PurchaseArrival.code,PU_PurchaseArrival.madedate,totalAmount,AA_Warehouse.name "+
			"from PU_PurchaseArrival,AA_Warehouse "+
			"where PU_PurchaseArrival.idwarehouse=AA_Warehouse.id and idpartner='"+idpartner+"' ";
		if(isStock)
			select+=" and totalAmount>0";
		else
			select+=" and totalAmount<0";
		if(!start.equals(""))
			select+=" and PU_PurchaseArrival.madedate >= '"+start+" 00:00:00'";
		if(!end.equals(""))
			select+=" and PU_PurchaseArrival.madedate <= '"+end+" 23:59:59'";
		if(!idwarehouse.equals(""))
			select+=" and AA_Warehouse.id= '"+idwarehouse+"' ";
		if(!code.equals(""))
			select+=" and PU_PurchaseArrival.code like '%"+code+"%' ";
		select+=" order by PU_PurchaseArrival.madedate";
		SQLServer db=new SQLServer();
		ResultSet rs=db.exeQuery(select);
		List<PurchaseArrival> arrivals=new ArrayList<PurchaseArrival>();
		try 
		{
			while(rs.next())
			{
				PurchaseArrival arrival=new PurchaseArrival();
				arrival.setId(rs.getString("id"));
				arrival.setCode(rs.getString("code"));
				arrival.setMadedate(new DateTime(rs.getString("madedate")));
				arrival.setWarehouse(rs.getString("name"));
				arrival.setTotalAmount(Double.parseDouble(rs.getString("totalAmount")));
				arrivals.add(arrival);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		db.close();
		return arrivals;
	}
	
	public static List<PurchaseArrivalDetail> findDetail(String idPurchaseArrival)
	{
		String select="select AA_Inventory.name as inventory,AA_Unit.name as unit,discountPrice,discountAmount,AA_Warehouse.name as warehouse,quantity,isPresent "+
			"from PU_PurchaseArrival_b,AA_Inventory,AA_Unit,AA_Warehouse "+
			"where PU_PurchaseArrival_b.idinventory=AA_Inventory.id and PU_PurchaseArrival_b.idunit=AA_Unit.id  "+
			"and PU_PurchaseArrival_b.idwarehouse=AA_Warehouse.id and idPurchaseArrivalDTO='"+idPurchaseArrival+"'";
		SQLServer db=new SQLServer();
		ResultSet rs=db.exeQuery(select);	
		List<PurchaseArrivalDetail> arrivalDetails=new ArrayList<PurchaseArrivalDetail>();
		try 
		{
			while(rs.next())
			{
				PurchaseArrivalDetail arrivalDetail=new PurchaseArrivalDetail();
				arrivalDetail.setIventory(rs.getString("inventory"));
				arrivalDetail.setUnit(rs.getString("unit"));
				arrivalDetail.setPrice(Double.parseDouble(rs.getString("discountPrice")));
				arrivalDetail.setAmount(Double.parseDouble(rs.getString("discountAmount")));
				arrivalDetail.setWarehouse(rs.getString("warehouse"));
				arrivalDetail.setQuantity(Double.parseDouble(rs.getString("quantity")));
				arrivalDetails.add(arrivalDetail);
				if(rs.getString("isPresent").equals("0"))
					arrivalDetail.setPresent(true);
				else
					arrivalDetail.setPresent(false);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		db.close();
		return arrivalDetails;
	}
}
