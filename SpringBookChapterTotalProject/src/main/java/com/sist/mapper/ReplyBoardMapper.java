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
}
