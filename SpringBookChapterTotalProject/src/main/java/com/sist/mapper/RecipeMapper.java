package com.sist.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

/*
 *  private int no,hit;
    private String title,poster,chef,link;
 */
import com.sist.vo.*;
import java.util.*;
public interface RecipeMapper {
   @SelectKey(keyProperty="no",resultType=int.class,before=true,
		   statement="SELECT NVL(MAX(no)+1,1) as no FROM recipe") // 시퀀스 
   @Insert("INSERT INTO recipe VALUES(#{no},#{title},#{poster},#{chef},#{link},0)")
   /*
    *   #{} => vo , map
    *   #{변수명} => vo일 경우에는 getXxx()
    *   #{변수명} => map일 경우에는 get("키")
    */
   public void recipeInsert(RecipeVO vo);
   /*
    *    INSERT INTO recipe VALUES(#{no},vo.getTitle(),vo.getPoster,vo.getChef(),vo.getLink(),0)
    */
   /*
    *   CHEF      NOT NULL VARCHAR2(200) 
	POSTER    NOT NULL VARCHAR2(260) 
	MEM_CONT1          NUMBER        
	MEM_CONT3          NUMBER        
	MEM_CONT7          NUMBER        
	MEM_CONT2          NUMBER  
    */
   @Insert("INSERT INTO chef VALUES(#{chef},#{poster},#{mem_cont1},#{mem_cont3},#{mem_cont7},"
		  +"#{mem_cont2})")
   public void chefInsert(ChefVO vo);
   
   /*
    *   NO                    NUMBER         
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
   @Insert("INSERT INTO recipe_detail VALUES("
		  +"#{no},#{poster},#{title},#{chef},"
		  +"#{chef_poster},#{chef_profile},#{info1},"
		  +"#{info2},#{info3},#{content},#{foodmake},#{foodimg})")
   public void recipeDetailInsert(RecipeDetailVO vo);
   
   @Select("SELECT no,link FROM recipe")
   public List<RecipeVO> recipeLinkData();
}









