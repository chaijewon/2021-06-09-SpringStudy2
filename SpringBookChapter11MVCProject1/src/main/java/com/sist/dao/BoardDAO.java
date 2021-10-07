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
}
