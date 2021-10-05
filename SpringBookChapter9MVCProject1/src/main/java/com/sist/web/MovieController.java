package com.sist.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.dao.*;
/*
 *    ~Model : 사용자 정의
 *    ~Controller : Spring(Model)
 *    ~Action : Struts 
 */
// 메모리 할당 => Model클래스인 것을 알려준다 
@Controller
public class MovieController {
   @Autowired
   private MovieDAO dao;
   
   @RequestMapping("movie/list.do")
   public String movie_list(HttpServletRequest request,HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   map.put("start", start);
	   map.put("end", end);
	   List<MovieVO> list=dao.movieListData(map);
	   int totalpage=dao.movieTotalPage();
	   // JSP로 전송 
	   request.setAttribute("list", list);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("curpage", curpage);
	   return "movie/list"; //=> 확장자를 저장 하고 있다 
   }
}






