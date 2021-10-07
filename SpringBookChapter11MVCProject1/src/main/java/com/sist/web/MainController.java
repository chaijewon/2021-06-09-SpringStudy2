package com.sist.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oracle.jdbc.proxy.annotation.GetProxy;

// Model => 
@Controller 
/*
 *    프로젝트 생성 ==> 1. 버전 변경 
 *                  2. pom.xml 
 *                  3. web.xml 
 *                  4. application-context.xml
 *                  5. 자바 
 *                     === 
 *                     1) Controller 
 *                     2) DAO 
 *                        = VO
 *                        = DAO
 *                        = Mapper
 *    요청 받아서 => 처리 => JSP에 값을 전송 
 *    
 *    1. request를 받아서 처리 
 *    2. DispatcherServlet로 부터 요청값을 받아서 처리  (권장) => 보안
 *       =============
 *         => 요청 처리 (데이터베이스 연결)
 *         => 일반 변수 (int , String , double...)
 *         => 객체단위 (~VO)
 *         => 컬렉션 (List) => name[0] , name[2] , name[3] ....
 *            ================================================
 *              => 멀티 업로드 
 *         => 필요한 모든 자료는 DispatcherServlet으로부터 받는다 
 *            => 매개변수를 이용한다 
 *         예)
 *            public String login(HttpSession session,Cookie cookie)
 *    3. 결과값을 전송 : request , 전송 객체를 이용한다 (Model) => ViewResolver로 전송 
 *       Model ==> model.addAttribute("list",list) => request.setAttribute("list",list)
 *       
 *       C/S   => Client/Server : 요청보내기 / 응답하기 (주고 받기)
 *                => 데이터 보낼때 (매개변수 이용)
 *                => 데이터 받을때 (Model)
 *    4. 해당 메소드 (요청) 
 *       @RequestMapping() : GET/POST
 *         = @GetMapping() : GET ===> <a>,location.href , sendRedirect()  ?page=1
 *         = @PostMapping() : POST ==> form외에는 post방식을 사용 할 수없다  <form method="post">
 *                                     ajax (get/post) , axios.get() , axios.post()
 *                                                       ========================== VueJS ,ReactJS
 *       
 *       http://localhost:8080/web/main/insert.do
 */
/*
 *     HTML/CSS/JavaScript/Kotlin : Client : 결과값을 출력 (Front-End)
 *     JSP/Java/Oracle/Spring/NodeJs : Server : 요청받아서 처리하고 결과값을 보내준다 (Back-End)
 *     ===============================================================================
 *      Front-End + Back-End => Full Stack (Ajax , Vue)
 */
// 요청시마다 호출 => DispatcherServlet : 메소드 호출 , 필요한 매개변수의 값을 넘겨준다 
// main(String[] arg) => JVM => Callback
// service , doGet , doPost ==> 톰캣
public class MainController {
    @RequestMapping("main/insert.do")
    public String main_insert(HttpServletRequest request)
    {
    	try
    	{
    		request.setCharacterEncoding("UTF-8");
    	}catch(Exception ex){}
    	String name=request.getParameter("name");
    	String sex=request.getParameter("sex");
    	String addr=request.getParameter("addr");
    	String tel=request.getParameter("tel");
    	
    	MemberVO vo=new MemberVO();
    	vo.setName(name);
    	vo.setSex(sex);
    	vo.setAddr(addr);
    	vo.setTel(tel);
    	
    	request.setAttribute("vo", vo);
    	return "main/output"; // 받은 데이터를 output.jsp로 전송해서 출력 
    }
    @RequestMapping("main/insert2.do")
    // 페이지 번호 , 로그인(id,pwd) , 상세보기 번호 
    public String main_insert2(String name,String sex,String addr,String tel,Model model)
    {
    	MemberVO vo=new MemberVO();
    	vo.setName(name);
    	vo.setSex(sex);
    	vo.setAddr(addr);
    	vo.setTel(tel);
    	
    	model.addAttribute("vo", vo);// request.setAttribute("vo", vo); 처리 => DAO
    	/*
    	 *   챗봇 ==> 사용자의 채팅문자열을 받는다 
    	 *   처리 ==> 데이터중에 질문한 내용과 유사한 답변을 찾는다 (처리) => 파일,오라클,몽고디비 
    	 *   찾은 데이터 전송  => model.addAttribute("dap",str)
    	 */
    	return "main/output";
    }
    @RequestMapping("main/insert3.do")
    // 회원가입 , 글쓰기 , 수정 , 답변=> VO단위 
    public String main_insert3(MemberVO vo,Model model) // 매개변수를 통해서 요청값 받기(275page)
    {
    	model.addAttribute("vo", vo);
    	return "main/output";
    }
    
    // 화면 출력 => 입력 폼 , 목록 출력 => PostMapping(submit)
    @GetMapping("main/input.do") // 메소드 타입이 틀린 경우에 405
    public String main_input()
    {
    	return "main/input";
    }
    
    @GetMapping("main/print.do")// 사용자가 전송한 값을 DispatcherServlet를 통해서 전송받는 경우 (매개변수를 이용한다)
    // int 설정이 아니라 ==> 값이 없는 경우 ==> String
    // <a href="print.do?page=1">
    public String main_print(int page)
    {
    	System.out.println("전송 받은 값:"+page);
    	return "main/print";
    }
    
    @PostMapping("main/print2.do")
    // <input type=text name=id size=10>
    public String main_print2(String id)
    {
    	System.out.println("전송 받은 값:"+id);
    	return "main/print";
    }
}









