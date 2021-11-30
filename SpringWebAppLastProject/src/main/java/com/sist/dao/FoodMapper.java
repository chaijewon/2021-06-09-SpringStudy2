package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
/*
 *    ========= DML(SELECT,UPDATE,DELETE,INSERT) / 
 *              DDL (기본 테이블 생성 = 제약조건) => View / Sequence / Table
 *              SELECT 
 *              ======
 *                => 기본 형식 
 *                => 내장 함수 
 *                => 연산자 
 *                => JOIN/SUBQUERY(스칼라서브쿼리) 
 *    XML 
 *     <select id="" resultType="" parameterType=""> 
 *             ===== ============= ================
 *             method명  리턴형             매개변수 
 *                                ======= 1개만 설정이 가능 (여러개일 경우: Map,~VO)
 *     <insert id="" parameterType=""> void
 *     <update id="" parameterType="">
 *     <delete id="" parameterType="">
 *    Annotation
 *      @Select
 *      @Update
 *      @Delete
 *      @Insert
 */
public interface FoodMapper {
   @Select({
	    "<script>"
	   +"SELECT cno,title,subject,poster "
	   +"FROM project_food_category "
	   +"<if test='no==1'>"
	   +"WHERE cno BETWEEN 1 AND 12"
	   +"</if>"
	   +"<if test='no==2'>"
	   +"WHERE cno BETWEEN 13 AND 18"
	   +"</if>"
	   +"<if test='no==3'>"
	   +"WHERE cno BETWEEN 19 AND 30"
	   +"</if>"
	   +"</script>"
   })
   public List<CategoryVO> categoryListData(Map map); // #{} 일반 변수 , 동적쿼리 => Map => 구현이 완료 
   
   @Select("SELECT no,name,poster,address,tel,type,score "
		 +"FROM project_food_house "
		 +"WHERE cno=#{cno}")
   
   public List<FoodVO> categoryFoodListData(int cno);
   
   @Select("SELECT title,subject FROM project_food_category "
		  +"WHERE cno=#{cno}")
   public CategoryVO categoryInfoData(int cno);
   
   @Select("SELECT * FROM project_food_house "
		  +"WHERE no=#{no}")
   
   public FoodVO foodDetailData(int no);
   
   // 검색 
   @Select("SELECT no,name,poster,num "
		  +"FROM (SELECT no,name,poster,rownum as num "
		  +"FROM (SELECT /*+ INDEX_ASC(project_food_location pfl_no_pk) */no,name,poster "
		  +"FROM project_food_location WHERE address LIKE '%'||#{loc}||'%')) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<FoodVO> foodFindData(Map map);
   // 총페이지 
   @Select("SELECT CEIL(COUNT(*)/12.0) "
		 +"FROM project_food_location "
		 +"WHERE address LIKE '%'||#{loc}||'%'")
   public int foodFindTotalPage(String loc);
}











