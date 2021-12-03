package com.sist.main;
import org.snu.ids.ha.index.*;
// 명사 찾기 
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String data="세계랭킹 10위 안에 든 실력파만 국제대회 경쟁력을 인정해 자동적으로 태극마크를 달고 국제대회에 출전하도록 제도개편이 이뤄진 것이다. 톱10 이외의 선수들은 완전한 경쟁을 통해 아시안게임 등 국제대회 출전권을 따내야 한다."
         +"2020 도쿄올림픽을 앞두고 올해 초 열린 ‘2021 국가대표 선발전’에서 남자부 2위를 한 안재현(삼성생명)을 제외하는 대신, 3위로 밀린 정영식(미래에셋증권)을 대한탁구협회 경기력향상위원회가 추천선수로 뽑으며 올림픽 출전 엔트리 3명(장우진 세계랭킹 최상위로 자동선발, 이상수 선발전 1위)을 확정해 공정성 논란에 휘말린 바 있다."
         +"논란이 되자 김택수 협회 전무이사는 제도개선을 약속했고 연말부터 시행하는 것으로 확인됐다."
         +"‘2022 국가대표 선발전’은 오는 17일 충북 제천실내체육관에서 시작된다."
         +"17~18일 여자부 1차 선발전, 19~20일 남자부 1차 선발전이 각각 열린다. "
         +"남녀 각각 10명씩을 추려내는 최종선발전은 23~27일로 예정돼 있다.";
        try
        {
        	// 꼬꼬마 (형태소 분석 라이브러리) => Korean Text (트워터)
        	// 코모란 ===> 카카오 (코모란) 
        	// R
        	KeywordExtractor ke=new KeywordExtractor();
        	KeywordList list=ke.extractKeyword(data.replaceAll("[0-9A-Za-z]", ""), true);
        	for(int i=0;i<list.size();i++)
        	{
        		Keyword kw=list.get(i); // String , count
        		if(kw.getCnt()>2 && kw.getString().length()>1)
        		{
        		   System.out.println(kw.getString()+":"+kw.getCnt());
        		}
        	}
        	
        }catch(Exception ex){}
	}

}
