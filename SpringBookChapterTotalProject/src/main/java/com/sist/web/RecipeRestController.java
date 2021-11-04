package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@RestController  // react출력 => JavaScript (JSON,XML) => reactJS
@CrossOrigin("http://localhost:3000")
// port가 틀릴 경우에 반드시 사용 승인 
public class RecipeRestController {
   @Autowired
   private RecipeDAO dao;
   
   @RequestMapping(value="recipe/rest_list.do",produces="text/plain;charset=UTF-8")
   public String recipe_list(String page)
   {
	   String json="";
	   try
	   {
		   // 데이터베이스에 저장된 값 
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   Map map=new HashMap();
		   int rowSize=20;
		   int start=(rowSize*curpage)-(rowSize-1); // 1, 21 , 41...
		   int end=rowSize*curpage; // 20 ,40 ,60...
		   map.put("start", start);
		   map.put("end", end);
		   List<RecipeVO> list=dao.recipeListData(map);
		   int totalpage=dao.recipeTotalPage();
		   int count=dao.recipeCount();
		   // JSON 변경 
		   JSONArray arr=new JSONArray();
		   int i=0;
		   for(RecipeVO vo:list)
		   {
			   JSONObject obj=new JSONObject(); // VO(객체) => JavaScript(객체) {}=>JSONObject
			   obj.put("no", vo.getNo()); // no:1 
			   obj.put("title", vo.getTitle());// title:''
			   obj.put("poster", vo.getPoster());
			   obj.put("chef", vo.getChef());
			   if(i==0)
			   {
				   obj.put("curpage", curpage);
				   obj.put("totalpage", totalpage);
				   obj.put("count", count);
			   }
			   
			   arr.add(obj);
			   i++;
			   
		   }
		   // JSON을 사용하는 언어 => Ajax , VueJS , ReactJS , Kotlin 
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
}









