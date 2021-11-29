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
}











