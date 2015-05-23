package com.xwkj.sevenstars.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xwkj.sevenstars.bean.User;
import com.xwkj.sevenstars.dao.UserDao;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public UserServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "delete":
			delete(request,response);
			break;
		case "checkUid":
			checkUid(request,response);
			break;
		case "checkPassword":
			checkPassword(request,response);
			break;
		default:
			break;
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "add":
			add(request,response);
			break;
		case "modifyPassword":
			modifyPassword(request,response);
			break;
		case "modifyUser":
			modifyUser(request,response);
			break;
		default:
			break;
		}
	}

	private void modifyPassword(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		User user=SessionServlet.get(request);
		if(user!=null)
		{
			String uid=user.getUid();
			String password=request.getParameter("password");
			boolean success=UserDao.modifyPassword(uid, password);
			if(success)
			{
				user.setPassword(password);
				session.removeAttribute("user");
				session.setAttribute("user", user);
			}
			out.print(success);
		}
		else
			out.print("sessionError");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int uid=Integer.parseInt(request.getParameter("uid"));
		boolean success=UserDao.delete(uid);
		response.getWriter().print(success);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		String uid=request.getParameter("uid");
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		int privilege=Integer.parseInt(request.getParameter("privilege"));
		User user=new User(uid, uname, password, privilege);
		boolean success=UserDao.insert(user);
		response.getWriter().print(success);
	}
	
	private void checkPassword(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String uid=request.getParameter("uid");
		if(!uid.equals(""))
		{
			String password=request.getParameter("password");
			boolean success=UserDao.isPasswordRight(uid, password);
			response.getWriter().print(success);
		}
		else
			response.getWriter().print(false);
	}

	private void checkUid(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{	
		String uid=request.getParameter("uid");
		if(!uid.equals(""))
		{
			boolean success=UserDao.isExist(uid);
			response.getWriter().print(success);
		} 
		else
			response.getWriter().print(false);
	}
	

	private void modifyUser(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		int uid=Integer.parseInt(request.getParameter("uid"));
		String type=request.getParameter("type");
		String value=request.getParameter("value");
		boolean success=UserDao.modifyUser(uid,type,value);
		response.getWriter().print(success);
	}
}
