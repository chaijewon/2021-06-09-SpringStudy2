package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// 웹 화면 이동 
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.dao.*;
@Controller
public class ChatBotController {
   @Autowired
   private FoodLocationDAO dao;
   
   @RequestMapping("main/chatbot.do")
   public String main_chatbot(Model model)
   {
	   List<FoodLocationVO> list=dao.foodListData();
	   model.addAttribute("list", list);
	   return "main/chatbot";
   }
}
