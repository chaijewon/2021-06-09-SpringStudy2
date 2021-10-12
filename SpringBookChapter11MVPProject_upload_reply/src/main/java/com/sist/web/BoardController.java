package com.sist.web;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.dao.*;
/*
 *    public class DispatcherServlet extends HttpServlet
 *    {
 *       // web.xml => 사용자가 등록한 클래스 => application-*.xml  => 메모리 할당 (대기)
 *       HandlerMapping hm;
 *       public void init(ServletConfig config)
 *       {
 *          String path=config.getInitParameter("contextConfigLocation");
 *          hm=new HandlerMapping(path); //XML를 파싱 => 요청시마다 @RequestMapping을 찾아서 밑에 있는 메소드 호출 
 *       }
 *    }
 *    =============================WebApplicationContext=========================================
 *    public class HandlerMapping
 *    {
 *       public String jspFind(String uri)
 *       {
 *           => 메소드 호출후에 return => ViewResolver
 *           => model
 *       }
 *    }
 *    public class Model 
 *    {
 *       HttpServletRequest request;==> DispatcherServlet에서부터 받아 온다 
 *       public Model(HttpServletRequest request)
 *       {
 *           this.request=request;
 *       }
 *       public void addAttribute(String key,Object obj)
 *       {
 *           request.setAtrtribute(key, obj);
 *       }
 *    }
 *    ========================================================================
 *    
 */
@Controller // Model클래스(요청 => 데이터베이스 연결 => 결과 처리 => 데이터 전송)
public class BoardController {
    @Autowired
    private BoardDAO dao;
    
    // 로그인 폼 요청 
    @GetMapping("board/login.do")
    public String board_login()
    {
    	return "board/login";
    }
    // 로그인 처리 요청 
    @PostMapping("board/login_ok.do")
    public String board_login_ok(String id,String pwd,Model model,HttpSession session)
    {
    	System.out.println("id="+id);
    	System.out.println("pwd="+pwd);
    	// 사용자가 보내준 데이터를 request(X) , DispatcherServlet을 통해서 값을 받는다 (각 메소드릐 매개변수를 이용한다)
    	// Model => 전송 객체  addAttribute() => request.setAttribute()
    	// HttpSession session=request.getSession() => request를 이용해서 얻어온다 
    	// request를 사용하지 않기 때문에 DispatcherServlet을 통해서 session을 얻어온다  (반드시 매개변수를 이용해서 받아온다)
    	MemberVO vo=dao.isLogin(id, pwd);
    	if(vo.getMsg().equals("OK")) // 로그인 처리 완료  => session에 등록 
    	{
    		session.setAttribute("id", vo.getId());
    		session.setAttribute("name", vo.getName());
    	}
    	// 화면 이동 
    	model.addAttribute("vo", vo); // NOID , NOPWD , OK => 이동화면이 다르다 
    	return "board/login_ok"; // @RestController 
    }
    // 데이터보드 요청 
    @GetMapping("board/list.do")
    public String board_list()
    {
    	return "board/list";
    }
    // 상세 요청 
    // 글쓰기 요청 
    // 다운로드 요청 
}








