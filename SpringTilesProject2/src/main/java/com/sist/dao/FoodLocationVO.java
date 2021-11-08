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
	    private int no,locno,good,soso,bad;
	    private double score;
	    private String poster,name,address,tel,price,parking,time,menu;
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
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
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
	    
}
