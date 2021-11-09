package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 *    1. JSP => .do (사용자 요청) 
 *    2. Controller (@GetMapping)
 *    3. Mapper : 데이터베이스 
 *    4. DAO : 구현된 메소드 읽기 
 *    5. Controller => 데이터 보내기  
 *    6. JSP 출력
 */
@Repository // DAO메모리 할당  : *** @Repository/@Service (BI) => 여러개의 DAO를 통합
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
   
   public FoodVO foodDetailData(Map map)
   {
	   return mapper.foodDetailData(map);
   }
   
   public List<RecipeVO> foodRecipeData(String title)
   {
	   return mapper.foodRecipeData(title); 
   }
   
   public List<FoodLocationVO> foodLocationSearchData(Map map)
   {
	   return mapper.foodLocationSearchData(map);
   }
   
   public int foodLocationTotalPage(String address)
   {
	   return mapper.foodLocationTotalPage(address);
   }
   // 추천 
   public List<String> foodGetNameData()
   {
	   return mapper.foodGetNameData();
   }
   public FoodLocationVO foodInfoData(String name)
   {
	   return mapper.foodInfoData(name);
   }
}
