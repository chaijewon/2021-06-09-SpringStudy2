package com.sist.dao;
import java.util.*;

import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

import java.sql.*;
/*
 *   1. PROCEDURE 
 *      => 문법 (PL) => procedural language  => PL/SQL => PL을 처리시에 SQL을 이용해서 처리 
 *      => 문법 
 *         ===
 *         1) 형식
 *         ============================
 *            DECLARE (익명)
 *              선언부  (변수선언)
 *         ============================
 *           1. procedure
 *              CREATE [OR REPLACE:수정이 자주 있는 경우] PROCEDURE pro_name(매개변수)
 *              IS
 *               선언부 (지역변수)
 *         ============================
 *           2. function
 *              CREATE [OR REPLACE:수정이 자주 있는 경우] FUNCTION func_name(매개변수) RETURN 데이터형
 *              IS
 *               선언부 (지역변수)
 *         ============================
 *           3. trigger
 *              CREATE [OR REPLACE:수정이 자주 있는 경우] TRIGGER tri_name
 *              ON INSERT/UPDATE/DELETE table_name
 *              IS
 *               선언부 (지역변수)
 *         ============================
 *            BEGIN
 *              구현부 (SQL문장으로 처리)
 *            END;
 *         2) 변수선언 
 *              = 스칼라 변수 : id VARCHAR2(10) , age NUMBER , avg NUMBER(2,1) , regdate DATE
 *              = 참조변수 
 *                 *****= %TYPE  ==> 컬럼이 가지고 있는 한개의 데이터형 => emp.job%TYPE
 *                 = %ROWTYPE ==> 테이블 전체의 컬럼 데이터형  => emp%ROWTYPE ==> ~VO
 *                   예)emp
 *                       empno , ename , job , mgr , hiredate , sal, comm, deptno
 *                     dept
 *                       deptno ,dname , loc
 *                    ==> JOIN (empno,ename,dname,loc)
 *              = 사용자 정의 (JOIN,SubQuery) => 컬럼 여러개 
 *                 = RECORD 
 *              =============================1 ROW(한명에 대한 정보,맛집 한개)==========================================
 *              = 여러개의 row을 받는 경우 처리 
 *              *****= CURSOR (List, ResultSet)
 *         3) 제어문
 *              조건문 
 *                 = 단일 조건문 
 *                   IF (조건문) THEN
 *                      처리 
 *                   END IF:
 *                 = 선택 조건문 
 *                   IF (조건문) THEN 
 *                      처리 
 *                   ELSE
 *                      처리 
 *                   END IF;
 *                 = 다중 조건문 
 *                   IF (조건문) THEN
 *                     처리
 *                   ELSIF (조건문) THEN
 *                     처리 
 *                   ELSIF (조건문) THEN
 *                     처리 
 *                   ELSIF (조건문) THEN
 *                     처리 
 *                   ELSE
 *                     처리
 *                   END IF;
 *                   
 *              반복문 
 *                = BASIC LOOP
 *                  LOOP
 *                    처리 
 *                    EXIT WHEN 조건
 *                  END LOOP
 *                = WHILE
 *                  WHILE (조건문) LOOP
 *                    처리 
 *                  END LOOP
 *                = FOR (CURSOR)
 *                  FOR 받는 변수 IN [RESERVE] 1..5 LOOP
 *                    처리
 *                  END LOOP;
 *         4) 연산자 
 *               = 조건 (비교연산자 , 논리연산자) : = (같다)
 *               
 *            => 매개변수 (사용자가 보내준 값) = 자바메소드 , 오라클 함수
 *               예) 로그인 처리 => id,pwd
 *               예) id중복  => id
 *               예) 글쓰기 => 이름,제목,내용,비밀번호 => 묶어서 한번에 전송 (자바:~VO,오라클:%ROWTYPE)
 *             => 결과값 
 *                =====
 *                 FUNCTION ~~ RETURN VARCHAR2
 *                 PROCEDURE는 리턴형이 없다 (매개변수에 변수를 설정 => 값을 받는 방식)
 *                    = 일반적으로 SQL에 대입하는 변수 : IN
 *                    = 값을 받는 변수 : OUT (포인터) => 메모리 주소를 지정 => 주소에 값을 채워 달라 
 *                => 요청값(매개변수) , 응답값 (리턴값)
 *                   =====           =====
 *                   클라이언트                 서버    ==========> C/S => 응답된 값만 출력하면 종료 
 *                => 재사용 (오라클 : 프로시저를 만들면 절대 지워지지 않는다 (DROP이 없는 이상)
 *                => table => 값 (영구적인 저장 장치)
 *                   메모리 : 램 (휘발성) => 변수 
 *         =====================================
 *          1. 프로젝트 
 *             1) PORCEDURE => 댓글 처리 
 *             2) TRIGGER   => 찜 자동 증가 (login횟수 => 등급) 
 *             @RestController 
 *             =======================================================================
 *             3) 맛집의 목록 => 상세보기 (VueJS)
 *             4) 레시피 목록 => 상세보기, 뉴스 출력 => (ReactJS) => Redux(MVC)
 *             5) React/NodeJS => 실시간 채팅 
 *             => 사용 가능한 기술 : Jquery,Ajax,VueJS,ReactJS,Redux,NodeJS, Mybatis....,WebPack(배포판)
 *             => DataBase : 오라클 / 마리아디비 (코틀린):데이터형 (자바/파이썬) 
 *             => React/Redux = 웹스톰 => STS
 *         ============================================================================
 *         
 */
