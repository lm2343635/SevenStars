package com.xwkj.sevenstars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.xwkj.sevenstars.bean.FirstLeveal;
import com.xwkj.sevenstars.bean.GoodStock;
import com.xwkj.sevenstars.util.QKXServer;

public class GoodStockDao 
{
	public static List<GoodStock> find(String name,String firstLeveal)
	{
		DecimalFormat df = new DecimalFormat("#.00");
		String select="select ptype.EntryCode as code,ptype.FullName as name,ptype.Standard as standard,PType_Price.PreSalePrice4 as shareholderPrice, "+
			" PType_Price.PreSalePrice5 as retailPrice,PType_Price.PreSalePrice2 as unitPrice,PType_Price.PreSalePrice6 as wholesalePrice,PreBuyPrice2 as preBuyPrice,"+
			" ptype.Comment as comment,GoodsStocks.Qty as quantity,firstleveal.FullName as leveal,ptype.usercode as usercode"+
			" from GoodsStocks,ptype,PType_Price,ptype as firstleveal where firstleveal.typeId=ptype.ParId"+
			" and GoodsStocks.PtypeId=ptype.typeId and PType_Price.PTypeID=ptype.typeId and GoodsStocks.KtypeId='00003' ";
		if(!name.equals(""))
			select+="and ptype.FullName like '%"+name+"%'";
		if(!firstLeveal.equals(""))
			select+=" and ptype.ParId='"+firstLeveal+"'";
		select+=" order by ptype.usercode";
		QKXServer db=new QKXServer();
		ResultSet rs=db.exeQuery(select);
		List<GoodStock> stocks=new ArrayList<GoodStock>();
		try 
		{
			GoodStock stock;
			while(rs.next())
			{
				stock=new GoodStock();
				stock.setCode(rs.getString("code"));
				stock.setComment(rs.getString("comment"));
				stock.setName(rs.getString("name"));
				stock.setLeveal(rs.getString("usercode"));
				stock.setRetailPrice(Double.parseDouble(rs.getString("retailPrice")));
				stock.setShareholderPrice(Double.parseDouble(rs.getString("shareholderPrice")));
				stock.setUnitPrice(Double.parseDouble(rs.getString("unitPrice")));
				stock.setWholesalePrice(Double.parseDouble(rs.getString("wholesalePrice")));
				stock.setPreBuyPrice(Double.parseDouble(rs.getString("preBuyPrice")));
				stock.setStandard(rs.getString("standard"));
				double quantity=Double.parseDouble(rs.getString("quantity"));
				String standard=rs.getString("standard");
				if(!standard.equals("")&&standard!=null)
				{
					String [] rates=standard.split("[*]");
					for(String rate:rates)
						quantity/=Double.parseDouble(rate);
				}
				stock.setQuantity(Double.parseDouble(df.format(quantity)));
				stocks.add(stock);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		db.close();
		return stocks;
	}
	
	public static List<FirstLeveal> findFirstLeveal()
	{
		String select="select typeId as id,FullName as name,usercode from ptype where leveal=1 order by usercode";
		QKXServer db=new QKXServer();
		List<FirstLeveal> leveals=new ArrayList<FirstLeveal>();
		ResultSet rs=db.exeQuery(select);
		try 
		{
			while(rs.next())
			{
				String id=rs.getString("id");
				String usercode=rs.getString("usercode");
				String name=rs.getString("name");
				FirstLeveal firstLeveal=new FirstLeveal(id, name, usercode);
				leveals.add(firstLeveal);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		db.close();
		return leveals;
	}
}
