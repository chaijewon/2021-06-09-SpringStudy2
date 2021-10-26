package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
/*
 *   VueJS 
 *   1. 형식   
 *      1) 영역을 지정 => Vue클래스 제어 영역 
 *      2) 디렉티브 (연결,제어문) 
 *         => 태그의 속성으로 정의 
 *         => v-model (연결) => Vue가가지고 있는 변수와 사용자가 입력한 값을 매칭 (검색,로그인)
 *         => v-if , v-else , v-for , v-show , v-hide 
 *         => 이벤트 : v-on:click , v-on:keyup , v-on:change , v-on:mouseover ....
 *         => v-bind =>  <img v-bind:src=""> <a :href=""> v-bind는 생략이 가능 
 *      3) 자바스크립트 : 이벤트 구동방법 
 *      4) 템플릿 = 컴포넌트 
 *      5) 사용자 정의 이벤트 
 *      6) 화면 이동 => 라우터 
 *   ----------------------------------------------------------------------------
 *   React => Redux(MVC) => WebPack => 실시간 접속자마다 (서버=>NodeJS) ==> 30줄 
 *   ==> Jquery / Ajax 
 *         
 */
@Repository
public class FoodDAO {
   @Autowired
   private FoodMapper mapper;
   
   public List<CategoryVO> categoryListData(Map map)
   {
	   return mapper.categoryListData(map);
   }
   
   public List<FoodVO> categoryFoodListData(int cno)
   {
	   return mapper.categoryFoodListData(cno);
   }
   
   public CategoryVO categoryInfoData(int cno)
   {
	   return mapper.categoryInfoData(cno);
   }
   
   public FoodVO foodDetailData(int no)
   {
	   return mapper.foodDetailData(no);
   }
}







