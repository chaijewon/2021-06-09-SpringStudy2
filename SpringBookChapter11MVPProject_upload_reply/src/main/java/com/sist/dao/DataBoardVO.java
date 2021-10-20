package com.sist.dao;
import java.time.LocalDateTime;
/*
 *  NO        NOT NULL NUMBER         
NAME      NOT NULL VARCHAR2(34)   
SUBJECT   NOT NULL VARCHAR2(1000) 
CONTENT   NOT NULL CLOB           
PWD       NOT NULL VARCHAR2(10)   
REGDATE            DATE           
HIT                NUMBER         
FILENAME           VARCHAR2(4000) 
FILESIZE           VARCHAR2(4000) 
FILECOUNT          NUMBER        

   셋팅 => VO(DAO) => Controller => JSP
 */
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
/*
 *    <input type=text name=name size=10>  => String name;
 *    
 *    <input type=text name=hobby>   => String[] hobby
 *    <input type=text name=hobby>
 *    <input type=text name=hobby>
 *    
 *    <input type=text name=hobby[0]> => List<String> hobby
 *    <input type=text name=hobby[1]>
 *    <input type=text name=hobby[2]>
 */
public class DataBoardVO {
    private int no,filecount,hit;
    private String name,subject,content,pwd,filename,filesize;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date regdate;
    // 파일 목록을 받는다 
    private List<MultipartFile> files;
    
    
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getFilecount() {
		return filecount;
	}
	public void setFilecount(int filecount) {
		this.filecount = filecount;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
   
}






