package com.sist.vo;
/*
 *  PRODUCT_ID     NOT NULL NUMBER         
PRODUCT_NAME   NOT NULL VARCHAR2(1000) 
PRODUCT_PRICE  NOT NULL NUMBER         
PRODUCT_POSTER NOT NULL VARCHAR2(260)  
PRODUCT_DESC            CLOB           
PRODUCT_AMOUNT          NUMBER         

 */
public class GoodsVO {
    private int product_id,product_amount,product_price,no;
    private String product_name,product_poster,product_desc;
    
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getProduct_amount() {
		return product_amount;
	}
	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
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
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	
	  
}
