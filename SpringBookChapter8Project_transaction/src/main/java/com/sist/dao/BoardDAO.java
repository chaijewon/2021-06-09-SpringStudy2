package com.sist.dao;
// 오라클과 연결만 담당 

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardDAO {
   @Autowired
   private BoardMapper mapper; //instanceof 연산자 (객체 비교)
   // 목록 
   public List<BoardVO> boardListData(Map map)
   {
	   return mapper.boardListData(map);
   }
   // 데이터 추가 
   public void boardInsert(BoardVO vo)
   {
	   mapper.boardInsert(vo);
   }
   // 상세보기 
   public BoardVO boardDetailData(int no)
   {
	   mapper.hitIncrement(no);//조회수 증가 UPDATE
	   return mapper.boardDetailData(no); // SELECT
   }
   // 수정 데이터 읽기
   public BoardVO boardUpdateData(int no)
   {
	   return mapper.boardDetailData(no);
   }
   // 실제 수정 
   public void boardUpdate(BoardVO vo)
   {
	   mapper.boardUpdate(vo);
   }
   // 답변하기 
   @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
   // 요청시마다 트랜잭션을 구현한다 (AOP구현이 완료되어 있다)
   // 일괄처리 (동시에 작업) => 저장 / 명령문 취소 
   public void boardReplyInsert(int root,BoardVO vo)// 다변 대상 번호
   {
	   /*
	    *   conn.setAutoCommit(false)
	    */
	   BoardVO pvo=mapper.boardParentInfoData(root);// 상위 (답변 대상)
	   mapper.boardGroupStepIncrement(pvo);// UPDATE
	   // 답변 추가 
	   vo.setGroup_id(pvo.getGroup_id());
	   vo.setGroup_step(pvo.getGroup_step()+1);
	   vo.setGroup_tab(pvo.getGroup_tab()+1);
	   vo.setRoot(root);
	   mapper.boardReplyInsert(vo);//INSERT
	   mapper.boardDepthIncrement(root);//UPDATE
	   /*
	    *  conn.commit()   ==> @Around
	    *  
	    *  Error발생시 
	    *  conn.rollback() ==> @After-Throwing
	    *  
	    *  finally 
	    *   conn.setAutoCommit(true)  @After
	    */
   }
}








