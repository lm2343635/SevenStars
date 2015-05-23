package com.xwkj.sevenstars.bean;

public class PurchaseArrival 
{
	private String id;
	private String code;
	private DateTime madedate;
	private double totalAmount;
	private String warehouse;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public DateTime getMadedate() {
		return madedate;
	}
	public void setMadedate(DateTime madedate) {
		this.madedate = madedate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public PurchaseArrival() {
		super();
	}
	public PurchaseArrival(String id, String code, DateTime madedate,
			double totalAmount, String warehouse) {
		super();
		this.id = id;
		this.code = code;
		this.madedate = madedate;
		this.totalAmount = totalAmount;
		this.warehouse = warehouse;
	}
}
