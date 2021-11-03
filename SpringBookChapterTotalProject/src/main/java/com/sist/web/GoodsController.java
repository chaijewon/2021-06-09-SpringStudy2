package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.data.*;
/*
 *    사용자 정의로 생성 ==> new => 빈공백으로 생성 
 *    스프링에서 객체 생성 ==> @Autowired , @Resource
 */
@Controller
public class GoodsController {
   // DAO 주소값 받기 (생성이 스프링)
   @Autowired
   private CartDAO dao;
   /*
    *   구분 : GET/POST 
    *   GET  ==> <a> , sendRedirect() , location.href , default 
    *   POST ==> <form> , ajax , axios.post():post,axios.get()
    *   ======================================================== @RequestMapping(GET/POST)
    *   
    *   로직이 없다 (프로그램에서 가장 쉬운 프로그램)  => 웹프로그램 
    *   => 사용자가 어떤 값을 보내줄지 (요청)
    *   => 요청 처리 => DAO,Manager
    *   => 어떤 데이터를 보낼 것인지 
    */
   @GetMapping("cart/list.do")
   // 메소드를 찾아서 호출 => HandlerMapping 
   // DispatcherServlet(HandlerMapping+ViewResolver) ==> *.do
   // DispatcherServlet => 찾기 (web.xml) => <url-pattern>*.do</url-pattern>
   // HandlerMapping => URI주소 => 어노테이션 
   // ViewResolver => application-context.xml 
   /*
    *   스프링 MVC 
    *   1. JSP 
    *   2. DispatcherServlet 
    *      = 요청처리 메소드를 찾아서 호출 (HandlerMapping) 
    *      = JSP출력할 필요한 데이터 전송 (ViewResolver)
    *      = 사용자 보낸값를 처리 (매개변수를 이용해서 전송)
    *      
    *   ==> cart/aaa.do
    *   class A
    *   {
    *      구분 ==> 어노테이션 (찾기=인덱스) => 조건 (if한개를 추가) => 메소드명은 개발자마다 ...
    *      public void aaa(){}
    *      구분
    *      public void bbb(){}
    *      구분
    *      public void ccc(){}
    *   }
    *   invoke(String , Object...) => instanceof (객체 비교)
    */
   public String cart_list(String page,Model model,HttpServletRequest request)
   {
	   // Model => 데이터를 JSP로 전송시에만 필요 (전송 객체) => forward일때 사용  return "경로명/jsp명"
	   // sendRedirect ==> RedirectAttributes  ==> return "redirect:" => _ok (insert,update,delete)
	   // dml(insert,update,delete) , dql(select)
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   // 현재 페이지에 해당되는 데이터를 오라클에서 가지고 온다 
	   int rowSize=20;
	   int start=(rowSize*curpage)-(rowSize-1); // 1 , 21 , 41....
	   //        20*1 = 20 - 19  ==> 1 
	   //        20*2 = 40 -19   ==> 21
	   int end=rowSize*curpage; // 20,40,60..
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);   
	   // WHERE num BETWEEN #{start} AND #{end}
	   //                   ======== map.get("start")  ===== map.get("end")
	   List<GoodsVO> list=dao.goodsListData(map); // start,end
	   // 총페이지 => 
	   int totalpage=dao.goodsTotalPage();
	   // 블록별 페이지 나누지   <  1 2 3 4 5 6 7 8 9 10 > => 11~
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;  // (10-1)/10 => 0 9/10 => +1 => 1
	   // curpage 11 ==> 11/10=> 1*10 => 10+1 ==> 11
	   // startPage => 현재페이지(curpage) 1~10 ==> 1
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   // endPage
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   // 데이터를 JSP로 전송 (ViewResolver)
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   model.addAttribute("BLOCK", BLOCK);
	   model.addAttribute("list", list);
	   model.addAttribute("main_jsp", "../cart/list.jsp"); // include
	   
