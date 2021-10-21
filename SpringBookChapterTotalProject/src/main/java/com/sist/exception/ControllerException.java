package com.sist.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/*
 *   Controller 에러 
 *   1. RuntimeException
 *      연산처리 , 메모리 할당이 안된 경우 , 형변환 , 배열범위 
 *      NullPointerException 
 *      ClassCastException 
 *   2. SQLException 
 *   2-1. IOException 
 *   3. Exception 
 */
@ControllerAdvice("com.sist.web")
// Controller전체의 예외처리하는 과정  ==> catch절을 처리하는 부분 (공통으로 catch절 적용)
public class ControllerException {
   @ExceptionHandler(Exception.class)
   public void exceptionHandler(Exception ex)
   {
	   System.out.println("======= 오류처리 (Exception) ========");
	   System.out.println(ex.getMessage());
   }
   
   @ExceptionHandler(SQLException.class)
   public void sqlExceptionHandler(SQLException ex)
   {
	   System.out.println("======= 오류처리 (SQLException) ========");
	   System.out.println(ex.getMessage());
   }
   
   @ExceptionHandler(RuntimeException.class)
   public void runtimeExceptionHandler(RuntimeException ex)
   {
	   System.out.println("======= 오류처리 (RuntimeException) ========");
	   System.out.println(ex.getMessage());
   }
   
   @ExceptionHandler(IOException.class)
   public void ioExceptionHandler(IOException ex)
   {
	   System.out.println("======= 오류처리 (IOException) ========");
	   System.out.println(ex.getMessage());
   }
   
}











