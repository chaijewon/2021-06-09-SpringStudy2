package com.sist.news;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.*;
import java.io.BufferedReader;
import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
// JAXP 
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
		   FileWriter fw=new FileWriter("c:\\download\\news.xml");
		   fw.write(sb.toString());
		   fw.close();
		   // jaxb (1.8 지원)
		   /*Document doc=Jsoup.parse(sb.toString());
		   Elements title=doc.select("rss channel item title");
		   Elements description=doc.select("rss channel item description");
		   Elements author=doc.select("rss channel item author");
		   //Elements link=doc.select("rss > channel > item > link");
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
		   }*/
		   DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance(); // 싱글턴
		   // ML (HTML , XML , WML ,HDML ....)
		   // newInstance , getInstance ==> 메모리 한번 생성 
		   // 기술 면접 : 디자인 패턴의 갯수 => 싱글턴 , 팩토리 , adapter (형변환) 
		   /*
		    *    new ====> 팩토리 패턴 (클래스) ====> Ioc(마틴 파울러) (제어역전) => 스프링 ===> DI
		    *    DI : 1. 클래스간의 의존성을 설계 
		    *         2. 주입 (필요한 데이터 주입 , 메소드 호출) 
		    */
		   // 파싱 => XML을 트리형태로 저장하는 과정 (트리형태로 저장 : DOM=>Document Object Model)
		   // XML(Ajax => javascript AND XML) => 파싱 (DOM/SAX) => Mybatis,Spring (SAX)
		   // 솔루션 면접 (SAX => XML코드를 한줄씩 읽어서 ==> 필요한 데이터를 읽어 온다)
		   // XML => 모든 운영체제에 호환성 (XML=>JSON)
		   // Back-End=XML(설정) pom.xml , Front-End=JSON package.json
		   // 파서기 
		   DocumentBuilder db=dbf.newDocumentBuilder();
		   // 파싱(트리형태로 메모리에 저장)
		   // 저장 공간 : Document
		   org.w3c.dom.Document doc=db.parse(new File("c:\\download\\news.xml"));
		   //System.out.println(doc.toString());
		   // Root 
		   Element root=doc.getDocumentElement();
		   System.out.println(root.getTagName());
		   NodeList channel=root.getElementsByTagName("channel");
		   
		   Element chan=(Element)channel.item(0);
		   System.out.println(chan.getTagName());
		   
		   NodeList item=chan.getElementsByTagName("item");
		   System.out.println(item.getLength());
		   int j=2;
		   int k=1;
		   for(int i=0;i<item.getLength();i++)
		   {
			   item=chan.getElementsByTagName("title");
			   String title=item.item(j).getFirstChild().getNodeValue();
			   
			   item=chan.getElementsByTagName("description");
			   String desc=item.item(k).getFirstChild().getNodeValue();
			   
			   item=chan.getElementsByTagName("author");
			   String author=item.item(i).getFirstChild().getNodeValue();
			   
			   item=chan.getElementsByTagName("link");
			   String link=item.item(i).getFirstChild().getNodeValue();
			   
			   item=chan.getElementsByTagName("media:thumbnail");
			   Element img=(Element)item.item(i);
			   String poster=img.getAttribute("url");
			   System.out.println(i+1);
			   System.out.println(title);
			   System.out.println(desc);
			   System.out.println(author);
			   System.out.println(link);
			   System.out.println(poster);
			   
			   NewsVO vo=new NewsVO();
			   vo.setTitle(title);
			   vo.setDescription(desc);
			   vo.setAuthor(author);
			   vo.setLink(link);
			   vo.setPoster(poster);
			   list.add(vo);
			   
			   j++;
			   k++;
		   }
		   //System.out.println(channel.getTagName());
		   /*System.out.println(channel.getLength()); // 태그의 갯수 확인 
		   for(int i=0;i<channel.getLength();i++)
		   {
			   Element chan=(Element)channel.item(i);
			   NodeList item=chan.getElementsByTagName("item");
			   for(int j=0;j<item.getLength();j++)
			   {
				   item=chan.getElementsByTagName("title");
				   String strTitle=item.item(j).getFirstChild().getNodeValue();
				   
				   item=chan.getElementsByTagName("description");
				   String strDesc=item.item(j).getFirstChild().getNodeValue();
				   
				   item=chan.getElementsByTagName("author");
				   String strAuthor=item.item(j).getFirstChild().getNodeValue();
				   
				   System.out.println(strTitle);
				   System.out.println(strDesc);
				   System.out.println(strAuthor);
			   }
		   }*/
	   }catch(Exception ex){}
	   return list;
   }
}










