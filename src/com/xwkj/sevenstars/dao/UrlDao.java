package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xwkj.sevenstars.bean.Url;
import com.xwkj.sevenstars.util.MySQL;

public class UrlDao 
{
	public static Url get()
	{
		MySQL db=new MySQL();
		String select="select * from url";
		ResultSet rs=db.exeQuery(select);
		Url url=new Url();
		try 
		{
			while(rs.next())
			{
				int name=Integer.parseInt(rs.getString("name"));
				String value=rs.getString("value");
				switch (name) 
				{
				case 0:
					url.setIp(value);
					break;
				case 1:
					url.setPort(value);
					break;
				case 2:
					url.setDbName(value);
					break;
				case 3:
					url.setUserName(value);
					break;
				case 4:
					url.setPassword(value);
					break;
				default:
					break;
				}
			}
			db.close();
		} 
		catch (NumberFormatException | SQLException e) 
		{
			e.printStackTrace();
			db.close();
		}
		return url;
	}
	
	public static Url get2()
	{
		MySQL db=new MySQL();
		String select="select * from url";
		ResultSet rs=db.exeQuery(select);
		Url url=new Url();
		try 
		{
			while(rs.next())
			{
				int name=Integer.parseInt(rs.getString("name"));
				String value=rs.getString("value");
				switch (name) 
				{
				case 5:
					url.setIp(value);
					break;
				case 6:
					url.setPort(value);
					break;
				case 7:
					url.setDbName(value);
					break;
				case 8:
					url.setUserName(value);
					break;
				case 9:
					url.setPassword(value);
					break;
				default:
					break;
				}
			}
			db.close();
		} 
		catch (NumberFormatException | SQLException e) 
		{
			e.printStackTrace();
			db.close();
		}
		return url;
	}
	
	public static boolean set(int name,String value)
	{
		String update="update url set value='"+value+"' where name="+name;
		return MySQL.update(update);
	}
	
	public static void main(String[] args) {
		System.out.println(get().getDbName());
	}
}
