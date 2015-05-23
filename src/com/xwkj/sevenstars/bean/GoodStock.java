package com.xwkj.sevenstars.bean;

/**
 * @author limeng
 *
 */
public class GoodStock 
{
	private String code;
	private String name;
	private String leveal;
	private String standard;
	private double shareholderPrice;
	private double retailPrice;
	private double unitPrice;
	private double wholesalePrice;
	private double preBuyPrice;
	private double quantity;
	private String comment;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public double getShareholderPrice() {
		return shareholderPrice;
	}
	public void setShareholderPrice(double shareholderPrice) {
		this.shareholderPrice = shareholderPrice;
	}
	public double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getLeveal() {
		return leveal;
	}
	public void setLeveal(String leveal) {
		this.leveal = leveal;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getWholesalePrice() {
		return wholesalePrice;
	}
	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	public double getPreBuyPrice() {
		return preBuyPrice;
	}
	public void setPreBuyPrice(double preBuyPrice) {
		this.preBuyPrice = preBuyPrice;
	}
	public GoodStock() 
	{
		super();
	}
	public GoodStock(String code, String name, String leveal, String standard,
			double shareholderPrice, double retailPrice, double unitPrice,
			double wholesalePrice, double preBuyPrice, double quantity,
			String comment) {
		super();
		this.code = code;
		this.name = name;
		this.leveal = leveal;
		this.standard = standard;
		this.shareholderPrice = shareholderPrice;
		this.retailPrice = retailPrice;
		this.unitPrice = unitPrice;
		this.wholesalePrice = wholesalePrice;
		this.preBuyPrice = preBuyPrice;
		this.quantity = quantity;
		this.comment = comment;
	}

}
