package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;//List

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;
// HandlerMapping 찾기 => 실행 => request에 있는 데이터를 JSP로 전송
/*
 *   Spring : 요청 받기 , 요청 결과값 전송 
 *                  요청처리 (데이터베이스)
 *            요청받을때 (데이터값 매개변수로 받는다)
 *            데이터베이스를 통해서 요청 처리
 *            Model을 이용해서 전송 
 *            
 *   클래스관리 
 *   스프링 => 등록된 클래스를 모아서 메모리 할당 (관리될 클래스, 관리하지 않는 클래스 구분: ~VO , interface)
 *                                   ============ ===================
 *                                   Model : 요청 => 요청처리 => 결과값 전송 
 *                                   DAO 
 *                                   =======
 *                                   Manager
 *                                   Service
 *                                   RestController(JSON:16장)
 *           클래스 메모리 할당시에 필요한 데이터가 있을 수도 있다 (데이터값 주입 : DI (생성자,Setter): 라이브러리 클래스 등록)
 *           공통적으로 사용하는 기능이 여러개 있다 (재사용: AOP적용) => 로그파일 , 트랜잭션 , 보안
 *           MVC구조 이해 
 *                              web.xml (*.do)              @Controller => @RequestMapping()
 *           요청  ============ (DispatcherServlet) ======== (HandlerMapping)
 *                                                            |
 *                                                      @Controller(Model)
 *                                                            | request,model
 *                                                         (ViwResolver) => 경로명 / 확장자 
 *                                                            | request
 *                                                           JSP 출력 =========> 브라우저 전송
 *       에러 
 *        404 , 500 , 405 , 400 (292~295page)
 *        =====================
 *        404 : 파일을 찾지 못하는 경우 
 *              1) xml : viewResoler => prefix => "/" , suffix(확장자) .jsp
 *              2) Model => @Controller , @RestController
 *              3) return값 
 *        500 : DAO (SQL문장) => 실행이 안되는 경우  => 정수 => "" 
 *              <a href="list.do? page = 1 ">제목</a>  ==> "1" , " 1"
 *        405 : <a href="list.do"> => Get  ===> Get/Post구분 
 *              ==================
 *              @PostMapping("list.do") => 405  ==> @RequestMapping()(GET/POST를 동시에 처리)
 *        400 : <a href="list.do?name=admin&check=1"> Bad Request
 *              @GetMapping("list.do")
 *              public String list(String name,boolean check)
 *              {
 *              }
 *        415 : 한글 코드 => UTF-8 => UFT-8
 */
@Controller
public class BoardController {
    // 필요한 클래스 객체 (DAO=>자동주입으로 받는다)
	@Autowired
	private BoardDAO dao;
	
