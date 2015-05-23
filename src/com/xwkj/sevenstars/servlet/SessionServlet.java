package com.xwkj.sevenstars.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xwkj.sevenstars.bean.User;
import com.xwkj.sevenstars.dao.UserDao;

@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public SessionServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "set":
			set(request,response);
			break;
		case "remove":
			remove(request,response);
			break;
		case "get":
			get(request,response);
			break;
		case "isAdmin":
			isAdmin(request,response);
			break;
		default:
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	private void get(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		User user=get(request);
		String show="";
		if(user!=null)
			show=user.showMe();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(show);
	}

	private void remove(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		HttpSession session=request.getSession();
		User user=get(request);
		if(user!=null)
			session.removeAttribute("user");	
		response.sendRedirect("index.html");
	}

	private void set(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		HttpSession session=request.getSession();
		int uid=Integer.parseInt(request.getParameter("uid"));
		User user=UserDao.get(uid);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		response.getWriter().print(true);
	}
	
	private void isAdmin(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		boolean isAdmin=isAdmin(request);
		response.getWriter().print(isAdmin);
	}

	public static boolean isAdmin(HttpServletRequest request)
	{
		User user=get(request);
		return user.getPrivilege()==0;
	}
	
	public static User get(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		return get(session);
	}
	
	public static User get(HttpSession session)
	{
		if(session.getAttribute("user")!=null)
			return (User)session.getAttribute("user");
		return null;
	}
}
