package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDAO {
    // 구현된 인터페이스 (RecipeMapper=>스프링)
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
	
	// 상세보기 
	public RecipeDetailVO recipeDetailData(int no)
	{
		return mapper.recipeDetailData(no);
	}
	// 쉐프 목록
	public List<ChefVO> chefListData(Map map)
	{
		return mapper.chefListData(map);
	}
	public int chefTotalPage()
	{
		return mapper.chefTotalPage();
	}
	// 쉐프 레시피 목록
	public List<RecipeVO> chefMakeData(Map map)
	{
		return mapper.chefMakeData(map);
	}
	public int chefMakeTotalPage(String chef)
	{
		return mapper.chefMakeTotalPage(chef);
	}
}





