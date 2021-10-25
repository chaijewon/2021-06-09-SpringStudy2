package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
/*
 *  private int mno,cno,hit;
    private double score;
    private String title,grade,reserve,genre,time,regdate,director,actor,showuser,poster,story,key;
	
 */
public interface MovieMapper {
	 // 목록 출력 (인덱스 => 자동으로 생성 (PRIMARY KEY, UNIQUE
	 @Select("SELECT mno,title,poster,num "
			+"FROM (SELECT mno,title,poster,rownum as num "
			+"FROM (SELECT /*+INDEX_ASC(project_movie pm_mno_pk)*/ mno,title,poster "
			+"FROM project_movie)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	 public List<MovieVO> movieListData(Map map);// => JS([{},{},{}....])
	 @Select("SELECT CEIL(COUNT(*)/12.0) FROM project_movie")
	 public int movieTotalPage();
	 // 상세 보기
	 @Select("SELECT mno,title,grade,reserve,genre,time,regdate,director,actor,showuser,"
			 +"poster,key FROM project_movie "
			 +"WHERE mno=#{mno}")
	 public MovieVO movieDetailData(int mno); // JS({})
	 // => 목록/상세
}










