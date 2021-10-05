package com.sist.dao2;

import org.springframework.stereotype.Repository;
// DAO메모리 할당 
/*
 *   구분 : 찾기
 *   @Component : 일반 클래스
 *   @Repository : DAO
 *   @Controller : Model
 *   @RestController : Model(JSON, 일반)
 *   
 *   ==> MyBatis,Hibernate
 *               save() => 자동으로 insert
 *               modify() => 자동으로 update()
 *   ==> 개발 (유지보수) => 다른 개발자 (기능 추가)
 */
@Repository
public class MyDAO {
   public String select()
   {
	   System.out.println("SELECT문장 수행");
	   return "오라클 SELECT문장 수행 완료";
   }
   public void insert(int no)
   {
	   System.out.println("INSERT문장 수행:"+no+"번을 저장");
   }
   public void update()
   {
	   System.out.println("UPDATE문장 수행");
   }
   public void delete()
   {
	   System.out.println("DELETE문장 수행");
   }
}
