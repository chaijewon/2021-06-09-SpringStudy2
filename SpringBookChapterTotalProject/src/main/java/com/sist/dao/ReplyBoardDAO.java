package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.vo.*;
import com.sist.mapper.*;
import java.util.*;
@Repository
public class ReplyBoardDAO {
   // 구현된 인터페이스를 받는다 (스프링이 관리) => 마이바티스 구현 (자동 구현)
   @Autowired
   private ReplyBoardMapper mapper;// 메모리 할당시 추가 
   // @Autowired/@Resource => 반드시 스프링에 의해 메모리 할당이 되야 된다 
   /*// 목록 출력 => 페이징 (인라인뷰) 
   @Select("SELECT no,subject,name,dbday,hit,group_tab,num "
		  +"FROM (SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,group_tab,rownum as num "
		  +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
		  +"FROM spring_replyboard0 ORDER BY group_id DESC , group_step ASC)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<ReplyBoardVO> replyBoardListData(Map map); // 매개변수는 1개만 사용한다, 입력값이 한개이상 (Map,VO)
   // 추가 => no(primary key) => 마이바티스 지원 , 서브쿼리 , 시퀀스  
   @SelectKey(keyProperty="no", resultType=int.class , before=true,
		     statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_replyboard0")
   // 자동 증가 번호를 만든다 
   @Insert("INSERT INTO spring_replyboard0(no,name,subject,content,pwd,group_id) VALUES("
		  +"#{no},#{name},#{subject},#{content},#{pwd},"
		  +"(SELECT NVL(MAX(group_id)+1,1) FROM spring_replyboard0))")
   public void replyBoardInsert(ReplyBoardVO vo);
   // 내용보기 
   @Update("UPDATE spring_replyboard0 SET "
		  +"hit=hit+1 "
		  +"WHERE no=#{no}")
   public void hitIncrement(int no);
   @Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,hit "
		  +"FROM spring_replyboard0 "
		  +"WHERE no=#{no}")
   public ReplyBoardVO replyBoardDetailData(int no);
   */
   // 목록 출력
   public List<ReplyBoardVO> replyBoardListData(Map map)
   {
	   return mapper.replyBoardListData(map); // SQL문장 1개 => 트랜잭션적용이 아니다 
   }
   // 총페이지 구하기
   public int replyboardTotalPage()
   {
	   return mapper.replyboardTotalPage();
   }
   // 게시물 추가
   public void replyBoardInsert(ReplyBoardVO vo)
   {
	   mapper.replyBoardInsert(vo);
   }
   // 내용추가 
   public ReplyBoardVO replyBoardDetailData(int no)
   {
	   mapper.hitIncrement(no);// Update
	   return mapper.replyBoardDetailData(no);// Select 
	   // 트랜잭션 => INSERT/UPDATE/DELELE => Commit / Rollback
   }
   
}
