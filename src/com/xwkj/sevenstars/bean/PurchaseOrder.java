package com.xwkj.sevenstars.bean;

public class PurchaseOrder 
{
	private String id;
	private String code;
	private DateTime createdTime;
	private double amount;
	private String person;
	
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
	public DateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(DateTime createdTime) {
		this.createdTime = createdTime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}

	public PurchaseOrder(String id, String code, DateTime createdTime,
			double amount, String person) {
		super();
		this.id = id;
		this.code = code;
		this.createdTime = createdTime;
		this.amount = amount;
		this.person = person;
	}
	public PurchaseOrder() 
	{
		super();
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(((PurchaseOrder)obj).getId()==this.getId())
			return true;	
		return false;
	}
	
	@Override
	public int hashCode() 
	{
		return id.hashCode()*13+code.hashCode()*29;
	}
}
