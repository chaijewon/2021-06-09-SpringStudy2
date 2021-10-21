package com.sist.exception;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
/*
 *   스프링 : 1~13장 => 세션 / 쿠키 (인터셉터) => 14장(날짜변환 => JSP,TO_CHAR) , PathValiable , 
 *   ControllerAdvice => Controller안에서 try~catch
 *   16장 (중요한 챕터) => 2주 (Ajax,Vue,React,Kotlin) ==> JSON(챗봇)
 *                              === ===== ======
 *                               1주   1주        2주
 *   DI , AOP , MVC 
 *   ============== 스프링의 핵심 
 *   MyBatis 
 *   ======= XML,Annotation => Procedure(오라클)  => SELECT/UPDATE/DELETE/INSERT
 *   ========================================================================== JSP(EL/JSTL)
 */
// Controller에서 에러가 발생시에 에러 공통으로 출력 (14page) ControllerAdvice(402)
// log(사용자의 동작) => 경로나 요청 내용 (걸린 시간) : AOP,인터셉트 (AOP를 많이 선택) 
// 예외처리 => 직접 처리(메소드마다 try~catch) , web.xml (<error-code>) , @ControllerAdvice
// 메모리 할당 
/*
 *   @Component, @Repository, @Service, @Controller, @RestController, @ControllerAdvice, 
     and @Configuration
     
      <context:component-scan base-package="com.sist.*">
      => 모든 클래스를 메모리할당하는 것이 아니다 
      => 메모리를 할당하고 관리요청 (클래스 지정) 
      => 클래스 기능별 분리 
      @Component, (일반 클래스) => MainClass , ~Manager , 크롤링 
      @Repository, Database (~DAO) = 저장소 
      @Service, : DAO여러개를 동시에 처리 
      @Controller, : Model 클래스 (전송: forward/sendRediect) => 화면 이동 
                     => 화면이동 => 현재 사용자가 보고 있는 JSP에 데이터만 전송 (@ResponseBody)
      @RestController, : 16장 => 화면이동을 하는 것이 아니라 (데이터(일반 문자열,JSON,XML,자바스크립트)만 전송)
                     => 자격조건 : Rest API 사용가능자 (웹/앱)
      @ControllerAdvice, : 14장 => 컨트롤러 에러시 공통 예외처리 
      @Configuration : XML에 대신 자바로 환경설정 (spring 5버전)
 */
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice("com.sist.web") //@Controller가 올라가 있는 패키지명 지정 
// catch(){} Controller에서 사용하지 않고 => 별도로 예외처리 하고 있다 => @ConrollerAdvice
public class ControllerExceptionHandler {
   @ExceptionHandler(Exception.class) // IOException , SQLException , RuntimeException
   // catch(Exception ex) {}
   public void exceptionHandler(Exception ex)
   {
	   System.out.println("===== 에러 발생(Exception) =====");
	   // 요청 ==>(요청 처리:데이터베이스 연결) ==>응답  (요청처리 => Controller : 조립기) => 모든 클래스가 Controller안에서 연결)
       System.out.println(ex.getMessage());
   }
   
   @ExceptionHandler(RuntimeException.class)
   public void runtimeExceptionHandler(RuntimeException ex)
   {
	   System.out.println("===== 에러발생(RuntimeException) =====");
	   System.out.println(ex.getMessage());
   }
   
   @ExceptionHandler(SQLException.class)
   public void sqlExceptionHandler(SQLException ex)
   {
	   System.out.println("===== 에러 발생(SQLExcetion) =====");
	   System.out.println(ex.getMessage());
   }
   
   
}












