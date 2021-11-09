package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
/*
 *   서버 : 스프링 (DAO)
 *   화면 출력 : Front-End 
 *            ========= 화면 변경없이 데이터를 바꿔준다 
 *                      Ajax(Jquery) , VueJS , ReactJS  => 50:50
 */
public interface RecipeMapper {
  // 1. 레시피 목록 (16만개)
  /*
   *   CREATE INDEX idx명 ON table명(컬럼명)
   *   ================================== 사용자 정의 
   *   자동으로 인덱스가 생성 
   *          : UNIQUE , PRIMARY KEY
   */
  @Select("SELECT no,title,poster,chef,num "
		 +"FROM (SELECT no,title,poster,chef,rownum as num "
		 +"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk) */ no,title,poster,chef "
		 +"FROM recipe)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
  // 구현 요청 => 메소드를 설정 
  // 1. 결과값 , 2. ?가 있는 경우에 어떤 데이터가 첨부 (매개변수)
  public List<RecipeVO> recipeListData(Map map); // Map => 여러개 데이터를 한번에 추가 (VO에 없는 변수 일경우에 사용)
  // 총페이지 
  @Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe")
  public int recipeTotalPage();
  
  // 레시피 총갯수 읽기
  @Select("SELECT COUNT(*) FROM recipe")
  public int recipeCount();
  /*
   *    NO                    NUMBER         
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
  // 2. 레시피 상세보기 
  @Select("SELECT * FROM recipe_detail "
		 +"WHERE no=#{no}")
  public RecipeDetailVO recipeDetailData(int no);
  // 3. 쉐프 목록
  /*
   *  CHEF      NOT NULL VARCHAR2(200) 
	POSTER    NOT NULL VARCHAR2(260) 
	MEM_CONT1          NUMBER        
	MEM_CONT3          NUMBER        
	MEM_CONT7          NUMBER        
	MEM_CONT2          NUMBER 
   */
  @Select("SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,num "
		+"FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,rownum as num "
		+"FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2 "
		+"FROM chef ORDER BY mem_cont1 DESC)) "
		+"WHERE num BETWEEN #{start} AND #{end}")
  public List<ChefVO> chefListData(Map map);
  
  @Select("SELECT CEIL(COUNT(*)/50.0) FROM chef")
  public int chefTotalPage();
  // 4. 쉐프 작품 (찾기) 
  
  @Select({
	    "<script>"   
	    +"SELECT no,title,poster,chef,num "
		+"FROM (SELECT no,title,poster,chef,rownum as num "
		+"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk) */ no,title,poster,chef "
		+"FROM recipe WHERE chef=#{chef} "
		+"<if test=\"ss!='all'\">"
		+"AND title LIKE '%'||#{ss}||'%'"
		+"</if>"
		+")) "
		+"WHERE num BETWEEN #{start} AND #{end}"
		+"</script>"
  })
  public List<RecipeVO> chefMakeRecipeData(Map map);
  
  @Select("SELECT CEIL(COUNT(*)/12.0) "
		 +"FROM recipe "
		 +"WHERE chef LIKE '%'||#{chef}||'%'")
  public int chefMakeTotalPage(String chef);
  // 5. 재료 => 레시피 찾기  ==> 레시피 => 맛집 => 명소/호텔/자연  
  // ==> REGEXP_LIKE(title, 'a|c|d')
  
}










