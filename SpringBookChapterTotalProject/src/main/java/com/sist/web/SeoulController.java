package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.manager.*;
import com.sist.vo.SeoulHotelVO;
import com.sist.vo.SeoulLocationVO;
import com.sist.vo.SeoulNatureVO;
// 클래스가 달라도 => RequestMapping("uri") => 같으면 안된다 
// 폴더명 ==> seoul/list.do   food/list.do 
@Controller
@RequestMapping("seoul/")
public class SeoulController {
	@Autowired
    private SeoulDAO dao;
	@Autowired
	private TwitterManager tm;
	@Autowired
	private WeatherManager wm;

	
	@RequestMapping("location_list.do")
	public String seoul_location_list(String page,Model model)
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
	public String seoul_hotel_list(String page,Model model)
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
		return "main/main";// main에서는 조립
	}
	// 상세 보기 => 전 (쿠키에 저장) => 최근 방문 명소 
	// ../seoul/location_detail_before.do?no=${vo.no }
	/*
	 *   URI => location_detail_before.do 인식  ?뒤에는 톰캣이 읽어서 request에 저장 
	 */
	// 구분 => HandlerMapping이 메소드 찾아서 호출이 가능 
	@GetMapping("location_detail_before.do")
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
		// 우편번호 주소
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../seoul/location_detail.jsp"); // 상세보기 내용을 출력
		// request공유 => include / forward ==> 공유가 된다 (request는 main.jsp)
		return "main/main";// 메뉴/footer유지 => 중간에 출력 
	}
	
}





