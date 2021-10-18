package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
// 메모리 할당 
@Controller
@RequestMapping("replyboard/") // 중복된 경로명을 저장
public class ReplyBoardController {
   // 구현된 DAO를 받아 온다 
   @Autowired
   private ReplyBoardDAO dao;
   
   // 목록 출력 => replyboard/list.do?page=1 (요청) 
   // => Controller <==> ReplyBoardDAO 
   // => Model(값을 담는다:결과값) => ViewResolver => 해당 JSP
   // => Model (HttpServletRequest)를 가지고 있다 
   /*
    *   @Controller 
    *    화면 이동 (JSP에 값을 전송한 후에 화면 이동) 
    *    => return을 통해서 => 출력할 JSP지정 => 항상 리턴형 => String
    *    => 특이한 경우 : 다운로드 (void)
    *    => String / void(Ajax) : 화면 변경이 없는 경우 
    */
   @GetMapping("list.do")
   public String replyboard_list(String page,Model model)
   {
	   // 페이지 나누기 
	   if(page==null)
		   page="1"; // 메뉴 클릭시에 => 페이지가 존재하지 않는 경우 (default)
	   // 현재 페이지 지정 
	   int curpage=Integer.parseInt(page);
	   // Map (start/end)
	   Map map=new HashMap();
	   int rowSize=10;
	   int start=(rowSize*curpage)-(rowSize-1); // rownum은 1번부터 ,자바 0번부터
	   int end=(rowSize*curpage);
	   map.put("start", start);
	   map.put("end", end);
	   // WHERE num BETWEEN #{start} AND #{end} => Map(#{start}=>키명)
	   List<ReplyBoardVO> list=dao.replyBoardListData(map);
	   // 1. 현재 페이지 
	   model.addAttribute("curpage", curpage);
	   // 2. 총페이지
	   int totalpage=dao.replyboardTotalPage();
	   model.addAttribute("totalpage", totalpage);
	   // 3. 목록 
	   model.addAttribute("list", list);
	   // 4. 오늘 날짜
	   Date date=new Date();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	   model.addAttribute("today", sdf.format(date));// new => dbday == today => new
	   model.addAttribute("main_jsp", "../replyboard/list.jsp");// 데이터를 받아서 출력
	   return "main/main";// replyboard/list=> include (메뉴/footer유지)
   }
   // 컨트롤러 : 화면 이동 / 데이터 요청값 받기 / 결과값 보내기 
   //         =======  ==============  =========
   //          return   매개변수로 처리        Model => model.addAttribute() (DAO연결)
   // 화면 이동 (목록/폼출력/<a>) ==> PostMapping(<form>)
   @GetMapping("insert.do")
   public String replyboard_insert(Model model)
   {
	   model.addAttribute("main_jsp", "../replyboard/insert.jsp");
	   return "main/main";//forward(request를 전송)
   }
   /*
    *    replyboard/list.do?page=1 
    *    ========================= ***메소드(String page) , 메소드(int page)
    *    replyboard/detail.do?no=1 
    *    ========================= ***메소드(int no)
    *    replyboard/detail.do?no=1&page=1 
    *    ================================ 메소드(int no,int page) 
    *                        ?보내는 변수가 여러개 있는 경우 (~VO안에 있는 변수)
    *                                     메소드(~VO vo)
    *    => <input type="checkbox" value="N" class="input-sm" name="fs">이름
            <input type="checkbox" value="S" class="input-sm" name="fs">제목
            <input type="checkbox" value="C" class="input-sm" name="fs">내용
                                                                                    메소드(String[] fs)
                                                                            
            <input type=text name=ss[0] size=15 class="input-sm">
            <input type=text name=ss[1] size=15 class="input-sm">
            <input type=text name=ss[2] size=15 class="input-sm">
                                                                    메소드(List<String> list)
             ==> JSP로 값을 보내는 경우에는 ==> Model
    */
   // 실제 데이터 추가 
   @PostMapping("insert_ok.do")
   public String replyboard_insert_ok(ReplyBoardVO vo) // Command객체 (객체단위로 값을 받는다)
   {
	   // redirect가 있는 경우는 request를 초기화하기 때문에 Model은 필요가 없다 
	   dao.replyBoardInsert(vo);
	   return "redirect:../replyboard/list.do";// 추가된 목록을 보여준다 (목록이 제작 => 이동)   
   }
   // ../replyboard/detail.do?no=${vo.no }&page=${curpage}
   @GetMapping("detail.do")
   public String replyboard_detail(int no,int page,Model model)
   {
	   // 결과값 읽기 => DAO연결 
	   // 내용보기 데이터 가지고 오기 
	   ReplyBoardVO vo=dao.replyBoardDetailData(no);
	   // 데이터를 JSP로 전송 
	   model.addAttribute("vo", vo);
	   model.addAttribute("curpage", page);
	   model.addAttribute("main_jsp", "../replyboard/detail.jsp");
	   return "main/main";
   }
   
