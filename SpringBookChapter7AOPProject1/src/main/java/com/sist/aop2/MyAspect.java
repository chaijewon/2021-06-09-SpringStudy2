package com.sist.aop2;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

// 공통 모듈 (DAO전체에서 공통으로 사용되는 메소드의 집합)
/*
 *    <aop:config>
        <aop:aspect ref="aspect">
 */
// 메모리할당이 안된다 
@Aspect
@Component
public class MyAspect {
   /*
    *  <aop:before method="getConnection"
         pointcut="execution(* com.sist.dao.MyDAO2.*())"
       />
    */
	@Before("execution(* com.sist.dao2.MyDAO.*(..))") // 매개변수는 어떤 것이 있어도 상관 없다 
	// .. => 매개변수 0이상 
   public void getConnection()
   {
	   System.out.println("오라클 연결");
   }
   /*
    *   <aop:after method="disConnection"
         pointcut="execution(* com.sist.dao.MyDAO2.*())"
       />
    */
	@After("execution(* com.sist.dao2.MyDAO.*(..))")
   public void disConnection()
   {
	   System.out.println("오라클 해제");
   }
}
