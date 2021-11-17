package com.sist.dao;
/*
 *  NO                    NUMBER         
POSTER       NOT NULL VARCHAR2(260)  
TITLE        NOT NULL VARCHAR2(1000) 
CHEF         NOT NULL VARCHAR2(200)  
CHEF_POSTER           VARCHAR2(260)  
CHEF_PROFILE          VARCHAR2(300)  
INFO1        NOT NULL VARCHAR2(30)   
INFO2        NOT NULL VARCHAR2(30)   
INFO3        NOT NULL VARCHAR2(30)   
CONTENT               VARCHAR2(1000) 
FOODMAKE     NOT NULL CLOB           

 */
public class RecipeDetailVO {
    private int no;
    private String poster,title,chef,chef_poster,chef_profile,info1,info2,info3,content,foodmake;
	private String foodimg;
	
    public String getFoodimg() {
		return foodimg;
	}
	public void setFoodimg(String foodimg) {
		this.foodimg = foodimg;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getChef() {
		return chef;
	}
	public void setChef(String chef) {
		this.chef = chef;
	}
	public String getChef_poster() {
		return chef_poster;
	}
	public void setChef_poster(String chef_poster) {
		this.chef_poster = chef_poster;
	}
	public String getChef_profile() {
		return chef_profile;
	}
	public void setChef_profile(String chef_profile) {
		this.chef_profile = chef_profile;
	}
	public String getInfo1() {
		return info1;
	}
	public void setInfo1(String info1) {
		this.info1 = info1;
	}
	public String getInfo2() {
		return info2;
	}
	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	public String getInfo3() {
		return info3;
	}
	public void setInfo3(String info3) {
		this.info3 = info3;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFoodmake() {
		return foodmake;
	}
	public void setFoodmake(String foodmake) {
		this.foodmake = foodmake;
	}
  
}
