package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Executable;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
// 댓글 
@Repository
public class FreeBoardDAO {
  @Autowired
  private FreeBoardMapper mapper;
  
  public List<FreeBoardVO> freeboardListData(Map map)
  {
	  return mapper.freeboardListData(map);
  }
  
  public int freeboardTotalPage()
  {
	  return mapper.freeboardTotalPage();
  }
  
  public void freeboardInsert(FreeBoardVO vo)
  {
	  mapper.freeboardInsert(vo);
  }
  
  public FreeBoardVO freeboardDetailData(int no)
  {
	  mapper.hitIncrement(no);
	  return mapper.freeboardDetailData(no);
  }
  
  public FreeBoardVO freeboardUpdateData(int no)
  {
	  return mapper.freeboardDetailData(no);
  }
  // 오라클 => NUMBER,VARCHAR2
  public boolean freeboardUpdate(FreeBoardVO vo)
  {
	  boolean bCheck=false;
	  // 비밀번호 체크
	  String db_pwd=mapper.freeboardGetPassword(vo.getNo());
	  if(db_pwd.equals(vo.getPwd()))
	  {
		  bCheck=true;
		  mapper.freeboardUpdate(vo);
	  }
	  else
	  {
		  // 비밀번호가 틀린다
		  bCheck=false;
	  }
	  return bCheck;
  }
  
  // 삭제
  // 트랜잭션 적용 : 일괄처리 (저장 , 취소)
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
  // AOP 
  public boolean freeboardDelete(int no,String pwd)
  {
	  /*
	   *   
	   *   conn.setAutoCommit(false); @Around
	   */
	  boolean bCheck=false;
	  // 비밀번호 검색 
	  String db_pwd=mapper.freeboardGetPassword(no);
	  if(db_pwd.equals(pwd))
	  {
		  bCheck=true;
		  //댓글 삭제 
		  mapper.freeboardDelete(no);
		  // conn.commit()
	  }
	  else
	  {
		  bCheck=false;
	  }
	  // catch=> conn.rollback() @After-Throwing 
	  return bCheck;
  }
}










