package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.manager.*;
// javascript로 데이터 전송 
@RestController
public class ChatBotRestController {
   @Autowired
   private ChatBotManager cm;
   
   @RequestMapping(value="main/chatbot_result.do")
   public String chatbot_result(String msg)
   {
	   String result=cm.chatbotMessage(msg);
	   return result;
   }
}
