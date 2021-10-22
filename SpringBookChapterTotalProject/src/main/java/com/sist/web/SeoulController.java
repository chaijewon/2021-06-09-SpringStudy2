package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.manager.*;
import com.sist.vo.CommonReplyVO;
import com.sist.vo.SeoulHotelVO;
import com.sist.vo.SeoulLocationVO;
import com.sist.vo.SeoulNatureVO;
// 클래스가 달라도 => RequestMapping("uri") => 같으면 안된다 
// 폴더명 ==> seoul/list.do   food/list.do 
@Controller // TYPE (클래스 구분)
@RequestMapping("seoul/")
public class SeoulController {
	@Autowired // {CONSTRUCTOR(생성자 구분), FIELD(멤버변수), METHOD(setter), PARAMETER)=>
    private SeoulDAO dao;
	@Autowired
    private CommonReplyDAO cDao;
	
	@Autowired
	private TwitterManager tm;
	@Autowired
	private WeatherManager wm;

	
	@RequestMapping("location_list.do")
	// 매개변수는 DispatcherServlet 
	public String seoul_location_list(String page,Model model,HttpServletRequest request)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		map.put("start", start);
		map.put("end", end);
		List<SeoulLocationVO> list=dao.locationListData(map);
		// 총페이지 
		    map.put("table_name", "seoul_location");
			int totalpage=dao.seoulTotalPage(map);
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("BLOCK", BLOCK);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "../seoul/location_list.jsp");//실제 출력 
		
		// Cookie에 저장된 데이터 전송 
		// 1. Cookie전체 데이터 읽기 
		Cookie[] cookies=request.getCookies();
		List<SeoulLocationVO> sList=new ArrayList<SeoulLocationVO>();
		if(cookies!=null && cookies.length>0)
		{
			// Cookie생성 => 쿠키에 값이 있는 경우 
			for(int i=cookies.length-1;i>=0;i--) // 최신 방문 
			{
				// getName() : 키 , getValue():값 
				//       loc1 loc2...
				if(cookies[i].getName().startsWith("loc"))
				{
					String no=cookies[i].getValue(); // 명소의 고유번호 
					SeoulLocationVO vo=dao.locationDetailData(Integer.parseInt(no));
					sList.add(vo);
				}
					
			}
		}
		model.addAttribute("sList", sList);
		return "main/main";
	}
	@RequestMapping("nature_list.do")
	public String seoul_nature_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		map.put("start", start);
		map.put("end", end);
		List<SeoulNatureVO> list=dao.natureListData(map);
		
		// 총페이지 
		    map.put("table_name", "seoul_nature");
			int totalpage=dao.seoulTotalPage(map);
				final int BLOCK=10;
				int startPage=((curpage-1)/BLOCK*BLOCK)+1;
				int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
				if(endPage>totalpage)
					endPage=totalpage;
				
				model.addAttribute("curpage", curpage);
				model.addAttribute("totalpage",totalpage);
				model.addAttribute("BLOCK", BLOCK);
				model.addAttribute("startPage",startPage);
				model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "../seoul/nature_list.jsp");// 실제 출력
		return "main/main";
	}
	@RequestMapping("hotel_list.do")
	public String seoul_hotel_list(String page,Model model,HttpServletRequest request)
	{
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		map.put("start", start);
		map.put("end", end);
		List<SeoulHotelVO> list=dao.hotelListData(map);
		// 총페이지 
		        map.put("table_name", "seoul_hotel");
				int totalpage=dao.seoulTotalPage(map);
				final int BLOCK=10;
				int startPage=((curpage-1)/BLOCK*BLOCK)+1;
				int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
				if(endPage>totalpage)
					endPage=totalpage;
				
				model.addAttribute("curpage", curpage);
				model.addAttribute("totalpage",totalpage);
				model.addAttribute("BLOCK", BLOCK);
				model.addAttribute("startPage",startPage);
				model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "../seoul/hotel_list.jsp");// 실제 내용을 출력 
		// request를 공유 : include , forward
		// Cookie 보내기 => request로 값을 읽어 온다 
		Cookie[] cookies=request.getCookies();
		List<SeoulHotelVO> hList=new ArrayList<SeoulHotelVO>();
		if(cookies!=null && cookies.length>0)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("hotel"))
				{
					String no=cookies[i].getValue();
					SeoulHotelVO vo=dao.hotelDetailData(Integer.parseInt(no));
					hList.add(vo);
				}
			}
		}
		model.addAttribute("hList", hList);
		return "main/main";// main에서는 조립
	}
	// 상세 보기 => 전 (쿠키에 저장) => 최근 방문 명소 
	// ../seoul/location_detail_before.do?no=${vo.no }
	/*
	 *   URI => location_detail_before.do 인식  ?뒤에는 톰캣이 읽어서 request에 저장 
	 */
	// 구분 => HandlerMapping이 메소드 찾아서 호출이 가능 
	@GetMapping("location_detail_before.do")
	// Method위에서 메소드만 구분 
	// @RequestMapping => 메소드 구분 , 클래스 구분 (target = METHOD , TYPE)
	public String location_detail_before(int no,RedirectAttributes attr,HttpServletResponse response)
	{
		// response => 응답 (클라이언트 브라우저로 전송 : HTML코드 , 쿠키 전송=> 한번에 전송이 불가능)
		// 1. 쿠키 생성
		// 내장객체가 아니다 (일반 객체)
	    Cookie cookie=new Cookie("loc"+no, String.valueOf(no)); // 데이터베이스에서 poster,title => 번호 저장
	    //                       ======= 키 (중복없이 사용) ==> hotel , nature
		/*
		 *   내장객체 
		 *   ======
		 *    HttpServletRequest , HttpServletResponse , HttpSession , ServletContext ,
		 *    ServletConfig , Exception , Object... (JSP에서 사용한 내장 객체)
		 */
		// 2. 경로 지정 , 저장기간 설정 
	    cookie.setPath("/");// root에 저장 
	    cookie.setMaxAge(60*60*24); // 1일 저장 
		// 3. 생성된 쿠키를 브라우저로 전송 
	    response.addCookie(cookie);
		attr.addAttribute("no", no);
		return "redirect:../seoul/location_detail.do";
	}
	// 상세보기 
	//@RequestMapping(value="",method=RequestMethod.GET) => GetMapping
	//@RequestMapping(value="",method=RequestMethod.POST)=> PostMapping
	@GetMapping("location_detail.do")
	public String location_detail(int no,Model model)
	{
		// 중간에 출력 
		/*
		 *   =======================
		 *       메뉴
		 *   =======================
		 *   
		 *    <jsp:include page="${main_jsp}"> => page가 사용자 요청에 따라 변경 (jsp파일명이 첨부)
		 *   
		 *   
		 *   =======================
		 *     footer
		 *   =======================
		 */
		// MyBatis연결 => 모양 => 1. 상세보기 내용 , 2. 댓글 (PL/SQL) 
		// 16만개 => 레시피 => 정렬시에 속도가 늦어진다 (ORDER BY) => INDEX
		// 실무 => include(X) => tiles , mybatis , front (vue,react) , 데이터 분석 (코모란,꼬꼬마,R(RJava))
		// 컴퓨터에 인식(딥러닝) => 지능형 웹 , 챗봇 , 실시간 검색 (루씬=검색엔진 (코난),솔트룩스)
		SeoulLocationVO vo=dao.locationDetailData(no);
		String addr=vo.getAddress();
		addr=addr.substring(addr.indexOf(" "));
		vo.setAddress(addr);
		
		List<CommonReplyVO> rList=cDao.replyListData(no, 1);
		model.addAttribute("rList", rList);
		// 우편번호 주소
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../seoul/location_detail.jsp"); // 상세보기 내용을 출력
		// request공유 => include / forward ==> 공유가 된다 (request는 main.jsp)
		return "main/main";// 메뉴/footer유지 => 중간에 출력 
	}
	// ../seoul/hotel_detail_before.do?no=${vo.no} => 요청 처리 (개발자) , Spring : 찾아서 메소드호출 
	// 요청 받기 (.do) => 처리 메소드 호출 => JSP로 request전송 
	//                 ======== 개발자가 수행 
	@GetMapping("hotel_detail_before.do")
	public String hotel_detail_before(int no,RedirectAttributes attr,HttpServletResponse response)
	{
		// forward : Model (request) , redirect : RedirectAttributes(request가 포함되어 있지 않는다)
		// ?no=1  ==> attr.addAttribute("no",1)
		// 쿠키 생성
		// Cookie(String name, String value)

		Cookie cookie=new Cookie("hotel"+no, String.valueOf(no));
		// 기간 / 패스 (저장위치)
		cookie.setMaxAge(60*60*24); // 초단위 계산 (1일 저장)
		cookie.setPath("/");
		// 쿠키를 클라이언트 브라우저로 전송 
		response.addCookie(cookie);
		attr.addAttribute("no", no); // redirect일 경우에만 사용한다 
		return "redirect:../seoul/hotel_detail.do";
	}
	
	//../seoul/hotel_detail.do => <form> , Ajax:type 
	// <a> , location.href , sendRedirect => GET 
	// sendRedirect => redirect:
	/*
	 *    HandlerMapping 
	 *    1. URI를 얻어 온다 (브라우저 주소창)
	 *    2. class안에서 => RequestMapping,GetMapping,PostMapping
	 *    
	 *    String uri=request.getRequestURI();
	 *    // http://localhost:8080 (URL)
	 *    uri="/web/seoul/hotel_list.do"
	 *         ====request.getContextPath()
	 *         
	 *    uri=uri.substring(request.getContextPath().length()+1)
	 *    uri="seoul/hotel_list.do"
	 *    
	 *    => method.invoke() => 메소드 호출 (메소드명이 없어도 호출이 가능) => 
	 */
	// ../seoul/hotel_detail.do
	// DAO ==> 호출되는 위치 => Controller ==> JSP전송 
	@GetMapping("hotel_detail.do")
	public String hotel_detail(int no,Model model) // model => request가 첨부 (전송만 가능:전송객체)
	{
		// forward => main
		/*
		 *   ===============
		 *   
		 *   ===============
		 *   
		 *   Detail 
		 *   
		 *   ===============
		 *   
		 *   ===============
		 */
		// Hotel 한개에 대한 데이터 전송 (DAO)
		SeoulHotelVO vo=dao.hotelDetailData(no);
		model.addAttribute("vo", vo);
		// include할 JSP를 보내준다 
		model.addAttribute("main_jsp", "../seoul/hotel_detail.jsp");
		return "main/main";
	}
	
	//../seoul/location_cookie_delete.do
	@GetMapping("location_cookie_delete.do")
	public String location_cookie_delete(HttpServletRequest request,HttpServletResponse response)
	{
		// 쿠키 삭제 
		Cookie[] cookies=request.getCookies();
		if(cookies!=null && cookies.length>0)
		{
			for(int i=0;i<cookies.length;i++)
			{
				if(cookies[i].getName().startsWith("loc"))
				{
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
		return "redirect:../seoul/location_list.do";
	}
	
	
	
}





