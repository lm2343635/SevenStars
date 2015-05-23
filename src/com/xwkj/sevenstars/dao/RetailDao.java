package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xwkj.sevenstars.bean.Retail;
import com.xwkj.sevenstars.util.SQLServer;

public class RetailDao 
{
	public static List<Retail> find(String start,String end,String idwarehouse,String idpartner)
	{
		String select="select AA_Inventory.DefaultBarCode AS code,AA_Inventory.name AS inventory,AA_Inventory.specification,"+
			" RE_Retail_b.createdtime,RE_Retail_b.basequantity as quantity,RE_Retail_b.price,RE_Retail_b.amount,AA_Warehouse.name as warehouse"+
			" from RE_Retail_b,AA_Inventory,RE_Retail,AA_DR_Store,AA_Warehouse"+
			" where RE_Retail_b.idinventory = AA_Inventory.id and RE_Retail_b.idRetailDTO = RE_Retail.id"+
			" and RE_Retail.idstore=AA_DR_Store.id and AA_DR_Store.idwarehouse=AA_Warehouse.id and AA_Warehouse.id='"+idwarehouse+"'"+
			" and AA_Inventory.idpartner='"+idpartner+"'";
		if(!start.equals(""))
			select+=" and RE_Retail_b.createdtime>='"+start+" 00:00:00'";
		if(!end.equals(""))
			select+=" and  RE_Retail_b.createdtime<='"+end+" 23:59:59'";
		select+=" order by AA_Inventory.DefaultBarCode";
		SQLServer db=new SQLServer();
		ResultSet rs=db.exeQuery(select);
		List<Retail> retails=new ArrayList<Retail>();
		try 
		{
			Retail retail;
			while(rs.next())
			{
				retail=new Retail();
				retail.setCode(rs.getString("code"));
				retail.setInventory(rs.getString("inventory"));
				retail.setQuantity(Double.parseDouble(rs.getString("quantity")));
				retail.setSpecification(rs.getString("specification"));
				retail.setWarehouse(rs.getString("warehouse"));
				retails.add(retail);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		db.close();
		return retails;
	}
	
	public static List<Retail> groupByCode(List<Retail> retails)
	{
		List<Retail> grouped=new ArrayList<Retail>();
		List<Retail> removes;
		while(retails.size()>0)
		{
			Retail group=retails.get(0);
			retails.remove(group);
			removes=new ArrayList<Retail>();
			for(Retail retail:retails)
			{
				if(retail.getCode().equals(group.getCode()))
				{
					group.setQuantity(group.getQuantity()+retail.getQuantity());
					removes.add(retail);
				}
			}
			for(Retail remove:removes)
				retails.remove(remove);
			grouped.add(group);
		}
		return grouped;
	}
}
