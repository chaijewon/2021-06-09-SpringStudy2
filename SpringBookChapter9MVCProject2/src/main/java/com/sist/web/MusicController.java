package com.sist.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// DispatcherServlet(URL) => HandlerMapping (@RequestMapping) => return에서 주소 => ViewResolver 
// => JSP전송 (request에 값을 담아서 전송) : request.setAttribute() ==> JSP(JSTL/EL) 출력 => 브라우저에 읽기
// 1. DAO연결 , 2. Model을 만들어서 request에 값을 담는다 , 3. JSP출력 
// 셋팅을 한다 
/*
 *    DispatcherServlet 등록 (web.xml) => HandlerMapping 
 *    app.xml에서 ViewResolver등록 (경로명,확장자)
 */
// Model클래스를 알려준다 (HandlerMapping)  => DAO => @Repository => 구분(어노테이션이 하는 역할) => index
import java.util.*;
import com.sist.dao.*;
/*
 *   자바구조 
 *   1. package (한번만 사용) 
 *   2. import (여러번 사용)
 *   3. 어노테이션 
 *   4. public class className
 *      {
 *      }
 */
@Controller
public class MusicController {
	@Autowired
	private MusicDAO dao;
    // 어노테이션으로 구분 
	@RequestMapping("music/list.do") // 사용자가 URL를 http://localhost:8080/web/music/list.do
	// 어노테이션은 찾는 클래스나 메소드 위에 존재 => 실행 (밑에 있는 메소드 , 클래스 실행)
	// 어노테이션은 if 대신 사용하고 있다 (조건 제시) if(url.equals("music/list.do"))
	// 처리 메소드 만들기 
	public String music_list(HttpServletRequest request,HttpServletResponse response) 
	{
		// 요청한 데이터를 받아서 처리 => 데이터베이스에서 값을 읽어서 request에 값을 담아서 전송 
		// request.setAttribute()
		// 오라클에서 데이터를 얻어와서 JSP로 전송 
		List<MusicVO> list=dao.musicListData();
		request.setAttribute("list", list);
		return "music/list"; // 응답할 데이터를 => list.jsp 보낸다 
	}
	@RequestMapping("music/detail.do")
	public String music_detail(HttpServletRequest request,HttpServletResponse response)
	{
		// 사용자가 보내준 번호를 받는다  detail.do?no=1
		String no=request.getParameter("no");
		// 오라클 연결 => no에 해당되는 데이터정보를 달라 
		MusicVO vo=dao.musicDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		return "music/detail"; // detail.jsp
	}
	@RequestMapping("music/join.do")
	public String music_join(HttpServletRequest request,HttpServletResponse response)
	{
		// 오라클에서 데이터를 받아 온다 
		List<GenieVO> list=dao.genieListData();
		// JSP로 전송 => ViewResolver => JSP
		request.setAttribute("list", list);
		return "music/join"; // music폴더안에 join.jsp가 존재 
	}
}
















