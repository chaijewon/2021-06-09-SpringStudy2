package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class BoardDAO {
   @Autowired
   private BoardMapper mapper;
   
   public List<BoardVO> boardListData(Map map)
   {
	   return mapper.boardListData(map);
   }
   
   public void boardInsert(BoardVO vo)
   {
	   mapper.boardInsert(vo);
   }
   // update 여러개 , insert여러개 
   public BoardVO boardDetailData(int no)
   {
	   //@Insert @Update @Delete => commit()
	   mapper.hitIncrement(no);
	   return mapper.boardDetailData(no);
   }
   public int boardTotalPage()
   {
	   return mapper.boardTotalPage();
   }
   //수정 => 전 내용을 읽어서 출력 : 기존의 SQL을 재사용 
   public BoardVO boardUpdateData(int no)
   {
	   return mapper.boardDetailData(no);// 재사용 
   }
   //실제 수정 (비밀번호 검색)
   public boolean boardUpdate(BoardVO vo)
   {
	   boolean bCheck=false;
	   // 비밀번호 읽기 
	   String db_pwd=mapper.boardGetPassword(vo.getNo());
	   if(db_pwd.equals(vo.getPwd()))
	   {
		   bCheck=true;
		   mapper.boardUpdate(vo);
	   }
	   else
	   {
		   bCheck=false;
	   }
	   return bCheck;
   }
   
   public boolean boardDelete(int no,String pwd)
   {
	   boolean bCheck=false; // default
	   // 비밀번호 검색 
	   String db_pwd=mapper.boardGetPassword(no);
	   System.out.println(pwd+"|"+db_pwd);
	   if(db_pwd.equals(pwd))
	   {
		   bCheck=true;
		   // 실제 삭제 
		   mapper.boardDelete(no);
	   }
	   return bCheck;
   }
   
   public List<BoardVO> boardFindData(Map map)
   {
	   return mapper.boardFindData(map);
   }
   
   public int boardFindCount(Map map)
   {
	   return mapper.boardFindCount(map);
   }
}








