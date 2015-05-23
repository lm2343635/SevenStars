package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xwkj.sevenstars.bean.DateTime;
import com.xwkj.sevenstars.bean.Document;
import com.xwkj.sevenstars.util.MySQL;

public class DocumentDao 
{
	public static ArrayList<Document> get(String where)
	{
		if(where==null)
			where="";
		String select="select did,document.uid,title,content,filename,time,uname from document,user where user.uid=document.uid "+where;
		MySQL db=new MySQL();
		ResultSet rs=db.exeQuery(select);
		ArrayList<Document> documents=null;
		try 
		{
			documents=new ArrayList<Document>();
			while(rs.next())
			{
				int did=Integer.parseInt(rs.getString("did"));
				String uid=rs.getString("uid");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String stime = rs.getString("time");
				stime = stime.substring(0, stime.length()-2);
				DateTime time=new DateTime(stime);
				String filename=rs.getString("filename");
				String uname=rs.getString("uname");
				documents.add(new Document(did, uid, uname, title, content, filename, time));
			}
			db.close();
		} 
		catch (NumberFormatException | SQLException e)
		{
			e.printStackTrace();
			db.close();
		}
		return documents;
	}
	
	public static Document get(int did)
	{
		String where=" and did="+did;
		return get(where).get(0);
	}
	
	public static int insert(Document document)
	{
		String insert="insert into document(uid,title,content,time) values('"+document.getUid()
				+"','"+document.getTitle()+"','"+document.getContent()+"','"+document.getTime().getDBString()+"')";
		boolean success=MySQL.update(insert);
		if(success)
		{
			String check="select did,time from document order by did desc limit 1";
			MySQL db=new MySQL();
			ResultSet rs=db.exeQuery(check);
			try 
			{
				if(rs.next())
				{
					int did=Integer.parseInt(rs.getString("did"));
					String stime=rs.getString("time");
					stime=stime.substring(0, stime.length()-2);
					db.close();
					if(stime.equals(document.getTime().getDBString()))
						return did;
				}
			} 
			catch (NumberFormatException | SQLException e) 
			{
				e.printStackTrace();
				db.close();
				return -1;
			}
		}
		return -1;
	}
	
	public static boolean writeName(String filename,int did)
	{
		String update="update document set filename='"+filename+"' where did="+did;
		return MySQL.update(update);
	}
	
	public static boolean delete(int did)
	{
		String delete="delete from document where did="+did;
		return MySQL.update(delete);
	}
}
