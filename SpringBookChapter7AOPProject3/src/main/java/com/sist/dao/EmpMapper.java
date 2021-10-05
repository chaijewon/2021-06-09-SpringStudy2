package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface EmpMapper {
   @Select("SELECT empno,ename,job,hiredate,sal,comm,deptno,"
		  +"(SELECT dname FROM dept WHERE dept.deptno=emp.deptno) as dname,"
		  +"(SELECT loc FROM dept WHERE dept.deptno=emp.deptno) as loc "
		  +"FROM emp")
   public List<EmpVO> empListData(); // mybatis �޼ҵ带 �ڵ� ���� 
   //     ===========           ====
   //      resultType          parameterType
   // set DI ==> set�޼ҵ� ���� ä���ش� (�Լ��� ����ϸ� �ݵ�� as�� �̿��ؼ� ������ ����) => index��ȣ�� �̿����� �ʰ� 
   // column���� �̿��ؼ� ó�� 
   @Select("SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal,comm,deptno,"
			  +"(SELECT dname FROM dept WHERE dept.deptno=emp.deptno) as dname,"
			  +"(SELECT loc FROM dept WHERE dept.deptno=emp.deptno) as loc "
			  +"FROM emp "
			  +"WHERE empno=#{empno}")
   public EmpVO empDetailData(int empno);
}
