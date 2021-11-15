package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
// React,Kotlin 요청을 받는다 => JSON응답 
@RestController // 파일명 (X) => 출력에 필요한 데이터를 보낸다 
// 출력 객체 (데이터를 여러개 있는 경우) ==> JSON
// {} => ~VO , List => []
// 자바 => {} (JSONObject) , [] (JSONArray)
public class RecipeRestController {
   // 전체적으로 필요한 클래스 객체 : RecipeDAO  => 스프링에서 생성 => 주소 (스프링으로부터 얻어 온다)
   @Autowired
   private RecipeDAO dao;
   
   // 레시피 목록 => 페이지별 
   @RequestMapping(value="recipe/rest_list.do",produces="text/plain;charset=UTF-8")
   // @RestController => Model클래스를 이용하지 않는다 => return에서 일반 문자열 전송 
   public String recipe_list(String page)
   {
	   String json="";
	   try
	   {
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   // JavaScript , Kotlin => List,~VO(X) => JSON
		   Map map=new HashMap();
		   int rowSize=20;
		   int start=(rowSize*curpage)-(rowSize-1);
		   int end=rowSize*curpage;
		   
		   map.put("start", start);
		   map.put("end", end);
		   List<RecipeVO> list=dao.recipeListData(map);
		   // List => JSON변경 ==> JSONArray
		   JSONArray arr=new JSONArray();
		   for(RecipeVO vo:list)
		   {
			   JSONObject obj=new JSONObject(); // VO
			   // no,title,poster,chef
			   obj.put("no", vo.getNo());
			   obj.put("title", vo.getTitle());
			   obj.put("poster", vo.getPoster());
			   obj.put("chef", vo.getChef());
			   // 배열로 묶어서 한번에 전송 
			   arr.add(obj);
		   }
		   json=arr.toJSONString();
		   System.out.println(json);
	   }catch(Exception ex){}
	   return json;
   }
   @RequestMapping(value="recipe/rest_total.do",produces="text/plain;charset=UTF-8")
   public String recipe_rest_total()
   {
	   String json="";
	   JSONObject obj=new JSONObject();
	   obj.put("totalpage", dao.recipeTotalPage());
	   json=obj.toJSONString();
	   return json;
   }
}











