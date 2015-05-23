package com.xwkj.sevenstars.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xwkj.sevenstars.bean.DateTime;
import com.xwkj.sevenstars.bean.Message;
import com.xwkj.sevenstars.bean.User;
import com.xwkj.sevenstars.dao.MessageDao;
import com.xwkj.sevenstars.util.DateTool;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public MessageServlet() 
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
		case "get":
			get(request,response);
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
		case "answer":
			answer(request,response);
			break;
		default:
			break;
		}
	}

	private void answer(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		request.setCharacterEncoding("UTF-8");
		int mid=Integer.parseInt(request.getParameter("mid"));
		String answer=request.getParameter("answer");
		DateTime atime=new DateTime(DateTool.getSystemTime());
		Message message=new Message(answer, mid, atime);
		boolean sucess=MessageDao.answer(message);
		response.getWriter().print(sucess);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String question=request.getParameter("question");
		DateTime qtime=new DateTime(DateTool.getSystemTime());
		User user=SessionServlet.get(request);
		if(user!=null)
		{
			Message message=new Message(user.getUid(), question, qtime);
			boolean success=MessageDao.insert(message);
			out.print(success);
		}
		else
			out.print("sessionError");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		int mid=Integer.parseInt(request.getParameter("mid"));
		boolean success=MessageDao.delete(mid);
		response.getWriter().print(success);
	}
	
	private void get(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int mid=Integer.parseInt(request.getParameter("mid"));
		Message message=MessageDao.get(mid);
		String print=message.getMid()+"$&$";
		print+=message.getQuestion()+"$&$";
		print+=message.getQtime().getDBString()+"$&$";
		print+=message.getUid()+"$&$";
		print+=message.getUname()+"$&$";
		print+=message.getAnswer()+"$&$";
		if(message.getAtime()!=null)
			print+=message.getAtime().getDBString();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(print);
	}
}
