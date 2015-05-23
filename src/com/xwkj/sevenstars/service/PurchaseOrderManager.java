package com.xwkj.sevenstars.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.xwkj.sevenstars.bean.PurchaseOrder;
import com.xwkj.sevenstars.bean.PurchaseOrderDetail;
import com.xwkj.sevenstars.dao.PurchaseOrderDao;
import com.xwkj.sevenstars.servlet.SessionServlet;

public class PurchaseOrderManager 
{
	public List<PurchaseOrder> getPurchaseOrders(String start,String end,HttpSession session)
	{
		String uid=SessionServlet.get(session).getUid();
		return PurchaseOrderDao.find(start, end, uid);
	}
	
	public List<PurchaseOrderDetail> getPurchaseOrderDetails(String idPurchaseOrder)
	{
		return PurchaseOrderDao.finDetails(idPurchaseOrder);
	}
}
