package com.sist.dao;
/*
 *    사용자 요청 
 *    ======== DispatcherServlet (*.do) 
 *                   | 
 *                HandlerMapping
 *                ============== 클래스안에서 => @RequestMapping()
 *                                           @GetMapping()
 *                                           @PostMapping()
 *                   | => 메소드 (model.addAttribute()...)
 *                 ViewResolver (model에 등록된 데이터를 받는다)
 *                   | model => request로 변환 
 *                  JSP로 전송 (request있는 값만 출력 => ${} EL)
 *    ==> 처리 (요청 => 요청에 해당되는 데이터를 가지고 오기 => ~DAO,~Manager)
 *        ==> DAO,~Manager호출 (뉴스 , 다른 사이트 JSON)
 *        ==> @Controller , @RestController => DAO,Manager
 *    ==> DAO,Manager에는 전송할 수 있는 request,response를 가지고 있지 않는다
 *    ==> 전송객체 (Controller)
 *    
 *          요청                                      요청 처리
 *    JSP  =======> @Controller  <======> DAO , Manager
 *         <======
 *          응답 
 */
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface FoodMapper {
  // 1. 카테고리 출력 
  @Select({
	  "<script>"
	 +"SELECT cno,title,poster,subject "
	 +"FROM project_food_category "
	 +"<choose>"
	 +"<when test='no==1'>"  // map.get("no")
	 +"WHERE cno BETWEEN 1 AND 12"
	 +"</when>"
	 +"<when test='no==2'>"
	 +"WHERE cno BETWEEN 13 AND 18"
	 +"</when>"
	 +"<when test='no==3'>"
	 +"WHERE cno BETWEEN 19 AND 30"
	 +"</when>"
	 +"</choose>"
	 +"</script>"
  })
  public List<CategoryVO> categoryListData(Map map); //동적 쿼리 => 필요한 데이터 => Map에 묶어서 처리 
  // 2. 카테고리별 맛집 출력 
  @Select("SELECT no,poster,name,score,address,tel,type "
		 +"FROM project_food_house "
		 +"WHERE cno=#{cno}")
  public List<FoodVO> categoryFoodListData(int cno);
  // 2-1 카테고리 정보 읽기 (JSON => SELECT)  
  @Select("SELECT title,subject FROM project_food_category "
		 +"WHERE cno=#{cno}")
  public CategoryVO categoryInfoData(int cno);
  // 3. 맛집 상세보기 
  /*
   *   JSP => EL로 출력 (일반 사이트) => model.addAttribute() => request로 전송 
   *          ${id} => request.getAttribute("id") 
   *   JSP => VueJS,ReactJS,Ajax => request에 있는 데이터를 출력할 수 없다 => 인식하는 언어 (JSON) 
   */
  @Select("SELECT no,name,poster,address,score,tel,type,parking,price,menu,time "
		+"FROM project_food_house "
		+"WHERE no=#{no}")
  public FoodVO foodDetailData(int no);
  // 입력값 넘기는 방법 (화면 분할 => 템플릿)
  // 라우터 (라이브러리)
}
























