package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// DAO를 연결해서 결과값을 JSP보내주는 역할 
// JSP는 출력만 담당 
// 스프링에 부탁 (개발자 = 프로그램) => 프로그램 중심 (제어의 역전) => Ioc (마틴 파울러) => AI
import java.util.*;

import javax.validation.Valid;

import com.sist.dao.*;
@Controller
public class ReplyBoardController {
   @Autowired
   private BoardDAO dao; // BoardDAO dao=new BoardDAO(); (X)
   
   @RequestMapping("board/insert.do")
   public String board_insert()
   {
	   return "board/insert";
   }
   @RequestMapping("board/insert_ok.do")
   public String board_insert_ok(@ModelAttribute @Valid BoardVO boardVO,Errors result)
   {
	   if(result.hasErrors())
	   {
		   return "board/insert";
	   }
	   
	   return "redirect:list.do";
   }
}





