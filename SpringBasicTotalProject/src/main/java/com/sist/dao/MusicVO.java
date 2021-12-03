package com.sist.dao;
/*
 *  NO     NOT NULL NUMBER         
POSTER          VARCHAR2(1000) 
TITLE           VARCHAR2(200)  
SINGER          VARCHAR2(100)  
ALBUM           VARCHAR2(200)  
OK              VARCHAR2(10)   
KEY             VARCHAR2(100)

  SELECT no,poster,singer,title,album FROM melon_cjw;
         == ====== ======
         setNo() , setPoster() , setTitle()
 */
public class MusicVO {
    private int melon_no;
    private String melon_poster,melon_title,melon_singer,melon_album,melon_key;
	public int getMelon_no() {
		return melon_no;
	}
	public void setMelon_no(int melon_no) {
		this.melon_no = melon_no;
	}
	public String getMelon_poster() {
		return melon_poster;
	}
	public void setMelon_poster(String melon_poster) {
		this.melon_poster = melon_poster;
	}
	public String getMelon_title() {
		return melon_title;
	}
	public void setMelon_title(String melon_title) {
		this.melon_title = melon_title;
	}
	public String getMelon_singer() {
		return melon_singer;
	}
	public void setMelon_singer(String melon_singer) {
		this.melon_singer = melon_singer;
	}
	public String getMelon_album() {
		return melon_album;
	}
	public void setMelon_album(String melon_album) {
		this.melon_album = melon_album;
	}
	public String getMelon_key() {
		return melon_key;
	}
	public void setMelon_key(String melon_key) {
		this.melon_key = melon_key;
	}
	   
   
}
