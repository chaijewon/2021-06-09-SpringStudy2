package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface RecipeMapper {
   // 1. Recipe목록 (페이지)
   /*
    *   private int no,hit;
        private String title,poster,chef,link;
    */
   @Select("SELECT no,title,poster,chef,num "
		  +"FROM (SELECT no,title,poster,chef,rownum as num "
		  +"FROM (SELECT /*+INDEX_ASC(recipe recipe_no_pk)*/no,title,poster,chef "
		  +"FROM recipe)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<RecipeVO> recipeListData(Map map);
   // 2. 총페이지 
   @Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe")
   public int recipeTotalPage();
   // 3. RecipeDetail 
   /*
    *  NO                    NUMBER         
	POSTER       NOT NULL VARCHAR2(260)  
	TITLE        NOT NULL VARCHAR2(1000) 
	CHEF         NOT NULL VARCHAR2(200)  
	CHEF_POSTER           VARCHAR2(260)  
	CHEF_PROFILE          VARCHAR2(300)  
	INFO1        NOT NULL VARCHAR2(30)   
	INFO2        NOT NULL VARCHAR2(30)   
	INFO3        NOT NULL VARCHAR2(30)   
	CONTENT               VARCHAR2(1000) 
	FOODMAKE     NOT NULL CLOB  
    */
   @Select("SELECT * FROM recipe_detail "
		  +"WHERE no=#{no}")
   public RecipeDetailVO recipeDetailData(int no);
   // 4. 쉐프 출력 
   // 5. 쉐프 작품 보기 (페이지)
   // 6. 맛집 카테고리 
   // 7. 맛집 상세보기 (지도) => 코틀린 지도 처리 
   // 8. 여행 목록 => 상세보기 
   // ==> React , Redux(Hooks) , Kotlin
}








