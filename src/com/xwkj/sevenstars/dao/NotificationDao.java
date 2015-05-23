package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xwkj.sevenstars.bean.DateTime;
import com.xwkj.sevenstars.bean.Notification;
import com.xwkj.sevenstars.util.MySQL;

public class NotificationDao
{
	public static int ALL=0;
	public static int MULTIPLE=1;
	public static int FRANCHISEE=2;
	public static int PROVIDER=3;
	public static int AGRICULTURAL=4;
	
	/**
	 * 插入一条通知
	 * @param notification 通知
	 * @return 插入是否成功
	 */
	public static boolean insert(Notification notification) 
	{
		String insert="insert into notification(title,content,publish,privilege) values('"+notification.getTitle()
				+"','"+notification.getContent()+"','"+notification.getPublish().getDBString()+"',"+notification.getPrivilege()+")";
		return MySQL.update(insert);
	}
	
	/**
	 * 删除一条通知
	 * @param nid 消息id号
	 * @return 删除是否成功
	 */
	public static boolean delete(int nid) 
	{
		String delete="delete from notification where nid="+nid;
		return MySQL.update(delete);
	}
	
	/**
	 * 查询通知
	 * @param where 查询条件
	 * @return
	 */
	public static ArrayList<Notification> getNotification(String where)
	{
		if(where==null)
			where="";
		ArrayList<Notification> notifications=null;
		String select="select * from notification "+where;
		MySQL db=new MySQL();
		ResultSet rs=db.exeQuery(select);
		try
		{
			notifications=new ArrayList<Notification>();
			while(rs.next())
			{
				int nid=Integer.parseInt(rs.getString("nid"));
				String title=rs.getString("title");
				String content=rs.getString("content");
				String sPublish = rs.getString("publish");
				sPublish = sPublish.substring(0, sPublish.length()-2);
				DateTime publish = new DateTime(sPublish);
				int privilege=Integer.parseInt(rs.getString("privilege"));
				notifications.add(new Notification(nid,title,content,publish,privilege));
			}
			db.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			db.close();
			return null;
		}
		return notifications;
	}
	
	/**
	 * 从数据库获取所有通知
	 * @return 所有通知
	 */
	public static ArrayList<Notification> getNotification(int privilege)
	{
		String where="";
		if(privilege!=0)
			where=" where privilege=0 or privilege="+privilege;
		return getNotification(where);
	}
	
	/**
	 * 获取limit条通知
	 * @param limit
	 * @return
	 */
	public static ArrayList<Notification> getNotification(int privilege,int limit)
	{
		String where="";
		if(privilege!=0)
			where=" where privilege=0 or privilege="+privilege;
		where+=" order by publish desc limit "+limit;
		return getNotification(where);
	}
	
	/**
	 * 得到指定id号的通知
	 * @param nid 通知id号
	 * @return
	 */
	public static Notification getNotificationByNid(int nid)
	{
		String where=" where nid="+nid;
		return getNotification(where).get(0);
	}
}
