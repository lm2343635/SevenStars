package com.xwkj.sevenstars.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.xwkj.sevenstars.bean.PurchaseArrival;
import com.xwkj.sevenstars.bean.PurchaseArrivalDetail;
import com.xwkj.sevenstars.dao.PurchaseArrivalDao;
import com.xwkj.sevenstars.dao.UserDao;
import com.xwkj.sevenstars.servlet.SessionServlet;

public class PurchaseArrivalManager 
{
	public List<PurchaseArrival> getPurchaseArrivals(String code,String start,String end,String idwarehouse,boolean isStock, HttpSession session)
	{
		String uid=SessionServlet.get(session).getUid();
		String idpartner=UserDao.getPartnerID(uid);
		return PurchaseArrivalDao.find(code, start, end, idwarehouse,idpartner,isStock);
	}
	
	public List<PurchaseArrivalDetail> getPurchaseArrivalDetails(String idPurchaseArrival,HttpSession session)
	{
		return PurchaseArrivalDao.findDetail(idPurchaseArrival);
	}
}
