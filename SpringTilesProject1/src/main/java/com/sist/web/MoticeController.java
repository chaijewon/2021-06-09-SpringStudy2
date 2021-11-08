package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MoticeController {
   @RequestMapping("notice/list.do")
   public String notice_list()
   {
	   return "notice/list";
   }
}
