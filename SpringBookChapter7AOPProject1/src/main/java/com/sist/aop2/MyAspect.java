package com.sist.aop2;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

// ���� ��� (DAO��ü���� �������� ���Ǵ� �޼ҵ��� ����)
/*
 *    <aop:config>
        <aop:aspect ref="aspect">
 */
// �޸��Ҵ��� �ȵȴ� 
@Aspect
@Component
public class MyAspect {
   /*
    *  <aop:before method="getConnection"
         pointcut="execution(* com.sist.dao.MyDAO2.*())"
       />
    */
	@Before("execution(* com.sist.dao2.MyDAO.*(..))") // �Ű������� � ���� �־ ��� ���� 
	// .. => �Ű����� 0�̻� 
   public void getConnection()
   {
	   System.out.println("����Ŭ ����");
   }
   /*
    *   <aop:after method="disConnection"
         pointcut="execution(* com.sist.dao.MyDAO2.*())"
       />
    */
	@After("execution(* com.sist.dao2.MyDAO.*(..))")
   public void disConnection()
   {
	   System.out.println("����Ŭ ����");
   }
}
