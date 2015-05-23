package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xwkj.sevenstars.bean.Warehouse;
import com.xwkj.sevenstars.util.SQLServer;

public class WarehouseDao 
{
	public static List<Warehouse> findAll()
	{
		String select="select id,code,name from AA_Warehouse";
		SQLServer db=new SQLServer();
		ResultSet rs=db.exeQuery(select);
		List<Warehouse> warehouses=new ArrayList<Warehouse>();
		try 
		{
			while(rs.next())
			{
				Warehouse warehouse=new Warehouse();
				warehouse.setId(rs.getString("id"));
				warehouse.setCode(rs.getString("code"));
				warehouse.setName(rs.getString("name"));
				warehouses.add(warehouse);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		db.close();
		return warehouses;
	}

}
