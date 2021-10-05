package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmpAspect {
  /*
   *    execution(public * 패키명.클래스명.())
   *              =========
   *                * => public 리턴형(*=> 모든 리턴형)
   *               클래스명.()   => 클래스명.*(..) => 모든 메소드 (매개변수는 관계없이 적용)
   *                           => 클래스명.set*(int) => set으로 시작하는 매소드 매개변수 int)
   *                                     set(int no) => O
   *                                     set(String name) => X
   *                                     set*(String) => String
   *                                     set*(int,..) ==> aop적용할 메소드를 찾는 패턴 
   *                       패키지 
   *                        => com.sist.dao.EmpDAO 
   *                        => com...EmpDAO
   *                        => com..dao.EmpDAO
   *                        => com.sist..EmpDAO   ===> 169page ,159page 중요 (그림)
   */
  @Pointcut("execution(* com.sist.dao.EmpDAO.*(..))")
  public void publicTarget()
  {
	  
  }
  /*@Before("beforeMethod()")
  public void before()
  {
	  System.out.println("Before수행");
  }*/
  //@Around("within(com.sist.dao.EmpDAO)")
  @Around("execution(* com.sist.dao.EmpDAO.*(..))")
  // EmpDAO가 가지고 있는 모든 메소드에 적용 , 선택(execution())
  /*
   *       Throwable
   *       =========
   *          |
   *     ===============
   *     Error      Exception
   */
  public Object around(ProceedingJoinPoint jp) throws Throwable// log파일 (정보)
  {
	   Object obj=null;
	   long start=System.currentTimeMillis();
	   System.out.println(jp.getSignature().getName()+" 호출전...");
	   obj=jp.proceed(); // 메소드 호출 
	   System.out.println(jp.getSignature().getName()+" 호출 완료...");
	   long end=System.currentTimeMillis();
	   System.out.println("메소드 수행시간:"+(end-start));
	   // 소스갱신(리팩토링) => 서버 리부트 
	   return obj;
  }
  /*
   *  ===================
   *   @Before : 다른 서버 연결 (NodeJS,파이썬 장고)
   *   @After  : 서버 연결 종료   =====================> DB(getConnection/disConnection)
   *  ===================
   *   @After-Returning : 정상적인 결과값을 확인 
   *   @After-Throwing : 에러처리 => 화면 이동 
   *   @Around : log파일 
   */
}










