package com.xwkj.sevenstars.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.xwkj.sevenstars.bean.Retail;
import com.xwkj.sevenstars.dao.RetailDao;
import com.xwkj.sevenstars.dao.UserDao;
import com.xwkj.sevenstars.servlet.SessionServlet;

public class RetailManager 
{
	public List<Retail> getRetails(String start,String end,String idwarehouse,HttpSession session)
	{
		String uid=SessionServlet.get(session).getUid();
		String idpartner=UserDao.getPartnerID(uid);
		List<Retail> retails=RetailDao.find(start, end, idwarehouse,idpartner);
		return RetailDao.groupByCode(retails);
	}
}
