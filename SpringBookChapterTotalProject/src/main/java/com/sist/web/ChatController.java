package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
   @RequestMapping("chat/chat.do")
   public String chat_main(Model model)
   {
	   model.addAttribute("main_jsp", "../chat/chat.jsp");
	   return "main/main";
   }
}
