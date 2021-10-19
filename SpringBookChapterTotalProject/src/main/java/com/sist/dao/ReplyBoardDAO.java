package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
   // 답변 하기 => AOP
   /*
    *    @Transactional()  => 매번 새로게 만든다 () => 트랜잭션 처리(매번 생성), 요청시마다 처리(미리) Propagation.REQUIRED
    *                        Propagation.REQUIRES_NEW                
    *    try
    *    {
    *       conn.setAutoCommit(false); => commit이 없는 경우는 오라클에 저장이 안된다 
    *      //1.오라클 연결
    		getConnection();
    		//2.SQL문장 (1) => pno가 가지고 있는 group_id,group_step,group_tab를 가지고 온다
    		// 답변과 관련된 데이터 읽기
    		String sql="SELECT group_id,group_step,group_tab "
    				  +"FROM mvcBoard "
    				  +"WHERE no=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, pno);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		int gi=rs.getInt(1); // 그대로 추가
    		int gs=rs.getInt(2); // gs+1
    		int gt=rs.getInt(3); // gt+1
    		rs.close();
    		==========================> @Select("SELECT group_id,group_step,group_tab FROMspring_replyboard0 WHERE no=#{no}")
    		//SQL문장 (2) => group_step를 증가 
    		// 답변 출력순서 지정 
    		sql="UPDATE mvcBoard SET "
    		   +"group_step=group_step+1 "
    	       +"WHERE group_id=? AND group_step>?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, gi);
    		ps.setInt(2, gs);
    		ps.executeUpdate();
    		=========================> @Update("UPDATE spring_replyboard0 SET "
			  +"group_step=group_step+1 "
			  +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
    		//SQL문장 (3) => insert 
    		sql="INSERT INTO mvcBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root,depth) "
    		   +"VALUES(mvc_no_seq.nextval,?,?,?,?,?,?,?,?,?)";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, vo.getName());
    		ps.setString(2, vo.getSubject());
    		ps.setString(3, vo.getContent());
    		ps.setString(4, vo.getPwd());
    		ps.setInt(5, gi);
    		ps.setInt(6, gs+1);
    		ps.setInt(7, gt+1);
    		ps.setInt(8, pno);
    		ps.setInt(9, 0);
    		ps.executeUpdate();
    		======================================> @Insert("INSERT INTO spring_replyboard0(no,name,subject,content,pwd,group_id,group_step,"
	   		  +"group_tab,root,depth) VALUES("
			  +"#{no},#{name},#{subject},#{content},#{pwd},"
			  +"#{group_id},#{group_step},#{group_tab},#{root},0)")
    		//SQL문장 (4) => depth를 증가 (답변의 갯수확인시킨다)
    		sql="UPDATE mvcBoard SET "
    		   +"depth=depth+1 "
    		   +"WHERE no=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, pno);
    		ps.executeUpdate();
    		=====================================> @Update("UPDATE spring_replyboard0 SET "
			  +"depth=depth+1 "
			  +"WHERE no=#{no}")
			conn.commit();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    		conn.rollback()
    	}
    	finally
    	{
    		disConnection();
    		conn.setAutoCommit(true) 원상 복귀
    	}
    }
    */
   @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
   public void replyBoardReplyInsert(int pno,ReplyBoardVO vo)
   {
	   // 1. conn.setAutoCommit(false) => Spring처리 ==> @Around
	   // 2. 사용자 호출 
	   // 2-1. 답변 대상 정보 읽기
	   ReplyBoardVO pvo=mapper.replyParentInfoData(pno);
	   // 2-2. group_step변경
	   mapper.replyboardGroupStepIncrement(pvo);
	   // 2-3. 데이터 추가 (insert와 동일) => 출력 형식 변경 (group_id,group_step,group_tab,root,depth)
	   //                                           ============================= ===========
	   //                                                   답변 출력 column          삭제와 관련
	   vo.setGroup_id(pvo.getGroup_id());
	   vo.setGroup_step(pvo.getGroup_step()+1);
	   vo.setGroup_tab(pvo.getGroup_tab()+1);
	   vo.setRoot(pno);
	   mapper.replyboardReplyInsert(vo);
	   // 2-4. depth증가 
	   mapper.replyboardDepthIncrement(pno);
	   // 3. conn.commit()  => Spring @Around
	   // 4. 에러시 conn.rollback() => Spring @After-Throwing
	   // 5. conn.setAutoCommit(true); => Spring @After
   }
   
   // 수정할 데이터 읽어 오기 
   public ReplyBoardVO replyUpdateData(int no)
   {
	   return mapper.replyBoardDetailData(no);
   }
   //  실제 수정
   public int replyboardUpdate(ReplyBoardVO vo)
   {
	   int result=0;
	   // 비밀번호 읽기 
	   String db_pwd=mapper.replyboardGetPassword(vo.getNo());
	   if(db_pwd.equals(vo.getPwd()))
	   {
		   result=1;
		   // 실제 수정 
		   mapper.replyboardUpdate(vo);
	   }
	   else
	   {
		   result=0;
	   }
	   return result;
   }
   /*
    *   // 삭제하기 => SQL (트랜잭션 적용)
	   // 1. 비밀번호 검색 => 재사용 (public String replyboardGetPassword(int no))
	   // 2. 비밀번호가 맞는 경우 
	   // 2-1. root,depth 읽기 
	   @Select("SELECT root,depth FROM spring_replyboard0 WHERE no=#{no}")
	   public ReplyBoardVO replyboardGetDepth(int no);
	   // 2-2. depth==0  ==> DELETE (삭제) => 비밀번호 (O) , depth=0
	   @Delete("DELETE FROM spring_replyboard0 WHERE no=#{no}")
	   public void replyDelete(int no);
	   // 2-3. depth!=0  ==> UPDATE (제목 : 관리자가 삭제한 게시물입니다)
	   @Update("UPDATE spring_replyboard0 SET "
			  +"subject=#{subject},content=#{content} "
			  +"WHERE no=#{no}")
	   public void replyboardDelUpdate(ReplyBoardVO vo);
	   // 2-4. depth 감소 => UPDATE
	   @Update("UPDATE spring_replyboard0 SET "
			  +"depth=depth-1 "
			  +"WHERE no=#{no}")
	   public void depthDecrement(int no);
	   
	   요청 : .do 
	            1) Controller => @RequestMapping (구분) <=====> DAO연결 
	                                     | request
	                                    JSP로 전송 
    */
   @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
   public int replyDelete(int no,String pwd)
   {
	   // conn.setAutoCommit(false);
	   int result=0;
	   // 비밀번호 읽기 
	   String db_pwd=mapper.replyboardGetPassword(no);
	   if(db_pwd.equals(pwd))
	   {
		   result=1;
		   // root,depth를 읽어 온다 
		   ReplyBoardVO vo=mapper.replyboardGetDepth(no);
		   if(vo.getDepth()==0)// 댓글이 없는 상태 
		   {
			   // 삭제하면 된다 
			   mapper.replyDelete(no); // DELETE
		   }
		   else // 댓글이 있는 상태
		   {
			   ReplyBoardVO pvo=new ReplyBoardVO();
			   pvo.setSubject("관리자가 삭제한 게시물입니다.");
			   pvo.setContent("관리자가 삭제한 게시물입니다.");
			   pvo.setNo(no);
			   mapper.replyboardDelUpdate(pvo);//UPDATE
		   }
		   /*int depth=mapper.replyboardRootGetDepth(vo.getRoot());
		   if(depth>0)
		   {*/
		       mapper.depthDecrement(vo.getRoot());// 상위게시물 //UPDATE
		  /* }*/
	   }
	   else
	   {
		   result=0;
	   }
	   // conn.commit()
	   // conn.rollback() => SQL문장을 저장하지 않고 취소 
	   // conn.setAutoCommit(true);
	   // => AOP ==> @Transactional() 
	   return result;
   }
   public List<ReplyBoardVO> replyboardFindData(Map map)
   {
	   return mapper.replyboardFindData(map);
   }
}













