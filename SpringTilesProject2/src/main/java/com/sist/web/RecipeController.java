package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *    요청 (.do) => JSP  =======> DispactherServlet 
 *                                   |
 *                                   |
 *                              HandlerMapping (요청처리 메소드를 찾는다) => 구분 (@RequestMapping)
 *                                              ===========
 *                                               메소드안에서 데이터베이스 연동 , 결과값을 JSP로 전송 
 *                                                                       ===============
 *                                                                            | ViewResolver
 *                                                                            |
 *                                                                          결과값 전송 
 *                                                                            |
 *                                                                            |
 *                                                                          JSP에서 출력 
 *         
 *   1. JSP 화면 (요청 => <a> , <form> , Ajax , axios) => 서버에 화면을 보여달라
 *   2. 모든 사용자가 공통으로 볼 수 있는 데이터를 가지고 온다 (데이터베이스)  DAO
 *                                                 | 
 *                                              공통된 데이터
 *   3. DAO연결해서 데이터읽기 => JSP로 전송 : @Controller
 *   ===================================================================
 *      jsp/dao/controller
 *          ============== 재사용(기능별 분리) => 컴포넌트(한개의 기능: 게시판전체,맛집...) => 컨테이너 (스프링)
 *          = 1. 컨테이너 
 *          = 2. 컴포넌트
 *          = 3. 공통모듈 
 *          ============= 기술면접                                                                                   
 *          = 객체지향이란는 프로그램이 있다 객체란 무엇인가? (캡슐화/상속/다형성)(X)
 *                                  ==== 프로그램에서 사용하는 사물을 단순화            
 *                                  ==== 300개 
 *          = 자바스크립트 : 클로저 
 *                
 */
import java.util.*;
import com.sist.dao.*;
@Controller
public class RecipeController {
   // DAO를 가지고 온다 (셋팅이 종료한 후 저장 => 스프링에서만 가지고 있다)
   @Autowired
   private RecipeDAO dao; // RecipeDAO dao=new RecipeDAO() => 셋팅 
   
   // 메뉴얼 => 레시피 목록 , 페이지 , 상세 , 쉐프... (웹기획)
   /*
    *   웹기획 (기능,요구사항) ===> DBA(데이터베이스 설계) ====> 퍼블러셔 (템플릿) ====> 웹개발자(구현) =====> 테스터(테스트)
    *   ====> 기술영업 
    *   팀 
    *    팀장 (PM) : 7년
    *     |
    *   ======
    *   |    |
    *  PL   PL : 5년
    *   |    |
    *  ===  ===
    *  |  | |  |  => 1~3
    *  ==
    *  | => 신규 
    */
   
   // 1. 메뉴 (레시피 목록을 보여준다) 
   @GetMapping("recipe/list.do")
   /*
    *   1. 메뉴 
    *      ===
    *      1) 메소드 (메뉴처리) 
    *         =====
    *         리턴형 / 매개변수 
    *         = 스프링에서 요청 처리 메소드의 리턴형 2개만 사용 
    *           1. String : 파일명 
    *           2. void : 다운로드 
    *         = 매개변수 
    *            = 일반 데이터형으로 받는 경우 (모든 데이터형 String으로 받을 수 있다 , 해당 데이터형으로 받는다)
    *              => 무조건 넘어오는 값 
    *                 detail.do?no=1  ==> int no
    *              => 사용자가 선택시에 넘어오는 값  
    *                 페이지 => String page 
    *            = 커맨드 객체 
    *              => ~VO (insert,update)
    *            = 컬렉션 
    *              => 파일업로드 여러개 ...
    *            = 라이브러리 클래스 
    *              => 1. 내장 객체 (JSP) 
    *                    HttpServletRequest 
    *                    HttpServletResponse
    *                    HttpSession 
    *                    RedirectAttributes
    *                    => Cookie는 내장 객체가 아니다 (직접 생성) 
    *              => 2. Model : 전송 객체 
    *              
    *              
    *              => 전송 객체 
    *                 1. forward  => Model  ==> return "경로/JSP명" => 해당 JSP request를 보내준다 
    *                 2. redirect => RedirectAttributes ==> return "redirect:~.do" => 
    *                                request를 초기화 ( _ok => 화면을 이동)
    *                                               =====
    *                                               처리만 하는 부분 => insert_ok => list.do
    *                                                              update_ok => detail.do
    *       피한다                                                                                                     delete_ok => list.do
    *             
    *             
    *       list.do?page=1 
    *         page=null 
    *         int ==> Integer.parseInt(null) => NumberFormatException => 500
    */
   public String recipe_list(String page,Model model)
   {
	   if(page==null)
		    page="1"; // 선택이 없는 상태 (default)
	   int curpage=Integer.parseInt(page);
	   
	   // start/end
	   int rowSize=20;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);
	   
	   // 오라클 => 목록 
	   List<RecipeVO> list=dao.recipeListData(map);
	   String temp="";
	   // ${fn:substring()}
	   for(RecipeVO vo:list)
	   {
		   String title=vo.getTitle();
		   if(title.length()>15)
		   {
			   temp=title.substring(0,15)+"...";
			   vo.setTitle(temp);// 화면 UI가 깨진다 
		   }
	   }
	   // 총페이지 
	   int totalpage=dao.recipeTotalPage();
	   