   // ../replyboard/reply.do?no=${vo.no }&page=${curpage}
   @GetMapping("reply.do")
   public String replyboard_reply(int no,int page,Model model)
   {
	   model.addAttribute("no", no);
	   model.addAttribute("page", page);
	   model.addAttribute("main_jsp", "../replyboard/reply.jsp");//답변 폼 
	   return "main/main";
   }
   // ../replyboard/reply_ok.do
   @PostMapping("reply_ok.do")
   public String replyboard_reply_ok(int pno,int page, ReplyBoardVO vo,RedirectAttributes attr)
   {
	   dao.replyBoardReplyInsert(pno, vo);
	   attr.addAttribute("page", page); // 데이터 전송 => xml에 셋팅 
	   // RedirectAttributes(sendRedirect()) , Model (forward일 경우에 사용) => 데이터 전송이 가능 
	   return "redirect:../replyboard/list.do";// page => list.do?page=+page
	   // http://localhost:8080/web/replyboard/list.do?page=2
   }
   // ../replyboard/update.do?no=${vo.no }&page=${curpage} => 요청 = 수정 폼을 보여달라 
   /*
    *    detail.do ======> update.jsp(X)
    *                      @Controller
    *                      @GetMapping("update.do") ======> update.jsp
    *              no,page                          no,page
    */
   @GetMapping("update.do")
   public String replyboard_update(int no,int page,Model model)
   {
	   // no,page를 전송 
	   model.addAttribute("no", no);
	   model.addAttribute("page", page);
	   // => 이전에 입력된 게시물을 전송 
	   ReplyBoardVO vo=dao.replyBoardDetailData(no);
	   model.addAttribute("vo", vo);
	   // JSP(Model1) , MVC(Model2) 
	   // => 보안(X), 확장성 낮고 , 혼자서 제작 
	   // M(Back-End)V(Front-End) , 보안(.class) , 확장성, 재사용이 높다 , 여러명이 동시 작업이 가능  
	   model.addAttribute("main_jsp", "../replyboard/update.jsp");
	   return "main/main";
   }
   // ../replyboard/update_ok.do
   /*
    *   {"no":no,"page":page,"name":name,"subject":subject,"content":content,"pwd":pwd};
    *   type:'post'
    */
   @GetMapping("update_ok.do") // 파일명(JSP명) 
   @ResponseBody // 데이터만 전송한다 
   public String replyboard_update_ok(int page,ReplyBoardVO vo)
   {
	   int no=0;
	   // DAO연결 => 데이터 읽기 
	   no=dao.replyboardUpdate(vo);
	   
	   return String.valueOf(no);
   }
   // ../replyboard/delete.do?no=${vo.no }&page=${curpage}
   @GetMapping("delete.do")
   public String replyboard_delete(int no,int page,Model model)
   {
	   model.addAttribute("no", no);
	   model.addAttribute("page", page);
	   model.addAttribute("main_jsp", "../replyboard/delete.jsp");
	   return "main/main";
   }
}







