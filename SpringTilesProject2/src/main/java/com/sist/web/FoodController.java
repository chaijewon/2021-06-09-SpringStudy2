package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 *             
 *     상황
휴식 드라이브 산책 집 출/퇴근길 휴가/여행 운동 하우스파티 시상식 일/공부 카페 거리 클럽 고백 해변 공연 라운지 애도 집중
감성
외로움 기분전환 슬픔 힘찬 이별 지침/힘듦 설렘 위로 오후 밤 새벽 저녁 아침 사랑 스트레스/짜증 그리움 추억 우울 행복 불안 분노 기쁨 축하
스타일
밝은 신나는 웅장한 따뜻한 편안한 그루브한 부드러운 로맨틱한 매혹적인 영화음악 잔잔한 댄서블한 달콤한 몽환적인 시원한 애절한 어두운 연주음악 발렌타인데이 화이트데이
날씨/계절
봄 여름 가을 겨울 맑은날 추운날 흐린날 비오는날 더운날 안개낀날 눈오는날
 */
import com.sist.manager.*;
@Controller
public class FoodController {
   @Autowired
   private FoodDAO dao;
   
   @Autowired
   private RecommandManager mgr;
   
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
   public String food_detail(int no,String type,Model model)
   {
	   // DAO 연결 = 데이터베이스에서 요청한 데이터를 읽어서 전송 
	   Map map=new HashMap();
	   if(type==null)
		   map.put("tbl", "project_food_house");
	   else
		   map.put("tbl", "project_food_location");
	   
	   map.put("no", no);
	   FoodVO vo=dao.foodDetailData(map);
	   
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
	   model.addAttribute("ss", ss);// 검색어 
	   return "food/location";
   }
   
   @GetMapping("food/recommand.do")
   public String food_recommand(Model model)
   {
	   /*List<String> list=new ArrayList<String>();
	   list.add("상황");
	   list.add("감성");
	   list.add("스타일");
	   list.add("날씨/계절");
	   model.addAttribute("list",list);*/
	   //System.out.println("no="+no);
	   String[] sub1={"휴식", "산책" ,"퇴근길", "휴가","여행", "운동" ,"카페", "고백"};
	   
	   String[] sub2={"외로움", "기분전환", "슬픔" ,"이별" ,"지침","힘듦", "설렘","오후", "저녁", "사랑",
			          "스트레스","짜증", "그리움", "추억", "우울" ,"행복" ,"불안" ,"기쁨", "축하"};
	   String[] sub3={"밝은", "신나는", "따뜻한", "편안한", "부드러운", "로맨틱한",
			           "달콤한", "시원한", "애절한"};
	   String[] sub4={"봄", "여름", "가을", "겨울", "맑은날", "추운날", "흐린날", "비오는날", 
			          "더운날" , "눈오는날"};
	   model.addAttribute("sub1", sub1);
	   model.addAttribute("sub2", sub2);
	   model.addAttribute("sub3", sub3);
	   model.addAttribute("sub4", sub4);
	   return "food/recommand";
   }
  
   @GetMapping("food/ajax/result.do")
   public String food_recommand_result(String fd,Model model)
   {
	   System.out.println("fd="+fd);
	   List<String> list=mgr.recommandData(fd);
	   List<String> fList=dao.foodGetNameData();
	   int[] count=new int[fList.size()];
	   Pattern[] p=new Pattern[fList.size()];
	   /*
	    *    문자열을 찾는 경우 
	    *    =============
	    *    Pattren p=Pattern.compile("[가-힣]+") 
	    *    맛있다 맛있고 맛있는 맛있고 맛있니 .....
	    *    
	    *    Pattern.compile("맛있+")  => 0 이상 => 맛있* 
	    *    지능형웹의 기본 
	    *      = 크롤링 (정적 => Jsoup / 동적 => 셀레니움) => 데이터 수집 (필요한 데이터 추출)
	    *      = 패턴 (문자형태를 만들어 찾는)
	    *      = 통계 
	    *      = 컴퓨터에 내장 (교육) => AI
	    *      
	    *      아이피 
	    *        [0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}
	    *    
	    *    1) 범위 []  => [0-9] [A-Z] [a-z] [가-힣]
	    *    2) 글자수 => {1,3} ,{3} , {1,10} 
	    *    3) 기호 
	    *       ^
	    *       $
	    *       ?
	    *       |
	    *       +
	    *       *
	    *       .
	    */
	   for(int i=0;i<p.length;i++) // 패턴 
	   {
		   p[i]=Pattern.compile(fList.get(i)); // 단어 패턴  => 문자열 (100) => 단어포함 여부 확인 
	   }
	   
	   Matcher[] m=new Matcher[fList.size()];
	   for(String s:list)
	   {
		   //System.out.println(s);
		   for(int i=0;i<m.length;i++)
		   {
			   m[i]=p[i].matcher(s);
			   while(m[i].find()) // 문자열 안에 맛집이 포함된 경우 
			   {
				   count[i]++;
			   }
		   }
	   }
	   // 분석 => 형태소 분석 
	   List<FoodLocationVO> lList=new ArrayList<FoodLocationVO>();
	   for(int i=0;i<fList.size();i++)
	   {
		   if(count[i]>2 && fList.get(i).length()>1)
		   {
			   System.out.println(fList.get(i)+":"+count[i]);
			   FoodLocationVO vo=dao.foodInfoData(fList.get(i));
			   lList.add(vo);
		   }
	   }
	   model.addAttribute("lList", lList);
	   return "food/ajax/result";
   }
    
  
}