	// 사용요청 (목록)
	@GetMapping("board/list.do") // 요청 매핑 GetMapping()
	// DispatcherServlet을 통해서 요청값을 받고, Model을 이용해서 결과값 전송 
	// 매개변수를 통해서 값을 받는 경우 => list.do?page=2 => 넘겨주는 변수명과 매개변수명이 일치해야 값을 받는다 
	// int page ==> list.do
	// 모든 데이터형 (String , 각 데이터 데이터형으로 받을 수 있다) => 상세보기 detail.do?no=1 => int no
	// 첫페이지에 값이 없는 경우 (페이지) ==> String
	public String board_list(String page, Model model)
	{
		// Model => 전송 객체 
		/*
		 *    public class Model
		 *    {
		 *        public void addAttribute(String key,Object obj)
		 *        {
		 *            request.setAttribute(key,obj);
		 *        }
		 *    }
		 *    
		 *    model.addAttribute("list",list);
		 *            |
		 *    request.setAttribute("list",list) 
		 */
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1); // 1 , 11 , 21 (rownum의 시작)
		int end=rowSize*curpage; // 10 ,20 ,30
		map.put("start", start);
		map.put("end", end);
		List<BoardVO> list=dao.boardListData(map);
		// 총페이지 / 현재페이지 
		int totalpage=dao.boardTotalPage();
		// BLOCK => 데이터가 많아야 된다 (레시피) => 채봇(시나리오) => 지능형 웹 (검색 => 모든 사이트 내용 검색=구글링)
		// 루씬 (검색 라이브러리) 다나와 , 인터파크 ... 네이버 / 카카오 ...
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list); // request.setAttribute("list",list)
		// request를 사용하지 않는다 (보안=>요청한 클라이언트의 IP를 가지고 있다)
		/*
		 *   웹 : 서버단 (Spring:Model) : 3명(*********) 
		 *       뷰단 (JSP)  : 2명
		 *       디비단 (MyBatis) : 1명  
		 *       디자인 :1 명 
		 *       PM : 1명 
		 *       ========================= 7명 한팀
		 *       
		 *       ==> Model : 1. 사용자가 보내주는 데이터 
		 *                   2. 결과값을 출력하기 위해서 어떤 값을 보내줄지 (List) <=> DAO
		 *       ==> Application : 알고리즘 (게임,온라인) => 고가 (서버프로그램) 
		 *           WebApplication : 화면 , 클라이언트의 전송 , 결과값 (흐름) => 서버,클라이언트 (제작)
		 *                                                            => 톰캣  , 브라우저,모바일
		 */
		return "board/list"; // /board/list.jsp 
	}
	// 글쓰기 (폼)
	@GetMapping("board/insert.do") // GetMapping
	public String board_insert()
	{
		return "board/insert"; // 기능은 없다 (단지 화면만 이동)
	}
	// 실제 글쓰기
	@PostMapping("board/insert_ok.do") // PostMapping
	/*
	 *try
	  {
		  // 한글 변환 (디코딩)
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  // 데이터를 모아서 => DAO전송 (오라클에 추가한다)
	  String name=request.getParameter("name");
	  String subject=request.getParameter("subject");
	  String content=request.getParameter("content");
	  String pwd=request.getParameter("pwd");
	  
	  
	  FreeBoardVO vo=new FreeBoardVO();
	  vo.setName(name);
	  vo.setSubject(subject);
	  vo.setContent(content);
	  vo.setPwd(pwd);
	 */
	// 스프링에서 매개변수 통해서 값을 받는 경우 
	// 일반 데이터형은 모드 가능 , ~VO단위 , List => (int page) => 자동으로 String=>int
	public String board_insert_ok(BoardVO vo)//DispatcherServlet를 통해서 값을 받는다 (VO,List)
	{
		dao.boardInsert(vo);
		return "redirect:list.do";// request가 초기화 => list.do를 다시 실행 => DB=> 추가된 상태 출력 
	}
	
	// 상세보기  ==> <a href="detail.do?no=1">제목</a> GET (URL뒤에 데이터를 첨부해서 보내는 데이터 전송방식)
	// => 데이터가 많을 경우 , 노출 가능성이 있다 (보안 취약) => GetMapping
	// => 보안 => 데이터 감춰서 전송 방식 (POST) => 내부 네트워크를 통해서 전송 => @PostMapping
	@GetMapping("board/detail.do")
	public String board_detail(int no,Model model) // 전송할 데이터가 있는 경우에 Model을 이용
	{
		//DB연동
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	// 수정 
	// 수정 데이터 읽기
	@GetMapping("board/update.do")
	public String board_update(int no,Model model)
	{
		// 출력데이터를 읽어서 jsp로 전송
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo", vo);
		return "board/update";
	}
	// 실제 수정  ==> 이동 , 자바스크립트를 전송 ==> @RestController
	// @Controller => forward/sendRedirect 
	//                => return "경로/JSP명"(request전송) , return "redirect:~.do"(화면만 변경)
	//                                                    => _ok:처리만하기 때문에 다른 파일로 이동 
	@PostMapping("board/update_ok.do")
	@ResponseBody //Ajax , VusJS , ReactJS => 자바스크립트와 연동 
	// => @RestController 변경 (필요한 데이터 전송 , 스크립트 전송이 가능) => 웹/앱 ==> JSON
	// JSP파일명 / .do (X) 
	// 자바스크립트 전송 
	// 한글 깨진다
	public String board_update_ok(BoardVO vo) //화면 이동 => 상세보기로 이동 => redirect
	{
		boolean bCheck=dao.boardUpdate(vo);
		String js="";
		// HTML, 입반 문자열 , 스크립트 전송
		if(bCheck==true)
		{
			js="<script>"
		      +"location.href=\"detail.do?no="+vo.getNo()+"\""
		      +"</script>";
		}
		else
		{
			js="<script>"
			  +"alert(\"비밀번호가 틀립니다...\");"
			  +"history.back();"
			  +"</script>";
		}
		return js;
	}
	/*
	 *    삭제 요청 
	 *    detail.jsp ==>  <a href="delete.do?no=1">삭제</a>
	 *    =================================================
	 *    delete.jsp안에 no이 설정 => no,pwd
	 */
	// 삭제
	@GetMapping("board/delete.do") // 화면만 보여준다 (GET) => <form>:POST를 지정 
	public String board_delete(int no,Model model)
	{
		model.addAttribute("no", no); // hidden을 처리
		return "board/delete";
	}
	@PostMapping("board/delete_ok.do")
	// 직접 받는 것(request)이 아니고 DispatcherServlet을 통해서 데이터 받는다 (매개변수를 이용하면) 
	public String board_delete_ok(int no,String pwd,Model model,HttpServletRequest request)
	{
		 
		// Model model => JSP값을 전송하는 객체 (request)
		boolean bCheck=dao.boardDelete(no, pwd);
		model.addAttribute("bCheck", bCheck); // request.setAttribute(bCheck",bCheck)(보안:IP)
		return "board/delete_ok";
	}
	// GetMapping(입력창 출력,list목록)<a>,location.href , PostMapping 구분 (<form>)
	// 파일명, redirect => , <script>,JSON,HTML,일반 데이터 전송 => @ResponseBody
	// 찾기  => #{} , ${}
	@PostMapping("board/find.do")
	public String board_find(String fs,String ss,Model model)
	{
		Map map=new HashMap();
	    map.put("fs", fs); // fieldString : column명 
	    map.put("ss", ss); // searchString : 검색어
	    // 검색 갯수 
	    int count=dao.boardFindCount(map);
	    // 검색 데이터목록
	    List<BoardVO> list=dao.boardFindData(map);
	    
	    model.addAttribute("count", count);
	    model.addAttribute("list", list);
	    // 입문 과정 => 실무 요구사항 (게시판,댓글)
		return "board/find";
	}
	//                              Model,request
	// 요청(.do) ==> 자바(Controller) =============> JSP
	/*
	 *                  |
	 *              1) forward => request를 전송  ==> return "경로/jsp명"
	 *              2) sendRedirect => 화면만 변경 ===> return "redirect:~"  => _ok
	 *                 => _ok 처리 => 여러개 일 경우 (비밀번호 검색: O이동 , X이동)
	 *              3) script 처리 => @ResponseBody
	 */
}











