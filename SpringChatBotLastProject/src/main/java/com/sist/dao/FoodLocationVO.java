package com.sist.dao;
/*
 *  NO      NOT NULL NUMBER         
	LOCNO            NUMBER         
	POSTER  NOT NULL VARCHAR2(2000) 
	NAME    NOT NULL VARCHAR2(300)  
	SCORE   NOT NULL NUMBER(2,1)    
	ADDRESS NOT NULL VARCHAR2(1000) 
	TEL     NOT NULL VARCHAR2(20)   
	TYPE    NOT NULL VARCHAR2(100)  
	PRICE            VARCHAR2(100)  
	PARKING          VARCHAR2(100)  
	TIME             VARCHAR2(100)  
	MENU             VARCHAR2(4000) 
	GOOD             NUMBER         
	SOSO             NUMBER         
	BAD              NUMBER 
 */
public class FoodLocationVO {
    private int no;
    private String name,address,tel,type,poster;
    private double score;
	public int getNo() {
		return no;
	}
	
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	  
}
