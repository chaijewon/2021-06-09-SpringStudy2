package com.sist.mapper;
// SQL문장 
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
// CURD 
/*
 *   Create  ==> Insert
 *   Update  ==> Update
 *   Read    ==> Select
 *   Delete  ==> Delete
 *   =================== DML(데이터 조작언어) => 웹 개발자 사용하는 SQL언어 (DDL,DCL:DBA)
 *                       TCL(트랜잭션) => COMMIT/ROLLBACK
 */
public interface FreeBoardMapper {
  // 목록 : (페이징 => 인라인뷰) : SELECT
  @Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
		 +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		 +"FROM (SELECT /*+ INDEX_DESC(spring_freeboard sfb_no_pk) */ no,subject,name,regdate,hit "
		 +"FROM spring_freeboard)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
  // #{start}  => map.get("start") , #{end} => map.get("end")
  // map.put("start",1) , map.put("end",15)
  public List<FreeBoardVO> freeboardListData(Map map);
  // 총페이지  : SELECT
  @Select("SELECT CEIL(COUNT(*)/15.0) FROM spring_freeboard")
  public int freeboardTotalPage();
  // 추가 (글쓰기)  : INSERT
  // 번호 => @SelectKey , 서브쿼리 , sequence
  @Insert("INSERT INTO spring_freeboard(no,name,subject,content,pwd) "
		 +"VALUES(sfb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
  // #{name} => vo.getName() 
  // mapper를 구현 반드시 매개변수는 1개만 사용 => 데이터가 많은 경우 (VO,Map) 
  // 기본 => VO
  public void freeboardInsert(FreeBoardVO vo);
  // 내용보기 : SELECT
  @Update("UPDATE spring_freeboard SET "
		 +"hit=hit+1 "
		 +"WHERE no=#{no}")
  // 조회수 증가 
  public void hitIncrement(int no);
  @Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,hit "
		 +"FROM spring_freeboard "
		 +"WHERE no=#{no}")
  public FreeBoardVO freeboardDetailData(int no);
  // 수정 : UPDATE
  // 비밀번호 체크 
  @Select("SELECT pwd FROM spring_freeboard "
		 +"WHERE no=#{no}")
  public String freeboardGetPassword(int no);
  // 실제 수정 
  @Update("UPDATE spring_freeboard SET "
		 +"name=#{name},subject=#{subject},content=#{content},regdate=SYSDATE "
		 +"WHERE no=#{no}")
  public void freeboardUpdate(FreeBoardVO vo);
  // 삭제  : DELETE
  @Delete("DELETE FROM spring_freeboard "
		 +"WHERE no=#{no}")
  public void freeboardDelete(int no);
}










