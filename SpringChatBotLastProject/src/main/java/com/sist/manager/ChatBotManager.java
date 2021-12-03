package com.sist.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sist.dao.*;
import com.sist.manager.*;
import org.snu.ids.ha.*;
import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
// DAO , NaverBlogManager ==> 결과를 웹 으로 전송 
@Component
public class ChatBotManager {
  @Autowired
  private FoodLocationDAO dao;
  
  @Autowired
  private NaverBlogManager mgr;
  
  // 사용자가 보내준 채팅 메세지 => msg
  public String chatbotMessage(String msg)
  {
	  String result="";
	  
	  try
	  {
		  KeywordExtractor ke=new KeywordExtractor();
		  KeywordList kl=ke.extractKeyword(msg, true);// 명사만 추출 
		  
		  String[] type={"맛집","음식점","식당"}; // 맛집 => 호텔 , 숙박업소 , 자연 ,명소 , 관광,공원...레시피
		  // 문자열 인덱스 
		  int k=0; // 클라이언트가 전송한 채팅문자열 => 포함여부 확인  (포함: 1, 미포함 :2)
		  // 1=> 찾기 , 0=> 문자열 (어떤 내용 검색...) 
		  String loc="";
		  for(int i=0;i<kl.size();i++)
		  {
			  Keyword kwrd=kl.get(i); // 문자열 (명사) , 갯수 
			  for(int j=0;j<type.length;j++)
			  {
				  if(kwrd.toString().contains(type[j]))
				  {
					  loc=kwrd.getString();
					  k=1;
					  break;
				  }
			  }
		  }
		  
		  if(k==0)
		  {
			 result="<font color='red'>맛집,음식점,식당만 검색이 가능 합니다.</font><br>";
			 System.out.println(result);
		  }
		  else
		  {
			  result=loc+"를(을) 검색하셨습니다!!<br>";
			  System.out.println(result);
		  }
		  // 지역 검색 , 서울 전역 
		  String[] gu = { "강서", "양천", "종로", "마포", "영등포", "금천",
				    "은평", "서대문", "동작", "관악", "종로", "중구", "용산", "서초", "강북",
				    "성북", "도봉", "동대문", "성동", "강남", "노원", "중랑", "광진", "송파",
				    "강동" };
          int p=0;
          String g="";
          for(int i=0;i<kl.size();i++)
          {
        	  Keyword kwrd=kl.get(i);
        	  for(int j=0;j<gu.length;j++)
        	  {
        		  if(kwrd.toString().contains(gu[j]))
        		  {
        			  g=kwrd.getString();
        			  p=1;
        			  break;
        		  }
        	  }
          }
          if(p==0)
          {
        	  result+="<br>서울 전체에서 맛집추천을 요청하셨습니다<br>";
          }
          else
          {
        	  result+="<br>"+g+"구에서 맛집 추천을 요청하셨습니다";
          }
          
          /// 추천 맛집을 검색 
          String s="";
          if(k==1)
          {
        	  if(p==0)
        	  {
        		  s="서울지역 맛집";
        	  }
        	  else
        	  {
        		  s=g+"구에 맛집";
        	  }
        	  
        	  // Naver
        	  List<String> nList=mgr.naverBlogData(s);
        	  // Database
        	  List<String> hList=dao.foodGetNameData();
        	  
        	  // 같은 맛집명을 찾는다 
        	  Pattern[] pp=new Pattern[hList.size()];
        	  int[] count=new int[hList.size()];
        	  for(int i=0;i<pp.length;i++)
        	  {
        		  pp[i]=Pattern.compile(hList.get(i)); // 찾는 문자열의 집합 => 문자 패턴 
        		  // 정규식을 이용해서 찾을 수 있다 
        		  // 211.238.142.181 , [0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}
        		  // 맛있고 맛있는 맛있게 맛있니 .... 맛있+ ==> AI(지능형 웹)
        	  }
        	  Matcher[] m=new Matcher[hList.size()]; // 문자열안에서 패턴을 찾을 경우에 처리 
        	  for(String ss:nList)
        	  {
        		  for(int i=0;i<m.length;i++)
        		  {
        			  m[i]=pp[i].matcher(ss); // ss는 네이버에서 읽어 온 댓글
        			  while(m[i].find())// 업체가 존재 true
        			  {
        				  count[i]++;
        			  }
        		  }
        	  }
        	  
        	  int kk=0;
        	  for(int i=0;i<count.length;i++)
        	  {
        		  // 댓글에 => 3번이상 언급 (추천) , 업체명은 2글자 이상 
        		  if(count[i]>2 && hList.get(i).length()>1)
        		  {
        			  if(!((hList.get(i).equals("라구"))||
        				 (hList.get(i).equals("이다"))||
        				 (hList.get(i).equals("17")))
        				)
        			  {
        				  FoodLocationVO vo=dao.foodInfoData(hList.get(i));
        				  String name=hList.get(i);
        				  String address=vo.getAddress();
        				  address=address.substring(0,address.indexOf("지"));
        				  String tel=vo.getTel();
        				  String ty=vo.getType();
        				  result+="<font color=magenta>"+name+"</font>"
        						 +"(주소:"+address+",전화:"+tel+",음식종류:"+ty+")<br>";
        				  kk++;
        			  }
        		  }
        	  }
        	  result+="총 "+kk+"검색되었습니다";
        	  
          }
	  }catch(Exception ex){}
	  
	  return result;
  }
}











