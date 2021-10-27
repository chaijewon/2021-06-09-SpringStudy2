package com.sist.web;

import org.apache.ibatis.annotations.Select;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.manager.*;// 외부에서 데이터 읽기 => 네이버 뉴스 읽기 , 뉴스 등록 (실시간 뉴스) => open api 
// 네플릭스 (open api)
// 책 => 알라딘 (api)
// 화면이동 => 데이터 변경 (필요한 데이터만 전송) => 같은 파일로 데이터만 전송 => JSON
/*
 *      1. 버전 변경 => 1.6 (스프링 5버전 => 1.8이상에서만 적용)
		   STS => 3.8~ (1.8) , 3.9~(11)
		             호환성이 좋은 JDK => (1.8) 이후버전 : 오라클
		2. pom.xml => 기본(spring 3.1.1=> 변경 spring 5.~)
		                    spring 5 => 4버전 , 3버전 => 포함
		3. web.xml => Controller등록 (servlet:톰캣이 담당) 
		              => 톰캣이 읽어서 처리
		============================= 실무는 이미 제작 
		4. application-*.xml => 직접 설정 (사용자 정의 클래스 등록)
		5. Annotation  
		 = 메모리 할당 (클래스를 호출후 바로 실행)
		     => 분리 (스프링에서 기능별 분리 => 관리) => 스프링 (클래스를 관리 => 다른 클래스에 영향이 없는 프로그램)
		          @Component :일반 클래스 (채팅,Manager(크롤링,IO))  
		          @Repository : 데이터베이스 (~DAO)
		          @Service  : DAO가 여러개를 한개로 묶어서 처리  => (DAO VS Service:기술면접)
		          @Controller : Model (비지니스로직) => 조립기(웹) => 반드시 올려야 HanlderMapping (웹 이동) 
		                           => 웹 이동 
		                           => sendRedirect => 완전 화면 이동 (request를 초기화) => URL자체가 변경  ===>   ~_ok.do 
		                                추가 => 목록 , 수정 => 상세보기 , 삭제 => 목록 , 쿠키저장하고 => 상세보기 
		                                => return "redirect:~.do";
		                           => forward => 화면이동이 아니라 => HTML을 변경 (URL주소가 변경되지 않는다 => request를 유지)
		                               => 덮어쓰기 return "경로명/JSP파일명":
		                           => 메소드를 만들때 : 리턴형 / 매개변수  
		                               리턴형 : String , void(다운로드)    , 매개변수는 사용자가 보내준 데이터를 받는다 
		                               => /main.do?page=1&no=2   ==> (int page,int no) => page는 첫페이지 (String)
		                               =>사용자가 보낸 모든 데이터는 => String , 같은 변수명이 여러개(체크박스) => String[]
		                               => 배열형식 ==> List
		                           => 요청시에 사용자 보낸준 데이터를 받아서 처리후에 화면 이동 (요청값 ==> 요청처리후 => 화면 이동)
		                               ====================================================================
		          @RestController :  같은 페이지에서 데이터값 변경해서 전송 (화면이동)  => 요청/응답 동시처리 (Ajax , Vue ,React)
		            ==========   1. 일반 데이터 (총페이지,로그인처리(NOID,NOPWD,OK) 
		                                 2. 보내는 데이터가 많은 경우 (목록출력 , 상세보기 ==> JSON(객체단위) JavaScript Object Notation 
		                                    JSON => 자바스크립트의 객체 표현법 (~VO=JSON) , List=[](배열) => [{},{},{},{}...]
		                                    {변수명:값,변수명:값,변수명:값...}
		                                 3. 일반 문자열 , 숫자 , JSON,XML,스크립트, HTML
		          @ControllerAdvice : 공통적인 예외처리 (Controller에서 발생하는 에러만 처리가 가능)
		  = 주입 (객체 주소를 주입)
		         = 일반 값을 주입 : @Value('') => 변수값을 설정 
		         = 객체주소 주입 (스프링에서 메모리 할당하고 관리를 하는 클래스에 대해서만)
		                               ========================================
		            @Autowired , @Resource(name="id명") , @Inject(자바로 환경 설정시에)
		  = 웹에서 요청 처리 
		           => http://localhost:8080 /web/main/main.do
		               ==================
		                   서버 주소  /web => ContextPath(루트)
		        = @RequestMapping(사용자가 보낸 URI)  => @RequestMapping("main/main.do")
		        => 어노테이션 => 구분(찾기를 할 수 있게 만든다)==> if 대신 사용하는 프로그램  ===> index
		  = 기타 (라이브러리) => 실시간 채팅 (WebSocket) , 크롤링 
		  = 마이바티스는 스프링하고 관련이 있지는 않는다 
		    ========(Google) =====(SpringOpensorce)  => JDBC / MyBatis / JPA / Hibernate 
		                                                                            =================== (Spring<=>Mybatis)
		  = Back (Spring / struts / 장고(파이썬) : 중심 (데이터베이스) 
		  = Front (Jquery(Ajax), Vue , React) 

 */
