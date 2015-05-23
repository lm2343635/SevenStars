package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xwkj.sevenstars.bean.CurrentStock;
import com.xwkj.sevenstars.bean.DateTime;
import com.xwkj.sevenstars.util.SQLServer;

public class CurrentStockDao 
{
	public static List< CurrentStock> find(String start,String end,String idwarehouse,String idpartner)
	{
		String select="select AA_Inventory.DefaultBarCode "+
			" as code,AA_Inventory.specification,AA_Inventory.name as inventory,  "+
			" ST_CurrentStock.baseQuantity,ST_CurrentStock.createdTime,AA_Warehouse.name as warehouse  "+
			" from ST_CurrentStock,AA_Inventory,AA_Warehouse  "+
			" where ST_CurrentStock.idinventory=AA_Inventory.id and AA_Warehouse.id=ST_CurrentStock.idwarehouse "+
			" and AA_Inventory.idpartner='"+idpartner+"'";
		if(!start.equals(""))
			select+=" and ST_CurrentStock.createdTime>='"+start+" 00:00:00' ";
		if(!end.equals(""))
			select+=" and ST_CurrentStock.createdTime<='"+end+" 23:59:59' ";
		if(!idwarehouse.equals(""))
			select+=" and ST_CurrentStock.idwarehouse='"+idwarehouse+"'";
		select+=" order by  ST_CurrentStock.createdTime ";
		SQLServer db=new SQLServer();
		ResultSet rs=db.exeQuery(select);
		List< CurrentStock> stocks=new ArrayList<CurrentStock>();
		try
		{
			while(rs.next())
			{
				CurrentStock stock=new CurrentStock();
				stock.setCode(rs.getString("code"));
				stock.setSpecification(rs.getString("specification"));
				stock.setInventory(rs.getString("inventory"));
				stock.setQuantity(Double.parseDouble(rs.getString("baseQuantity")));
				stock.setCreatedTime(new DateTime(rs.getString("createdTime")));
				stock.setWarehouse(rs.getString("warehouse"));
				stocks.add(stock);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		db.close();
		return stocks;
	}
}
