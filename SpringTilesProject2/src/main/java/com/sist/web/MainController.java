package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
   @RequestMapping("main/main.do")
   public String main_main()
   {
	   return "main";
   }
   @RequestMapping("site/chat/chat.do")
   public String chat_chat()
   {
	   return "site/chat/chat";
   }
}
