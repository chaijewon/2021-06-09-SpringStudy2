package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
/*
 *   View : 화면 출력 
 *          1) 목록 => 페이지 나누기
 *          2) 검색 
 *          3) 상세보기 
 *          =========== 게시판 / 로그인 (X) => 일반 JSP 
 *          
 */
public interface RecipeMapper {
     // title,poster,chef,link,no
	 @Select("SELECT no,title,poster,chef,num "
			+"FROM (SELECT no,title,poster,chef,rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/ no,title,poster,chef "
			+"FROM recipe)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	 public List<RecipeVO> recipeListData(Map map);
	 
	 // 총페이지 구하기 
	 @Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe")
	 public int recipeTotalPage();
	 
}











