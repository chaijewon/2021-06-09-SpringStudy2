package com.sist.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MovieAspect {
  @Around("execution(* com.sist.web.MovieController.*(..))")
  public Object log(ProceedingJoinPoint jp) throws Throwable
  {
	  Object obj=null;
	  long start=System.currentTimeMillis();
	  System.out.println("사용자 요청 메소드 호출:"+jp.getSignature().getName());
	  Object[] o=jp.getArgs();
	  obj=jp.proceed(); // 메소드 호출 
	  long end=System.currentTimeMillis();
	  HttpServletRequest request=(HttpServletRequest)o[0];
	  request.setAttribute("msg", "영화 목록");
	  System.out.println("요청 처리 수행 시간:"+(end-start));
	  return obj;
  }
}
