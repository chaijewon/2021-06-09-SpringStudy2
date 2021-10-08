package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.dao.*;
@Controller
// DAO => 재사용 (분리) ==> CBD => 기본을 만들어 주고 조립은 프로그램에 맞게 (레고)=프레임워크 
public class MainController {
   @Autowired
   private SeoulDAO dao;
   
   @RequestMapping("main/main.do")
   public String main_main(Model model)
   {
	   // 데이터 전송
	   // 명소 
	   List<LocationVO> lList=dao.locationTopData();
	   // 자연
	   List<NatureVO> nList=dao.natureTopData();
	   // 호텔
	   List<HotelVO> hList=dao.hotelTopData();
	   
	   model.addAttribute("lList", lList);
	   model.addAttribute("nList", nList);
	   model.addAttribute("hList", hList);
	   // include(공유) ==> main,home도 사용이 가능  ==> request공유 (JSP마다 따로 가지고 있다) : include,forward
	   model.addAttribute("main_jsp", "../main/home.jsp");
	   return "main/main";
   }
}
