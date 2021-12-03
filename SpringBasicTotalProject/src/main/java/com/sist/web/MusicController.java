package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 위임을 받는다 
// DispatcherServlet (서빙) => 레고 / 라이브러리(완제품)=> .jar / 프레임워크(레고) .java 
                              
/*
 *   1. 요청을 받는다 (주문) => 스프링에 지원
 *   ============================
 *   ==> 위임 (@Controller) ==> 사이트에 맞게 구현 
 *   2. 주방에 전달 ======> DAO연결 
 *   3. 음식을 가지고 온다  => 데이터베이스 값을 읽어온다 
 *                       =========
 *                       공통된 데이터 
 *   ============================
 *   4. 응답 (상차림) => JSP에 출력  
 *      ==========스프링에서 지원 
 *      
 *   주문 : 음식 주문 @Controller => 테이블 지정 => forward
 *                               jsp파일명 
 *        ======= 
 *        반찬 주문  @RestController => 일반 데이터 , JSON
 *   
 *   게시판 => 글쓰기,수정 , 삭제 
 *   
 *   
 *   ==> 위임을 받아서 제작 ==> 스프링에서 관리 (메모리 할당 => 스프링에 맞긴다)
 *                                     => XML , Annotataion
 *                                       ==================
 *                                       XML / Annotation 차이점 
 *                                       1) 공통개발  => XML
 *                                       2) 각자 개발  => Annotation
 */
import java.util.*;
import com.sist.dao.*;
@Controller
// 요청 처리 => Model
public class MusicController {
   @Autowired
   //private MusicDAO dao;// Model소속 
   private MusicMapper dao;
   // as
   @RequestMapping("music/list.do")
   public String music_list1(Model model)
   {
	   List<MusicVO> list=dao.musicListData1();
	   model.addAttribute("list", list);
	   return "music/list";
   }
   // @Results
   @RequestMapping("music/list2.do")
   public String music_list2(Model model)
   {
	   List<MusicVO> list=dao.musicListData2();
	   model.addAttribute("list", list);
	   return "music/list2";
   }
}











