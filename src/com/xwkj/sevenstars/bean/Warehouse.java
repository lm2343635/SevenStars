package com.xwkj.sevenstars.bean;

public class Warehouse 
{
	private String id;
	private String code;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Warehouse() {
		super();
	}
	public Warehouse(String id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
}
