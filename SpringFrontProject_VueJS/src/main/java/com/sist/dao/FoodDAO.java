package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class FoodDAO {
   @Autowired
   private FoodMapper mapper;
   
   public List<CategoryVO> food_categoryData(Map map)
   {
	   System.out.println(map.get("no"));
	   return mapper.food_categoryData(map);
   }
   /*public List<CategoryVO> food_categoryData(int cno)
   {
	   if(cno==1)
	    return mapper.food_categoryData1();
	   else if(cno==2)
		return mapper.food_categoryData2();
	   else
		return mapper.food_categoryData3();
   }*/
}
