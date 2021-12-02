package com.sist.dao;
// SQL ==> 구현할 메소드만 선언 => 마이바티스 (구현)
/*
 *   ========= 웹 (자바 , 오라클 , JSP , 스프링 사용법) => CURD(SELECT,INSERT,UPDATE,DELETE)
 *          1. 자바 (1권 => 면접)
 *             기본 (변수의 종류 => 지역변수 , 멤버변수 (instance,static))
 *                 ====> 연산자 , 제어문 , 메소드
 *             객체 지향 프로그램 (클래스, 객체 , 클래스의 구조, 클래스 종류) => 3대 특성(상속/포함 , 오버라이딩/오버로딩)
 *                                                                => 은닉화/캡슐화 
 *             예외처리 (직접(예외복구=> try~catch),간접(예외회피=> throws))
 *             기타 : 라이브러리 
 *                  java.lang : Object , String , StringBuffer , Wrapper(Integer,Double..)
 *                  java.io  : File , FileWriter,FileReader , FileInputStream,FileOutputStream
 *                             BufferedReader 
 *                             => (Writer/Reader ==> 문자 스트림 (읽기,쓰기 => 2byte) => 한글 
 *                             => (InputStream/OutputStream ==> 바이트 스트림 (읽기/쓰기 => 1byte) 
 *                                => 네트워크 , 파일 업로드 , 파일 다운로드 
 *                  java.util : Date , StringTokenizer , Collection(List,Map)
 *                  java.net : URL , URLEncoding
 *   SQL (구조화된 질의언어) ==> 형식 
 *    => DML(SELECT,INSERT,UPDATE,DELETE) 
 *           ====== JOIN (INNER JOIN , OUTER JOIN(LEFT,RIGTH))
 *                        ========== EQUI_JOIN (Join ~ ON:Ansi)
 *                                   WHERE emp.deptno=dept.deptno (오라클)
 *                                   ON emp.deptno=dept.deptno
 *                                   ========================== My-Sql , MariaDB
 *    => DDL(CREATE , DROP , ALTER , TRUNCATE , RENAME)
 *           ======= 간단한 테이블 (게시판,댓글) , View , Sequence (PL/SQL => 메뉴얼(DBA))
 *                   ==> 유효성 검사 (제약조건) 
 *                       = Primary Key 
 *                       = Foreign key
 *                       = Check
 *                       = NOT NULL
 *    => TCL(COMMIT , ROLLBACK) => Transaction  ===> Java (JDBC=> AutoCommit(true))
 *    
 *    JSP 
 *     => 내장 객체 (request,response,session) => cookie 
 *     => JSTL 
 *         = <c:set> 
 *         = <c:forEach>
 *         = <c:if>
 *         = <c:choose>
 *         = <fmt:formatDate>
 *         = fn:스트링 메소드 
 *     => EL (브라우저에 출력)
 *         = ${requestScope.key},           ${sessionScope.key}
 *           => requestScope. 생략이 가능 
 *           => request.getAttribute(key)   => session.getAttribute(key)
 *     => GET / POST(보안 , 데이터 많다)
 *     => MVC 구조 
 *        M (Model)  ========> VO,DTO(데이터를 모아서 한번에 전송할 목적) , DAO , Manager , 요청처리(Model)
 *                             => .java
 *        V (View)   ========> JSP
 *        C (Controller) ====> Servlet
 *        ================================ 기본 틀 (Spring Framework)
 *     ============================== 전체 학원의 기본 코스 ==========================================  
 *        Ajax , Jquery , VueJS .... (JavaScript)
 *      
 */
import java.util.*; // List

import org.apache.ibatis.annotations.Select;
public interface SeoulMapper {
  @Select("SELECT no,title,poster,address,msg,num "
		 +"FROM (SELECT no,title,poster,address,msg,rownum as num "
		 +"FROM (SELECT no,title,poster,address,msg "
		 +"FROM seoul_location ORDER BY no ASC)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
  public List<SeoulLocationVO> locationListData(Map map);
  
  @Select("SELECT CEIL(COUNT(*)/12.0) FROM seoul_location")
  public int locationTotalPage();
}






