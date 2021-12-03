package com.sist.web;

import org.springframework.stereotype.Controller;
// 웹 화면 이동 
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ChatBotController {
   @RequestMapping("main/chatbot.do")
   public String main_chatbot()
   {
	   return "main/chatbot";
   }
}
