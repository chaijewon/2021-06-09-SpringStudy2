package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface EmpMapper {
   @Select("SELECT empno,ename,job,hiredate,sal,comm,deptno,"
		  +"(SELECT dname FROM dept WHERE dept.deptno=emp.deptno) as dname,"
		  +"(SELECT loc FROM dept WHERE dept.deptno=emp.deptno) as loc "
		  +"FROM emp")
   public List<EmpVO> empListData(); // mybatis 메소드를 자동 구현 
   //     ===========           ====
   //      resultType          parameterType
   // set DI ==> set메소드 값을 채워준다 (함수를 사용하면 반드시 as를 이용해서 변수를 지정) => index번호를 이용하지 않고 
   // column명을 이용해서 처리 
   @Select("SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal,comm,deptno,"
			  +"(SELECT dname FROM dept WHERE dept.deptno=emp.deptno) as dname,"
			  +"(SELECT loc FROM dept WHERE dept.deptno=emp.deptno) as loc "
			  +"FROM emp "
			  +"WHERE empno=#{empno}")
   public EmpVO empDetailData(int empno);
}
