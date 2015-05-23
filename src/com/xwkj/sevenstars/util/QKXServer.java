package com.xwkj.sevenstars.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xwkj.sevenstars.dao.UrlDao;

public class QKXServer 
{
	Connection connection=null;
	Statement statement=null;
	
	public QKXServer()
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url=UrlDao.get2().getURL();
			connection=DriverManager.getConnection(url);
			statement=connection.createStatement();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ResultSet exeQuery(String sql)
	{
		ResultSet rs=null;
		try 
		{
			rs=statement.executeQuery(sql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rs;	
	}
	
	public int exeUpdate(String sql)
	{
		int flag=0;
		try 
		{
			flag=statement.executeUpdate(sql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	public void close()
	{
		try 
		{
			statement.close();
			connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static boolean update(String sql)
	{
		QKXServer db=new QKXServer();
		int row=db.exeUpdate(sql);
		db.close();
		if(row>0)
			return true;
		return false;
	}
}

