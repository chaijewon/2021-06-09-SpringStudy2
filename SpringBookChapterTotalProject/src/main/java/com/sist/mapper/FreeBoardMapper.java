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
  /*
   *   CREATE TABLE spring_reply_project(
    no NUMBER,
    bno NUMBER,
    id VARCHAR2(20),
    name VARCHAR2(34) CONSTRAINT srp_name_nn NOT NULL,
    msg CLOB CONSTRAINT srp_msg_nn NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    group_id NUMBER,
    group_step NUMBER DEFAULT 0,
    group_tab NUMBER DEFAULT 0,
    root NUMBER DEFAULT 0,
    depth NUMBER DEFAULT 0,
    CONSTRAINT srp_no_pk PRIMARY KEY(no),
    CONSTRAINT srp_bno_fk FOREIGN KEY(bno)
    REFERENCES spring_freeboard(no),
    CONSTRAINT srp_id_fk FOREIGN KEY(id)
    REFERENCES project_member(id)
	);
	CREATE SEQUENCE srp_no_seq
	   START WITH 1
	   INCREMENT BY 1
	   NOCACHE
	   NOCYCLE;
   */
  // 자유게시판 => 댓글 올리기
  @Insert("INSERT INTO spring_reply_project(no,bno,id,name,msg,group_id) VALUES("
		 +"srp_no_seq.nextval,#{bno},#{id},#{name},#{msg},"
		 +"(SELECT NVL(MAX(group_id)+1,1) FROM spring_reply_project))")
  public void freeboardReplyInsert(ReplyVO vo);
  
  // 댓글 출력  WHERE bno=#{bno} => 해당 게시물의 댓글을 읽어라 
  @Select("SELECT no,bno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,group_tab "
		 +"FROM spring_reply_project "
		 +"WHERE bno=#{bno}"  
		 +"ORDER BY group_id DESC , group_step ASC") // group_id DESC (최신순) group_step ASC(답변 순서)
  public List<ReplyVO> freeboardReplyListData(int bno);
  
  // 댓글 수정 
  @Update("UPDATE spring_reply_project SET "
		 +"msg=#{msg} "
		 +"WHERE no=#{no}")
  public void freeboardReplyUpdate(ReplyVO vo); // Map 
  /*
   *                       group_id  group_step group_tab depth
   *    AAAAA                  1         0          0       2
   *      =>DDDDD              1         1          1       0
   *      =>BBBBB              1         2          1       1 
   *        =>CCCCCC           1         3          2       0
   *       
   *      
   */
  // 댓글 - 댓글  => Tansaction 처리 
  // 1. 댓글 대상의 정보 : group_id,step,tab : SELECT
  @Select("SELECT group_id,group_step,group_tab "
		 +"FROM spring_reply_project "
		 +"WHERE no=#{no}")
  public ReplyVO freeboardReplyParentInfoData(int no);
  // 2. step 변경  : UPDATE 
  @Update("UPDATE spring_reply_project SET "
		 +"group_step=group_step+1 "
		 +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
  public void freeboardReplyStepIncrement(ReplyVO vo);
  // 3. insert : INSERT
  @Insert("INSERT INTO spring_reply_project(no,bno,id,name,msg,group_id,group_step,group_tab,root) "
		 +"VALUES(srp_no_seq.nextval,#{bno},#{id},#{name},#{msg},"
		 +"#{group_id},#{group_step},#{group_tab},#{root})")
  public void freeboardReply2Insert(ReplyVO vo);
  // 4. depth 증가 : UPDATE 
  @Update("UPDATE spring_reply_project SET "
		 +"depth=depth+1 "
		 +"WHERE no=#{no}")
  public void freeboardReplyDepthIncrement(int no);
  
  // 댓글 삭제  트랜잭션 대상 
  // 1. depth,root 읽기  SELECT
  @Select("SELECT depth,root "
		 +"FROM spring_reply_project "
		 +"WHERE no=#{no}")
  public ReplyVO freeboardDepthInfoData(int no);
  // 2. depth==0 삭제  , depth!=0 수정  DELETE/UPADTE
  @Delete("DELETE FROM spring_reply_project "
		 +"WHERE no=#{no}")
  public void freeboardReplyDelete(int no);
  @Update("UPDATE spring_reply_project SET "
		 +"msg='관리자가 삭제한 댓글입니다.' "
		 +"WHERE no=#{no}")
  public void freeboardReplyMsgUpdate(int no);
  // 3. depth 감소  UPDATE 
  @Update("UPDATE spring_reply_project SET "
		 +"depth=depth-1 "
		 +"WHERE no=#{no}")
  public void freeboardReplyDepthDecrement(int no);
  
}










