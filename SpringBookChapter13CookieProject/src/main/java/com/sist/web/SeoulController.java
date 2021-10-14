package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
// 요청 처리 => 화면 이동 
@Controller
public class SeoulController {
   @Autowired
   private HotelDAO hdao;
   
   // 사용자 요청 : 1. 호텔 목록을 요청 
   @GetMapping("hotel/list.do")
   /*
    *   @Controller에서 메소드 형식
    *   1. 메소드명은 규정이 없다 (개발자가 지정)
    *   2. 리턴형 : String(대부분:화면이동이 가능하게 jsp지정(forward),.do지정(sendRedirect) , void (download)
    *   3. 매개변수 : 내장 객체를 받을 수 있고 , 객체 단위 , 일반 변수 , 전송객체 사용
    *      => 요청변수와 일치가 되야 한다 , 데이터형 설정 => 설정이 틀리면 400 bad request
    *      => list.do?page=1&check=true
    *        ===========================
    *        list(int page, int check) => 400
    *        list(String page , String check) => 정상수행 (모든 요청변수는 String으로 받을 수 있다)
    *                           => Boolean.parseBoolean(check)
    *        list(int pg,int ck) => 데이터를 받지 못한다
    *        
    *        // 
    */
   public String hotel_list(String page,Model model,HttpServletRequest request)
   {
	   // 첫페이지는 지정이 불가능 : default로 설정 
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   //시작위치 , 마지막위치 => rownum
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1); // 1~12 , 13~
	   int end=(rowSize*curpage);
	   Map map=new HashMap();
	   // #{start} => key명  ==> map.get("start")
	   // #{name} => vo.getName() #{address} => vo.getAddress()
	   map.put("start", start);
	   map.put("end", end);
	   // 목록을 마이바티스로부터 받는다 
	   List<HotelVO> hList=hdao.hotelListData(map);
	   // 총페이지 
	   int totalpage=hdao.hotelTotalPage();
	   // 블럭별 나누기 
	   final int BLOCK=5;// [1]...[5] >  , < [6]~[10] > => 상수처리 
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1; // 1,6,11,16...
	   /*
	    *     (curpage-1)/BLOCK ==> 1~5 => 0
	    *       1-1/5 => 0/5 =0
	    *       2-1/5 => 1/5 =0
	    *       3-1/5 => 2/5 =0
	    *       4-1/5 => 3/5 =0
	    *       5-1/5 => 4/5 =0
	    *       
	    *     (curpage-1)/BLOCK*BLOCK
	    *       6-1    5/5*5 =>  5 + 1  ==> 6
	    *       7-1    6/5*5 => 5+1     ==> 6
	    *       8-1
	    *       9-1
	    *       10-1   9/5*5 
	    *              ===
	    *               1*5  ==> 5+1    ==> 6
	    */
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;// (1~5)5, (6~10)10, (11~15)15 ....
	   // curpage => 1~5 : startPage=1 , curpage => 6~10 : startPage=6
	   // totalpage = 13  => endPage = 15 => 13
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   // list.jsp로 전송해서 출력이 가능하게 만든다 => 출력완료 => 요청한 클라이언트의 브라우저 읽어 간다 
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("BLOCK", BLOCK);
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   model.addAttribute("list", hList);//request.setAttribute("list",hList);
	   // <c:forEach var="vo" items="${list}">
	   // ${list} => request.getAttribute("키") => request.getAttribute("list");
	   // 쿠키에 저장된 데이터를 보내준다 => 1.@CookieValue() , HttpServletRequest이용
	   // 쿠키 : 단점 (보안에 취약 , 다른 컴퓨터를 이용시에는 볼 수 없다,문자열만 저장이 가능), 장점(Map형식이기 때문에 중복이 없다)
	   // 쿠키 => 사용 용도 (방문,자동 로그인)
	   Cookie[] cookies=request.getCookies();
	   List<HotelVO> cList=new ArrayList<HotelVO>();
	   if(cookies!=null && cookies.length>0) //쿠키에 값이 존재 할때
	   {
		   for(int i=cookies.length-1;i>=0;i--) // 저장된 역순으로 (최신)
		   {
			   if(cookies[i].getName().startsWith("h"))
			   {
				   cookies[i].setPath("/");
				   String no=cookies[i].getValue(); // 번호 
				   HotelVO vo=hdao.hotelDetailData(Integer.parseInt(no));
				   cList.add(vo);
			   }
		   }
	   }
	   model.addAttribute("cList", cList);// 쿠키에 저장된 값을 보내준다 
	   return "hotel/list"; // forward => 해당 JSP에 request를 전송시에 사용 
   }
   /*
    *   쿠키에 저장 ==> HttpServletResponse (클라이언트로 전송)
    *               ====================
    *               직접 생성이 안된다 (ip,port) => 톰캣 => DispatcherServlet로 전송 
    *   쿠키를 읽어온다 ==> HttpServletRequest 
    */
   @GetMapping("hotel/detail_before.do")
   public String hotel_detail_before(int no,int page,HttpServletResponse response,
		    RedirectAttributes attr)
   {
	   // 쿠키 저장 
	   Cookie cookie=new Cookie("h"+no, String.valueOf(no)); // 중복
	   // 쿠키는 저장할 수 있는 데이터 => 문자열 
	   // 저장의 위치 지정
	   cookie.setPath("/");//루트에 저장 
	   // 저장 기간 
	   cookie.setMaxAge(60*60*24);//24시간 (초단위)
	   // 쿠키를 클라이언트로 전송 
	   response.addCookie(cookie);
	   
	   attr.addAttribute("no", no);
	   attr.addAttribute("page", page);
	   // RedirectAttributes attr => redirect일 경우에 사용 
	   return "redirect:detail.do"; // detail.do?no=1&page=2 => request를 초기화
   }
   // 상세 보기 
   @GetMapping("hotel/detail.do")
   public String hotel_detail(int no,int page,Model model)
   {
	   // Model => forward에서 데이터 전송 
	   // RedirectAttributes => sendRedirect에서 데이터 전송 
	   HotelVO vo=hdao.hotelDetailData(no);
	   model.addAttribute("vo", vo);
	   model.addAttribute("page", page);
	   return "hotel/detail"; //request를 전송 
   }
}











