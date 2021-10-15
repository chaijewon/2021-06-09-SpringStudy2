package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
   @RequestMapping("main/main.do")
   public String main_main(Model model)
   {
	   model.addAttribute("main_jsp", "../main/home.jsp");
	   return "main/main";
   }
}
