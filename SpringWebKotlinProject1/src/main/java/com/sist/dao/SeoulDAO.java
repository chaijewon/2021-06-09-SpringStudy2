package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 *    *** DAO VS Service의 차이점 
 *    
 *    스프링안에서 메모리 할당 => 어노테이션 
 *    @Component : 일반 클래스 (MainClass, ~Manager..., ~AI)
 *    @Repository : DAO (데이터 저장소)
 *    @Service : DAO+DAO => BI(한개로 통합) (******)
 *    @Controller : 화면 변경(데이터 전송)
 *    @RestController : 일반문자열 ..., JSON
 *    @ControllerAdvice : 공통기반의 예외처리 
 *    
 *    스프링안에서 메모리 주소를 주입 
 *    @Autowired : 스프링에 등록된 객체주소를 자동으로 주입  
 *    @Resource : 특정 객체 를 지정해서 주소값을 얻어 오는 경우 (********)
 */
@Repository
public class SeoulDAO {
    @Autowired // 스프링에서 메모리 할당 
    private SeoulMapper mapper;
    
    public List<SeoulLocationVO> seoulLocListData(Map map)
    {
    	return mapper.seoulLocListData(map);
    }
}
