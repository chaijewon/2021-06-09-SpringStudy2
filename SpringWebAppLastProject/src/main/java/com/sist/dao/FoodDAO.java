package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 *   .do =====> DispatcherServlet | ==> 위임 (@Controller(JSP연결 => 데이터 전송),@RestController)
 *              =================               => 다른 프로그램으로 데이터만 전송 (Ajax,Vue...Kotlin:JSON)                  
 *                연결 (JSP / Java)               |
 *                                          Controller를 여러개 만든다 => 찾아서 메소드를 호출 (HandlerMapping)
 *                                               | 데이터 전송 
 *                                            ViewResolver (JSP를 찾고 => 데이터를 전송)
 *   .do ======> DispatcherServlet ====> HandlerMapping =====> @Controller ======> ViewResolver => JSP
 *                     |
 *                   제일 먼저 등록 (web.xml)  => 자동 설정                         사용자 정의                      등록 : application-context.xml
 *                                                                                      |
 *                                                                                     경로명 / 확장자 
 *   ==> 기본 형식 (웹 틀) ==> FrameWork (기본 틀을 제공)
 */
import java.util.*;
@Repository
public class FoodDAO {
   // 스프링에서 구현된 Mapper의 주소를 가지고 온다 
   @Autowired
   private FoodMapper mapper;
   // 카테고리 
   public List<CategoryVO> categoryListData(Map map)
   {
	   return mapper.categoryListData(map);
   }
   // 카테고리별 맛집 출력 
   public List<FoodVO> categoryFoodListData(int cno)
   {
	   return mapper.categoryFoodListData(cno);
   }
   // 카테고리 정보 읽기 
   public CategoryVO categoryInfoData(int cno)
   {
	   return mapper.categoryInfoData(cno);
   }
   // 상세 보기 
   public FoodVO foodDetailData(int no)
   {
	   return mapper.foodDetailData(no);
   }
   
   // 지역별 맛집 찾기 => DAO에 있는 메소드는 Mapper에 있는 메소드와 다를 수도 있다 
   
   public List<FoodVO> foodFindData(Map map) // 매개변수가 여러개 사용이 가능 (사용자 정의)
   {
	   return mapper.foodFindData(map); // 매개변수를 한개만 사용 (여러개 => Map,VO)
	   // 매퍼는 Mybatis형식에 적용 , DAO는 사용자 마음..
   }
   // 총페이지 
   public int foodFindTotalPage(String loc)
   {
	   return mapper.foodFindTotalPage(loc);
   }
}
