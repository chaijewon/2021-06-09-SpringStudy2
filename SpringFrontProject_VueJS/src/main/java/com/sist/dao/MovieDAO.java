package com.sist.dao;
/*
 *                     .do(요청)
 *    JSP            =========> 서버
 *(자바:EL,JSLT)      <=========
 *                     List에 묶어서 전송 (request)(응답)
 *                    .do(요청)
 *   JSP            ========> 서버
 *                  <========
 *   자바(X)            응답
 *   자바스크립트               (자바스크립트가 인식할 수 있는 데이터로 전송)
 *                    =================================
 *                    MovieVO   =========> {mno:1,title:''....}
 *                                 => 객체표현법 (JSON)
 *                    int (total) ===> int
 *                    List<MovieVO> ===> [{},{},{}...] 
 *                    =============     ===============
 *                       자바                          Javascript/Kotlin(언어)
 *                                        var name:String
 *                                        var age:Int
 *                                        val => final 
 *                                        fun 함수명(매개변수):Int
 *                                        {
 *                                        }
 *                                        data class MovieVO
 *           서버 => 데이터베이스는 변경이 없다 
 */
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDAO {
   @Autowired
   private MovieMapper mapper;
  /*
   *  @Select("SELECT mno,title,poster,num "
			+"FROM (SELECT mno,title,poster,rownum as num "
			+"FROM (SELECT /*+INDEX_ASC(project_movie pm_mno_pk) mno,title,poster "
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
	 
	 ====> Cursor (ResultSet)
	 ====> List<MovieVO> => JavaScript(X) (List,클래스가 존재하지 않는다)
	       ============
	       자바스크립트가 인식할 수 있는 데이터 전송 ==> JSON => {키:값,키:값....}
	                                              {mno:1,title:'',........) ==> Rest
	                                              RestController
   */
	public List<MovieVO> movieListData(Map map)
	{
		return mapper.movieListData(map);
	}
	public int movieTotalPage()
	{
		return mapper.movieTotalPage();
	}
	public MovieVO movieDetailData(int mno)
	{
		return mapper.movieDetailData(mno);
	}
}







