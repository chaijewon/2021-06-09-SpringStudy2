package com.sist.dao;
import java.util.*;
// 오라클 => SELECT (데이터베이스 : 데이터가 저장된 장소) 
/*
 *    데이터 추가 , 수정 , 삭제 , 검색 (CURD)
 *    ========================= 
 *    1. 게시판 (댓글) 
 *    2. 회원 가입 
 *    3. 장바구니 
 *    4. 찜 
 *    =========================
 *    => SELECT (데이터 검색)
 */
/*
 *   tiles : <jsp:include> 대신 사용 
 *   mybatis : DAO (ORM) => 
 *   
 *   =>
 *   OOP 
 *   ORM => Mybatis 
 *   형상관리 => GIT ==> 협업 
 */
import org.apache.ibatis.annotations.Select;
public interface FoodMapper {
  // Category 출력 
  @Select({  
	         "<script>"
	    	 +"SELECT cno,title,poster,subject "
		     +"FROM project_food_category "
		     +"WHERE "
		     +"<if test='cno==1'>"
		     +"cno BETWEEN 1 AND 12"
		     +"</if>"
		     +"<if test='cno==2'>"
		     +"cno BETWEEN 13 AND 18 "
		     +"</if>"
		     +"<if test='cno==3'>"
		     +"cno BETWEEN 19 AND 30"
		     +"</if>"
		     +"</script>"
  })
  public List<CategoryVO> categoryListData(Map map);
  // Category별 맛집 
  @Select("SELECT no,cno,poster,name,score,address,tel,type "
		 +"FROM project_food_house "
		 +"WHERE cno=#{cno}")
  public List<FoodVO> categoryFoodListData(int cno);
  
  @Select("SELECT cno,title,subject FROM project_food_category "
		 +"WHERE cno=#{cno}")
  public CategoryVO categoryInfoData(int cno);
  // 맛집 상세보기 
  @Select("SELECT * FROM ${tbl} "
		 +"WHERE no=#{no}")
  public FoodVO foodDetailData(Map map);
  
  // 레시피 연동 (12)  
  @Select("SELECT no,title,poster,chef,rownum "
		 +"FROM recipe "
		 +"WHERE REGEXP_LIKE(title,#{title}) "
		 +"AND rownum<=12")
  // |  => OR (포함된 단어를 찾는 경우) 
  // REGEXP_LIKE => 125PAGE	
  public List<RecipeVO> foodRecipeData(String title); 
  
  // 지역별 맛집 찾기 
  @Select("SELECT no,name,poster,score,address,num "
		 +"FROM (SELECT /*+ INDEX_ASC(project_food_location pfl_no_pk) */no,name,poster,score,address,rownum as num "
		 +"FROM project_food_location "
		 +"WHERE address LIKE '%'||#{address}||'%') "
		 +"WHERE num BETWEEN #{start} AND #{end}")
  public List<FoodLocationVO> foodLocationSearchData(Map map);
  
  @Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food_location "
		 +"WHERE address LIKE '%'||#{address}||'%'")
  public int foodLocationTotalPage(String address);
  // 맛집 추천 => Naver 블로그 이용 (데이터 분석) (*******) ==> XML,JSON
  // 맛집 명 
  @Select("SELECT DISTINCT name FROM project_food_location")
  public List<String> foodGetNameData();
  // 맛집 명 => 정보 받기
  @Select("SELECT * FROM project_food_location "
		 +"WHERE name=#{name}")
  public FoodLocationVO foodInfoData(String name);
  //트위터 => 등록 => 인스타그램 (React) 
}












