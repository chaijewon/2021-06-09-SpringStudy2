package com.sist.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import com.sist.dao.*;
@Component
@Aspect  // 공통 기반 (모듈) => 모든 메소드에서 사용하는 기능 
public class DBAspect {
   @Autowired
   private DBConnection conn;
   
   // getConnection/disConnection
   @Before("execution(* com.sist..StudentDAO.*(..))")
   public void before()
   {
	   System.out.println("####### Before 적용 ######");
	   conn.getConnection();
   }
   /*@After("execution(* com.sist.dao.StudentDAO.*(..))")
   public void after()
   {
	   System.out.println("####### After 적용 ######");
	   conn.disConnection();
   }*/
   @After("within(com.sist.dao.StudentDAO)")
   public void after()
   {
	   System.out.println("####### After 적용 ######");
	   conn.disConnection();
   }
   @Around("execution(* com.sist.dao.StudentDAO.*(..))")
   public Object around(ProceedingJoinPoint jp) throws Throwable
   {
	   System.out.println("####### Around 적용 ######");
	   Object obj=null;
	   long start=System.currentTimeMillis();
	   System.out.println(jp.getSignature().getName()+"=>호출전...");
	   System.out.println("====== 확인 (모니터링) log파일 ======");
	   System.out.println("1. 매개변수 확인:"+Arrays.toString(jp.getArgs()));
	   System.out.println("2. 메소드명 확인:"+jp.getSignature().getName());
	   System.out.println("3. Advice종류 확인:"+jp.getKind());//Around
	   obj=jp.proceed(); // 메소드 호출 
	   System.out.println(jp.getSignature().getName()+"=>호출후...");
	   long end=System.currentTimeMillis();
	   System.out.println(jp.getSignature().getName()+"=>수행 시간:"+(end-start));
	   return obj;
   }
   //@AfterReturning :  정상 수행시 => 결과값 확인
   @AfterReturning(value="execution(* com.sist.dao.StudentDAO.*(..))",returning="val")
   public void afterReturning(JoinPoint jp,Object val)
   {
	   System.out.println("####### AfterReturning 적용 #########");
	   //System.out.println("리턴값:"+val);
	   String method=jp.getSignature().getName();
	   if(method.equals("studentListData"))
	   {
		   List<StudentVO> list=(List<StudentVO>)val;
		   for(StudentVO vo:list)
		   {
			   System.out.println(vo.getHakbun()+" "
	        			+vo.getName()+" "
	        			+vo.getKor()+" "
	        			+vo.getEng()+" "
	        			+vo.getMath());
		   }
	   }
	   else if(method.equals("studentDetailData"))
	   {
		   StudentVO vo=(StudentVO)val;
		   System.out.println("============ 상세보기 ============");
		   System.out.println(vo.getHakbun()+" "
       			+vo.getName()+" "
       			+vo.getKor()+" "
       			+vo.getEng()+" "
       			+vo.getMath());
	   }
   }
   //@AfterThrowing  : catch절 수행(에러발생시 처리)
   @AfterThrowing(value="execution(* com.sist.dao.StudentDAO.*(..))",throwing="ex")
   public void afterThrowing(JoinPoint jp,Exception ex)
   {
	   System.out.println("####### AfterThrowing ########");
	   ex.printStackTrace();
   }
   //158페이지 : 여러 객체에서 공통으로 적용할 수 있는 기능을 분리해서 재사용성을 높여주는 프로그램 기법 
   //DAO마다 적용 => getConnection/disConnection => catch , finally => 재사용
   /*
    *   프로그램 : 공통기능 (따라 모아서 관리) => 필요시마다 사용이 가능 (재사용)
    */
   /*
    *   159page 
    *   AOP 용어 
    *   Advice : JoinPoint+PointCut
    *   JoinPoint : 공통으로 적용하는 기능의 호출 시점 
    *               시작전 : Before
    *               종료시 : After
    *               위아래 : Around
    *               =======(O) setAutoCommit(false)
    *               기능
    *               =======(O) commit()
    *               정상수행 : After-Returning
    *               에러 : After-Throwing
    *               
    *   PointCut : 적용할 메소드 지정
    *   Aspect   : 공통으로 사용되는 공통모듈을 모아서 관리 
    *   Weaving  : 타켓을 찾아 준다 => 프록시 (대신 적용된 메소드를 호출)
    *   
    *   169page 
    *      execution("* 패키명.클래스명.메소드(..)")
    *                               =====
    *                                 *
    *                                set*()
    *                                get*()
    *                                tx*() ==> 트랜젝션이 적용 
    *     execution("* 패키명.클래스명.메소드(int,int)") => 매개변수 int,int 
    *     execution("* 패키명.클래스명.메소드(String,..)") => 매개변수 int,int 
    *     
    *   169page
    *     적용 순서 
    *     @Around
    *     @Before
    *     @After
    *     @After-Retruning
    *     
    *   AOP : 특별한 경우에만 제작 (웹에서 서버 모니터링 : 로그파일)
    *         빅데이터 (데이터 수집=>분석)
    *         다른 서버를 먼저 돌릴때 (NodeJS,파이썬(제이썬)=>AI) 
    *   => AOP로 만든 라이브러리 (트랜잭션 , 보안)
    *   => 웹 => 데이터 전송 (JavaScript) => AOP적용이 가능 (실시간으로 전송) : Ajax,ReactJS
    */
   
}







