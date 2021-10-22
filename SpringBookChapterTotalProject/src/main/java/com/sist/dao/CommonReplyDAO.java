package com.sist.dao;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.sql.*;
// 메모리 (Spring : (MyBatis , JDBC사용, JPA , Hibernate)) => 관리 (스프링 : 메모리 할당)
@Repository
public class CommonReplyDAO {
   private Connection conn; // => MyBatis 
   // procedure호출 => <resultMap> , <parameterMap> , <select type="CALLABLE">
   private CallableStatement cs; // PreparedStatement ps(일반 SQL 전송) , 
   //CallableStatement cs (프로시저 호출)
   private final String URL="jdbc:oracle:thin:@211.238.142.181:1521:XE";
   // 드라이버 등록
   public CommonReplyDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex){}
   }
   // 연결
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
	   }catch(Exception ex){}
   }
   // 해제
   public void disConnection()
   {
	   try
	   {
		   if(cs!=null) cs.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex){}
   }
   
   // 1. 목록 출력 
   // 2. 댓글 쓰기
   // 3. 댓글 수정 
   // 4. 댓글 삭제 
   /*
    *       CREATE OR REPLACE PROCEDURE replyDelete(
			   pNo commonReply.no%TYPE
			)
			IS
			BEGIN
			  DELETE FROM commonReply 
			  WHERE no=pNo;
			  COMMIT;
			END;
			/
    */
   public void replyDelete(int no)
   {
	    try
	    {
	    	getConnection();
	    	String sql="{CALL replyDelete(?)}";
	    	cs=conn.prepareCall(sql);
	    	cs.setInt(1, no);
	    	cs.executeUpdate();
	    }catch(Exception ex)
	    {
	    	ex.printStackTrace();
	    }
	    finally
	    {
	    	disConnection();
	    }
   }
}










