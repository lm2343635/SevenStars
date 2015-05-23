package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xwkj.sevenstars.bean.DateTime;
import com.xwkj.sevenstars.bean.Message;
import com.xwkj.sevenstars.util.MySQL;

public class MessageDao 
{
	public static boolean insert(Message message)
	{
		String insert="insert into message(question,uid,qtime) values('"
				+message.getQuestion()+"','"+message.getUid()+"','"+message.getQtime().getDBString()+"')";
		return MySQL.update(insert);
	}
	
	public static ArrayList<Message> get(String where)
	{
		if(where==null)
			where="";
		String select="select mid,question,message.uid,uname,answer,qtime,atime "
			+"from message,user where message.uid=user.uid"+where;
		ArrayList<Message> messages=null;
		MySQL db=new MySQL();
		ResultSet rs=db.exeQuery(select);
		try
		{
			messages=new ArrayList<Message>();
			while(rs.next())
			{
				int mid=Integer.parseInt(rs.getString("mid"));
				String question=rs.getString("question");
				String uid=rs.getString("message.uid");
				String uname=rs.getString("uname");
				String answer=rs.getString("answer");
				String sQtime=rs.getString("qtime");
				String sAtime=rs.getString("atime");
				DateTime qtime=null;
				DateTime atime=null;
				if(sQtime!=null)
					qtime=new DateTime(sQtime.substring(0, sQtime.length()-2));
				if(sAtime!=null)
					atime=new DateTime(sAtime.substring(0,sAtime.length()-2));
				messages.add(new Message(mid, uid, uname, question, answer, qtime, atime));
			}
			db.close();
		} 
		catch (NumberFormatException | SQLException e) 
		{
			e.printStackTrace();
			db.close();
			return null;
		}
		return messages;
	}
	
	public static boolean answer(Message message)
	{
		String update="update message set answer='"+message.getAnswer()
				+"',atime='"+message.getAtime().getDBString()+"' where mid="+message.getMid();
		return MySQL.update(update);
	}
	
	public static boolean delete(int mid)
	{
		String delete="delete from message where mid="+mid;
		return MySQL.update(delete);
	}
	
	public static ArrayList<Message> getAnswered(String uid)
	{
		String where=" and answer is not null ";
		if(uid!=null)	
			where+=" and message.uid='"+uid+"'";
		return get(where);
	}
	
	public static ArrayList<Message> getUnAnswered(String uid)
	{
		String where=" and answer is null ";
		if(uid!=null)	
			where+=" and message.uid='"+uid+"'";
		return get(where);
	}
	
	public static Message get(int mid)
	{
		String where=" and mid="+mid;
		return get(where).get(0);
	}
	
	public static ArrayList<Message> getWithLimit(int limit,String uid)
	{
		String where="";
		if(uid!=null)	
			where+=" and message.uid='"+uid+"'";
		where+=" order by mid desc limit "+limit;
		return get(where);
	}
}
