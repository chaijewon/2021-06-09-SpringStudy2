package com.sist.dao;
// SQL문장 => PreparedStatement / ResultSet 
// SqlSessionFactoryBean => Connection
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface FoodMapper {
    @Select("SELECT no,name,poster,type,address,num "
    	   +"FROM (SELECT no,name,poster,type,address,rownum as num "
    	   +"FROM (SELECT no,name,poster,type,address "
    	   +"FROM project_food_location "
    	   +"WHERE address LIKE '%'||#{address}||'%' ORDER BY no ASC)) "
    	   +"WHERE num BETWEEN #{start} AND #{end}")
    public List<FoodVO> foodFindData(Map map); // #{} => ?  : ?여러개 있는 경우 (~VO,Map)
    
    @Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food_location "
    	  +"WHERE address LIKE '%'||#{address}||'%'")
    public int foodTotalPage(String address);
    
    @Select("SELECT COUNT(*) FROM project_food_location "
      	  +"WHERE address LIKE '%'||#{address}||'%'")
    public int foodCount(String address);
    
    // 상세보기 
    @Select("SELECT * FROM project_food_location "
    	   +"WHERE no=#{no}")
    public FoodVO foodDetailData(int no);
}









