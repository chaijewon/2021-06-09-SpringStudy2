package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
@RestController
public class MusicRestController {
	@Autowired
    private MusicDAO dao;
	
	@RequestMapping(value="music/rest_list.do",produces="text/plain;charset=UTF-8")
	public String music_rest_list()
	{
		String json="";
		try
		{
			// 오라클에서 데이터 읽기 
			List<MusicVO> list=dao.musicListData();
			// JSON변경 
			// List => JSONArray 
			JSONArray arr=new JSONArray();
			// MusicVO => JSONObject
			for(MusicVO vo:list)
			{
				JSONObject obj=new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("poster", vo.getPoster());
				obj.put("title", vo.getTitle());
				obj.put("singer", vo.getSinger());
				obj.put("album", vo.getAlbum());
				obj.put("key", vo.getKey());
				arr.add(obj);
			}
			json=arr.toJSONString();
			
		}catch(Exception ex){}
		return json;
	}
	// 영화 데이터를 JSON으로 변경 
	/*
	 *  MNO      NOT NULL NUMBER        
		CNO               NUMBER        
		TITLE    NOT NULL VARCHAR2(300) 
		REGDATE           VARCHAR2(100) 
		GENRE    NOT NULL VARCHAR2(200) 
		NATION            VARCHAR2(100) 
		GRADE    NOT NULL VARCHAR2(50)  
		TIME     NOT NULL VARCHAR2(30)  
		SCORE             NUMBER(2,1)   
		SHOWUSER          VARCHAR2(100) 
		POSTER   NOT NULL VARCHAR2(260) 
		STORY             CLOB  
	 */
	 @RequestMapping(value="movie/rest_list.do",produces="text/plain;charset=UTF-8")
	 public String movie_rest_list()
	 {
		 String json="";
		 try
		 {
			 // 오라클 데이터 읽기 
			 List<MovieVO> list=dao.movieListData();
			 // List => JSONArray
			 JSONArray arr=new JSONArray();
			 for(MovieVO vo:list)
			 {
				 // vo=>JSONObject
				 JSONObject obj=new JSONObject();
				 obj.put("mno", vo.getMno());
				 obj.put("title", vo.getTitle());
				 obj.put("poster", vo.getPoster());
				 obj.put("regdate", vo.getRegdate());
				 obj.put("grade", vo.getGrade());
				 obj.put("genre", vo.getGenre());
				 obj.put("nation", vo.getNation());
				 obj.put("time", vo.getTime());
				 obj.put("score", vo.getScore());
				 obj.put("showUser", vo.getShowUser());
				 
				 arr.add(obj);
			 }
			 
			 json=arr.toJSONString(); // Ajax , Vue , React , Kotlin
		 }catch(Exception ex){}
		 return json;
	 }
	
	
}












