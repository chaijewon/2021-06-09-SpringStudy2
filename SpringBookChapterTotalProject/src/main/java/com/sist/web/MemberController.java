package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member/")
public class MemberController {
   // ../member/join.do => 요청 처리 
   @GetMapping("join.do") // 회원가입 입력폼을 보여달라
   public String member_join(Model model)
   {
	   // 폼만 전송 
	   // include할 파일을 넘겨준다
	   model.addAttribute("main_jsp", "../member/join.jsp");
	   return "main/main";
   }
   
}
