package com.sist.kotlin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import com.sist.news.*;
// 핸드폰으로 전송 (JSON)
@RestController
public class KotlinFoodRestController {
   @Autowired
   private FoodDAO dao;
   
   @Autowired
   private RecipeDAO rDao;
   
   @Autowired
   private SeoulDAO sDao;
   
   @Autowired
   private NewsManager mgr;
   
   @GetMapping(value="main/kotlin_main.do",produces="text/plain;charset=UTF-8")
   public String main_kotlin_main(int no)
   {
	   String json="";
	   try
	   {
		   // 오라클에서 데이터 읽기
		   Map map=new HashMap();
		   map.put("no", no);
		   List<CategoryVO> list=dao.categoryListData(map);
		   // JSON으로 변경 
		   // List => JSONArray , CategoryVO => JSONObject
		   JSONArray arr=new JSONArray();
		   for(CategoryVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("cno", vo.getCno());
			   obj.put("title", vo.getTitle());
			   obj.put("poster", vo.getPoster());
			   obj.put("subject", vo.getSubject());
			   arr.add(obj); 
		   }
		   
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
   
   // category별 출력 
   @GetMapping(value="food/kotlin_category_list.do",produces="text/plain;charset=UTF-8")
   public String food_kotlin_category_list(int cno)
   {
	   String json="";
	   try
	   {
		   // 데이터 읽기 
		   List<FoodVO> list=dao.categoryFoodListData(cno);
		   for(FoodVO vo:list)
		   {
			   String poster=vo.getPoster();
			   poster=poster.substring(0,poster.indexOf("^")); 
			   String address=vo.getAddress();
			   address=address.substring(0,address.indexOf("지"));
			   vo.setPoster(poster);
			   vo.setAddress(address);
		   }
		   
		   CategoryVO cvo=dao.categoryInfoData(cno);
		   
		   // JSON을 만들어서 전송 
		   JSONArray arr=new JSONArray(); //List
		   int k=0;
		   for(FoodVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("no", vo.getNo());
			   obj.put("name", vo.getName());
			   obj.put("poster", vo.getPoster());
			   obj.put("score", vo.getScore());
			   obj.put("address", vo.getAddress());
			   obj.put("tel", vo.getTel());
			   obj.put("type", vo.getType());
			   
			   if(k==0)
			   {
				   obj.put("title", cvo.getTitle());
				   obj.put("subject", cvo.getSubject());
			   }
			   k++;
			   arr.add(obj);
		   }
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
   
   @RequestMapping(value="news/kotlin_news_find.do",produces="text/plain;charset=UTF-8")
   public String news_find(String ss)
   {
	   String json="";
	   try
	   {
		   // NewsManager 읽기 
		   List<NewsVO> list=mgr.newsListData(ss);
		   // JSON으로 변환 전송 => Kotlin => 화면 출력 
		   // List ==> JSONArray 
		   JSONArray arr=new JSONArray();
		   for(NewsVO vo:list)
		   {
			   // VO ==> JSONObject
			   JSONObject obj=new JSONObject();
			   obj.put("title", vo.getTitle());
			   obj.put("description", vo.getDescription());
			   obj.put("author", vo.getAuthor());
			   obj.put("link", vo.getLink());
			   obj.put("poster", vo.getPoster());
			   arr.add(obj);
		   }
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
   // FoodTask:Async   ==>  Okhttp
   @RequestMapping(value="food/kotlin_find.do",produces="text/plain;charset=UTF-8")
   public String food_kotlin_find(String page,String loc)
   {
	   System.out.println("Client=>page="+page);
	   System.out.println("Client=>loc="+loc);
	   String json="";
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   
	   if(loc==null)
		   loc="마포";
	   
	   Map map=new HashMap();
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   
	   map.put("start", start);
	   map.put("end", end);
	   map.put("loc", loc);
	   
	   
	   // 오라클에서 데이터 읽기 
	   List<FoodVO> list=dao.foodFindData(map);
	   for(FoodVO vo:list)
	   {
		   String poster=vo.getPoster();
		   poster=poster.substring(0,poster.indexOf("^"));
		   vo.setPoster(poster);
	   }
	   int totalpage=dao.foodFindTotalPage(loc);
	   /////// 오라클 
	   /////// 코틀린으로 전송 ==> JSON, 일반 문자열 
	   int k=0;
	   JSONArray arr=new JSONArray();
	   for(FoodVO vo:list)
	   {
		   JSONObject obj=new JSONObject();
		   obj.put("poster", vo.getPoster());
		   obj.put("name", vo.getName());
		   if(k==0)
		   {
			   obj.put("curpage", curpage);
			   obj.put("totalpage", totalpage);
			   obj.put("loc", loc);
		   }
		   
		   arr.add(obj);
		   k++;
	   }
	   json=arr.toJSONString();
	   System.out.println(json);
	   return json;
   }
   ///////////// recipe 관련 
   /*
    *    http://localhost/web/recipe/detail.do?no=1 => ?뒤는 자동으로 request
    *    ========================================== ==> URL
    *    ================ 서버 정보
    *                    ==== ContextPath 
    *                        ======================= ==> URI
    *                        
    */
   @RequestMapping(value="recipe/kotlin_list.do",produces="text/plain;charset=UTF-8")
   public String recipe_kotlin_list(String page)
   {
	   // OkHttp3 ==> 웹서버 연결시 사용  ==> AsyncTask ==> 경고  ==> OkHttp
	   String json="";
	   try
	   {
		   // JSP/Java ==> List,VO 인식 
		   // Kotlin , Ajax, VueJs ==> List,VO를 인식할 수 없다 =====> JSON
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   Map map=new HashMap();
		   int rowSize=12;
		   int start=(rowSize*curpage)-(rowSize-1);
		   int end=rowSize*curpage;
		   
		   map.put("start", start);
		   map.put("end", end);
		   
		   List<RecipeVO> list=rDao.recipeListData(map);
		   int totalpage=rDao.recipeTotalPage(); 
		   // ==> JSON으로 전송 
		   int k=0;
		   JSONArray arr=new JSONArray(); //List
		   for(RecipeVO vo:list)
		   {
			   JSONObject obj=new JSONObject(); // VO
			   obj.put("no", vo.getNo());
			   obj.put("title", vo.getTitle());
			   obj.put("poster", vo.getPoster());
			   obj.put("chef", vo.getChef());
			   if(k==0)
			   {
				   obj.put("curpage", curpage);
				   obj.put("totalpage", totalpage);
			   }
			   
			   arr.add(obj);
			   k++;
		   }
		   
		   json=arr.toJSONString();  // Kotlin , VueJS , ReactJS , Redux(React MVC) , vuex(Vue MVC) , Ajax
		// VueJS ==> MVVM 
		// 가상DOM , MVVM 
	   }catch(Exception ex){} // 미리 예상된 에러를 처리 => 정상수행이 가능하게 만든다 
	   return json;
   }
   
   // recipe 상세 
   @RequestMapping(value="recipe/recipe_detail.do",produces="text/plain;charset=UTF-8")
   public String recipe_detail(int no)
   {
	   String json="";
	   try
	   {
		   // 오라클로 부터 데이터를 받는다 
		   RecipeDetailVO vo=rDao.recipeDetailData(no);
		   // 받은 데이터를 JSON으로 변경후 ==> Kotlin으로 전송 
		   JSONObject obj=new JSONObject();
		   obj.put("poster", vo.getPoster());
		   obj.put("title", vo.getTitle());
		   obj.put("content", vo.getContent());
		   obj.put("chef", vo.getChef());
		   obj.put("chef_poster", vo.getChef_poster());
		   obj.put("chef_profile", vo.getChef_profile());
		   obj.put("info1", vo.getInfo1());
		   obj.put("info2", vo.getInfo2());
		   obj.put("info3", vo.getInfo3());
		  /* JSONArray arr1=new JSONArray();
		   String[] make=vo.getFoodmake().split("\n");
		   for(String m:make)
		   {
			   arr1.add(m);
		   }
		   JSONArray arr2=new JSONArray();
		   String[] img=vo.getFoodimg().split("\\^");
		   for(String i:img)
		   {
			   arr2.add(i);
		   }*/
		   
		   obj.put("foodmake", vo.getFoodmake());
		   obj.put("foodimg", vo.getFoodimg());
		   // Kotlin => StringTokenizer , split[]
		   // {no:1,title:'aaa',foodmake:[],foodimg:[]}
		   
		   json=obj.toJSONString();
		   System.out.println(json);
	   }catch(Exception ex){}
	   return json;
   }
   
   @RequestMapping(value="seoul/kotlin_location.do",produces="text/plain;charset=UTF-8")
   public String seoul_kotlin_location(String page)
   {
	   String json="";
	   try
	   {
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   Map map=new HashMap();
		    int rowSize=12;
		    int start=(rowSize*curpage)-(rowSize-1); // rownum => 1번부터 시작 
		    // 1, 13 ....
		    int end=rowSize*curpage; // 12, 24 , 36...
		    
		    // 데이터 읽기 
		    map.put("start", start);
		    map.put("end", end);
		    
		    List<SeoulLocationVO> list=sDao.locationListData(map);
		    int totalpage=sDao.locationTotalPage();
		    
		    // JSON으로 변경 
		    // List ==> JSONArray
		    JSONArray arr=new JSONArray();
		    int k=0;
		    // VO   ==> JSONObject
		    for(SeoulLocationVO vo:list)
		    {
		    	JSONObject obj=new JSONObject();
		    	obj.put("no", vo.getNo());
		    	obj.put("title", vo.getTitle());
		    	obj.put("poster", vo.getPoster());
		    	obj.put("msg", vo.getMsg());
		    	obj.put("address", vo.getAddress());
		    	
		    	if(k==0)
		    	{
		    		obj.put("curpage", curpage);
		    		obj.put("totalpage", totalpage);
		    	}
		    	
		    	arr.add(obj);
		    	k++;
		    }
		    json=arr.toJSONString();
		    // @RestController ==> @Controller (@ResponseBody=>승격 @RestController)
		    // 스프링은 순수하게 서버 역할 ==> Front-End(Ajax,VueJS,ReactJS) => jsp가 아니라도 가능
	   }catch(Exception ex){}
	   return json;
   }
}











