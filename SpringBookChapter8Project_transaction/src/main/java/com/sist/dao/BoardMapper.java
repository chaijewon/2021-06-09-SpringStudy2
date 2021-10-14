package com.sist.dao;
// SQL문장을 저장 
import java.util.*;
/*
 *   프로그램 : 영업/사무/무역 (초반) 
 *   ======= 연차 (사람하고 대면:컴퓨터 대면) => 2800(2600)
 */

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
public interface BoardMapper {
   // 목록 SELECT
	@Select("SELECT no,subject,name,regdate,hit,group_tab,num "
			 +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
			 +"FROM (SELECT no,subject,name,regdate,hit,group_tab, "
			 +"FROM spring_replyboard0 ORDER BY no DESC)) "
	         +"WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
   // 총페이지 SELECT
    @Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_replyboard0")
    public int databoardTotalPage();
   // 게시물 추가  INSERT 
    @Insert("INSERT INTO spring_replyboard0(no,name,subject,content,pwd,group_id) "
    	   +"VALUES((SELECT NVL(MAX(no)+1,1) FROM spring_replyboard0),#{name},"
    	   +"#{subject},#{content},#{pwd},"
    	   +"(SELECT NVL(MAX(group_id)+1,1) FROM spring_replyboard0)")
    public void boardInsert(BoardVO vo);
   // 상세보기  UPDATE SELECT
   @Update("UPDATE spring_replyboard0 SET "
		  +"hit=hit+1 "
		  +"WHERE no=#{no}")
   public void hitIncrement(int no);
   @Select("SELECT no,name,subject,content,hit,regdate as dbday "
		  +"FROM spring_replyboard0 "
		  +"WHERE no=#{no}")
   public BoardVO boardDetailData(int no);
   // 수정하기  UPDATE 
   @Select("SELECT pwd FROM spring_replyboard0 "
		  +"WHERE no=#{no}")
   public String boardGetPassword(int no);
   
   @Update("UPDATE spring_replyboard0 SET "
		  +"name=#{name},subject=#{subject},content=#{content} "
		  +"WHERE no=#{no}")
   public void boardUpdate(BoardVO vo);
   // 답변하기 (트랜잭션 적용) => SQL(4개) => UPDATE ,SLEECT ,INSERT
   // 1. 답변 대상의 정보 
   @Select("SELECT group_id,group_step,group_tab "
		  +"FROM spring_replyboard0 "
		  +"WHERE no=#{no}")
   public BoardVO boardParentInfoData(int no);
   // 2. step을 증가 
   /*                          group_step ASC
    *              group_id   group_step group_tab
    *    AAAAA       1           0         0
    *      =>KKKKK   1           1         1
    *      =>DDDDD   1           2         1  
    *      =>BBBBB   1           3         1
    *       =>CCCCC  1           4         2
    *     
    *      
    *     
    * 
    */
   // 답변 (묻고 답하기) => 핵심 
   @Update("UPDATE spring_replyboard0 SET "
		  +"group_step=group_step+1 "
		  +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
   public void boardGroupStepIncrement(BoardVO vo);
   // 3. 답변 Insert
   @Insert("INSERT INTO spring_replyboard0(no,name,subject,content,pwd,group_id,group_step,group_tab,root) "
    	   +"VALUES((SELECT NVL(MAX(no)+1,1) FROM spring_replyboard0),#{name},"
    	   +"#{subject},#{content},#{pwd},"
    	   +"#{group_id},#{group_step},#{group_tab},#{root})")
    public void boardReplyInsert(BoardVO vo);
   // 4. depth증가 
   @Update("UPDATE spring_replyboard0 SET "
		  +"depth=depth+1 "
		  +"WHERE no=#{no}")
   public void boardDepthIncrement(int no);
   // 삭제하기 (트랜잭션 적용) => SQL(5개) => UPDATE,DELETE, SELECT
   // 검색 => <trim~>
}







