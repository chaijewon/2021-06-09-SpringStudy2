package com.sist.dao;
/*
 *   VO => Mapper => DAO => FoodController(연결) => JSP (개발자 소스코딩)
 *                  Jsoup : 실시간 날씨,뉴스 
 *                  Open API : 데이터 수집 (통계)
 *                  채봇 : 통계,분석
 *                  JSON (만드는 방법 / 파싱 방법) = simple-json
 *                  ========================== 속도가 빠른 Front (VueJS,ReactJS)
 */
/*
 *   NO      NOT NULL NUMBER         
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
public class FoodVO {
    private int no,locno,good,soso,bad;
    private String poster,name,address,tel,type,price,parking,time,menu;
    private double score;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getLocno() {
		return locno;
	}
	public void setLocno(int locno) {
		this.locno = locno;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getSoso() {
		return soso;
	}
	public void setSoso(int soso) {
		this.soso = soso;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
    
}
