package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class RecipeDAO {
   @Autowired
   private RecipeMapper mapper;
   
   public List<RecipeVO> recipeListData(Map map)
   {
	   return mapper.recipeListData(map);
   }
   
   public int recipeTotalPage()
   {
	   return mapper.recipeTotalPage();
   }
}
