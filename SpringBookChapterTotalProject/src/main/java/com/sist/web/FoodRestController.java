package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import lombok.Getter;
@RestController // 데이터만 전송
// template (중복이 많은 경우) , router : 화면 이동 
// 사용자 정의 이벤트 : 채팅 , 실시간 상담 => NodeJS
public class FoodRestController {
   @Autowired
   private FoodDAO dao;
   
   @GetMapping(value="food/rest_category.do",produces="text/plain;charset=UTF-8")
   public String rest_category(int no)
   {
	   System.out.println("no="+no);
	   String json="";
	   try
	   {
		   Map map=new HashMap();
		   map.put("no", no);
		   List<CategoryVO> list=dao.categorySelectData(map);
		   
		   // Javascript가 인식할 수 있는 데이터형태로 변경 (JSON)
		   JSONArray arr=new JSONArray(); //List
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
		   System.out.println(json);
	   }catch(Exception ex){ex.printStackTrace();}
	   return json;
   }
   // 검색 
   @RequestMapping(value="food/rest_find.do",produces="text/plain;charset=UTF-8")
   // 사용자 요청한 URI
   // 요청 처리 
   public String food_rest_find(int page,String ss)
   {
	   String json="";
	   try
	   {
		   int curpage=page;
		   int rowSize=20;
		   int start=(rowSize*curpage)-(rowSize-1);
		   int end=(rowSize*curpage);
		   
		   Map map=new HashMap();
		   map.put("start", start);
		   map.put("end", end);
		   map.put("ss", ss);
		   
		   List<FoodVO> list=dao.foodFindData(map);
		   // 총페이지 받기 
		   int totalpage=dao.foodFindTotalPage(ss);
		   // List => JSON
		   // 비정형 (데이터 갯수가 다를 수 있다)/ 정형(오라클) => 문법사항이 없다 
		   /*
		    *   {curpage:1,totalpage:10,no:1,name:'',poster:''}
		    *   {no:1,name:'',poster:''}
		    *   {no:1,name:'',poster:''}
		    */
		   JSONArray arr=new JSONArray();
		   int i=0;
		   for(FoodVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("no", vo.getNo());
			   obj.put("name", vo.getName());
			   String poster=vo.getPoster();
			   poster=poster.substring(0,poster.indexOf("^"));// VueJS
			   obj.put("poster", poster);
			   if(i==0)
			   {
				   obj.put("curpage", curpage);
				   obj.put("totalpage", totalpage);
			   }
			   arr.add(obj);
			   i++;
		   }
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
   @GetMapping(value="food/rest_detail.do",produces="text/plain;charset=UTF-8")
   public String food_res_detail(int cno)
   {
	   String json="";
	   try
	   {
		   JSONArray arr=new JSONArray();
	   }catch(Exception ex){}
	   return json;
   }
   // @RequestMapping() = @GetMapping+@PostMapping
   /*
    *    폼 한개를 가지고 두개 처리  
    *    GET / POST
    *    
    *    
    */
   @RequestMapping(value="food/rest_info.do",produces="text/plain;charset=UTF-8")
   public String food_res_info(int cno)
   {
	   String json="";
	   try
	   {
		   JSONObject obj=new JSONObject();
	   }catch(Exception ex){}
	   return json;
   }
   
   
}










