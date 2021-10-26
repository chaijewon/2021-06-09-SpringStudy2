package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface FoodMapper {
   // cno:1~12 믿고 보는 맛집 리스트 
   // cno:13~18 지역별 맛집리스트 
   // cno:19~30 인기별 맛집리스트
   @Select({"<script> SELECT cno,title,poster,subject "
		  +"FROM project_food_category "
		  /*+"WHERE cno BETWEEN 1 AND 12"*/
		  +"<choose> "
		  +"<when test=\"no==1\">"
		  +"WHERE cno BETWEEN 1 AND 12"
		  +"</when> "
		  +"<when test=\"no==2\">"
		  +"WHERE cno BETWEEN 13 AND 18"
		  +"</when> "
		  +"<when test=\"no==3\">"
		  +"WHERE cno BETWEEN 19 AND 30"
		  +"</when> "
		  +"</choose>"
		  +"</script>"})
   public List<CategoryVO> food_categoryData(Map map);
   /*public List<CategoryVO> food_categoryData1();
   @Select("SELECT cno,title,poster,subject "
			  +"FROM project_food_category "
			  +"WHERE cno BETWEEN 13 AND 18")
   public List<CategoryVO> food_categoryData2();
   @Select("SELECT cno,title,poster,subject "
			  +"FROM project_food_category "
			  +"WHERE cno BETWEEN 19 AND 30")
   public List<CategoryVO> food_categoryData3();*/
}
