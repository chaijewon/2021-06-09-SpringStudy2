package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Select;
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
   /*
    *  @Select("SELECT title,subject FROM project_food_category "
		   +"WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno);
	// 2. 카테고리별 맛집
	@Select("SELECT no,name,address,tel,type,poster "
		  +"FROM project_food_house "
		  +"WHERE cno=#{cno}")
    */
   @GetMapping(value="food/rest_detail.do",produces="text/plain;charset=UTF-8")
   public String food_res_detail(int cno)
   {
	   String json="";
	   try
	   {
		   JSONArray arr=new JSONArray();
		   List<FoodVO> list=dao.categoryFoodListData(cno);
		   for(FoodVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("no", vo.getNo());
			   obj.put("name", vo.getName());
			   obj.put("address", vo.getAddress().substring(0,vo.getAddress().indexOf("지")));
			   obj.put("tel", vo.getTel());
			   obj.put("type", vo.getType());
			   obj.put("poster", vo.getPoster().substring(0,vo.getPoster().indexOf("^")));
			   arr.add(obj);
		   }
		   json=arr.toJSONString();
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
		   CategoryVO vo=dao.categoryInfoData(cno);
		   obj.put("title", vo.getTitle());
		   obj.put("subject", vo.getSubject());
		   json=obj.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
    
   @RequestMapping(value="food/rest_food_detail.do",produces="text/plain;charset=UTF-8")
   public String food_detail(int no)
   {
	   String json="";
	   try
	   {
		   // 오라클 데이터 읽기 
		   FoodVO vo=dao.foodDetailData(no);
		   // JSON변경 
		   JSONObject obj=new JSONObject();
		   obj.put("no", vo.getNo());
		   obj.put("name", vo.getName());
		   obj.put("poster", vo.getPoster());
		   obj.put("address", vo.getAddress());
		   obj.put("tel", vo.getTel());
		   obj.put("type", vo.getType());
		   obj.put("time", vo.getTime());
		   obj.put("parking", vo.getParking());
		   obj.put("menu", vo.getMenu());
		   obj.put("good", vo.getGood());
		   obj.put("soso", vo.getSoso());
		   obj.put("bad", vo.getBad());
		   obj.put("score", vo.getScore());
		   
		   json=obj.toJSONString();
		   // 해당 JSP로 전송 =>Front
	   }catch(Exception ex){}
	   return json; // DispatcherServlet => 해당 JSP로 전송 
   }
   //no,title,address,poster,msg
   @RequestMapping(value="food/rest_nature_list.do",produces="text/plain;charset=UTF-8")
   public String food_nature_list(String addr)
   {
	   String json="";
	   try
	   {
		   // addr=학동로 338 강남파라곤 S동 1F 103호
		   String temp=addr;
		   temp=temp.substring(temp.indexOf(" "));
		   String temp1=temp.trim();// 앞에 있는 공백 제거 
		   temp1=temp1.substring(0,temp1.indexOf(" "));
		   System.out.println("주소:"+addr);
		   System.out.println("변경 주소:"+temp1);
		   List<SeoulNatureVO> list=dao.foodLikeNature(temp1.trim());
		   JSONArray arr=new JSONArray();
		   for(SeoulNatureVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("title", vo.getTitle());
			   obj.put("poster", vo.getPoster());
			   arr.add(obj);
		   }
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
   
   @RequestMapping(value="food/rest_hotel_list.do",produces="text/plain;charset=UTF-8")
   public String food_hotel_list(String addr)
   {
	   String json="";
	   try
	   {
		   String temp=addr;
		   temp=temp.substring(temp.indexOf(" "));
		   String temp1=temp.trim();
		   temp1=temp1.substring(0,temp1.indexOf(" "));
		   System.out.println("주소:"+addr);
		   System.out.println("변경 주소:"+temp1);
		   List<SeoulHotelVO> list=dao.foodLikeHotel(temp1.trim());
		   JSONArray arr=new JSONArray();
		   for(SeoulHotelVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("name", vo.getName());
			   obj.put("poster", vo.getPoster());
			   arr.add(obj);
		   }
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
   
   @RequestMapping(value="food/rest_loc_list.do",produces="text/plain;charset=UTF-8")
   public String food_location_list(String addr)
   {
	   // 강남구 학동로 338 강남파라곤 S동 1F 103호
	   String json="";
	   try
	   {
		   /*
		    *   substring(int start) => start부터
		    *   substring(int start,int end)
		    *             start부터 ~ (end-1)까지
		    */
		   String temp=addr;
		   temp=temp.substring(temp.indexOf(" "));// 제외를 안한다 
		   System.out.println("temp="+temp);
		   String temp1=temp.trim();
		   temp1=temp1.substring(0,temp1.indexOf(" "));// 제외가 된다
		   System.out.println("주소:"+addr);
		   System.out.println("변경 주소:"+temp1);
		   List<SeoulLocationVO> list=dao.foodLikeLocation(temp1.trim());
		   JSONArray arr=new JSONArray();
		   for(SeoulLocationVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("title", vo.getTitle());
			   obj.put("poster", vo.getPoster());
			   arr.add(obj);
		   }
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
   
}










