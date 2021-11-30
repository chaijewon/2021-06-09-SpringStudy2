package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.news.*;
// 해당 JSP 데이터 전송 
@Controller
public class NewsController {
	@Autowired
    private NewsManager mgr;
	
	@RequestMapping("news/news_find.do")
	public String news_find(String ss,Model model) // Model => 전송 객체 
	{
		if(ss==null)
			ss="맛집";
		List<NewsVO> list=mgr.newsListData(ss);
		// 데이터 전송 (형식=> 요청값 받기(매개변수) , 오라클(DAO),외부데이터(Manager) => 요청 처리 완료)
		// Model을 이용해서 해당 JSP로 값 전송 
		// 1. include 파일 
		model.addAttribute("main_jsp", "../news/news_find.jsp");
		model.addAttribute("list", list);
		return "main/main";
		
	}
}










