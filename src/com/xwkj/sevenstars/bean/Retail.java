package com.xwkj.sevenstars.bean;

public class Retail 
{
	private String code;
	private String specification;
	private String inventory;
	private double quantity;
	private String warehouse;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
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

	public Retail(String code, String specification, String inventory,
			double quantity, String warehouse) {
		super();
		this.code = code;
		this.specification = specification;
		this.inventory = inventory;
		this.quantity = quantity;
		this.warehouse = warehouse;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public Retail() 
	{
		super();
	};
}
