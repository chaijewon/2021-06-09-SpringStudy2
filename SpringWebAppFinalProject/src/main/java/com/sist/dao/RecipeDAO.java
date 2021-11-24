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
	// MyBatis 
	/*
	 *   1. SQL 
	 *   2. 결과값은 어떤 데이터형으로 받을 지 (리턴형)
	 *   3. 매개변수 설정 (사용자가 보내주는 데이터)
	 *   ====> 자동 구현 
	 */
	public List<CategoryVO> categoryListData(Map map)
	{
		return mapper.categoryListData(map); //브라우저로 전송이 불가능 
	}
	/*
	 *   DAO : 오라클 연동 (오라클 데이터 읽기) => 서버 자체(브라우저 ,모바일에 전송(X))
	 *   Controller : 서버에서 전송이 가능하게 만들어 준 클래스 
	 *   Manager : 외부 API를 이용해서 데이터 읽기 => 서버 자체 (브라우저 ,모바일에 전송(X))
	 */
	public CategoryVO categoryInfoData(int cno)
	{
		return mapper.categoryInfoData(cno);
	}
	
	public List<FoodVO> foodCategoryListData(int cno)
	{
		return mapper.foodCategoryListData(cno);
	}
	public FoodVO foodDetailData(int no)
	{
		return mapper.foodDetailData(no);
	}
	// 서울 여행 
	/*
	 *  
      public List<SeoulLocationVO> seoulLocationListData(Map map);
   
      public List<SeoulNatureVO> seoulNatureListData(Map map);
   
      public List<SeoulHotelVO> seoulHotelListData(Map map);
      
            마이바티스
            1. SQL문장 => 실행후 결과(리턴형) 
                        = List ==> row가 여러개 (목록)
                        = VO   ==> row가 한개 (상세보기)
                        = int  ==> 총페이지 
                        = String => 비밀번호 , 이름 ....
                        = LIKE , BETWEEN => List
            2. 매개변수 => ?에 들어가는 값   
                        #{변수명} => 1개 ==> 일반데이터형 
                                   여러개 => ~VO를 설정 , VO에 없는 값 => Map
                         ==== INSERT , UPDATE (VO) 
                         ==== 검색 , 동적쿼리 (Map)
	 */
	public List<SeoulLocationVO> seoulLocationListData(Map map)
	{
		return mapper.seoulLocationListData(map);
	}
	public List<SeoulNatureVO> seoulNatureListData(Map map)
	{
		return mapper.seoulNatureListData(map);
	}
	public List<SeoulHotelVO> seoulHotelListData(Map map)
	{
		return mapper.seoulHotelListData(map);
	}
	// 테이블 3개를 동시에 처리 
	public int seoulTotalPage(Map map)
	{
		return mapper.seoulTotalPage(map);
	}
	
	// 찾기
	public List<FoodVO> foodFindData(Map map)
	{
		return mapper.foodFindData(map);
	}
	
	public int findTotalPage(String ss)
	{
		return mapper.findTotalPage(ss);
	}
	
	public List<ChefVO> chefListData(Map map)
	{
		return mapper.chefListData(map);
	}
	
	public int chefTotalPage()
	{
		return mapper.chefTotalPage();
	}
	
	public List<RecipeVO> recipeChefMakeData(String chef)
	{
		return mapper.recipeChefMakeData(chef);
	}
	
	public FoodVO foodFindDetailData(int no)
	{
		return mapper.foodFindDetailData(no);
	}
	
	// React ==> NavLink to="http://localhost:8080/web/food/find_detail.do" 요청 
	// @RestController => @RequestMapping => DAO연결 => JSON변경 => React출력 
	// Kotlin(모바일) ==> Link => @RequestMapping => DAO연결 => JSON변경 => Kotlin(모바일)
}










