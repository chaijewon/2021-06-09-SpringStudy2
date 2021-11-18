package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
// 다른 프로그램에 데이터 전송하는 역할 (JSON)
/*
 *    데이터 읽기 
 *     1. 오라클 데이터 읽기  : DAO => 오라클에서 데이터를 읽고 브라우저,모바일로 전송이 불가능  
 *     2. 데이터 전송 : @Controller,@RestController (배달) 
 *     
 *     ==> @Controller , @RestController 
 *         ==> DsipatcherServlet에서 request,response를 사용할 권한 
 */
@RestController
@CrossOrigin("http://localhost:3000")
// 포트 3000 사용 허용 
public class WebAppSiteRestController {
	@Autowired // 메모리 할당된 객체 요청 
    private RecipeDAO dao;
	
	// 레시피 목록 요청 
	@RequestMapping(value="recipe/rest_list.do",produces="text/plain;charset=UTF-8")
	public String recipe_list(int page)
	{
		String json="";
		// 1. 데이터를 읽어 온다
		Map map=new HashMap();
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		map.put("start", start);
		map.put("end", end);
		List<RecipeVO> list=dao.recipeListData(map);
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>12)
			{
				title=title.substring(0,12)+"...";
				vo.setTitle(title);
			}
		}
		// 총페이지 
		int totalpage=dao.recipeTotalPage();
		
		// 블럭별 처리 
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		/*
		 *   page=10 => (10-1)/10*10 => 0 + 1  ==> 1
		 *   page 1~10 ==> startPage = 1  [1]~[10]
		 *   page 11~20 ==> startPage=11
		 *   
		 *   33 ==> [31][32][33]~[40]
		 */
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK; // 10 , 20 , 30 
		if(endPage>totalpage)
			  endPage=totalpage;
		
		// JSON으로 전송 
		try
		{
			// List => 배열 (JSONArray)
			JSONArray arr=new JSONArray();
			// RecipeVO => JSONObject
			int i=0;
			for(RecipeVO vo:list)
			{
				JSONObject obj=new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("title", vo.getTitle());
				obj.put("poster", vo.getPoster());
				obj.put("chef", vo.getChef());
				
				if(i==0)
				{
					obj.put("page", page);
					obj.put("totalpage", totalpage);
					obj.put("startPage", startPage);
					obj.put("endPage", endPage);
				}
				
				arr.add(obj);
				i++;
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	
	@RequestMapping(value="recipe/rest_detail.do",produces="text/plain;charset=UTF-8")
	public String recipe_detail(int no)
	{
		String json="";
		RecipeDetailVO vo=dao.recipeDetailData(no);//BSON
		try
		{
			JSONObject obj=new JSONObject();
			if(vo!=null)
			{
				
				obj.put("title", vo.getTitle());
				obj.put("poster", vo.getPoster());
				obj.put("content", vo.getContent());
				obj.put("info1",vo.getInfo1());
				obj.put("info2",vo.getInfo2());
				obj.put("info3",vo.getInfo3());
				obj.put("chef", vo.getChef());
				obj.put("chef_poster", vo.getChef_poster());
				obj.put("chef_profile", vo.getChef_profile());
				//obj.put("foodmake", vo.getFoodmake());
				//obj.put("foodimg", vo.getFoodimg());
				json=obj.toJSONString();
			}
			else
			{
				obj.put("title", "준비된 내용이 없습니다");
				json=obj.toJSONString();
			}
				
		}catch(Exception ex){}
		return  json;
	}
	@RequestMapping(value="recipe/rest_foodmake.do",produces="text/plain;charset=UTF-8")
	public String recipe_foodmake(int no)
	{
		String json="";
		RecipeDetailVO vo=dao.recipeDetailData(no);
		try
		{
			String make=vo.getFoodmake();
			String[] s=make.split("\n");
			JSONArray arr=new JSONArray();
			for(String ss:s)
			{
				arr.add(ss);
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	@RequestMapping(value="recipe/rest_foodimg.do",produces="text/plain;charset=UTF-8")
	public String recipe_foodimg(int no)
	{
		String json="";
		RecipeDetailVO vo=dao.recipeDetailData(no);
		try
		{
			String img=vo.getFoodimg();
			String[] s=img.split("\\^");
			JSONArray arr=new JSONArray();
			for(String ss:s)
			{
				arr.add(ss);
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	/*
	 *    Java(JSP) , C#(ASP.Net) => 웹  => 스크립트 (PHP)
	 *    파이썬 => AI특화(데이터 수집,통계,분석)
	 *    코틀린 , 스칼라 => 모바일 
	 *    C/C++ => HW , 게임 (exe)
	 *    ===== 브라우저 (char(1byte)) 자바(2byte)
	 *                 
	 */
	@RequestMapping(value="food/rest_category.do",produces="text/plain;charset=UTF-8")
	public String food_category(int no)
	{
		//1. 오라클에서 데이터 읽기 
		//2. JSON으로 변경후 전송 => kotlin,react,vue,ajax (Java) => 호환성 있는 데이터형식 (JSON,XML)
		//3. => 전송 (스프링 ==> JSP) => List,~VO 
		String json="";
		try
		{
			Map map=new HashMap();
			map.put("no", no);
			List<CategoryVO> list=dao.categoryListData(map);// => JSONArray => []
			JSONArray arr=new JSONArray();
			for(CategoryVO vo:list)
			{
				// CategoryVO => JSONObject => {} => 자바스크립트에서는 {} 객체 ==> 표현법 (JSON)
				JSONObject obj=new JSONObject();
				obj.put("cno", vo.getCno());
				obj.put("title", vo.getTitle());
				obj.put("poster", vo.getPoster());
				arr.add(obj);
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	
	@RequestMapping(value="food/rest_food_category_list.do",produces="text/plain;charset=UTF-8")
	public String food_category_list(int cno)
	{
		String json="";
		try
		{
			// 1. 오라클 데이터 
			List<FoodVO> list=dao.foodCategoryListData(cno);
			CategoryVO cvo=dao.categoryInfoData(cno);
			int i=0;
			JSONArray arr=new JSONArray();
			for(FoodVO vo:list)
			{
				JSONObject obj=new JSONObject();
				// no,name,address,poster,tel,type,score
				obj.put("no", vo.getNo()); // {no:1,name:"",address:""}
				obj.put("name", vo.getName());
				String addr=vo.getAddress();
				addr=addr.substring(0,addr.lastIndexOf("지"));
				obj.put("address", addr);
				String poster=vo.getPoster();
				poster=poster.substring(0,poster.indexOf("^"));
				obj.put("poster", poster);
				obj.put("tel", vo.getTel());
				obj.put("type", vo.getType());
				obj.put("score", vo.getScore());
				
				arr.add(obj);
				i++;
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	@RequestMapping(value="food/rest_food_category_info.do",produces="text/plain;charset=UTF-8")
	public String food_category_info(int cno)
	{
		String json="";
		try
		{
			// 1. 오라클 데이터 
			
			CategoryVO cvo=dao.categoryInfoData(cno);
			JSONObject obj=new JSONObject();
			obj.put("title", cvo.getTitle());
			obj.put("subject", cvo.getSubject());
			json=obj.toJSONString();
		}catch(Exception ex){}
		return json;
	}
}