import java.util.*;
import com.sist.dao.*;
@RestController
// 데이터 전송 => Ajax,VueJS,ReactJs => JSON,XML
public class FoodRestController {
   // DAO가 필요하다 
   @Autowired
   private FoodDAO dao;
   
   @Autowired
   private MovieManager mgr; // => 스프링에서 객체 생성 => 스프링을 통해서 객체 주소를 얻어 온다 
   // @RequestMapping => URI는 반드시 Primary => 500 
   @RequestMapping(value="food/rest_category.do",produces="text/plain;charset=UTF-8")
   // 믿고 보는 맛집리스트(no=1) => 1~12, 지역별 맛집리스트(no=2) 13~18 , 인기별 맛집리스트 (no=3) 19~30
   public String food_rest_category(int no)
   {
	   String json="";
	   try
	   {
		   Map map=new HashMap();
		   map.put("no", no); // 동적 쿼리  => no => map.get("no")
		   List<CategoryVO> list=dao.categoryListData(map);
		   // 12 , 6 , 12 => List를 자바스크립트에서는 받을 수 없다 (List존재하지 않는다) => List역할 => 배열 []
		   // List => [] , VO => {}
		   JSONArray arr=new JSONArray(); //[]
		   for(CategoryVO vo:list)
		   {
			   // vo={} JSONObject 
			   JSONObject obj=new JSONObject();
			   obj.put("cno", vo.getCno());
			   obj.put("poster", vo.getPoster());
			   obj.put("title", vo.getTitle());
			   obj.put("subject", vo.getSubject());
			   arr.add(obj);
			   // {"cno":1,"poster":"http~","title":"aaa","subject":"~~"} => VO
			   // [{},{},{},{}...]
		   }
		   json=arr.toJSONString();
		   System.out.println(json);
	   }catch(Exception ex){}
	   return json;
   }
   
