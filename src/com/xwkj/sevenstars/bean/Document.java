package com.xwkj.sevenstars.bean;

public class Document 
{
	private int did;
	private String uid;
	private String uname;
	private String title;
	private String content;
	private String filename;
	private DateTime time;
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public String getTitle(int limit) 
	{
		if(title.length()>limit)
			return title.substring(0,limit-1)+"...";
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public String getContent(int limit) 
	{
		if(content.length()>limit)
			return content.substring(0,limit-1)+"...";
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public Document(int did, String uid, String uname, String title,
			String content, String filename, DateTime time) {
		super();
		this.did = did;
		this.uid = uid;
		this.uname = uname;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.time = time;
	}

	public Document(String uid, String title, String content, DateTime time) {
		super();
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.time = time;
	}
}
