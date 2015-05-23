package com.xwkj.sevenstars.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MySQL 
{
	Connection connection=null;
	Statement statement=null;
	
	public MySQL()
	{
		try
		{
//			Class.forName("com.mysql.jdbc.Driver");
//			String url="jdbc:mysql://127.0.0.1:3306/sevenstars?user=root&password=qikexing2014";
//			connection=DriverManager.getConnection(url);
//			statement=connection.createStatement();
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java://comp/env/jdbc/sevenstars");
			connection=ds.getConnection();
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
		MySQL ms=new MySQL();
		int row=ms.exeUpdate(sql);
		ms.close();
		if(row>0)
			return true;
		return false;
	}
}
