package com.sist.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
// 외부에서 데이터 읽기 => 일반 클래스 
import java.util.*;
import java.io.*;
import java.net.*;
@Component
public class MovieManager {
  public static void main(String[] args) {
	MovieManager m=new MovieManager();
	m.movieListData(1);
  }
  public String movieListData(int no)
  {
	  String json="";
	  String url="https://www.kobis.or.kr/kobis/business/main/";
	  if(no==1)
	  {
		  url="https://www.kobis.or.kr/kobis/business/main/searchMainDailyBoxOffice.do";//일일 박스오피스 
	  }
	  else if(no==2)
	  {
		  url="https://www.kobis.or.kr/kobis/business/main/searchMainRealTicket.do";//실시간 예매율
	  }
	  else if(no==3)
	  {
		  url="https://www.kobis.or.kr/kobis/business/main/searchMainDailySeatTicket.do";//좌석 점유율
	  }
	  else if(no==4)
	  {
		  url="https://www.kobis.or.kr/kobis/business/main/searchMainOnlineDailyBoxOffice.do";//온라인상영관 일일
	  }
	  try
	  {
		  Document doc=Jsoup.connect(url).get();
		  String msg=doc.toString();
		  msg=msg.substring(msg.indexOf("["),msg.lastIndexOf("]")+1);//[{},{},{},{}]
		  //System.out.println(msg.trim());
		  json=msg.trim();
	  }catch(Exception ex){}
	  return json;
  }
}
