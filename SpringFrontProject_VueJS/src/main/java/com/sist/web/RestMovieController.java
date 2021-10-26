package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
// 16장 
@RestController // @ResponseBody (클래스형으로 변경) => 일반 문자열 , 데이터를 묶어서 전송 (JSON)
// 외부 프로그램 연결 (자바스크립트 , 안드로이드) => List(Collection이 없다)
// 화환되는 파일(JSON,XML)
// 자바스크립트 , 안드로이드(모바일) => 직접적으로 오라클 연결이 안된다 (서버를 거쳐서 데이터 읽기) 
public class RestMovieController {
   // XML,JSON , 자바스크립트 => @Controller : forward(JSP파일) , sendRedirect(.do)
	@Autowired
	private MovieDAO dao;
	
	@Autowired
	private FoodDAO fdao;
	// text/html = html , text/xml=xml , text/plain = JSON 
	@RequestMapping(value="main/movie_list.do",produces="text/plain;charset=UTF-8")
	// 외부 클라이언트와 연결 
	public String movie_list(String page)
	{
		String json="";
		try
		{
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			int rowSize=12;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			// Map에 첨부 
			Map map=new HashMap();
			map.put("start", start);
			map.put("end", end);
			List<MovieVO> list=dao.movieListData(map);// JSON의 변경 
			JSONArray arr=new JSONArray(); // [{},{},{}...] [] => 영화정보 여러개 
			// {} => 영화정보 1개만 존재
			int i=0;
			for(MovieVO vo:list)
			{
				JSONObject obj=new JSONObject(); // {"mno":1,"title":'...',"poster":''} => VO
				obj.put("mno", vo.getMno());
				obj.put("title", vo.getTitle());
				obj.put("poster", vo.getPoster());
				if(i==0)
				{
					obj.put("totalpage",dao.movieTotalPage());
					obj.put("curpage", curpage);
				}
				arr.add(obj);
				i++;
			}
			
			json=arr.toJSONString(); // JavaScript : Ajax , VueJS , ReactJS 
			// 최근에는 Front(Ajax)=VueJS,ReactJS / Back (@RestController)
			// 1. 보안 소스가 보이지 않는다 (크롤링) , 2. 속도 , 3. 화면변경없이 처리 (한페이지)
			// 대기업 (현대자동차:React) , 삼성(모바일 =>5G) => 속도에 맞는 프론트 개발 => 유지(쌍용정보통신)
		}catch(Exception ex){}
		return json;
	}
	// 카테고리 출력
	// @RequestMapping("list.do") => value(default)
	@RequestMapping(value="main/category.do",produces="text/plain;charset=UTF-8")
	public String main_category(int cno)
	{
		System.out.println("cno="+cno);
		String json="";
		try
		{
			Map map=new HashMap();
			map.put("no", cno);
			List<CategoryVO> list=fdao.food_categoryData(map);
			JSONArray arr=new JSONArray(); //[{},{},{},{}...]
			for(CategoryVO vo:list)
			{
				JSONObject obj=new JSONObject(); //자바스크립트에 인식 할 수 있게 (List를 변경=>JSON(배열))
				// []=> JSONArray  {} => JSONObject => [{},{},{},{},{}.. 12]  
				obj.put("cno",vo.getCno());
				obj.put("title", vo.getTitle());
				obj.put("subject", vo.getSubject());
				obj.put("poster", vo.getPoster());
				arr.add(obj);
			}
			System.out.println(arr.toJSONString());
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json; // 실제 JSON을 만들어서 전송 
	}
	
}











