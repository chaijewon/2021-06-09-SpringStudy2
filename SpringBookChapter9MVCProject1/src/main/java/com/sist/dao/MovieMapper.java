package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface MovieMapper {
   @Select("SELECT mno,title,poster,num "
		  +"FROM (SELECT mno,title,poster,rownum as num "
		  +"FROM (SELECT mno,title,poster "
		  +"FROM project_movie ORDER BY mno ASC)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<MovieVO> movieListData(Map map);
   /*
    * public List<MovieVO> movieListData(int cno,int page)
      {
    	  List<MovieVO> list=new ArrayList<MovieVO>();
    	  try
    	  {
    		  getConnection();
    		  String sql="SELECT mno,poster,title,num "
    				    +"FROM (SELECT mno,poster,title,rownum as num "
    				    +"FROM (SELECT mno,poster,title "
    				    +"FROM project_movie WHERE cno=? ORDER BY mno ASC)) "
    				    +"WHERE num BETWEEN ? AND ?";
    		 ps=conn.prepareStatement(sql);
    		 int rowSize=12;
    		 int start=(rowSize*page)-(rowSize-1);
    		 int end=rowSize*page;
    		 ps.setInt(1, cno);
    		 ps.setInt(2, start);
    		 ps.setInt(3, end);
    		 
    		 ResultSet rs=ps.executeQuery();
    		 while(rs.next())
    		 {
    			 MovieVO vo=new MovieVO();
    			 vo.setMno(rs.getInt(1));
    			 vo.setPoster(rs.getString(2));
    			 vo.setTitle(rs.getString(3));
    			 list.add(vo);
    		 }
    		 rs.close();
    	  }
    	  catch(Exception ex)
    	  {
    		  ex.printStackTrace();
    	  }
    	  finally
    	  {
    		  disConnection();
    	  }
    	  return list;
      }
      
      public int movieTotalPage(int cno)
      {
    	  int total=0;
    	  try
    	  {
    		  getConnection();
    		  String sql="SELECT CEIL(COUNT(*)/12.0) FROM project_movie "
    				    +"WHERE cno=?";
    		  ps=conn.prepareStatement(sql);
    		  ps.setInt(1,cno);
    		  ResultSet rs=ps.executeQuery();
    		  rs.next();
    		  total=rs.getInt(1);
    		  rs.close();
    	  }catch(Exception ex)
    	  {
    		  ex.printStackTrace();
    	  }
    	  finally
    	  {
    		  disConnection();
    	  }
    	  return total;
      }
    */
   // #이 여러개 있는 경우 ==> Map, ~VO (VO에 없는 변수면 => Map)
   @Select("SELECT CEIL(COUNT(*)/12.0) FROM project_movie") // SQL문장 => 프로그래머가 제작 
   public int movieTotalPage();
   // 매개변수(?에 들어가는 값 => #{}) / 리턴형 (결과값) => 라이브러리 (장점: 사용하기 쉽다,표준화)
   // 단점 : 자바 소스안에 있는 내용을 알 수가 없다 
   // => JDBC를 연결해서 처리 => 전에 소스코딩이 그대로 반영 
}