   @RequestMapping(value="food/rest_detail.do",produces="text/plain;charset=UTF-8")
   // text/html (HTML) , text/xml (XML) , text/plain (JSON,WML:무선 마크업 언어)
   public String food_rest_detail(int cno)
   {
	   String json="";
	   try
	   {
		   // DAO로부터 데이터값을 받아서 => JSON로 변경 
		   List<FoodVO> list=dao.categoryFoodListData(cno);
		   for(FoodVO vo:list)
		   {
			   String poster=vo.getPoster();
			   poster=poster.substring(0,poster.indexOf("^"));
			   vo.setPoster(poster);
		   }
		   // Vue=>substring을 이용할 수 있다  => split()
		   // JSON을 만든다 
		   // EL => fn:substring
		   // [{}=VO,{},{}...]=List
		   JSONArray arr=new JSONArray(); // Vue , React , Ajax , Kotlin (JSON:모바일)
		   for(FoodVO vo:list)
		   {
			   JSONObject obj=new JSONObject(); // jakson => simple-json(구글) ... bson
			   obj.put("no", vo.getNo());
			   obj.put("name", vo.getName());
			   obj.put("poster", vo.getPoster());
			   obj.put("score", vo.getScore());
			   obj.put("address", vo.getAddress());
			   obj.put("tel", vo.getTel());
			   obj.put("type", vo.getType());
			   arr.add(obj);
		   }
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
	   
   }
   
   @RequestMapping(value="food/rest_category_info.do",produces="text/plain;charset=UTF-8")
   public String food_rest_category_info(int cno)
   {
	   String json="";
	   try
	   {
		   // 값을 DAO로 부터 받아 온다 (VueJS는 오라클 연결을 할 수 없다 => Spring서버를 통해서 데이터를 받는다)
		   // JS => JS가 인식할 수 있는 데이터로 전송 (일반 데이터는 인식 , JSON , XML) => 자동파싱(JSON)
		   // 1. 정보를 받는다 
		   CategoryVO vo=dao.categoryInfoData(cno);
		   // vo는 인식을 못한다 
		   // java => c / c++ / c# (불가능)  => socket(네트워크를 이용해서 전송)
		   // 카톡 ==> 서버(C) / 클라이언트 (자바)
		   // List ==> JSONArray 
		   JSONObject obj=new JSONObject(); // VO를 자바스크립트에서 인식 
		   obj.put("title", vo.getTitle());
		   obj.put("subject", vo.getSubject());
		   /*
		    *    let info={"title":"","subject":""}
		    *    info.title
		    *    info.subject
		    */
		   json=obj.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
   
   @RequestMapping(value="food/rest_detail_data.do",produces="text/plain;charset=UTF-8")
   public String food_rest_detail_data(int no)
   {
	   String json="";
	   try
	   {
		   // DAO에서 상세보기 데이터 읽기 
		   FoodVO vo=dao.foodDetailData(no); 
		   // vo=>JSP인식 , vo=JavaScript(X) => vo와 동일하게 인식 => XML , JSON (자동 파일)
		   // FoodVO => JSON변경 
		   JSONObject obj=new JSONObject();
		   // no,name,poster,address,score,tel,type,parking,price,menu,time
		   obj.put("no", vo.getNo());
		   obj.put("name", vo.getName());
		   obj.put("poster", vo.getPoster());
		   obj.put("address", vo.getAddress());
		   obj.put("score", vo.getScore());
		   obj.put("tel", vo.getTel());
		   obj.put("type", vo.getType());
		   obj.put("parking", vo.getParking());
		   obj.put("price", vo.getPrice());
		   obj.put("menu", vo.getMenu());
		   obj.put("time", vo.getTime());
		   // 데이터 전송 
		   json=obj.toJSONString();
		   System.out.println(json);
	   }catch(Exception ex){}
	   return json;
   }
   /*
    *     params:{
    				ss:this.ss,
    				page:this.page
    			 }
    */
   // web/  => 루트  , uri => web/~~
   @RequestMapping(value="food/rest_find.do",produces="text/plain;charset=UTF-8")
   public String food_rest_find(int page, String ss)
   {
	   // find.do?page=1    ==> int page
	   // find.do           ==> page=null  => int page(오류) , String page 
	   String json=""; //RestAPI => @RestController => 다른 언어와 연동 할때 (자바스크립트,코틀린)
	   // 공용으로 사용하는 데이터 ==> JSON , XML (파싱 : JSON)
	   try
	   {
		   int curpage=page;
		   // 해당 페이지의 데이터 읽기 
		   // VueJS(검색,페이지 나누기) / ReactJS(외부의 데이터 실시간)=> 증권 ,날씨 , 여행정보,예약=>배포
		   // webPack(CSS/JavaScript) => src="파일명"
		   // 외부 요청 (업체) = 다방면으로 편집기 (editplus , 울트라edit , 아톰 , 웹스톰 , vscode)
		   // 데이터베이스 연동 (DAO) 
		   Map map=new HashMap();
		   int rowSize=20;
		   int start=(rowSize*curpage)-(rowSize-1); // 1 , 13 , 25...
		   //        1=> 12*1-11 => 1 , 2=> 12*2-11 => 13
		   int end=rowSize*curpage; // 12 , 24 , 36...
		   /*
		    *  @Select("SELECT no,name,poster,num "
				 +"FROM (SELECT no,name,poster,rownum as num "
				 +"FROM (SELECT /*+ INDEX_ASC(project_food_house pfh_no_pk)  no,name,poster "
				 +"FROM project_food_house WHERE address LIKE '%'||마포||'%')) "
				 +"WHERE num BETWEEN 1 AND 12")
		    */
		   map.put("start", start);
		   map.put("end", end);
		   map.put("ss", ss);
		   List<FoodVO> list=dao.foodFindData(map);
		   int totalpage=dao.foodFindTotalPage(ss);
		   
		   // List => JSON
		   //[{},{},{}] List(vo,vo...) 
		   /*
		    *   {no:1,name:'',curpage:1,totalpage:2}
		    *   {no:2,name:'',sex:''}
		    *   {no:3,name:'',sex:'',addr:''}
		    */
		   JSONArray arr=new JSONArray();
		   int i=0;
		   for(FoodVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("no", vo.getNo());
			   obj.put("name", vo.getName());
			   obj.put("poster", vo.getPoster().substring(0,vo.getPoster().indexOf("^")));
			   
			   if(i==0)
			   {
				   obj.put("curpage", curpage);
				   obj.put("totalpage", totalpage);
			   }
			   
			   arr.add(obj);// 배열에 추가 [{},{}...] 12
			   i++;
		   }
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
   // XML (공공데이터) => JSON  (NodeJS => xml2json)
   @RequestMapping(value="movie/rest_list.do",produces="text/plain;charset=UTF-8")
   public String movie_rest_list(int no)
   {
	   // 출력 => JSON
	   String json=mgr.movieListData(no);
	   json=json.replace("<", "");
	   json=json.replace(">", "");
	   System.out.println(json);
	   return json;
   }
}









