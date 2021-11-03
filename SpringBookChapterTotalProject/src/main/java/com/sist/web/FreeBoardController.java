package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/*
 *   1. VO == O
 *   2. Mapper == O
 *   3. DAO == O
 *   ===============================> 외부 데이터 (뉴스 , JSON,공공데이터..., OPEN API(카카오맵,네이버 블러그)
 *      => 책 (알라딘 , 교보)
 *      => JSOUP 
 *   4. Controller => DAO에서 보내주는 데이터를 받아서 JSP
 *   5. JSP = 보내준 데이터 출력 
 *   
 *   ==> 패키지 
 *       => DAO => com.sist.dao
 *       => VO  => com.sist.vo
 *       => Mapper => com.sist.mapper => 한번에 구현 
 *       => Controller => com.sist.web
 *       => Manager => com.sist.manager 
 *       ===============================
 *       com.sist.board.dao
 *       com.sist.loc.dao
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.dao.FreeBoardDAO;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.vo.*;
// DispatcherServlet (모든 기능 : 사용자 요청 , 요청 처리 , 결과값 응답)
// 요청을 처리할 수 있게 위임 => 개발자 
@Controller
public class FreeBoardController {
   //DAO를 가지고 온다 => 구현된 DAO (스프링이 가지고 있다) FreeBoardDAO dao=new FreeBoardDAO()(구현이 안된다)
   @Autowired
   private FreeBoardDAO dao;
   
   // 사용자가 요청한 내용을 처리 => 화면이동 
   /*
    *    <jsp:include page="header.jsp"> 메뉴
    *    =====================================
    *    
    *      <jsp:include page="${main_jsp}"> => 변경이 가능 
    *     
    *    =====================================
    *    <jsp:include page="footer.jsp"> 회사정보 
    */
   @GetMapping("freeboard/list.do") // 목록을 요청했다면 => if (어노테이션 : 구분)
   public String freeboard_list(String page, Model model)
   {
	   //Model은 JSP로 데이터 전송하는 역할 (request)
	   // 게시판 출력 : 포함 
	   // 데이터 읽기 
	   if(page==null) // 게시판늘 클릭하면 => page를 지정할 수 없기 때문에 default설정 
		   page="1";
	   int curpage=Integer.parseInt(page); // 현재페이지 => 1page
	   //int curpage=page;
	   // 1page => 1~15
	   Map map=new HashMap();
	   int rowSize=15;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   map.put("start", start);
	   map.put("end", end);
	   List<FreeBoardVO> list=dao.freeboardListData(map);
	   // 총페이지 
	   int totalpage=dao.freeboardTotalPage();
	   // 페이지 => 데이터가 많은 경우 => 블록 
	   // 이전 / 다음 
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("list", list);
	   model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	   model.addAttribute("main_jsp", "../freeboard/list.jsp");
	   return "main/main";
   }
   
   @GetMapping("freeboard/insert.do")
   public String freeboard_insert(Model model)
   {
	   model.addAttribute("main_jsp", "../freeboard/insert.jsp");
	   return "main/main";
   }
   
   @PostMapping("freeboard/insert_ok.do")
   public String freeboard_insert_ok(FreeBoardVO vo)
   {
	   dao.freeboardInsert(vo);
	   return "redirect:../freeboard/list.do";
   }
   
   @GetMapping("freeboard/detail.do")
   public String freeboard_detail(int page,int no,Model model)
   {
	   // 1. 게시물에 대한 상세보기 데이터 전송
	   FreeBoardVO vo=dao.freeboardDetailData(no);
	   // 2. 댓글 전송 
	   model.addAttribute("vo", vo);
	   model.addAttribute("page", page);
	   model.addAttribute("main_jsp", "../freeboard/detail.jsp");
	   return "main/main";
   }
   // 예측 (유도)  => 매개변수 
   // 화면 출력 => 필요한 데이터 
   // 정답이 없다 => 사용자중심의 프로그램 (Actor) => 등급 (IERP) => 회귀 
   /*
    *     MainFrame (IBM) = C/S = Grid 
    *                             ===== 클라우드 ==== 감성 컴퓨팅 (AI) 
    *                                     | 데이터 분석 => 빅데이터 
    *                                   Empty 컴퓨터  => AWS , 공유 오피스 
    *                                     |
    *                                     ?  ====> 영국
    *     Front 
    *       JavaScript : 로직이 틀리다 (개발자)
    *          = Jquery = Ajax 
    *            ==============
    *             VueJS,ReactJS => 검색 (페이스북) 
    *              |
    *            AngularJS 
    *     J2ME => WML => Android(코틀린) / iphone 
    *                    ======================== React-Native (Mobile-Jquery) => 브라질  
    */
   // 수정 
   @GetMapping("freeboard/update.do")
   public String freeboard_update(int no,int page,Model model)
   {
	   // 수정 => 이전에 입력된 데이터를 읽는다 
	   FreeBoardVO vo=dao.freeboardUpdateData(no);
	   model.addAttribute("vo", vo);
	   model.addAttribute("page", page);
	   model.addAttribute("main_jsp", "../freeboard/update.jsp"); //main_jsp (main.jsp중간에 첨부)
	   return "main/main";
   }
   // 삭제 
   @GetMapping("freeboard/delete.do")
   public String freeboard_delete(int no,int page,Model model)
   {
	   model.addAttribute("no", no);
	   model.addAttribute("page", page);
	   model.addAttribute("main_jsp", "../freeboard/delete.jsp");
	   return "main/main";
   }
}














