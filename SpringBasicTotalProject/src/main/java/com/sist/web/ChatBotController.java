package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import com.sist.dao.*;
import com.sist.manager.ChatBotManager;
@Controller
public class ChatBotController {
   @Autowired
   private MusicDAO dao;
   @Autowired
   private ChatBotManager mgr;
   
   @RequestMapping("chatbot/chatbot.do")
   public String chatbot_chatbot(Model model)
   {
	   List<MusicVO> list=dao.musicListData1();
	   model.addAttribute("list", list);
	   return "chatbot/chatbot";
   }
   @RequestMapping("chatbot/chatbot_result.do")
   @ResponseBody
   public String chatbot_result(String msg)
   {
	   String result="";
	   result=mgr.chatbotMessage(msg);
	   return result;
   }
}
