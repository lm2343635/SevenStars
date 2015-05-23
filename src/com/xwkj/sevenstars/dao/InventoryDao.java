package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xwkj.sevenstars.bean.DateTime;
import com.xwkj.sevenstars.bean.InventoryClass;
import com.xwkj.sevenstars.util.SQLServer;

public class InventoryDao 
{
	public static List<InventoryClass> findAllInventoryClass()
	{
		String select="select code,name,updated from AA_InventoryClass";
		SQLServer db=new SQLServer();
		ResultSet rs=db.exeQuery(select);
		List<InventoryClass> classes=new ArrayList<InventoryClass>();
		try 
		{
			while(rs.next())
			{
				String code=rs.getString("code");
				String name=rs.getString("name");
				DateTime updated=new DateTime(rs.getString("updated"));
				classes.add(new InventoryClass(code, name, updated));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		db.close();
		return classes;
	}

}
