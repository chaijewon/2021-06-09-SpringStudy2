package com.sist.dao;
/*
 *  NO     NOT NULL NUMBER         
POSTER          VARCHAR2(1000) 
TITLE           VARCHAR2(200)  
SINGER          VARCHAR2(100)  
ALBUM           VARCHAR2(200)  
OK              VARCHAR2(10)   
KEY             VARCHAR2(100) 
 */
public class MusicVO {
    private int no;
    private String poster,title,singer,album,key;
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
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
   
}
