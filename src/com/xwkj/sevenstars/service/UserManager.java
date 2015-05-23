package com.xwkj.sevenstars.service;

import javax.servlet.http.HttpSession;

import com.xwkj.sevenstars.bean.User;
import com.xwkj.sevenstars.servlet.SessionServlet;

public class UserManager 
{
	public User getUsingUser(HttpSession session)
	{
		return SessionServlet.get(session);
	}
}
