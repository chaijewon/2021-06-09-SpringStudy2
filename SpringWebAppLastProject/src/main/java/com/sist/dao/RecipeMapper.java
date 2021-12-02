package com.sist.dao;
import java.util.*;
/*
 *   NO     NOT NULL NUMBER         
TITLE  NOT NULL VARCHAR2(1000) 
POSTER NOT NULL VARCHAR2(260)  
CHEF   NOT NULL VARCHAR2(200) 
 */
import org.apache.ibatis.annotations.Select;
public interface RecipeMapper {
   @Select("SELECT no,title,poster,chef,num "
		  +"FROM (SELECT no,title,poster,chef,rownum as num "
		  +"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk) */ no,title,poster,chef "
		  +"FROM recipe)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<RecipeVO> recipeListData(Map amp);
   
   // 총페이지
   @Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
   public int recipeTotalPage();
   
   @Select("SELECT * FROM recipe_detail "
		  +"WHERE no=#{no}")
   public RecipeDetailVO recipeDetailData(int no);
   
   // 쉐프 목록 읽기
   /*
    *  CHEF      NOT NULL VARCHAR2(200) 
	POSTER    NOT NULL VARCHAR2(260) 
	MEM_CONT1          NUMBER        
	MEM_CONT3          NUMBER        
	MEM_CONT7          NUMBER        
	MEM_CONT2          NUMBER    
    */
   @Select("SELECT chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7,num "
		  +"FROM (SELECT chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7,rownum as num "
		  +"FROM (SELECT chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7 "
		  +"FROM chef ORDER BY mem_cont1 DESC)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<ChefVO> chefListData(Map map);
   
   @Select("SELECT CEIL(COUNT(*)/20.0) FROM chef")
   public int chefTotalPage();
   
   // 쉐프 관련 
   /*
    *   SELECT ==> 검색 
    *     = table 대신 사용 (인라인뷰) 
    *     = column 대신 사용 (스칼라 서브쿼리) : JOIN대체 
    *     = 순서 
    *       SELECT * | col1,col2
    *       FROM table|view|(SELECT~)
    *       [
    *          WHERE 
    *          GROUP BY
    *          HAVING
    *          ORDER BY
    *       ]
    */
   @Select("SELECT no,title,chef,poster,num "
		  +"FROM (SELECT no,title,chef,poster,rownum as num "
		  +"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no,title,chef,poster "
		  +"FROM recipe WHERE chef LIKE '%'||#{chef}||'%')) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<RecipeVO> chefMakeData(Map map);
   
   @Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe "
		  +"WHERE chef LIKE '%'||#{chef}||'%'")
   public int chefMakeTotalPage(String chef);
   
   
}










