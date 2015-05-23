package com.xwkj.sevenstars.bean;

public class Url 
{
	private String ip;
	private String port;
	private String dbName;
	private String userName;
	private String password;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Url() 
	{
		super();
	}
	
	public Url(String ip, String port, String dbName, String userName,
			String password) 
	{
		super();
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
	}

	public String getURL()
	{
		String url="jdbc:sqlserver://"+ip+":"+port+";" +
				   "databaseName="+dbName+";user="+userName+";password="+password+";";
		return url;
	}
	
}
