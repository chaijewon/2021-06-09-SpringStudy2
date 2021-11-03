package com.sist.vo;
// JSP => Spring (일반 스프링)
// React(레시피 => 상세보기 , 쉐프) => 실시간 채팅 
// 간단한 챗봇 
/*
 *   SpringFrameWork
 *   스프링 (Container , DI , AOP , MVC, JSON(REST))
 *   MyBatis : DML => SELECT (JOIN ,SUBQUERY)
 *   =============================================
 *   Front : Ajax(Jquery) , Vue , React
 *   *** 지능형 웹 => 1주 (챗봇)
 */
/*
 *  CART_ID    NOT NULL NUMBER       
	ID                  VARCHAR2(20) 
	PRODUCT_ID          NUMBER       
	AMOUNT              NUMBER       
	REGDATE             DATE    
 */
import java.util.*;
public class CartVO {
    private int cart_id,product_id,amount,ischeck,issale;
    private String id;
    private Date regdate;
    
    public int getIssale() {
		return issale;
	}
	public void setIssale(int issale) {
		this.issale = issale;
	}
	public int getIscheck() {
		return ischeck;
	}
	public void setIscheck(int ischeck) {
		this.ischeck = ischeck;
	}
	private String product_name,product_poster,product_price;
    
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_poster() {
		return product_poster;
	}
	public void setProduct_poster(String product_poster) {
		this.product_poster = product_poster;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
  
}
