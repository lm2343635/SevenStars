package com.xwkj.sevenstars.bean;

public class PurchaseOrderDetail 
{
	private String code;
	private String inventory;
	private double quantity;
	private String specification;
	private double price;
	private double amount;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
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
	public PurchaseOrderDetail(String code, String inventory, double quantity,
			String specification, double price, double amount) {
		super();
		this.code = code;
		this.inventory = inventory;
		this.quantity = quantity;
		this.specification = specification;
		this.price = price;
		this.amount = amount;
	}
	public PurchaseOrderDetail() {
		super();
	}
}
