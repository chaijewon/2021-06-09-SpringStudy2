package com.sist.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
// jaxb (객체변수 = 태그값을 대입) => 1.8이상이면 지원하지 않는다 (11)
// jaxp (파싱) => DOM vs SAX
/*
 *   <item>
<title>[브리핑 대구] 자동차산업 원로들과 함께한 자동차창업캠프</title>
<link>http://www.newsprime.co.kr/news/article.html?no=554955</link>
<description>
<![CDATA[ ■ 대구 독립영화 쉴 틈 없는 영화제 진출 쾌거 ■ 대구수목원에서 국화와 함께 청정 대구를 즐겨요 ■ 대구시 녹색제품 공공의무구매 이행 우수기관 선정 [프라임경제] 한국자동차공학한림원(회장 한동철)은 지난 10월23일 대구... ]]>
</description>
<pubDate>Wed, 27 Oct 2021 14:18:00 +0900</pubDate>
<author>프라임경제</author>
<category>섹션없음</category>
<media:thumbnail url="https://imgnews.pstatic.net/image/thumb140/5325/2021/10/27/246450.jpg"/>
</item>
 */
/*
 *    오라클 데이터 => JSON
 *    외부에서 JSON 
 *    외부에서 XML => JSON
 *    ======================= 오라클에 있는 데이터만 사용하는 것이 아니다 (Open API => XML,CSV)
 *    => 데이터 분석 (지능형 웹) => 수학 (공식)
 */
import java.util.*;
import java.net.*;
import java.text.SimpleDateFormat;
@Component
public class NewsManager {
   public static void main(String[] args) {
	    NewsManager m=new NewsManager();
	    m.newsFindData("맛집");
   }
   public List<NewsVO> newsFindData(String ss)
   {
	   List<NewsVO> list=new ArrayList<NewsVO>();
	   try
	   {
		   String url="http://newssearch.naver.com/search.naver?where=rss&query="
				        +URLEncoder.encode(ss, "UTF-8");//인코딩 (byte[]) => 원상복귀 (decode)
		   Document doc=Jsoup.connect(url).get();
		   //System.out.println(doc1.toString());
		   //String xml=doc1.toString();
		   //xml=xml.replace(":", "");
		   //Document doc=Jsoup.parse(xml);
		   Elements title=doc.select("rss > channel > item > title");
		   Elements desc=doc.select("rss > channel > item > description");
		   Elements link=doc.select("rss > channel > item > link");
		   Elements pubDate=doc.select("rss > channel > item > pubDate");
		   Elements author=doc.select("rss > channel > item > author");
		   //Elements image=doc.select("rss > channel > item > media");
		   for(int i=0;i<title.size();i++)
		   {
			   System.out.println(title.get(i).text());
			   System.out.println(desc.get(i).text());
			   System.out.println(link.get(i).text());
			   System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date(pubDate.get(i).text())));
			   System.out.println(author.get(i).text());
			   //System.out.println(image.get(i).attr("url"));
			   NewsVO vo=new NewsVO();
			   vo.setTitle(title.get(i).text());
			   vo.setDescription(desc.get(i).text());
			   vo.setLink(link.get(i).text());
			   vo.setPubDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(pubDate.get(i).text())));
			   vo.setAuthor(author.get(i).text());
			   list.add(vo);
		   }
	   }catch(Exception ex){ex.printStackTrace();}
	   return list;
   }
}








