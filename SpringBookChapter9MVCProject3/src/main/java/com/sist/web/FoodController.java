package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *    에러 : => xml 
 *         web.xml : <servlet-name> , class명이 틀릴 경우 : 톰캣이 읽어가는 파일 
 *                   서블릿 등록 / 한글변환  / 에러 코드
 *                   ======== Controller (보통 : 서블릿)
 *                   => DispatcherServlet : 스프링(*****)
 *                   => FilterDispatcher : 스트럿츠2
 *                   => ActionServlet : 스트럿츠
 *         pom.xml : 라이브러리 로딩( Maven은 반드시 pom.xml에 등록이 있는 라이브러리만 인식 )
 *         server.xml : 서버 관련 
 *         application-context.xml : 스프링에서 관리할 클래스 등록파일 
 */
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
// MVC구조의 Model인 것을 알려준다 => HandlerMapping에 요청시 찾아준다 

@Controller
@RequestMapping("food/") //  중복이 있는 경로가 있는 경우 => 여기서 처리 (마이바티스,스프링=>항상 중복이 있는 경우 처리 가능)
public class FoodController {
   // 필요한 객체를 스프링에 자동 주입 요청
	@Autowired
    private FoodDAO dao;
	
	// 사용자 요청시 처리 => 목록 요청 
	@RequestMapping("list.do")
	// public String food_list(String address,Model model)
	// Spring 2.5
	// Spring 3~5
	// 실무 4버전 => XML , 5버전 => Java
	public String food_list(HttpServletRequest request,HttpServletResponse response)
	{
		// 한글 => 검색어 
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
	    String page=request.getParameter("page");
	    if(page==null)
	    	 page="1"; // default 
		String address=request.getParameter("address");
		if(address==null)
			address="마포구"; // default 
		// 오라클로부터 데이터 얻기 
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("address", address);
		map.put("start",start);
		map.put("end", end);
		// #{start} AND #{end} => map.get("start") , map.get("end") => map (키지정) #{start:map키}
		// INSERT ~~~ VALUES(#{id}) ==> vo.getId()
		List<FoodVO> list=dao.foodFindData(map);
		for(FoodVO vo:list)
		{
			String addr=vo.getAddress();
			String s1=addr.substring(addr.indexOf(" ")+1);
			String s2=s1.substring(s1.indexOf(" ")+1);
			String s3=s2.substring(0,s2.indexOf(" "));
			vo.setAddress(s3);
		}
		// 총페이지 
		int totalpage=dao.foodTotalPage(address);
		int count=dao.foodCount(address);
		// list.jsp 보내주는 데이터 5개 
		request.setAttribute("list", list);
		request.setAttribute("count", count);// 검색 갯수
		request.setAttribute("curpage", curpage);// 현재 페이지
		request.setAttribute("totalpage", totalpage);// 총페이지
		request.setAttribute("address", address);// 검색어 
		return "food/list"; // .jsp는 viewResolver에서 처리 => 확장자를 붙이면 안된다 
	}
	// 상세 요청 ==> 조립기 (요청 => 데이터 처리 => 데이터 전송) => Model (main())
	@RequestMapping("detail.do")
	public String food_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		FoodVO vo=dao.foodDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		return "food/detail";
	}
} 















