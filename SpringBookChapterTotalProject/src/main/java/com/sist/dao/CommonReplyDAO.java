package com.sist.dao;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.sist.vo.CommonReplyVO;

import oracle.jdbc.OracleTypes;

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
   /*
    *   CREATE OR REPLACE PROCEDURE replyListData(
		   pCno commonReply.cno%TYPE,
		   pTno commonReply.tno%TYPE,
		   pResult OUT SYS_REFCURSOR
		)
		IS
		BEGIN
		   -- cursor (ResultSet) => select문장 실행시 출력된 모든 데이터를 읽어 온다 
		   -- 목록을 출력(여러개의 데이터를 모아서 넘겨주는 경우 : CURSOR 
		   OPEN pResult FOR
		    SELECT no,cno,tno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') 
		    FROM commonReply
		    WHERE cno=pCno AND tno=pTno;
		    
		    -- cno : 참조 번호 (게시물 번호) , tno : 구분 (게시판(1),호텔(2),명소(3),레시피(4),맛집(5))
		    
		END;
		/
    */
   /*
    *   클래스마다 역할 분담 
    *   ==============
    *    ~Controller : 데이터베이스 연결해서 결과값을 가지고 와서 JSP로 전송
    *    ~VO : 데이터값만 저장 (전송목적)
    *    ~DAO : 데이터베이스만 연결하는 역할 
    *    
    *    ===> Controller (1) , VO(1) , DAO(1) ==> 단점 (재사용)
    */
   public List<CommonReplyVO> replyListData(int cno,int tno)
   {
	   List<CommonReplyVO> list=new ArrayList<CommonReplyVO>(); // JSP로 전송(X) => @Controller
	   // 클래스를 기능별 분리 => 조립해서 사용 (객체지향 프로그램) => CBD 
	   // 필요시마다 해당 클래스를 다시 사용이 => 확장(extends) , 변경 (오버라이딩), 추가(오버로딩) 
	   // OOP
	   try
	   {
		   getConnection();
		   String sql="{CALL replyListData(?,?,?)}";
		   cs=conn.prepareCall(sql); // dao.replyListData()
		   // ?에 값을 채운다 
		   cs.setInt(1, cno);
		   cs.setInt(2, tno);
		   // 저장할 공간을 만들어 준다 (임시 기억장소:레지스터)
		   cs.registerOutParameter(3, OracleTypes.CURSOR);
		   // 실행 => 결과값을 3번에 오라클에서 저장 => 읽어서 List에 저장 
		   cs.executeUpdate();
		   // 결과값 읽기
		   ResultSet rs=(ResultSet)cs.getObject(3);
		   while(rs.next())
		   {
			   CommonReplyVO vo=new CommonReplyVO();
			   vo.setNo(rs.getInt(1));
			   // no,cno,tno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS')
			   vo.setCno(rs.getInt(2));
			   vo.setTno(rs.getInt(3));
			   vo.setId(rs.getString(4));
			   vo.setName(rs.getString(5));
			   vo.setMsg(rs.getString(6));
			   vo.setDbday(rs.getString(7));
			   list.add(vo);
		   }
		   rs.close();
		   
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return list;
   }
   // 2. 댓글 쓰기
   /*
    *   CREATE OR REPLACE PROCEDURE replyInsert(
		   pCno commonReply.cno%TYPE, -- commonReply.cno%TYPE cno NUMBER
		   pTno commonReply.tno%TYPE,
		   pId commonReply.id%TYPE,  -- id VARCHAR2(20)
		   pName commonReply.name%TYPE, -- name VARCHAR2(34)
		   pMsg commonReply.msg%TYPE  -- msg CLOB
		)
		IS
		BEGIN
		   INSERT INTO commonReply VALUES(
		     (SELECT NVL(MAX(no)+1,1) FROM commonReply),
		     pCno,pTno,pId,pName,pMsg,SYSDATE
		   );
		   COMMIT;
		END;
		/
    */
   public void replyInsert(CommonReplyVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="{CALL replyInsert(?,?,?,?,?)}";
		   cs=conn.prepareCall(sql);
		   cs.setInt(1, vo.getCno());
		   cs.setInt(2, vo.getTno());
		   cs.setString(3, vo.getId());
		   cs.setString(4, vo.getName());
		   cs.setString(5, vo.getMsg());
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
   // 3. 댓글 수정 
   /*
    *   CREATE OR REPLACE PROCEDURE replyUpdate(
		   pNo commonReply.no%TYPE,
		   pMsg commonReply.msg%TYPE
		)
		IS
		-- 선언부
		BEGIN
		-- 구현부 
		   UPDATE commonReply SET
		   msg=pMsg
		   WHERE no=pNo;
		   COMMIT;
		END;
		/
    */
   // VO , 일반 데이터 => 매개변수는 3개이상 => 묶어서 처리 (클래스(데이터 다를 경우) , 배열(데이터이 같은 경우))
   //                                                 ============= VO  
   public void replyUpdate(int no,String msg) // isLogin(String id,String pwd)
   {
	   try
	   {
		   getConnection();
		   String sql="{CALL replyUpdate(?,?)}";
		   cs=conn.prepareCall(sql);
		   // 실행 요청전에 반드시 ?에 값을 채운후에 실행
		   cs.setInt(1, no);
		   cs.setString(2, msg);
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










