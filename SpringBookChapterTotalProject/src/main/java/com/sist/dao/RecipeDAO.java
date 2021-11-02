package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

//메모리 할당 요청 
@Repository
public class RecipeDAO {
  // 구현된 Mapper주소 읽기
  @Autowired
  private RecipeMapper mapper;
  
  public void recipeInsert(RecipeVO vo)
  {
	  mapper.recipeInsert(vo);
  }
  public void chefInsert(ChefVO vo)
  {
	  mapper.chefInsert(vo);
  }
  public void recipeDetailInsert(RecipeDetailVO vo)
  {
	  mapper.recipeDetailInsert(vo);
  }
  public List<RecipeVO> recipeLinkData()
  {
	  return mapper.recipeLinkData();
  }
}
