package com.sist.web;
/*
 *    Controller 
 *    Mapper
 *    DAO 
 *    ============= 기능별 클래스 제작 
 */
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;

// 메모리 할당 
@Controller
public class RecipeController {
   @Autowired
   private RecipeDAO dao;
   
   // 기능 => <form> , ajax , axios => Post
   @GetMapping("recipe/list.do")
   public String recipe_list(String page,Model model)
   {
	   if(page==null)
		   page="1"; // default 
	   int curpage=Integer.parseInt(page);
	   
	   // 페이지에 해당되는 데이터 읽기
	   Map map=new HashMap();
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   // ==> 페이지 나누기 (페이징 기법) , CURD(게시판) => 응용 (Session,Cookie)
	   map.put("start", start);
	   map.put("end", end);
	   List<RecipeVO> list=dao.recipeListData(map);
	   // 글자수 조절 => 화면 UI
	   for(RecipeVO vo:list)
	   {
		   String title=vo.getTitle();
		   if(title.length()>25)
		   {
			   title=title.substring(0,25)+"...";
		   }
		   vo.setTitle(title);
	   }
	   // 총페이지 
	   int totalpage=dao.recipeTotalPage();
	   
	   final int BLOCK=10;
	   // 자바 지역변수에서 사용할 수 있는 유일한 키워드 => final(상수 => const val)
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;  //startPage => curpage=10 => 1
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; //      10 ,20..30
	   if(endPage>totalpage)
	   {
		   endPage=totalpage;
	   }
	   // 출력할 데이터 전송 
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("BLOCK", BLOCK);
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   model.addAttribute("list", list);
	   model.addAttribute("main_jsp", "../recipe/list.jsp");
	   return "main/main";
   }
   
   // 상세보기 
   @GetMapping("recipe/detail.do")
   public String recipe_detail(int no,Model model)
   {
	   // 오라클 연결 => 해당 번호의 데이터 읽기 ==> SQL
	   RecipeDetailVO vo=dao.recipeDetailData(no);
	   
	   List<String> mList=new ArrayList<String>();
	   String[] make=vo.getFoodmake().split("\n");
	   mList=Arrays.asList(make); // 배열 => List  ==> List에서 배열 ==> toArray()
	   
	   List<String> iList=new ArrayList<String>();
	   String[] image=vo.getFoodimg().split("\\^");
	   iList=Arrays.asList(image);
	   
	   model.addAttribute("mList", mList);
	   model.addAttribute("iList", iList);
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_jsp", "../recipe/detail.jsp");
	   return "main/main";
   }
   
   // 데이터베이스 => Controller ==> JSP
   // SQL           스프링 동작과정    화면 UI ==> JSTL/EL
   // 인라인뷰 , 스칼라서브쿼리 , JOIN ==> SELECT (INSERT,UPDATE,DELETE=>형식이 1개)
   // 쉐프 목록 
   @GetMapping("recipe/chef.do")
   public String recipe_chef(String page,Model model)
   {
	   // 오라클 연결 => chef 데이터 읽기 => 총페이지 
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=20;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=(rowSize*curpage);
	   
	   map.put("start", start);
	   map.put("end", end);
	   
	   List<ChefVO> list=dao.chefListData(map);
	   int totalpage=dao.chefTotalPage();
	   
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("list", list);
	   
	   model.addAttribute("main_jsp", "../recipe/chef.jsp");
	   return "main/main";
   }
   
   @RequestMapping("recipe/chef_make.do")
   public String recipe_chef_make(String page,String chef,Model model)
   {
	   // 오라클 연결후 데이터 전송 
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=20;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=(rowSize*curpage);
	   
	   map.put("start", start);
	   map.put("end", end);
	   map.put("chef",chef);
	   
	   List<RecipeVO> list=dao.chefMakeData(map);
	   for(RecipeVO vo:list)
	   {
		   String title=vo.getTitle();
		   if(title.length()>20)
		   {
			   title=title.substring(0,20)+"...";
		   }
		   vo.setTitle(title);
	   }
	   // 총페이지 
	   int totalpage=dao.chefMakeTotalPage(chef);
	   
	   // JSP로 전송
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("chef", chef);
	   model.addAttribute("list", list);
	   model.addAttribute("main_jsp", "../recipe/chef_make.jsp");
	   return "main/main";
   }
}















