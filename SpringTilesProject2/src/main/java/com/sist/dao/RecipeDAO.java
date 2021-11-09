package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDAO {
   @Autowired // 자동 주입(스프링에서 메모리할당한 객체주소를 찾아주는 역할)
   private RecipeMapper mapper;
   
   public List<RecipeVO> recipeListData(Map map)
   {
	   return mapper.recipeListData(map);
   }
   public int recipeTotalPage()
   {
	   return mapper.recipeTotalPage();
   }
   public int recipeCount()
   {
	   return mapper.recipeCount();
   }
   public RecipeDetailVO recipeDetailData(int no)
   {
	   return mapper.recipeDetailData(no);
   }
   
   public List<ChefVO> chefListData(Map map)
   {
	   return mapper.chefListData(map);   
   }
   public int chefTotalPage()
   {
	   return mapper.chefTotalPage();
   }
   
   public List<RecipeVO> chefMakeRecipeData(Map map)
   {
	   return mapper.chefMakeRecipeData(map);
   }
   
   public int chefMakeTotalPage(String chef)
   {
	   return mapper.chefMakeTotalPage(chef);
   }
}
