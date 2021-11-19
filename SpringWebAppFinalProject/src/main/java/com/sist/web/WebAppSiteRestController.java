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
	/*
	 *   Spring (라이브러리) 
	 *      클라언트 요청 ========>DispatcherServlet ====> 요청 처리 
	 *                                                   |
	 *                                                 HandlerMapping => @RequestMapping
	 *                                                   | 결과값 
	 *                                                 ViewResolver
	 *                                                 동작의 기본 (웹 => 틀) => 프레임워크 
	 *        *.do
	 *        =구분
	 *   Mybatis : 라이브러리  => 오라클 기본 정보 , SQL , VO/List/일반 데이터 
	 *   =========
	 *     axios => then()
	 *   React  =
	 *   Vue    =
	 *   Ajax   =  형식 , 순서 => View
	 *   ================ 웹 (클라이언트 / 서버)
	 *                         요청         응답 
	 *   Kotlin 
	 */
	@RequestMapping(value="food/rest_detail.do",produces="text/plain;charset=UTF-8")
	public String food_detail(int no)
	{
		String json="";
		try
		{
			FoodVO vo=dao.foodDetailData(no);
			// vo=> JSONObject 
			JSONObject obj=new JSONObject();
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			String addr=vo.getAddress();
			addr=addr.substring(0,addr.lastIndexOf("지"));
			obj.put("address", addr);
			obj.put("tel", vo.getTel());
			obj.put("type", vo.getType());
			obj.put("price", vo.getPrice());
			obj.put("time", vo.getTime());
			obj.put("parking", vo.getParking());
			obj.put("menu", vo.getMenu());
			
			json=obj.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	
	// 서울 관련 
	/*
	 *   스프링에서는 역할 분담 
	 *   @Controller : 화면 변환 , 변환시 출력할 데이터 전송 
	 *                 forward : request를 전송 ==> model.addAttribute()
	 *                 return "main/main"
	 *                 redirect : request를 전송하는 것이 아니고 (화면 변경)
	 *                 return "redirect:~.do";
	 *   @RestController : 화면변환은 없고 출력할 데이터만 전송
	 *                 => 일반 데이터 전송 
	 *                 => 데이터가 많은 경우 , 다른 언어에서 사용할 경우 : JSON
	 *                    JavaScript(Ajax,VueJs,ReactJS) / Kotlin ==> List,~VO가 존재하지 않는다 
	 *   =====================  클라이언트로 데이터 전송 , 화면 변환 
	 *   @Repository (~DAO)
	 *   ===================== 오라클에 있는 데이터만 관리 (클라이언트로 직접 전송이 불가능 하다)
	 *   
	 *   MVC
	 *   = Model => 요청처리 
	 *   = View  => 처리된 결과 
	 *   = Controller => 요청을 받아서 결과값을 보내준다 (DispatcherServlet)
	 *                                           ==================
	 *                                                | 위임
	 *                                               @Controller
	 *     ==========
	 *     *** MVC의 단점 Controller 의존성이 높다 (분산:여러개의 Controller사용 => 도메인) : Spring5
	 *     
	 *     폭포수 => 프로토타입 => 애자일 (분산 : 각팀마다 요구사항)
	 *               | 수시로 요구사항 => 체크 
	 *      | 위에서 관리 (요구사항)  
	 *      
	 *    JSON 변경 => List => JSONArray  ===> []
	 *                VO   => JSONObject ===> {} (JSON:자바스크립트 객체 표현법)
	 *                
	 *    어노테이션은 구분하는 프로그램 
	 *             ==== 인덱스 
	 */
	// 찾기 (사용자 요청 찾기) => @RequestMapping , @GetMapping , @PostMapping 
	@RequestMapping(value="seoul/location_list.do",produces="text/plain;charset=UTF-8")
	public String seoul_location(int page)
	{
		System.out.println("page="+page);
		String json="";
		try
		{
			// page처리 
			Map map=new HashMap();
			int rowSize=20;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			map.put("start", start);
			map.put("end", end);
			List<SeoulLocationVO> list=dao.seoulLocationListData(map);
			System.out.println(list);
			map.put("table", "seoul_location");
			int totalpage=dao.seoulTotalPage(map);
			System.out.println("totalpage="+totalpage);
			// JSON변경 
			JSONArray arr=new JSONArray();
			int k=0;
			for(SeoulLocationVO vo:list)
			{
				// vo=>JSONObject
				JSONObject obj=new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("title", vo.getTitle());
				obj.put("poster", vo.getPoster());
				if(k==0)
				{
					obj.put("page", page);
					obj.put("totalpage", totalpage);
				}
				arr.add(obj);
				k++;
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	@RequestMapping(value="seoul/nature_list.do",produces="text/plain;charset=UTF-8")
	public String seoul_nature(int page)
	{
		String json="";
		try
		{
			// page처리 
			Map map=new HashMap();
			int rowSize=20;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			map.put("start", start);
			map.put("end", end);
			List<SeoulNatureVO> list=dao.seoulNatureListData(map);
			map.put("table", "seoul_nature");
			int totalpage=dao.seoulTotalPage(map);
			
			// JSON변경 
			JSONArray arr=new JSONArray();
			int k=0;
			for(SeoulNatureVO vo:list)
			{
				// vo=>JSONObject
				JSONObject obj=new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("title", vo.getTitle());
				obj.put("poster", vo.getPoster());
				if(k==0)
				{
					obj.put("page", page);
					obj.put("totalpage", totalpage);
				}
				
				arr.add(obj);
				k++;
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	
	@RequestMapping(value="seoul/hotel_list.do",produces="text/plain;charset=UTF-8")
	public String seoul_hotel(int page)
	{
		String json="";
		try
		{
			// page처리 
			Map map=new HashMap();
			int rowSize=20;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			map.put("start", start);
			map.put("end", end);
			List<SeoulHotelVO> list=dao.seoulHotelListData(map);
			map.put("table", "seoul_hotel");
			int totalpage=dao.seoulTotalPage(map);
			
			// JSON변경 
			JSONArray arr=new JSONArray();
			int k=0;
			for(SeoulHotelVO vo:list)
			{
				// vo=>JSONObject
				JSONObject obj=new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("name", vo.getName());
				obj.put("poster", vo.getPoster());
				obj.put("score",vo.getScore());
				if(k==0)
				{
					obj.put("page", page);
					obj.put("totalpage", totalpage);
				}
				arr.add(obj);
				k++;
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	
	@RequestMapping(value="food/find.do",produces="text/plain;charset=UTF-8")
	public String food_find(String page,String ss)
	{
		System.out.println("page="+page);
		String json="";
		try
		{
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			// 오라클 연결 
			Map map=new HashMap();
			int rowSize=20;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			map.put("start", start);
			map.put("end", end);
			map.put("ss", ss);
			List<FoodVO> list=dao.foodFindData(map);
			System.out.println("list="+list);
			int totalpage=dao.findTotalPage(ss);
			System.out.println("totalpage="+totalpage);
			// JSON으로 변경
			JSONArray arr=new JSONArray();
			int k=0;
			/*
			 *   정형화 : 저장된 갯수 동일 (오라클) 
			 *   반정형화 : 저장된 갯수 동일하지 않는데 구분이 된 데이터 (JSON,XML)
			 *   비정형화 : 구분이 안되는 데이터 ==> 빅데이터 ==> 분석 , 통계 ==> 정형화 ==> AI
			 *   ========== 형태소 분석 
			 *   
			 *   마포에 있는 맛집을 추천해 주세요 
			 *   ===     ===  ===
			 *   마포         맛집    추천  ===> 챗봇  ===> 꼬꼬마 (형태소 => 명사찾아주는 라이브러리 (R))
			 *   
			 *   일반 챗봇 
			 *   문장 ==> 문장안에 포함된 데이터가 많은 답변을 찾아서 응답 
			 */
			for(FoodVO vo:list)
			{
				// vo => {} => JSONObject
				JSONObject obj=new JSONObject(); // Rest API (Restful) => JSON (다른 응용프로그램과 연결)
				// 웹 / 앱 (모바일)  no,name,poster
				obj.put("no", vo.getNo());
				obj.put("name",vo.getName());
				String poster=vo.getPoster();
				poster=poster.substring(0,poster.indexOf("^"));
				obj.put("poster", vo.getPoster());
				
				if(k==0)
				{
					obj.put("page", curpage);
					obj.put("totalpage", totalpage);
				}
				
				arr.add(obj); // list.add()  
				k++;
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
}















