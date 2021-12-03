package com.sist.main;
import java.util.*;
import org.snu.ids.ha.*;
import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // 사용자가 추천 요청 
		Scanner scan=new Scanner(System.in);
		System.out.print("추천 내용 입력:");
		String msg=scan.nextLine(); // 한줄 전체 받기 
		
		// 데이터 분석 
		KeywordExtractor ke=new KeywordExtractor();
		KeywordList list=ke.extractKeyword(msg, true);
		
		// index => 
		String[] type={"맛집","식당","음식점"};
		int k=0;
		String s="";
		for(int i=0;i<list.size();i++)
		{
			Keyword word=list.get(i);
			for(int j=0;j<type.length;j++)
			{
			  if(word.toString().contains(type[j]))
			  {
				  s=word.getString();
				  k=1;
				  break;
			  }
			 
			}
		}
		
		String ss="";
		if(k==0)
		{
			ss="맛집,음식점,식당만 검색이 가능합니다<br>";
		}
		else
		{
			ss=s+"를(을) 검색합니다<br>";
		}
		System.out.println(ss);
		// ==> Naver (블로그확인:추천) ==> 추천된 맛집과 데이터베이스에 있는 맛집이 동일 => 5개만 추출 전송 
		String[] gu = { "강서", "양천", "종로", "마포", "영등포", "금천",
			    "은평", "서대문", "동작", "관악", "종로", "용산", "서초", "강북",
			    "성북", "도봉", "동대문", "성동", "강남", "노원", "중랑", "광진", "송파",
			    "강동" };
		String g="";
		int p=0;
		for(int i=0;i<list.size();i++)
		{
			Keyword word=list.get(i);
			for(int j=0;j<gu.length;j++)
			{
			  if(word.toString().contains(gu[j]))
			  {
				  g=word.getString();
				  p=1;
				  break;
			  }
			 
			}
		}
		
		if(p==0)
		{
			ss+="서울 전체에서 추천 요청 하셨습니다";
		}
		else
		{
			ss+=g+"구에서 추천을 요청하셨습니다";
		}
		System.out.println(ss);
		
		// 블로그로 전송 ==> DataBase에 있는 저장된 데이터와 비교 ==> 전송 (챗봇) => 추천 콘텐츠 
	}

}











