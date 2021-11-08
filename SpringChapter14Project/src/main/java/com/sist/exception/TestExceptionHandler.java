package com.sist.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.sist.web")
public class TestExceptionHandler{
	@ExceptionHandler(Exception.class)  // 이런식으로 익셉션 종류별로 처리를 둘 수 있다.
	public void handleException(Exception e){
		System.out.println("=====오류발생=====");
		System.out.println(e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)  // 이런식으로 익셉션 종류별로 처리를 둘 수 있다.
	public void handleRuntimeException(RuntimeException e){
		
		System.out.println(e.getMessage());
		
	}
}