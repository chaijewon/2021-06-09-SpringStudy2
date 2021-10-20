package com.sist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 *       Spring 
 *       ======
 *       1. setting (String동작을 위한 셋팅) => web.xml , application-*.xml
 *       2. 사용자 요청 처리 
 *          @Controller => 메소드 @RequestMapping,@GetMapping,@PostMapping
 *       3. JSP (자바 코딩을 하지 않는다 => JSTL/EL)
 *       ================================================================
 *       MyBatis는 데이터베이스 연결 (일반 JDBC,DBCP,ORM) => 설정 따로 된다 
 *       ==========> mybatis-spring(호환)
 */
// 로그 파일 만들때 
public class CommonLogInterceptor extends HandlerInterceptorAdapter{

	// Controller => @RequestMapping에 해당되는 메소드 수행전 => @Before
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("1. Client 요청 Start ============");
		System.out.println("2. 요청 주소:"+request.getRequestURI());
		System.out.println("3. ========= 요청 준비 끝 ==========");
		return super.preHandle(request, response, handler);
	}
    // Controller => @RequestMapping에 해당되는 메소드 수행후 ==> @After-Returning
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("1 Client 요청에 대한 처리 종료 =======");
		System.out.println("2. JSP로 값을 전송한다 ==============");
		System.out.println("3. Controller 수행 완료============");
		
		super.postHandle(request, response, handler, modelAndView);
	}
    // Controller와 View(JSP) 수행 완료 되었을때  => 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("======= JSP 화면 출력 완료 ===========");
		super.afterCompletion(request, response, handler, ex);
	}
  
}
