package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
// HandlerMapping => Class검색 (@Controller가 있는 클래스에서만 검색)
/*
 *   404 
 *   1. @Controller 존재여부 
 *   2. ViewResolver설정 여부 
 *   3. @RequestMapping("uri"), return 값
 *   400 
 *   1. Get/Post => GetMapping / PostMapping
 *   405
 *   1. 매개변수의 데이터형이 틀린 경우 
 *      예) list.do?page=1&check=true
 *      public String list(int page,int check) => 405  ==> 
 *                                  1. boolean check
 *                                  2. String check 
 *                                  ================= 전송에 일반 데이터 => String으로 받아도 상관없다
 *                                     String으로 받을 경우 단점 => 형변환후 사용  
 *                                  제외 : 커맨드객체 , Collection 
 *                                       ========= ===========
 *                                        BoardVO   List
 *    500 
 *    1. SQLException (SQL)
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
// https://news.v.daum.net/v/20211020083130303 (data)
// /
// https://www.google.co.kr/search?q=jquery+ui+dialog+width&sxsrf=AOaemvKks-MHyeJ9PqZOPkISH-96C4YCMw%3A1634690634467&source=hp&ei=SmZvYYDDGvaW4-EP66is6AQ&iflsig=ALs-wAMAAAAAYW90WiR1l0xZ1CtKvjFs8Z9CT-MCfZTZ&oq=jquery+ui+dialog+width&gs_lcp=Cgdnd3Mtd2l6EAEYADIHCCMQ6gIQJzIHCCMQ6gIQJ1AAWABg0RloAXAAeACAAQCIAQCSAQCYAQCwAQI&sclient=gws-wiz
public class MainController {
    @RequestMapping(value="main/{id}/{pwd}",method=RequestMethod.GET) // Get방식일때만 처리 GetMapping
    //@RequestMapping(value="main/${id}/${pwd}",method=RequestMethod.POST)// PostMapping
    public String main_path(@PathVariable("id") String id,@PathVariable("pwd") String pwd,Model model)
    {
    	model.addAttribute("id", id);
    	model.addAttribute("pwd", pwd); // Vue/React => 화면 (NodeJS서버)
    	return "main/data";
    }
    
}











