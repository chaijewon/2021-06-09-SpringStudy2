package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
@Repository
public class FoodDAO {
	@Autowired
    private FoodMapper mapper;
    /*
     *                                                         ============================ 유지보수
     *                                                          주문 내역 확인 (HandlerMapping)
     *                                                          @RequestMapping 
     *                                                          @GetMapping
     *                                                          @PostMapping
     *                                                          ========= 미리 제작 
     *    요청 (category.do)  ======> DispatcherServlet =======> 주문 내역을 만든다(@Controller)<==> 재료
     *    맛집 카테고리를 보여 달라                    서빙(주문) => 배달                     @RestController          ~Manager,~DAO
     *                               Dispatcher:배달부 
     *                               FilterDispatcher  
     *    DAO => @Controller,@RestController ====> 라이더(ViewResolver) ====> 요청 브라우저로 전송 
     *    
     *    개발 : 8개월 , 4개월 : 유지보수  SM/SI (80%), 솔루션 : 자체 개발 (기술영업 , SM)
     *    프레임워크 : SM(X) => 개발 (위탁개발)
     *    
     *    교육 => 노동부 ( 기본 5년 ) / 교육 / 실무 (3년) 
     *    
     *    아이템 개발 , 설계 (아키텍쳐)
     */
    public List<CategoryVO> categorySelectData(Map map)
    {
    	return mapper.categorySelectData(map);// JSP로 전송 (@Controller,@RestController)
    	
    }
    
    // 검색
    public List<FoodVO> foodFindData(Map map) // MyBatis가 아니다 (매개변수를 여러개 사용 할 수 있다)
    {
    	return mapper.foodFindData(map);
    }
    
    // 총페이지 
    public int foodFindTotalPage(String address) // 사용자정의 
    {
    	return mapper.foodFindTotalPage(address); // 매개변수가 1개만 사용 가능 
    }
    
    public CategoryVO categoryInfoData(int cno)
    {
    	return mapper.categoryInfoData(cno);
    }
    public List<FoodVO> categoryFoodListData(int cno)
    {
    	return mapper.categoryFoodListData(cno);
    }
}










