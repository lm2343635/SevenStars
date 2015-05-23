package com.xwkj.sevenstars.bean;

public class InventoryClass 
{
	private String code;
	private String name;
	private DateTime updated;
	
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
	public DateTime getUpdated() {
		return updated;
	}
	public void setUpdated(DateTime updated) {
		this.updated = updated;
	}
	public InventoryClass(String code, String name, DateTime updated) {
		super();
		this.code = code;
		this.name = name;
		this.updated = updated;
	}
}
