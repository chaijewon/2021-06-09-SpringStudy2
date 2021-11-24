package com.sist.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
@Component

public class NewsManager {
	public static void main(String[] args) {
		NewsManager m=new NewsManager();
		m.newsListData("맛집");
	}
   public List<NewsVO> newsListData(String fd)
   {
	   List<NewsVO> list=new ArrayList<NewsVO>();
	   try
	   {
		   String strUrl="http://newssearch.naver.com/search.naver?where=rss&query="
				                  +URLEncoder.encode(fd, "UTF-8");
		   URL url=new URL(strUrl);
		   HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		   StringBuffer sb=new StringBuffer();// 데이터 저장 공간 
		   if(conn!=null)//웹서버에 접근
		   {
			   BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			   while(true)
			   {
				   String msg=in.readLine();
				   if(msg==null) break;
				   System.out.println(msg);
				   sb.append(msg);
			   }
		   }
		   Document doc=Jsoup.parse(sb.toString());
		   Elements title=doc.select("rss channel item title");
		   Elements description=doc.select("rss channel item description");
		   Elements author=doc.select("rss channel item author");
		   //Elements link=doc.select("rss channel item link");
		   for(int i=0;i<title.size();i++)
		   {
			   System.out.println((i+1));
			   System.out.println(title.get(i).text());
			   System.out.println(description.get(i).text());
			   System.out.println(author.get(i).text());
			   //System.out.println(link.get(i).text());
			   System.out.println("=========================================================");
			   NewsVO vo=new NewsVO();
			   vo.setTitle(title.get(i).text());
			   vo.setDescription(description.get(i).text());
			   vo.setAuthor(author.get(i).text());
			   list.add(vo);
		   }
	   }catch(Exception ex){}
	   return list;
   }
}










