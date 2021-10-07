package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;//List
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
		
		model.addAttribute("list", list); // request.setAttribute("list",list)
		// request를 사용하지 않는다 (보안=>요청한 클라이언트의 IP를 가지고 있다)
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
	// 삭제
	// 찾기 
}











