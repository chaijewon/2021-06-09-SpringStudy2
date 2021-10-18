package com.sist.mapper;

import org.apache.ibatis.annotations.*;
import java.util.*;
import com.sist.vo.*;

public interface ReplyBoardMapper {
	// 목록 출력 => 페이징 (인라인뷰) 
	   @Select("SELECT no,subject,name,dbday,hit,group_tab,num "
			  +"FROM (SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,group_tab,rownum as num "
			  +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
			  +"FROM spring_replyboard0 ORDER BY group_id DESC , group_step ASC)) "
			  +"WHERE num BETWEEN #{start} AND #{end}")
	   public List<ReplyBoardVO> replyBoardListData(Map map); // 매개변수는 1개만 사용한다, 입력값이 한개이상 (Map,VO)
	   // 총페이지 
	   @Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_replyboard0")
	   public int replyboardTotalPage();
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
	   
	   // 답변 (SQL=>4개)
	   // 1. 답변 대상의 group_id,group_step,group_tab => group_id는 그대로 첨부 , group_step,group_tab은 +1
	   @Select("SELECT group_id,group_step,group_tab FROM spring_replyboard0 WHERE no=#{no}")
	   public ReplyBoardVO replyParentInfoData(int no);
	   /*                    group_id,group_step,group_tab  depth
	    *    AAAAAA            1          0         0         1  => 삭제여부 확인 (Update 관리자 삭제한 게시물)
	    *      =>BBBBB         1          1         1         1
	    *       =>CCCCC        1          2         2         1
	    *        =>DDDDD       1          3         3         0
	    *    FFFFFF            2          0         0
	    *      =>KKKKKK        2          1         1
	    *       => OOOOO       2          2         2
	    *                 같은 답변을 모아서 관리 
	    *                 
	    *    FFFFFF            2          0         0      2
	    *      =>PPPPP         2          1         1      0
	    *      =>KKKKKK        2          2         1      1
	    *       => OOOOO       2          3         2    2 0 0
	    *      
	    */
	   // 2. 첨부할때 group_step을 증가 (1) => 핵심 SQL
	   @Update("UPDATE spring_replyboard0 SET "
			  +"group_step=group_step+1 "
			  +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
	   public void replyboardGroupStepIncrement(ReplyBoardVO vo);// vo에 없는 변수 => Map
	   // 3. 실제 데이터 추가 
	   @SelectKey(keyProperty="no", resultType=int.class , before=true,
			     statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_replyboard0")
	   // 자동 증가 번호를 만든다 
	   @Insert("INSERT INTO spring_replyboard0(no,name,subject,content,pwd,group_id,group_step,"
	   		  +"group_tab,root,depth) VALUES("
			  +"#{no},#{name},#{subject},#{content},#{pwd},"
			  +"#{group_id},#{group_step},#{group_tab},#{root},0)")
	   public void replyboardReplyInsert(ReplyBoardVO vo);
	   // 4. depth 증가
	   @Update("UPDATE spring_replyboard0 SET "
			  +"depth=depth+1 "
			  +"WHERE no=#{no}")
	   public void replyboardDepthIncrement(int no);
	   // 1=>SELECT , 2=>UPDATE , 3.INSERT , 4.UPDATE  ==> 자동 저장 :AutoCommit
	   // 일괄 처리 => SQL문장 전체가 에러가 없는 경우 (COMMIT수행) ,  SQL문장중에 한개라도 에러가 있는 경우 ROLLBACK
	   
	   // 수정하기 
	   // 1. 비밀번호 검색 
	   @Select("SELECT pwd FROM spring_replyboard0 WHERE no=#{no}")
	   public String replyboardGetPassword(int no);
	   // 2. 실제 수정 
	   @Update("UPDATE spring_replyboard0 SET "
			  +"name=#{name},subject=#{subject},content=#{content} "
			  +"WHERE no=#{no}")
	   public int replyboardUpdate(ReplyBoardVO vo);
	   
	   // 삭제하기 => SQL (트랜잭션 적용)
	   // 1. 비밀번호 검색 => 재사용 (public String replyboardGetPassword(int no))
	   // 2. 비밀번호가 맞는 경우 
	   // 2-1. root,depth 읽기 
	   // 2-2. depth==0  ==> DELETE (석제)
	   // 2-3. depth!=0  ==> UPDATE (제목 : 관리자가 삭제한 게시물입니다)
	   // 2-4. depth 감소 => UPDATE
	   // 검색 => 동적 쿼리 
	   // 마이바티스 => 동적 쿼리 , 어노테이션 (JOIN) , PL/SQL ==> CURD (INSERT/UPDATE/DELETE/SELECT)
	   // @SELECT({"SELECT * FROM spring_replyboard0 WHERE "
	   //           +"<script><trim prefix="("
}












