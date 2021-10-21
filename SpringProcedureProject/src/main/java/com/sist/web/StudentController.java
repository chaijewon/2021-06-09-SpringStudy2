package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// 스프링 셋팅 (XML) 
// 메모리 할당 (클래스) 
// 요청 => RequestMapping()
// DispatcherServlet => 위임 
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*; // List
import com.sist.dao.*;
@Controller
public class StudentController {
   // 스프링에서 
  @Autowired
  private StudentDAO dao;
  @RequestMapping("student/list.do") // 학생 목록을 보여 달라 (요청) 
  public String student_list(Model model) // Model (전송객체:데이터를 서버에서 클라이언트 전송객체)
  {
	  // 학생목록을 읽어 와서 클라이언트(브라우저) 전송
	  List<StudentVO> list=dao.studentListData();
	  model.addAttribute("list", list); // request.setAttribute("list",;list)
	  /*
	   *    public void addAttribute(String key,Object obj)
	   *    {
	   *       request.setAttribute(key,obj);
	   *    }
	   */
	  // 출력한 HTML을 전송 => 브라우저에서 읽어 본다 
	  return "student/list"; // 확장자는 이미 추가되어 있다 
  }
  @RequestMapping("student/insert.do") //등록폼을 보여 달라
  public String student_insert()
  {
	  return "student/insert";
  }
  
  @RequestMapping("student/insert_ok.do")// 실제 오라클에 등록을 요청
  public String student_insert_ok(StudentVO vo) // 데이터를 받는 경우 VO단위 (커맨드 객체)
  {
	  dao.studentInsert(vo);
	  return "redirect:list.do"; // 추가된 목록을 보여 준다
  }
  
  @RequestMapping("student/delete.do")
  public String student_delete(int hakbun)
  {
	  dao.studentDelete(hakbun);
	  return "redirect:list.do";
  }
  
}









