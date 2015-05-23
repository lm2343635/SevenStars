package com.xwkj.sevenstars.service;

import java.util.List;

import com.xwkj.sevenstars.bean.FirstLeveal;
import com.xwkj.sevenstars.bean.GoodStock;
import com.xwkj.sevenstars.dao.GoodStockDao;

public class GoodStockManager 
{
	public List<GoodStock> getGoodStocks(String name,String firstLeveal)
	{
		return GoodStockDao.find(name,firstLeveal);
	}
	
	public List<FirstLeveal> getFirstLeveals()
	{
		return GoodStockDao.findFirstLeveal();
	}
}
