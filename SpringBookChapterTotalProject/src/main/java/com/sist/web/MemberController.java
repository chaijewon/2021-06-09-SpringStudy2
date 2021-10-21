package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

@Controller
@RequestMapping("member/")
public class MemberController {
   @Autowired
   private MemberDAO dao;
   // ../member/join.do => 요청 처리 
   @GetMapping("join.do") // 회원가입 입력폼을 보여달라
   public String member_join(Model model)
   {
	   // 폼만 전송 
	   // include할 파일을 넘겨준다
	   model.addAttribute("main_jsp", "../member/join.jsp");
	   return "main/main";
   }
   // 회원 가입 전에 처리 
   @PostMapping("login_ok.do") // Dialog (화면이 변경이 되면 자동으로 사라진다 => Ajax)
   @ResponseBody
   // 화면 이동을 하지 않고 => 데이터만 보내는 경우에 사용 
   // 예전 => 데이터만 전송하는 소스을 만들수가 없어서 => jsp안에 출력했다가 읽어가는 형식 
   // 직접을 데이터 전송 => @ResponseBody
   // 데이터(JSON(JavaScript),HTML,XML,일반데이터) : 화면을 유지상태에서 데이터만 받아서 처리가 가능 
   // ==> 현재 사이트 (화면변경) ==> 승격(Method=class로 사용이 가능) => @RestController(검색),앱 
   public String member_login_ok(String id,String pwd,HttpSession session)
   {
	   String msg="";
	   // DAO연동 결과값 읽기 
	   MemberVO vo=dao.isLogin(id, pwd);
	   if(vo.getMsg().equals("OK")) // 로그인이 되었다면 
	   {
		   session.setAttribute("id", vo.getId());
		   session.setAttribute("name", vo.getName()); // 프로그램 종료시까지 필요한 데이터 저장 
	   }
	   msg=vo.getMsg();
	   return msg; //NOID,NOPWD,OK
   }
   @PostMapping("join_ok.do")
   public String member_join_ok(MemberVO vo)
   {
	   //DAO 
	   return "redirect:../main/main.do"; //main화면 => 로그인  
   }
   
}








