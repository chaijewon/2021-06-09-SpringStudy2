package com.sist.vo;
/*
 *  NO      NOT NULL NUMBER         
	NAME    NOT NULL VARCHAR2(34)   
	SUBJECT NOT NULL VARCHAR2(4000) 
	CONTENT NOT NULL CLOB           
	PWD     NOT NULL VARCHAR2(10)   
	REGDATE          DATE           
	HIT              NUMBER  
 */
// 댓글 => 게시물 => 트랜잭션 적용 
import java.util.*;
// 댓글 직접 ==> 댓글 ...
public class FreeBoardVO {
    private int no,hit;
    private String name,subject,content,pwd,dbday;
    private Date regdate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	  
  
}










