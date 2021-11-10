package com.sist.web;

import java.awt.Dialog.ModalExclusionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.manager.RecommandManager;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sist.dao.*;
@Controller
public class RecommandController {
   @Autowired
   private RecommandManager mgr;
   
   @Autowired
   private RecommandDAO dao;
   
   @RequestMapping("recommand/list.do")
   public String recommand_list(String gu,Model model)
   {
	   if(gu==null)
		   gu="마포구";
	   String[] gu1 = { "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
			    "은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구",
			    "성북구", "도봉구", "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구",
			    "강동구" };
	   List<String> list=Arrays.asList(gu1);
	   model.addAttribute("list", list);
	   
	   // 추천 내용 전송 
	  {
	   List<String> locList=mgr.recommandData(gu+" 명소");
	   List<String> tList=dao.seoulLocationTitleData(gu);
	   
	   // 분석 => 패턴 (단어 , 형태패턴) 
	   /*
	    *    맵다 , 맵고 , => 맵*(like) 맵+
	    *    title=title 
	    *    tile Like '%단어%'
	    *    
	    *    짜다 , 짜고 .. 짜* , 짜+ => 짜장 
	    */
	   Pattern[] p=new Pattern[tList.size()]; // 단어패턴 
	   for(int i=0;i<p.length;i++)
	   {
		   p[i]=Pattern.compile(tList.get(i));
	   }
	   // 문장(블로그)에서 패턴에 등록된 데이터를 찾기 
	   Matcher[] m=new Matcher[tList.size()];
	   // 갯수 확인 => MapReduce 알고리즘 => 하둡 (리눅스),파이썬 
	   int[] count=new int[tList.size()];
	   for(String msg:locList)
	   {
		   for(int i=0;i<m.length;i++)
		   {
			   m[i]=p[i].matcher(msg);//contains
			   while(m[i].find())
			   {
				   count[i]++;
			   }
		   }
	   }
	   
	   // Max값을 읽어온다 
	   int max=0;
	   int ii=0;
	   for(int i=0;i<count.length;i++)
	   {
		   if(max<count[i])
		   {
			   max=count[i];
			   ii=i;
		   }
	   }
	   
	   //System.out.println(tList.get(ii));
	   /*for(int i=max;i>max-3;i--)
	   {
		   System.out.println(tList.get(i));
	   }*/
	   
	   List<SeoulLocationVO> lvo=dao.seoulLocationInfoData(tList.get(ii));
	   
	   model.addAttribute("lvo", lvo);
	   model.addAttribute("size", lvo.size());
	  }
	  {
	   List<String> hList=mgr.recommandData(gu+" 호텔");
	   List<String> nList=dao.seoulLocationHotelData(gu);
	   
	   Pattern[] p=new Pattern[nList.size()]; // 단어패턴 
	   for(int i=0;i<p.length;i++)
	   {
		   p[i]=Pattern.compile(nList.get(i));
	   }
	   // 문장(블로그)에서 패턴에 등록된 데이터를 찾기 
	   Matcher[] m=new Matcher[nList.size()];
	   // 갯수 확인 => MapReduce 알고리즘 => 하둡 (리눅스),파이썬 
	   int[] count=new int[nList.size()];
	   for(String msg:hList)
	   {
		   for(int i=0;i<m.length;i++)
		   {
			   m[i]=p[i].matcher(msg);//contains
			   while(m[i].find())
			   {
				   count[i]++;
			   }
		   }
	   }
	   
	   // Max값을 읽어온다 
	   int max=0;
	   int ii=0;
	   for(int i=0;i<count.length;i++)
	   {
		   if(max<count[i])
		   {
			   max=count[i];
			   ii=i;
		   }
	   }
	   
       List<SeoulHotelVO> hvo=dao.seoulHotelInfoData(nList.get(ii));
	   
	   model.addAttribute("hvo", hvo);
	   model.addAttribute("size1", hvo.size());
	  }
	  {
		 // 변수의 스코프 지정 (사용범위) => class 영역 / 메소드 영역 
	  }
	   return "recommand/list";
   }
  
}









