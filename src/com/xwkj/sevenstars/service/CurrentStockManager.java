package com.xwkj.sevenstars.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.xwkj.sevenstars.bean.CurrentStock;
import com.xwkj.sevenstars.dao.CurrentStockDao;
import com.xwkj.sevenstars.dao.UserDao;
import com.xwkj.sevenstars.servlet.SessionServlet;

public class CurrentStockManager 
{
	public List<CurrentStock> getCurrentStocks(String start,String end,String idwarehouse,HttpSession session)
	{
		String uid=SessionServlet.get(session).getUid();
		String idpartner=UserDao.getPartnerID(uid);
		return CurrentStockDao.find(start, end, idwarehouse,idpartner);
	}
}
