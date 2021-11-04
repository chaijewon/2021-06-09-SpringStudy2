package com.sist.vo;
// 동적쿼리 (찾기) => 트랜잭션 / 동적 쿼리 (어노테이션)
// 어노테이션 => 조인 / 서브쿼리 
import java.util.*;
public class ReplyBoardVO {
   /*
    *   group_id, => 답변별로 모아서 출력 
    *   group_step, => 답변 순서 (출력 순서)
    *   group_tab => 답변 타입(답변, 답변에 답변)
    *   ================================== 답변 출력용
    *   root, => 어느 게시물에 대한 답변인 여부 
    *   depth => 게시물별로 답변의 갯수
    *   ================================== 삭제용 
    */
    private int no,hit,group_id,group_step,group_tab,root,depth,bno;
    private String name,subject ,content,pwd,dbday,id;
    private Date regdate;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
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
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getGroup_step() {
		return group_step;
	}
	public void setGroup_step(int group_step) {
		this.group_step = group_step;
	}
	public int getGroup_tab() {
		return group_tab;
	}
	public void setGroup_tab(int group_tab) {
		this.group_tab = group_tab;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
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