	   // 쿠키 읽기
	   Cookie[] cookies=request.getCookies();
	   List<GoodsVO> dList=new ArrayList<GoodsVO>(); 
	   if(cookies!=null && cookies.length>0) // 쿠키에 값이 있는 경우 => "g"+1  h loc  g1 1
	   {
		   for(int i=cookies.length-1;i>=0;i--)
		   {
			   if(cookies[i].getName().startsWith("g"))
			   {
				   String no=cookies[i].getValue();
				   GoodsVO vo=dao.goodsDetailData(Integer.parseInt(no));
				   dList.add(vo);
			   }
		   }
		   
	   }
	   model.addAttribute("dList", dList);
	   // 화면 출력 
	   return "main/main"; // header(메뉴) , footer를 유지 상태 => cart/list.jsp
   }
   // 한개의 메소드안에서는 쿠키전송 , HTML을 동시처리가 불가능 (response => 한개만 전송 (쿠키,HTML))
   // 쿠키 저장
   @GetMapping("cart/detail_before.do")
   public String cart_detail_before(int no,HttpServletResponse response,RedirectAttributes attr)
   {
	   // Model : forward 전송
	   // RedirectAttributes : sendRedirect
	   //1. cookie설정 (쿠키는 저장할 수 있는 데이터가 반드시 String만 가능하다)
	   Cookie cookie=new Cookie("g"+no,String.valueOf(no));// (String,String)
	   // session => (String(key),Object)
	   //2. path
	   cookie.setPath("/"); // 루트에 저장   /goods
	   //3. 기간 (저장)
	   cookie.setMaxAge(60*60*24); //24시간 (초단위)
	   //4. 클라이언트 브라우저로 전송 
	   response.addCookie(cookie); 
	   
	   // 이동 => 상세보기 
	   attr.addAttribute("no", no);
	   return "redirect:../cart/detail.do";
   }
   
   // 실제 상세보기 
   @GetMapping("cart/detail.do")
   public String cart_detail(int no,Model model)
   {
	   // 오라클에서 데이터 읽기 
	   GoodsVO vo=dao.goodsDetailData(no);
	   vo.setNo(vo.getProduct_id());
	   model.addAttribute("vo", vo);
	   model.addAttribute("tno", 7);
	   model.addAttribute("main_jsp", "../cart/detail.jsp");
	   return "main/main";
   }
   // 장바구니 
   @PostMapping("cart/cart_ok.do")
   public String catr_cart_ok(int product_id,int amount,HttpSession session)
   {
	   CartVO vo=new CartVO();
	   vo.setProduct_id(product_id);
	   vo.setAmount(amount);
	   String id=(String)session.getAttribute("id");
	   vo.setId(id);
	   // 오라클 전송 
	   dao.cartInsert(vo);
	   return "redirect:../page/mypage.do"; // adminpage 
   }
   
   @GetMapping("page/mypage.do")
   public String page_mypage(HttpSession session,Model model)
   {
	   // 데이터 읽기 
	   String id=(String)session.getAttribute("id");
	   // 오라클 연결 
	   List<CartVO> list=dao.cartListData(id);
	   model.addAttribute("list", list);
	   model.addAttribute("main_jsp", "../page/mypage.jsp");
	   return "main/main";
   }
   
   @GetMapping("page/adminpage.do")
   public String page_adminpage(Model model)
   {
	   // 구매한 모든 내용을 보내준다 
	   List<CartVO> list=dao.cartAdminListData();
	   model.addAttribute("list", list);
	   model.addAttribute("main_jsp", "../page/adminpage.jsp");
	   return "main/main";
   }
   /*
    *   1. 요청   ==> JSP안에서 <a> , <form> , ajax , axios  => .do (@Controller,@RestController)
    *      @Controller : JSP화면 변경 (forward=> return "폴더명/JSP명",
    *      sendRedirect=> return "redirect:~")
    *        => 데이터만 전송 
    *        => @ResponseBody
    *      @RestController : 데이터만 전송 (데이터가 많은 경우 : JSON) => JavaScript (Ajax,ReactJS,VueJS)
    */
   @GetMapping("page/goodsAdminYes.do")
   public String page_goodsAdminYes(int no,HttpSession session)
   {
	   // 구매내역을 이메일 (id,password) ==> JMail (라이브러리)
	   //DAO연동 
	   String name=(String)session.getAttribute("name");
	   CartVO vo=dao.cartYesData(no);
	   MailSender.naverMailSend(vo,name);
	   dao.goodsAdminYes(no);
	   return "redirect:../page/adminpage.do";
   }
   // 스프링에서 제어를 하기 위한 조립(클래스)
   @GetMapping("page/goodsYes.do")
   public String page_goodsYes(int no)
   {
	   // DAO => Update (issale:0=>1)
	   dao.cartSaleUpdate(no);
	   return "redirect:../page/mypage.do";
   }
   @GetMapping("page/goodsNo.do")
   public String page_goodsNo(int no)
   {
	   // DAO => Delete 
	   dao.cartSaleDelete(no);
	   return "redirect:../page/mypage.do";
   }
   
  
}








