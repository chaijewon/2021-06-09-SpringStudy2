package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
@Controller
public class SeoulController {
   // DAO 
	@Autowired  // 스프링에서 메모리 할당된 클래스만 자동 주입이 가능 
	private SeoulDAO dao;
	
	@RequestMapping("seoul/location.do")  // POST , GET ==> 혼합
	public String seoul_location(String page,Model model)
	{
		// JSP ==> 데이터 (request => model) => 출력할 JSP를 지정 
		// @Controller ==> 메소드 리턴형 (String , void:다운로드) => JSP,.do
		if(page==null)
			page="1"; // default 
	    int curpage=Integer.parseInt(page);
	    Map map=new HashMap();
	    int rowSize=12;
	    int start=(rowSize*curpage)-(rowSize-1); // rownum => 1번부터 시작 
	    // 1, 13 ....
	    int end=rowSize*curpage; // 12, 24 , 36...
	    
	    // 데이터 읽기 
	    map.put("start", start);
	    map.put("end", end);
	    
	    List<SeoulLocationVO> list=dao.locationListData(map);
	    int totalpage=dao.locationTotalPage();
	    
	    final int BLOCK=5;
	    int startPage=((curpage-1)/BLOCK*BLOCK)+1;// 1~5 => 1 , 6~10 => 6
	    /*
	     *     [1][2][3][4][5]
	     *     
	     *     => curpage => 1~5
	     */
	    int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; // 5 10 15 20...
	    
	    if(endPage>totalpage)
	    	endPage=totalpage;
	    
	    // JSP로 전송 
	    model.addAttribute("curpage", curpage);
	    model.addAttribute("totalpage", totalpage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    model.addAttribute("list", list);
	    model.addAttribute("main_jsp", "../seoul/location.jsp");
		return "main/main";
	}
	
	
}











