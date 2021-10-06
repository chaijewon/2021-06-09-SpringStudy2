package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
// 스프링에서 메모리 할당 => @Autowired를 사용한다 
@Repository
public class FoodDAO {
   // 스프링으로부터 구현된 클래스의 주소를 자동 주입
   @Autowired
   private FoodMapper mapper;
   
   /*@Select("SELECT no,name,poster,type,num "
    	   +"FROM (SELECT no,name,poster,type,rownum as num "
    	   +"FROM (SELECT no,name,poster,type "
    	   +"FROM project_food_location "
    	   +"WHERE address LIKE '%'||#{address}||'%' ORDER BY no ASC)) "
    	   +"WHERE num BETWEEN #{start} AND #{end}")*/
    public List<FoodVO> foodFindData(Map map)
    {
    	return mapper.foodFindData(map);
    }
    
    /*@Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food_location "
    	  +"WHERE address LIKE '%'||#{address}||'%'")*/
    public int foodTotalPage(String address)
    {
    	return mapper.foodTotalPage(address);
    }
    
    /*@Select("SELECT COUNT(*) FROM project_food_location "
      	  +"WHERE address LIKE '%'||#{address}||'%'")*/
    public int foodCount(String address)
    {
    	return mapper.foodCount(address);
    }
    /*
     * @Select("SELECT * FROM project_food_location "
    	      +"WHERE no=#{no}")
     */
    // 실행된 결과를 받아서 JSP로 전송 ==> Model
    public FoodVO foodDetailData(int no)
    {
    	return mapper.foodDetailData(no);
    }
}
