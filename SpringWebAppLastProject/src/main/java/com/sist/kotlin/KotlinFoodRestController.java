package com.sist.kotlin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
// 핸드폰으로 전송 (JSON)
@RestController
public class KotlinFoodRestController {
   @Autowired
   private FoodDAO dao;
   
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
}











