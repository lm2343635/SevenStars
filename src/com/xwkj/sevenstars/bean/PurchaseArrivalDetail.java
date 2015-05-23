package com.xwkj.sevenstars.bean;

public class PurchaseArrivalDetail 
{
	private String iventory;
	private String unit;
	private String warehouse;
	private double price;
	private double amount;
	private double quantity;
	private boolean isPresent;
	
	public String getIventory() {
		return iventory;
	}
	public void setIventory(String iventory) {
		this.iventory = iventory;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public boolean isPresent() {
		return isPresent;
	}
	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	public PurchaseArrivalDetail() {
		super();
	}
	
	public PurchaseArrivalDetail(String iventory, String unit,
			String warehouse, double price, double amount, double quantity,boolean isPresent) 
	{
		super();
		this.iventory = iventory;
		this.unit = unit;
		this.warehouse = warehouse;
		this.price = price;
		this.amount = amount;
		this.quantity = quantity;
		this.isPresent=isPresent;
	}
}
