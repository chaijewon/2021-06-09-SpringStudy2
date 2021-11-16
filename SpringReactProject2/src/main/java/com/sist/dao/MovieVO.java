package com.sist.dao;
/*
 *  MNO      NOT NULL NUMBER        
	CNO               NUMBER        
	TITLE    NOT NULL VARCHAR2(300) 
	REGDATE           VARCHAR2(100) 
	GENRE    NOT NULL VARCHAR2(200) 
	NATION            VARCHAR2(100) 
	GRADE    NOT NULL VARCHAR2(50)  
	TIME     NOT NULL VARCHAR2(30)  
	SCORE             NUMBER(2,1)   
	SHOWUSER          VARCHAR2(100) 
	POSTER   NOT NULL VARCHAR2(260) 
	STORY             CLOB  
 */
public class MovieVO {
    private int mno,cno;
    private String title,regdate,genre,nation,grade,time,showUser,poster,story;
    private double score;
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getShowUser() {
		return showUser;
	}
	public void setShowUser(String showUser) {
		this.showUser = showUser;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	   
}
