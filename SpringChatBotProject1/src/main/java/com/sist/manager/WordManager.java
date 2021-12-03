package com.sist.manager;

import org.springframework.stereotype.Component;
import org.snu.ids.ha.*;
import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

import java.util.*;
@Component
public class WordManager {
  public List<WordVO> wordData(String content)
  {
	  List<WordVO> list=new ArrayList<WordVO>();
	  KeywordExtractor ke=new KeywordExtractor(); // 명사를 가지고 온다 
	  KeywordList k=ke.extractKeyword(content.replaceAll("[A-Za-z0-9]",""), true);// 명사만 가지고 온다 
	  //               =============== 명사와 갯수처리 
	  for(int i=0;i<k.size();i++)
	  {
		  Keyword word=k.get(i);// word/count
		  if(word.getString().length()>1 && word.getCnt()>1)
		  {
			  WordVO vo=new WordVO();
			  vo.setWord(word.getString());
			  vo.setCount(word.getCnt());
			  list.add(vo);
			  System.out.println(vo.getWord()+"=>"+vo.getCount());
		  }
	  }
	  return list;
  }
}






