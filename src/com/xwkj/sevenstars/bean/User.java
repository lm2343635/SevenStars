package com.xwkj.sevenstars.bean;

public class User 
{
	private String uid;
	private String uname;
	private String password;
	private int privilege;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPrivilege() {
		return privilege;
	}
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}
	
	public User(String uid, String uname, String password, int privilege) 
	{
		super();
		this.uid = uid;
		this.uname = uname;
		this.password = password;
		this.privilege = privilege;
	}
	
	public User(String uname, String password, int privilege) 
	{
		super();
		this.uname = uname;
		this.password = password;
		this.privilege = privilege;
	}
	
	public String showMe()
	{
		return uid+","+uname+","+password+","+privilege;
	}
}