@Repository
public class StudentDAO {
    private Connection conn;
    private PreparedStatement ps;// SQL문장 전송 
    private CallableStatement cs;// Procedure 호출 
    private final String URL="jdbc:oracle:thin:@211.238.142.181:1521:XE";
    // SqlSessionFactoryBean
    // 드라이버 
    public StudentDAO()
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
    		if(ps!=null) ps.close();
    		if(cs!=null) cs.close();
    		if(conn!=null) conn.close();
    	}catch(Exception ex){}
    }
    
    // 목록 읽기
    public List<StudentVO> studentListData()
    {
    	List<StudentVO> list=new ArrayList<StudentVO>(); // selectList()
    	try
    	{
    		getConnection();
    		String sql="SELECT hakbun,name,kor,eng,math,studentGetTotal(hakbun),studentGetAvg(hakbun) FROM pro_student";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			StudentVO vo=new StudentVO();
    			vo.setHakbun(rs.getInt(1));
    			vo.setName(rs.getString(2));
    			vo.setKor(rs.getInt(3));
    			vo.setEng(rs.getInt(4));
    			vo.setMath(rs.getInt(5));
    			vo.setTotal(rs.getInt(6));
    			vo.setAvg(rs.getDouble(7));
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
    // 추가  Call studentInsert('홍길동',80,90,89)
    public void studentInsert(StudentVO vo)
    {
    	try
    	{
    		getConnection();
    		String sql="{CALL studentInsert(?,?,?,?)}";
    		cs=conn.prepareCall(sql); 
    		// ?에 값을 채워서 실행 
    		cs.setString(1, vo.getName());
    		cs.setInt(2, vo.getKor());
    		cs.setInt(3, vo.getEng());
    		cs.setInt(4, vo.getMath());
    		cs.executeUpdate(); // 실행 요청 
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    }
    // 상세보기 
    /*
     * CREATE OR REPLACE PROCEDURE studentDetailData(
		   phakbun pro_student.hakbun%TYPE,
		   pname OUT pro_student.name%TYPE,
		   pkor OUT pro_student.kor%TYPE,
		   peng OUT pro_student.eng%TYPE,
		   pmath OUT pro_student.math%TYPE
		)
     */
    public StudentVO studentDetailData(int hakbun)
    {
    	StudentVO vo=new StudentVO();
    	try
    	{
    		getConnection();
    		String sql="{CALL studentDetailData(?,?,?,?,?)}";
    		cs=conn.prepareCall(sql);
    		cs.setInt(1,hakbun);
    		cs.registerOutParameter(2, OracleTypes.VARCHAR);
    		cs.registerOutParameter(3, OracleTypes.INTEGER);
    		cs.registerOutParameter(4, OracleTypes.INTEGER);
    		cs.registerOutParameter(5, OracleTypes.INTEGER);
    		cs.executeUpdate();
    		// 값을 메모리에서 가지고 온다 
    		vo.setName(cs.getString(2));
    		vo.setKor(cs.getInt(3));
    		vo.setEng(cs.getInt(4));
    		vo.setMath(cs.getInt(5));
    		// CURSOR => ResultSet 처리 
    		
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    	return vo;
    }
    
    // 삭제  CREATE OR REPLACE PROCEDURE studentDelete(vhakbun pro_student.hakbun%TYPE)
    public void studentDelete(int hakbun)
    {
    	try
    	{
    		getConnection();
    		String sql="{CALL studentDelete(?)}";
    		cs=conn.prepareCall(sql);
    		cs.setInt(1, hakbun);
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
    
    // 수정 : 
    /*
     *  CREATE OR REPLACE PROCEDURE studentUpdate(
	      vhakbun pro_student.hakbun%TYPE,
	      vname pro_student.name%TYPE,
	      vkor pro_student.kor%TYPE,
	      veng pro_student.eng%TYPE,
	      vmath pro_student.math%TYPE)
     */
    public void studentUpdate(StudentVO vo)
    {
    	try
    	{
    		getConnection();
    		String sql="{CALL studentUpdate(?,?,?,?,?)}";
    		cs=conn.prepareCall(sql);
    		cs.setInt(1, vo.getHakbun());
    		cs.setString(2, vo.getName());
    		cs.setInt(3, vo.getKor());
    		cs.setInt(4, vo.getEng());
    		cs.setInt(5, vo.getMath());
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






