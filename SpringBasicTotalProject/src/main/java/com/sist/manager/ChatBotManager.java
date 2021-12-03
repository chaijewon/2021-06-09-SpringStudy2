package com.sist.manager;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
import org.snu.ids.ha.ma.MExpression;
import org.snu.ids.ha.ma.MorphemeAnalyzer;
import org.snu.ids.ha.ma.Sentence;
import org.snu.ids.ha.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.ChatBotDAO;
import com.sist.dao.FoodLocationVO;

import java.util.*;
@Component
public class ChatBotManager {
	@Autowired
	private RecommandManager mgr;
	@Autowired
	private ChatBotDAO dao;
	
	public String chatbotMessage(String msg) {
		// string to extract keywords
		
		/*ApplicationContext app=
				new ClassPathXmlApplicationContext("application-context.xml");
		ChatBotManager cm=(ChatBotManager)app.getBean("chatBotManager");*/
		
		//Scanner scan=new Scanner(System.in);
		//System.out.print("대화:");
		String result="";
		String strToExtrtKwrd = msg;

		// init KeywordExtractor
		KeywordExtractor ke = new KeywordExtractor();
        
		// extract keywords
		KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
        String[] type={"맛집","호텔","명소","자연","관광","음식점","식당","공원"};
		// print result
        int k=0;
        String loc="";
		for( int i = 0; i < kl.size(); i++ ) {
			Keyword kwrd = kl.get(i);
		   for(int j=0;j<type.length;j++)
		   {
			if(kwrd.toString().contains(type[j]))
			 {
				System.out.println(kwrd.getString());
				loc=kwrd.getString();
				k=1;
				break;
			 }
		   }
			//System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
		}
		if(k==0)
		{
			result="호텔,맛집,명소,관광,자연만 검색 가능합니다..<br>";
			System.out.println("호텔,맛집,명소,관광,자연만 검색 가능합니다..");
		}
		else
		{
			result=loc+"를(을) 검색하셨습니다<br>";
			System.out.println(loc+"를(을) 검색하셨습니다");
		}
		// 응답하기 
		String[] gu = { "강서", "양천", "종로", "마포", "영등포", "금천",
			    "은평", "서대문", "동작", "관악", "종로", "중", "용산", "서초", "강북",
			    "성북", "도봉", "동대문", "성동", "강남", "노원", "중랑", "광진", "송파",
			    "강동" };
		
		int p=0;
		String g="";
		for( int i = 0; i < kl.size(); i++ ) {
			Keyword kwrd = kl.get(i);
		    for(int j=0;j<gu.length;j++)
		    {
			if(gu[j].contains(kwrd.getString()))
			 {
				System.out.println(kwrd.getString());
				p=1;
				g=kwrd.getString();
				break;
			 }
		   }
			//System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
		}
		
		if(p==0)
		{
			result+="<br>서울 전체를 추천요청 하셨습니다..<br>";
			System.out.println("서울 전체를 추천요청 하셨습니다..");
		}
		else
		{
			result+="<br>"+g+"구에서 추천 요청 하셨습니다<br>";
			System.out.println(g+"구에서 추천 요청 하셨습니다");
		}
		///////////////////////////////////////// 실제 추천중 가장 많은 추천을 통해 사용자에게 챗봇으로 전송 예정 
		
		try
		{
			String s="";
			if(k==1)
			{
				if(p==0)
				{
					s="서울 지역 "+loc;
				}
				else
				{
					s=g+"구에 "+loc;
				}
				
				List<String> rList=mgr.recommandData(s);
				List<String> hList=dao.foodHouseGetNameList();
				
				Pattern[] pat=new Pattern[hList.size()];
				int[] count=new int[pat.length];
				for(int i=0;i<pat.length;i++)
				{
					pat[i]=Pattern.compile(hList.get(i));
				}
				
				Matcher[] m=new Matcher[hList.size()];
				for(String ss:rList)
				{
				  for(int i=0;i<m.length;i++)
				  {
				       m[i]=pat[i].matcher(ss);
				       while(m[i].find())
				       {
				    	   count[i]++;
				       }
				  }
				}
				
				int kk=0;
				for(int i=0;i<count.length;i++)
				{
					if(count[i]>2 && hList.get(i).length()>1)
					{
						if(!(hList.get(i).equals("라구") || hList.get(i).equals("이다") || hList.get(i).equals("17")))
						{
						      FoodLocationVO vo=dao.foodDetailData(hList.get(i));
						      String addr=vo.getAddress();
	
							  result+="<font color=magenta>"+hList.get(i)+"</font>"
									  +"(주소:"+vo.getAddress().substring(0,addr.lastIndexOf("지"))+",전화:"+vo.getTel()+",음식종류:"+vo.getType()+")<br>";
							  System.out.println(hList.get(i));
						      kk++;
						}
					}
				}
				System.out.println("총  "+kk+"검색 되었습니다");
				result+="총  "+kk+"검색 되었습니다";
			}
			else
			{
				System.out.println("다음중에 선택하세요\n1.식당,맛집,음식점\n2.호텔\n3.명소\n4.자연,관광");
				result+="<font color=red>다음중에 선택하세요.<br>1.식당,맛집,음식점<br>2.호텔<br>3.명소<br>4.자연,관광</font>";
			}
		}catch(Exception ex){}
		
		return result;
	}
}

	
