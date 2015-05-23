package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xwkj.sevenstars.bean.User;
import com.xwkj.sevenstars.util.MySQL;
import com.xwkj.sevenstars.util.SQLServer;

public class UserDao 
{
	public static int ADMIN=0;
	public static int MULTIPLE=1;
	public static int FRANCHISEE=2;
	public static int PROVIDER=3;
	public static int WHOLESALE=4;
	public static int DIRECT=5;
	
	public static ArrayList<User> get(String where)
	{
		if(where==null)
			where="";
		String select="select * from user "+where;
		ArrayList<User> users=null;
		MySQL ms=new MySQL();
		ResultSet rs=ms.exeQuery(select);
		try 
		{
			users=new ArrayList<User>();
			while(rs.next())
			{
				String uid=rs.getString("uid");
				String uname=rs.getString("uname");
				String password=rs.getString("password");
				int privilege=Integer.parseInt(rs.getString("privilege"));
				users.add(new User(uid, uname, password, privilege));
			}
		} 
		catch (NumberFormatException | SQLException e) 
		{
			e.printStackTrace();
			ms.close();
		}	
		ms.close();
		return users;
	}
	
	public static boolean insert(User user)
	{
		String insert="insert into user values('"+user.getUid()+"','"+user.getUname()
				+"','"+user.getPassword()+"',"+user.getPrivilege()+")";
		return MySQL.update(insert);
	}
	
	public static boolean delete(int uid)
	{
		String delete="delete from user where uid="+uid;
		return MySQL.update(delete);
	}
	
	public static User get(int uid)
	{
		ArrayList<User> users=get("where uid="+uid);
		if(users!=null)
			return users.get(0);
		return null;
	}
	
	public static ArrayList<User> getByPrivilege(int privilege)
	{
		ArrayList<User> users=get("where privilege="+privilege);
		return users;
	}

	public static boolean isExist(String uid) 
	{
		String select="select * from user where uid="+uid;
		MySQL ms=new MySQL();
		ResultSet rs=ms.exeQuery(select);
		try 
		{
			if(rs.next())
			{
				ms.close();
				return true;
			}
			else
			{
				ms.close();
				return false;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			ms.close();
		}
		return false;
	}

	public static boolean isPasswordRight(String uid, String password) 
	{
		String select="select password from user where uid="+uid;
		MySQL ms=new MySQL();
		ResultSet rs=ms.exeQuery(select);
		try 
		{
			if(rs.next())
			{
				String rightPassword=rs.getString("password");
				ms.close();
				if(rightPassword.equals(password))
					return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			ms.close();
		}
		return false;
	}

	public static boolean modifyPassword(String uid, String password) 
	{
		String update="update user set password="+password+" where uid="+uid;
		return MySQL.update(update);
	}

	public static boolean modifyUser(int uid, String type, String value) 
	{
		String update="update user set "+type+"='"+value+"' where uid="+uid;
		return MySQL.update(update);
	}
	
	public static String getPartnerID(String uid)
	{
		String select="select id from AA_Partner where code='"+uid+"'";
		SQLServer db=new SQLServer();
		ResultSet rs=db.exeQuery(select);
		String id=null;
		try
		{
			if(rs.next())
				id=rs.getString("id");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		db.close();
		return id;
	}
}
