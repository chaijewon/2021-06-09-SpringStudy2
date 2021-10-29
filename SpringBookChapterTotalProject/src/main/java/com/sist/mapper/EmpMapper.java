package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.EmpVO;
/*
 *    SELECT (SQL)
 *             == Query (질의 => 데이터 읽기 : SELECT) => RDBMS (공통으로 사용하는 데이터집합)
 *                                                   ===== 
 *                                                   실제 : 금융권 , 국세청 , 학교 = DB2,사이베이스 (대용량)
 *                                                         대기업 : 오라클 , MS-SQL (중형)
 *                                                         중소기업 : 마리아디비,MSSQL
 *                                                                 =========
 *                                                         핸드폰 : SQLite (소형)
 *      => 1.형식 
 *           SELECT *|column1,column2...
 *           FROM table_name|view_name|(SELECT~~)
 *           [
 *              WHERE 
 *              GROUP BY
 *              HAVING 
 *              ORDER BY
 *           ]
 *      => 2. WHERE (연산자)
 *            = NOT 
 *            = LIKE
 *            = IN
 *            = BETWEEN ~ AND
 *      => 3. 내장함수 
 *            = NVL 
 *            = RPAD , SUBSTR 
 *            = CEIL , ROUND
 *      => 4. JOIN (INNER JOIN, OUTER JOIN)
 *            = EQUI_JOIN
 *            = NON_EQUI_JOIN
 *            = LEFT OUTER JOIN
 *            = RIGHT OUTER JOIN
 *      => 5. 서브쿼리 
 *            = 스칼라 서브쿼리 (컬럼대신) => JOIN대체 
 *            = 인라인뷰 
 *      => 6. 시퀀스 , View (인라인뷰=페이지)
 *      => 7. PL/SQL => 자바 호출 방법 
 *    INSERT,UPDATE,DELETE  => 제약조건 
 */
public interface EmpMapper {
   @Select("SELECT empno,ename,job,hiredate,sal,comm,dname,loc "
		  +"FROM emp,dept "
		  +"WHERE emp.deptno=dept.deptno") // EQUI_JOIN
   public List<EmpVO> empListData();
}









