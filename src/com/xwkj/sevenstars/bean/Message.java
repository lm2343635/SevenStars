package com.xwkj.sevenstars.bean;

public class Message 
{
	private int mid;
	private String uid;
	private String uname;
	private String question;
	private String answer;
	private DateTime qtime;
	private DateTime atime;
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getQuestion() {
		return question;
	}
	
	public String getQuestion(int limit) 
	{
		if(question.length()>limit)
			return question.substring(0,limit-1)+"...";
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	
	public String getAnswer(int limit)
	{
		if(question.length()>limit)
			return answer.substring(0,limit-1)+"...";
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public DateTime getQtime() {
		return qtime;
	}
	public void setQtime(DateTime qtime) {
		this.qtime = qtime;
	}
	public DateTime getAtime() {
		return atime;
	}
	public void setAtime(DateTime atime) {
		this.atime = atime;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public Message(int mid, String uid, String uname, String question,
			String answer, DateTime qtime, DateTime atime)
	{
		super();
		this.mid = mid;
		this.uid = uid;
		this.uname = uname;
		this.question = question;
		this.answer = answer;
		this.qtime = qtime;
		this.atime = atime;
	}
	
	public Message(String uid, String question, DateTime qtime) 
	{
		super();
		this.uid = uid;
		this.question = question;
		this.qtime = qtime;
	}
	
	public Message(String answer,int mid, DateTime atime) 
	{
		super();
		this.mid = mid;
		this.answer = answer;
		this.atime = atime;
	}
}
