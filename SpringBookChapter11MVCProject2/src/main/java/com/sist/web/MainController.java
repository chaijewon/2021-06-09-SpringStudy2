package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/*
 *         DispatcherServlet => HandlerMapping (XML)
 */
// JSP (요청) : main.jsp => MainController <=> DAO
//            ViewResolver(XML) =========> JSP(결과값):print.jsp
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("main/") // 경로 지정 (GetMapping,PostMapping) (경로가 중복일 경우) => 307page
public class MainController {
   @PostMapping("print.do") // 메소드 위에서 작업
   public String main_print(String name1,String name2,String name3,String name4,String name5,Model model)
   {
	   model.addAttribute("name1", name1);
	   model.addAttribute("name2", name2);
	   model.addAttribute("name3", name3);
	   model.addAttribute("name4", name4);
	   model.addAttribute("name5", name5);
	   return "main/print";// request를 받는 jsp지정 
   }
   /*
    *    String name=request.getParameter("name")
    *    String[] name=request.getParameterValues("name")
    */
   @PostMapping("print2.do")
   public String main_print2(String[] name,Model model)
   {
	   model.addAttribute("name", name);
	   
	   return "main/print2";// request를 받는 jsp지정 
   }
   // List로 값을 받는 경우
   // 302page => Model(해당 JSP에 값을 전송하는 객체) => Spring에서 지원하는 클래스
   /*
    *    public class Model
    *    {
    *        public void addAttribute(String key,Object obj)
    *        {
    *            request.setAttribute(key,obj); // 보안 (IP노출)
    *        }
    *    }
    */
   @PostMapping("print3.do")
   public String main_print3(NamesVO vo, Model model)
   {
	   model.addAttribute("list", vo.getName()); // => request.setAttribute()
	   return "main/print3";
   }
   
   @GetMapping("print4.do") // 사용빈도는 거의 없다 (Spring 2.5) => 306page
   public ModelAndView dataSend()
   {
	   ModelAndView mv=new ModelAndView();// 전송내용 + JSP이름 동시에 전송 
	   mv.addObject("msg","Hello Spring!!");
	   mv.setViewName("main/print4");
	   return mv;
   }
}






