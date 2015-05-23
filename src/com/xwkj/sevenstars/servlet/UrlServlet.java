package com.xwkj.sevenstars.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xwkj.sevenstars.bean.Url;
import com.xwkj.sevenstars.dao.UrlDao;

@WebServlet("/UrlServlet")
public class UrlServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public UrlServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "get":
			get(request,response);
			break;
		case "set":
			set(request,response);
			break;
		case "get2":
			get2(request,response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

	private void get(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Url url=UrlDao.get();
		String print=url.getIp()+"$&$";
		print+=url.getPort()+"$&$";
		print+=url.getDbName()+"$&$";
		print+=url.getUserName()+"$&$";
		print+=url.getPassword()+"$&$";
		print+=url.getURL();
		response.getWriter().print(print);
	}
	
	private void set(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int name=Integer.parseInt(request.getParameter("name"));
		String value=request.getParameter("value");
		boolean success=UrlDao.set(name, value);
		response.getWriter().print(success);
	}
	
	private void get2(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Url url=UrlDao.get2();
		String print=url.getIp()+"$&$";
		print+=url.getPort()+"$&$";
		print+=url.getDbName()+"$&$";
		print+=url.getUserName()+"$&$";
		print+=url.getPassword()+"$&$";
		print+=url.getURL();
		response.getWriter().print(print);
	}
}
