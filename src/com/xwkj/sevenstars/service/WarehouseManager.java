package com.xwkj.sevenstars.service;

import java.util.List;

import com.xwkj.sevenstars.bean.Warehouse;
import com.xwkj.sevenstars.dao.WarehouseDao;

public class WarehouseManager 
{
	public List<Warehouse> getAllWarehouses()
	{
		return WarehouseDao.findAll();
	}

}
