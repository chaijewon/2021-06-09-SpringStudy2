package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
/*
 *   전송 (JSP) 
 *     = @Controller : 파일 변경 (데이터 전송)
 *          = forward : CV (파일이 변경되지 않고 => 내용만 변경) => return "폴더명/JSP명"
 *          = sendRedirect : 다른 파일에 저장    => "redirect:~"
 *          ==> 전송시 (Model,HttpServletRequest)
 *     = @RestController : 데이터 변경 (데이터 전송) => 일반 문자열 , JSON , XML , HTML
 *          => 다른 언어를 이용할 때 사용 
 *             1. JavaScript (Ajax,VueJS,ReactJS)
 *             2. Kotlin 
 */
@Controller
public class FoodController {
   @Autowired
   private FoodDAO dao;
   
   // 사용 요청할 내용 구분 
   @GetMapping("food/category.do")
   public String food_category(String cno,Model model)
   {
	   if(cno==null)
		   cno="1";
	   int no=Integer.parseInt(cno);
	   Map map=new HashMap();
	   map.put("cno", no);
	   List<CategoryVO> list=dao.categoryListData(map);
	   // 데이터 전송
	   model.addAttribute("list", list);
	   return "food/category"; // => /WEB-INF/food/category.jsp => tiles
   }
   
   @GetMapping("food/category_list.do")
   public String food_category_list(int cno,Model model)
   {
	   // 데이터베이스 읽기 => DAO
	   List<FoodVO> list=dao.categoryFoodListData(cno);
	   for(FoodVO vo:list)
	   {
		   String poster=vo.getPoster();
		   poster=poster.substring(0,poster.indexOf("^"));
		   vo.setPoster(poster);
		   String address=vo.getAddress();
		   address=address.substring(0,address.indexOf("지"));
		   vo.setAddress(address);
	   }
	   model.addAttribute("list", list);
	   
	   CategoryVO vo=dao.categoryInfoData(cno);
	   model.addAttribute("vo", vo);
	   return "food/category_list";
   }
   
   @GetMapping("food/detail.do")
   /*
    *    1. Model => forward (request)
    *    2. RedirectAttributes => redirect (request를 초기화)
    */
   public String food_detail(int no,Model model)
   {
	   // DAO 연결 = 데이터베이스에서 요청한 데이터를 읽어서 전송 
	   FoodVO vo=dao.foodDetailData(no);
	   String address=vo.getAddress();
	   String addr1=address.substring(0,address.indexOf("지"));// 지 생략 
	   String addr2=address.substring(address.indexOf("지")); // 지를 포함  => 지도 
	   // 전송 
	   model.addAttribute("vo", vo);
	   model.addAttribute("addr1", addr1);
	   model.addAttribute("addr2", addr2);
	   
	   // 관련 레시피를 보낸다 
	   /*
	    *   국수 / 면 요리  => 국수|면 요리
	    *   베이커리
	    */
	   String title=vo.getType();
	   title=title.replace("기타","");
	   title=title.replace("정통", "");
	   int n=title.indexOf("/");
	   String s="";
	   if(n>=0)
	   {
		   StringTokenizer st=new StringTokenizer(title,"/");
		   while(st.hasMoreTokens())
		   {
			   s+=st.nextToken()+"|"; // REGEXP_LIKE (정규식) => 오라클  => [가-힣] [A-B]
			   // 여러개를 동시에 찾는 경우   | 
			   // WHERE title LIKE '%'||국수||'%' OR title LIKE '%'||면 요리||'%'
			   // WHERE REGEXP_LIKE(title, '국수|면 요리')
		   }
		   s=s.substring(0,s.lastIndexOf("|"));
	   }
	   else
	   {
		   s=title;
	   }
	   
	   List<RecipeVO> list=dao.foodRecipeData(s.trim());
	   for(RecipeVO rvo:list)
	   {
		   String ss=rvo.getTitle();
		   if(ss.length()>12)
		   {
			   ss=ss.substring(0,12)+"...";
			   
		   }
		   rvo.setTitle(ss);
	   }
	   
	   model.addAttribute("list", list);
	   return "food/detail";
   }
   
   @GetMapping("food/location.do")
   public String food_location(String page,String ss,Model model)
   {
	   // 검색 => 검색어를 넘겨준다
	   if(ss==null)
		   ss="마포";
	   
	   if(page==null)
		   page="1";
	   
	   int curpage=Integer.parseInt(page);
	   
	   Map map=new HashMap();
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=(rowSize*curpage);
	   
	   map.put("start", start);
	   map.put("end", end);
	   map.put("address", ss);
	   
	   List<FoodLocationVO> list=dao.foodLocationSearchData(map);
	   int totalpage=dao.foodLocationTotalPage(ss);
	   
	   model.addAttribute("list", list);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("ss", ss);
	   return "food/location";
   }
}










