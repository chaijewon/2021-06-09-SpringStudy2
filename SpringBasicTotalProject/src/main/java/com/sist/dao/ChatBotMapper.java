package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface ChatBotMapper {
  @Select("SELECT DISTINCT name FROM project_food_location")
  public List<String> foodHouseGetNameList(); 
  @Select("SELECT no,name,address,tel,type "
		 +"FROM project_food_location "
		 +"WHERE name=#{name}")
  public FoodLocationVO foodDetailData(String name);
}
