package com.xwkj.sevenstars.bean;

public class FirstLeveal 
{
	private String id;
	private String name;
	private String usercode;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public FirstLeveal(String id, String name, String usercode) {
		super();
		this.id = id;
		this.name = name;
		this.usercode = usercode;
	}
	public FirstLeveal() {
		super();
	}
	
	
}
