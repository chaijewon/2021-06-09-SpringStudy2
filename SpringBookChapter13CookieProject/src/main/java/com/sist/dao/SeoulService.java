package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//BI (DAO를 통합)
/*
 *    <context:component-scan base-package="com.sist.*"/>
 *    메모리 할당하는 어노테이션이 있는 클래스
 *    @Component  : 실시간 (Jsoup , 셀레니움, XML, JSON파싱),MainClass
 *    @Repository : DAO 
 *    @Service  : DAO여러개를 합쳐서 처리 (BI)
 *    @Controller : Model (화면 이동)
 *    @RestController : Model (일반 문자열 , JSON,XML) => JavaScript 연결 , Ajax , 코틀린 (모바일)
 */
//@Service
public class SeoulService {
   @Autowired
   private HotelDAO hdao;
   @Autowired
   private NatureDAO ndao;
   @Autowired
   private LocationDAO ldao;
}
