package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링에서 전송 ==> 일반 웹 (List,VO)
// 스프링 ==> 다른 운영체제(다른 언어에서 받는 경우) ==> 호환성(JSON)
/*
 *    @Controller : JSON(X) => 화면 변경 
 *                             forward/redirect
 *                             return "main/main" => main.jsp에 request전송
 *                              => request를 공유 
 *                             redirect 
 *                             return "redirect:~.do" // 재실행 
 *                              => request를 초기화 
 *    @RestController : 화면 변경(X) =>  필요한 데이터 전송 
 *                                    데이터가 여러개를 동시에 전송 => JSON (AJAX,Kotlin,React,Vue)
 */
import java.util.*;
import com.sist.dao.*;
@RestController
public class SeoulRestController {
    // DAO => 데이터 읽기 => JSON => Kotlin으로 전송 
	@Autowired
	private SeoulDAO dao;
	
	@RequestMapping(value="seoul/location.do",produces="text/plain;charset=UTF-8")
	public String seoul_location(int page)
	{
		System.out.println("접속 완료...");
		// Kotlin에서 요청 => 요청처리 => 결과값을 전송 
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start",start);
		map.put("end", end);
		List<SeoulLocationVO> list=dao.seoulLocListData(map);
		// List => JSONArray
		// VO => JSONObject  
		String json="";
		try
		{
			JSONArray arr=new JSONArray(); //List
			for(SeoulLocationVO vo:list)
			{
				JSONObject obj=new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("title", vo.getTitle());
				obj.put("poster", vo.getPoster());
				arr.add(obj);
			}
			json=arr.toJSONString();
		}catch(Exception ex){}
		return json;
	}
	
	
	
	
	
	
	
	
}
