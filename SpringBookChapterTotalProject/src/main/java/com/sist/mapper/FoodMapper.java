package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
public interface FoodMapper {
  /*
   *   MyBatis : 데이터베이스 연결  => MyBatis / JPA (DataSet) : LinkQ  / Hibernate
   *                             ======== iBatis(open source => google인수)
   *             => DML : Select,Insert,Update,Delete 
   *                #{} , ${} 
   *             => 동적 쿼리 
   *             => XML / Annotation 
   *             
   *             => 이름  제목 내용 
   *                이름 
   *                제목
   *                내용
   *                이름 제목
   *                이름 내용
   *                제목 내용 
   *                이름 제목 내용  => 1개
   *             => 상품 
   *             
   *         => <if test="조건">
   *         => <choose> 
   *             <when test="조건">SELECT~</when>
   *             <when test="조건">SELECT~</when>
   *             <otherwise></otherwise>
   *            </choose> 
   *         => <foreach var="" collection=""> => IN
   *         => <trim> => 첨부 (prefix , suffix) , 제거 (prefixOverrides="OR|AND", suffixOverrides="")
   *         => <where>
   *         WHERE  (조건 OR 조건)
   *         <if test="이름">
   *          OR 조건 
   *         </if>
   *         <if test="제목">
   *         OR 조건 
   *         </if>
   *         <if test="내용">
   *         OR 조건 
   *         </if>
   */
	/*
	 *     +"<if test='no==1'>"
		   +"cno BETWEEN 1 AND 12"
		   +"</if>"
		   +"<if test='no==2'>"
		   +"cno BETWEEN 13 AND 18"
		   +"</if>"
		   +"<if test='no==3'>"
		   +"cno BETWEEN 19 AND 30"
		   +"</if>"
		   
		   => <choose>
		      <when test='no==1'>
		       cno BETWEEN 1 AND 12
		      </when>
		      <when test='no==2'>
		       cno BETWEEN 13 AND 18
		      </when>
		      <when test='no==3'>
		       cno BETWEEN 19 AND 30
		      </when>
		      </choose>
	 */
	@Select({
		"<script>"
	   +"SELECT cno,title,subject,poster "
	   +"FROM project_food_category "
	   +"WHERE "
	   +"<if test='no==1'>"  // no => map.get("no") , vo => #{no} => int no
	   +"cno BETWEEN 1 AND 12"
	   +"</if>"
	   +"<if test='no==2'>"
	   +"cno BETWEEN 13 AND 18"
	   +"</if>"
	   +"<if test='no==3'>"
	   +"cno BETWEEN 19 AND 30"
	   +"</if>"
	   +"</script>"
	})
	// 동적 => Map(get(""),~VO(vo.getNo())
	public List<CategoryVO> categorySelectData(Map map);
	// CREATE INDEX pf_name_idx ON table_name(컬럼) , Trigger
	// Primary Key , Unique => 자동 생성 
	// 검색 
	@Select("SELECT no,name,poster,num "
		   +"FROM (SELECT no,name,poster,rownum as num "
		   +"FROM (SELECT /*+ INDEX_ASC(project_food_location pfl_no_pk) */ no,name,poster "
		   +"FROM project_food_location WHERE address LIKE '%'||#{ss}||'%')) "
		   +"WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindData(Map map); // 매개변수는 1개 (여러개 => ~VO,~Map)
	// parameterType="1개 지정"
	// 총페이지 
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM project_food_location "
		   +"WHERE address LIKE '%'||#{address}||'%'")
	public int foodFindTotalPage(String address);
	// 상세보기 / 카테고리별 맛집 
	// 추천 
	// 댓글 
}