	   // 블럭별 페이지 나누기
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   /*
	    *     1  2  3  4  5  6  7  8  9  10
	    *     
	    *     curpage = 1~10  => startPage=1
	    *     ((curpage-1)/BLOCK*BLOCK)+1;
	    *     => curpage=1 => ((1-1)/10*10)+1 => 1
	    *                     ((10-1)/10*10)+1 => 1
	    *                      =========
	    *                       9/10 => 0 
	    *         curpage ((11-1)/10*10)+1 ==>
	    *                   ========
	    *                     1*10 +1 ==> 11
	    *     
	    *     curpage = 11~20 => startPage=11
	    *     
	    *     curpage = 1~10  => endPage=10
	    *     cuprage = 11~20 => endPage=20
	    */
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   
	   // endPage =>  10 , 20 , 30 .... =>totalpage= 111/10 => 12 => 10 2
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   // list.jsp로 출력에 필요한 데이터를 보내준다 
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("BLOCK", BLOCK);
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   model.addAttribute("list", list); // JavaScript => JSONArray => []
	   model.addAttribute("count", dao.recipeCount());
	   // 1. => 페이지 기법 (반드시)
	   return "recipe/list";
   }
   // 사용자 => .do => @Controller/@RestController
   /*
    *   MVC => java먼저 => JSP
    *          ======= 데이터를 가지고 JSP에서 
    *          Controller => Model => Controller => View 
    *          
    *   JSP 
    *   <%
    *       
    *   %>
    *   <html>
    *   </html>
    *   
    *   View => View => Model => View 
    *   MVVC  MVVM(Vue) => MVC
    */
   @GetMapping("recipe/detail.do")
   public String recipe_detail(int no,int page,Model model)
   {
	   // DAO연결 => 오라클에서 no에 해당되는 데이터 읽기 
	   RecipeDetailVO vo=dao.recipeDetailData(no);
	   String make=vo.getFoodmake();
	   String[] makes=make.split("\n");
	   List<String> mList=Arrays.asList(makes);
	   
	   
	   String img=vo.getFoodimg();
	   String[] imgs=img.split("\\^");
	   List<String> iList=Arrays.asList(imgs); // 배열 => List로 변경 (asList())
	   // 배열 => List Arrays.asList()  , List => 배열  iList.toArray()
	   
	   
	   
	   // regex => ^ (시작,부정) ==> ^자체 문자를 읽을 경우 => \\^
	   // | , * , . , ? , ^ , +     \\?
	   
	   model.addAttribute("page", page);
	   
	   model.addAttribute("vo", vo); // Front (JavaScript) => JSONObject => {}
	   model.addAttribute("mList", mList);
	   model.addAttribute("iList", iList);
	   return "recipe/detail";
   }
   
   // 쉐프 
   @GetMapping("recipe/chef.do")
   public String recipe_chef(String page,Model model)
   {
	   // DAO
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=50;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);
	   // 목록 읽기
	   List<ChefVO> list=dao.chefListData(map);
	   // 총페이지 읽기
	   int totalpage=dao.chefTotalPage();
	   // chef.jsp =>  전송 
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("list", list);
	   return "recipe/chef";
   }
   
   @RequestMapping("recipe/chef_make.do")
   public String recipe_chef_make(String page,String chef,String ss,Model model)
   {
	   if(ss==null||ss.equals(""))
	   {
		   ss="all";
	   }
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);
	   map.put("chef",chef);
	   map.put("ss", ss);
	   // 데이터 읽기 (목록)
	   List<RecipeVO> list=dao.chefMakeRecipeData(map);
	   String temp="";
	   // ${fn:substring()}
	   for(RecipeVO vo:list)
	   {
		   String title=vo.getTitle();
		   if(title.length()>15)
		   {
			   temp=title.substring(0,15)+"...";
			   vo.setTitle(temp);// 화면 UI가 깨진다 
		   }
	   }
	  
	   // 총페이지 
	   int totalpage=dao.chefMakeTotalPage(chef);
	   // 목록 / 총페이지 / 현재페이지 전송 
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("list", list);
	   model.addAttribute("chef", chef);
	   return "recipe/chef_make";
   }
   
   @GetMapping("recipe/recipe_make.do")
   public String recipe_make(Model model)
   {
	     String[] data= {
				  "밑반찬","메인반찬","국|탕","찌개","초스피드","손님접대","밥|죽|떡","술안주","면|만두",
				  "일상","빵","다이어트","디저트","샐러드","양식","김치|젓갈|장류","도시락","간식",
				  "돼지고기","영양식","과자","양념|소스|잼","차|음료|술","닭고기","야식","채소류","볶음",
				  "스프","소고기","해물류","푸드스타일링","육류","달걀|유제품","부침","조림","이유식",
				  "무침","해장","명절","버섯류","가공식품류","과일류","튀김","끓이기","찜","비빔",
				  "밀가루","건어물류","절임","굽기","삶기","회","쌀","콩|견과류","곡류","데치기","퓨전"  
		  };
	     model.addAttribute("data", data);
	     return "recipe/recipe_make";
   }
}























