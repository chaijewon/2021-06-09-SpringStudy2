package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
@Controller
public class WebController {
   @RequestMapping("main/form.do")
   public String main_form()
   {
	   return "main/form";
   }
   
   @RequestMapping("main/date.do")
   public String main_date(DateVO vo,Model model)
   {
	   /*int i=10/0;
	   System.out.println("i="+i);*/
	   model.addAttribute("vo", vo);
	   return "main/date";
   }
   @RequestMapping("main/{id}/{pwd}")
   public String main_data(@PathVariable("id") String id,@PathVariable("pwd") String pwd,Model model)
   {
	   model.addAttribute("id", id);
	   model.addAttribute("pwd", pwd);
	   return "main/data";
   }
}
