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
	       session.setAttribute("admin", vo.getAdmin());
	   }
	   msg=vo.getMsg();
	   return msg; //NOID,NOPWD,OK
   }
   @GetMapping("logout.do")
   public String member_logout(HttpSession session)
   {
	   session.invalidate(); // 등록된 모든 데이터 삭제 ==> id=null , name=null
	   return "redirect:../main/main.do";
   }
   @PostMapping("join_ok.do")
   public String member_join_ok(MemberVO vo)
   {
	   //DAO 
	   // tel1 , tel2 => tel
	   vo.setTel(vo.getTel1()+"-"+vo.getTel2());
	   dao.memberInsert(vo);
	   return "redirect:../main/main.do"; //main화면 => 로그인  
   }
   
   @GetMapping("idcheck.do")
   // JSP로 결과값만 전송 
   @ResponseBody
   public String member_idcheck(String id)
   {
	   String msg="";
	   // DAO 연결
	   int count=dao.memberIdCheck(id);
	   msg=String.valueOf(count);
	   return msg;
   }
   // @RestController => 
   @GetMapping("join_delete.do")
   public String member_join_delete(HttpSession session)
   {
	   String id=(String)session.getAttribute("id");
	   // DAO
	   dao.memberDelete(id);
	   session.invalidate(); // DB에서 삭제 => session은 그대로 있다 
	   return "member/delete";
   }
   
   @GetMapping("join_update.do") // 회원 수정 요청 => 스프링 (기본 틀 => 요청이 들어오면 해당 메소드 호출 => 데이터 전송)
   // 요청 처리 , 어떤 데이터 (모델 => 요청에 대한 처리 => 결정 (어떤 화면을 클라이언트에 전송) : 데이터를 보낸다 
   // ======= Database  , return , Model => addAttribute()
   public String member_join_update(HttpSession session,Model model)
   {
	   // DAO => 호출 
	   // 처리 
	   // 데이터 보내는 과정 ==> Model (조립기) => main
	   // Id읽기 
	   String id=(String)session.getAttribute("id"); // 브라우저마다 session은 한개만 생성 
	   // 채팅 (목록) => sessionid => 
	   
	   MemberVO vo=dao.memberUpdateData(id);
	   String tel=vo.getTel();
	   tel=tel.substring(4);
	   vo.setTel(tel.trim());
	   model.addAttribute("vo", vo); // 데이터를 보내는 경우는 여러개를 보낼 수 있다 (request:Object)
	   
	   // 기존 이력을 기록을 가지고 온다 
	   model.addAttribute("main_jsp", "../member/join_update.jsp");
	   return "main/main";
   }
   
   @PostMapping("join_update_ok.do")
   public String member_join_update_ok(MemberVO vo,HttpSession session,Model model)
   {
	   // 오라클 / session은 별도(반드시 => 오라클 처리 / 세션 처리)
	   // DAO연결 
	   vo.setTel(vo.getTel1()+"-"+vo.getTel2());
	   boolean bCheck=dao.memberJoinUpdate(vo);
	   if(bCheck==true)
	   {
		   session.setAttribute("name", vo.getName());
	   }
	   model.addAttribute("bCheck", bCheck); 
	   // bCheck=true(main.do),bCheck=false(history.back()) => @ResponseBody
	   return "member/join_update_ok"; // 화면에 출력을 하지 않는다 
	   // 화면에 출력 => return "main/main";
   }
   // 후보키 두개 (기본키=> Primary KEY => 잃어 버린 경우 => 대체할 수 있는 키 : 전화번호 / 이메일) 
   // 전화번호 , 이메일 => Unique
   // id를 찾기 위해서는 => 전화번호 , 이메일
   @GetMapping("idfind.do")
   public String member_idfind(Model model)
   {
	   model.addAttribute("main_jsp", "../member/idfind.jsp");
	   return "main/main";
   }
   
   @PostMapping("idfind_tel_ok.do")
   @ResponseBody
   public String member_idfind_tel_ok(String tel)
   {
	   String msg="";
	   msg=dao.memberIdFindTelData(tel);
	   return msg;
   }
   @PostMapping("idfind_email_ok.do")
   @ResponseBody
   public String member_idfind_email_ok(String email)
   {
	   String msg="";
	   //DAO연결
	   msg=dao.memberIdFindEmailData(email);
	   return msg;
   }
   @GetMapping("pwdfind.do")
   public String pwdfind(Model model)
   {
	   model.addAttribute("main_jsp", "../member/pwdfind.jsp");
	   return "main/main";
   }
   @PostMapping("pwdfind_find_ok.do")
   @ResponseBody
   public String member_pwdfind_ok(String id)
   {
	   String msg="";
	   //DAO연결
	   msg=dao.memberPwdFindData(id);
	   return msg;
   }
   
}








