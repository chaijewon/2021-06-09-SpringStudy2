package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// 메모리 할당 
/*
 *   스프링 관리 
 *   ========
 *     1. Model : @Controller(return:파일명) / @RestController(return:json,일반문자열,스크립트)
 *                                           ================ Ajax,VueJS,Kotlin
 *     2. DAO : 데이터베이스 연결  => @Repository
 *              오라클 / 마리아디비(무료) => 지능형 웹 (MySQL => 분산)
 *     3. Service (PL/SQL => 댓글) => 함수  => 금융권/공기업 (ERP), AI : @Service
 *     4. Manager : Jsoup , XML , HTML ,  공공포털 , 통계 , AI   : @Component
 *     ==> 등록 
 */
// main() 조립 ==> 조립(Model) 
@Repository
public class MusicDAO {
   // 구현한 클래스를 받아 온다 
   @Autowired  // 스프링에 의해서 생성된 객체의 주소를 주입요청 
   private MusicMapper mapper;
   
   public List<MusicVO> musicListData()
   {
	   return mapper.musicListData();
   }
   public MusicVO musicDetailData(int no)
   {
	   return mapper.musicDetailData(no);
   }
   public List<GenieVO> genieListData()
   {
	   return mapper.genieListData();
   }
}












