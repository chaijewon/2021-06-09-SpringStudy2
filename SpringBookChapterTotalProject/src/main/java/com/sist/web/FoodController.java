package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;
@Controller // 화면 이동 (forward(request를 전송) , redirect(이전의 실행된 파일로 이동)
/*
 *    forward (request,Model)  return "경로명/JSP파일"
 *    sendRedirect (화면만 변경 => 다른 URL로 이동)  return "redirect:~.do"
 */
@RequestMapping("food/")
public class FoodController {
	@Autowired
    private FoodDAO dao;
	
	@GetMapping("category.do") // @ResponseBody => @RestController (JSON데이터 전송)
	// 메소드 중심 => 클래스 중심 
	/*
	 *   =========================
	 *       메뉴 (header.jsp) ===> 고정
	 *   =========================
	 *   
	 *       변경되는 부분 <jsp:include page="${main_jsp}"></jsp:include>
	 *       <jsp:include page="../food/category.jsp"></jsp:include>
	 *       <jsp:include page="../board/list.do"></jsp:include>
	 *   =========================
	 *      회사정보(footer.jsp) ===> 고정 
	 *   =========================
	 */
	public String food_category(Model model)
	{
		model.addAttribute("main_jsp", "../food/category.jsp");
		return "main/main";
		//return "food/category";
	}
	
	@GetMapping("find.do")
	public String food_find(Model model)
	{
		model.addAttribute("main_jsp", "../food/search.jsp");
		return "main/main";
	}
}








