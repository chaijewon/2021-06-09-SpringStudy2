package com.sist.kotlin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
}











