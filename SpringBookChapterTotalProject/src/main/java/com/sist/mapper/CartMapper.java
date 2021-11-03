package com.sist.mapper;
import java.util.*;
/*
 *  PRODUCT_ID     NOT NULL NUMBER         
	PRODUCT_NAME   NOT NULL VARCHAR2(1000) 
	PRODUCT_PRICE  NOT NULL NUMBER         
	PRODUCT_POSTER NOT NULL VARCHAR2(260)  
	PRODUCT_DESC            CLOB           
	PRODUCT_AMOUNT          NUMBER 
 */
import com.sist.vo.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
public interface CartMapper {
   @SelectKey(keyProperty="product_id",resultType=int.class,before=true,
		   statement="SELECT NVL(MAX(product_id)+1,1) as product_id FROM goods")
   @Insert("INSERT INTO goods(product_id,product_name,product_price,product_poster,product_desc) "
		  +"VALUES(#{product_id},#{product_name},#{product_price},#{product_poster},#{product_desc})")
   public void goodsInsert(GoodsVO vo);
   
   // 상품목록 출력 
   @Select("SELECT product_id,product_name,product_poster,product_price,num "
		  +"FROM (SELECT product_id,product_name,product_poster,product_price,rownum as num "
		  +"FROM (SELECT /*+ INDEX_ASC(goods goods_id_pk)*/ product_id,product_name,product_poster,product_price "
		  +"FROM goods)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<GoodsVO> goodsListData(Map map);
   
   @Select("SELECT CEIL(COUNT(*)/20.0) FROM goods")
   public int goodsTotalPage();
   // 상세보기 
   @Select("SELECT product_id,product_name,product_poster,product_price,product_amount "
		  +"FROM goods "
		  +"WHERE product_id=#{product_id}")
   public GoodsVO goodsDetailData(int product_id);
   // 장바구니 등록 
   /*
    *       CART_ID    NOT NULL NUMBER       
			ID                  VARCHAR2(20) 
			PRODUCT_ID          NUMBER       
			AMOUNT              NUMBER       
			REGDATE             DATE
    */
   @Insert("INSERT INTO cart VALUES("
		  +"(SELECT NVL(MAX(cart_id)+1,1) FROM cart),#{id},#{product_id},#{amount},SYSDATE,0,0)")
   public void cartInsert(CartVO vo);
   // 마이페이지 => 취소/결제 => 결제(이메일로 전송)
   @Select("SELECT /*+ INDEX_DESC(cart cart_id_pk)*/ cart_id,id,amount,ischeck,issale,"
		  +"(SELECT product_name FROM goods WHERE goods.product_id=cart.product_id) as product_name,"
		  +"(SELECT product_poster FROM goods WHERE goods.product_id=cart.product_id) as product_poster,"
		  +"(SELECT product_price FROM goods WHERE goods.product_id=cart.product_id) as product_price "
		  +"FROM cart "
		  +"WHERE id=#{id} "
		  +"AND regdate>=SYSDATE-3 AND regdate<=SYSDATE")
   public List<CartVO> cartListData(String id);
   
   // 구매요청 
   @Update("UPDATE cart SET "
		  +"issale=1 "
		  +"WHERE cart_id=#{cart_id}")
   public void cartSaleUpdate(int cart_id);
   // 구매취소 
   @Delete("DELETE FROM cart "
		  +"WHERE cart_id=#{cart_id}")
   public void cartSaleDelete(int cart_id);
   // 어드민 페이지에서 결제 
   @Select("SELECT /*+ INDEX_DESC(cart cart_id_pk)*/ cart_id,id,amount,ischeck,issale,"
			  +"(SELECT product_name FROM goods WHERE goods.product_id=cart.product_id) as product_name,"
			  +"(SELECT product_poster FROM goods WHERE goods.product_id=cart.product_id) as product_poster,"
			  +"(SELECT product_price FROM goods WHERE goods.product_id=cart.product_id) as product_price "
			  +"FROM cart "
			  +"WHERE issale=1") // 구매를 요청한 데이터만 읽는다 
   public List<CartVO> cartAdminListData();
   
   @Update("UPDATE cart SET "
		  +"ischeck=1 "
		  +"WHERE cart_id=#{cart_id}")
   public void goodsAdminYes(int cart_id);
   
   @Select("SELECT /*+ INDEX_DESC(cart cart_id_pk)*/ cart_id,id,amount,ischeck,issale,"
			  +"(SELECT product_name FROM goods WHERE goods.product_id=cart.product_id) as product_name,"
			  +"(SELECT product_poster FROM goods WHERE goods.product_id=cart.product_id) as product_poster,"
			  +"(SELECT product_price FROM goods WHERE goods.product_id=cart.product_id) as product_price "
			  +"FROM cart "
			  +"WHERE cart_id=#{cart_id}")
   public CartVO cartYesData(int cart_id);
			  
}








