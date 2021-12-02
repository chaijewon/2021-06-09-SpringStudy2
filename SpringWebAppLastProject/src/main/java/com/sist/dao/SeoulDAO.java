package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 *   자바 프로그램 
 *     = 네트워크 (게임)
 *     = 데이터베이스 (웹)
 */
@Repository
public class SeoulDAO {
   @Autowired
   private SeoulMapper mapper; // 인터페이스 구현 완료 
   
   public List<SeoulLocationVO> locationListData(Map map)
   {
	   return mapper.locationListData(map);
   }
   
   
   public int locationTotalPage()
   {
	   return mapper.locationTotalPage();
   }
}
