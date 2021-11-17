package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 *   메모리 할당 (스프링)
 *   @Controller => ViewResolver를 이용해서 화면 변경 (값:JSP파일명 , redirect)
 *   @RestController => 해당 웹파일에 데이터만 전송  => @ResponseBody
 *   @Service => DAO+DAO (DAO조립)
 *   @Repository => DAO
 *   @ControllerAdvice => 공통 예외처리 
 *   @Component => 일반 클래스 (크롤링)
 *   
 *   메모리 주소 주입
 *   @Autowired : 스프링에 찾아서 주입 (자동 주입)
 *   @Resource : 직접 ID명 설정 
 *     @Resource(name="id")
 *     
 *   요청 처리 
 *   @RequestMapping()
 *   @GetMapping()
 *   @PostMapping()
 *   
 *   기타 (AOP)
 *   @Aspect
 *   @Before , @After , @After-Throwing , @Around , @After-Returning
 *   @Transactional 
 */
@Repository
public class RecipeDAO {
	@Autowired
    private RecipeMapper mapper;
	// 메소드 
	/*
	 *    1. 게시판이나 목록을 출력 했을때 페이징 기법에 사용된 기술? => 인라인뷰 사용 
	 *                                                    ====== 뷰의 종류 
	 *                                                    = 단일 뷰
	 *                                                    = 복합 뷰
	 *                                                    = 인라인 뷰 
	 *    @Select("SELECT no,title,poster,chef,num "
		         +"FROM (SELECT no,title,poster,chef,rownum as num "
		         +"FROM (SELECT  INDEX_ASC(recipe recipe_no_pk)no,title,poster,chef "
		         +"FROM recipe)) "
		         +"WHERE num BTEWEEN #{start} AND #{end}")
		   public List<RecipeVO> recipeListData(Map map);
		   // 2. 총페이지 
		   @Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe")
		   public int recipeTotalPage();
	 */
	public List<RecipeVO> recipeListData(Map map)
	{
		return mapper.recipeListData(map);
	}
	public int recipeTotalPage()
	{
		return mapper.recipeTotalPage();
	}
	public RecipeDetailVO recipeDetailData(int no)
	{
		return mapper.recipeDetailData(no);
	}
}










