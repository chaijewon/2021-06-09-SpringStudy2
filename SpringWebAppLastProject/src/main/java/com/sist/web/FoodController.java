package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.*;
import java.util.*;
@Controller
public class FoodController {
   @Autowired
   private FoodDAO dao;
   
   @GetMapping("food/category_list.do")
   // 요청 처리 => 메소드 
   public String food_category_list(int cno,Model model)
   {
	   // include되는 파일을 지정 => 데이터 전송(오라클에 존재 => 오라클 데이터 처리 : FoodDAO)
	   List<FoodVO> list=dao.categoryFoodListData(cno);
	   for(FoodVO vo:list)
	   {
		   String poster=vo.getPoster();
		   poster=poster.substring(0,poster.indexOf("^"));
		   String address=vo.getAddress();
		   address=address.substring(0,address.indexOf("지"));
		   vo.setPoster(poster);
		   vo.setAddress(address);
	   }
	   /*
	    *    => web: <a href=""> 이동  ==> webView (모바일=>사이트)=> Kotlin(X) , 사이트 주소
	    *    => app: Intent 이동 
	    */
	   // 데이터 전송 
	   CategoryVO vo=dao.categoryInfoData(cno);
	   model.addAttribute("cvo", vo);
	   model.addAttribute("list", list);
	   model.addAttribute("main_jsp", "../food/category_list.jsp");
	   return "main/main";
   }
   // JSON변환 => Ajax , VueJS ,ReactJS , Kotlin  ==> Rest
   @GetMapping("food/food_detail.do")
   public String food_detail(int no,Model model)
   {
	   FoodVO vo=dao.foodDetailData(no);
	   String address=vo.getAddress();
	   address=address.substring(0,address.indexOf("지"));
	   vo.setAddress(address);
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_jsp", "../food/food_detail.jsp");
	   return "main/main";
   }
}









