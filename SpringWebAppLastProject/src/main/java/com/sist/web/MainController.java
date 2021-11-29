package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;


import com.sist.dao.*;
@Controller
public class MainController {
   @Autowired // 전역 => MainController가 가지고 있는 모든 메소드에서 사용이 가능 
   private FoodDAO dao;
   
   @GetMapping("main/main.do") // GetMapping(화면 출력 , 폼) , PostMapping(<form>,ajax) ==> RequestMapping
   public String main_main(String no,Model model)
   {
	   if(no==null)
		   no="1";
	   Map map=new HashMap();
	   map.put("no", Integer.parseInt(no));
	   
	   // 데이터 받기 
	   List<CategoryVO> list=dao.categoryListData(map);
	   // 전송 
	   // Include => 대상 (Model:전송(데이터 전송))
	   model.addAttribute("main_jsp", "../food/category.jsp"); // 코틀린은 List를 전송할 수 없다 => JSON
	   model.addAttribute("list", list);
	   return "main/main";
		  
   }
}
