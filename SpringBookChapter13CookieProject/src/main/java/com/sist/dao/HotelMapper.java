package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
/*
 *  NO      NOT NULL NUMBER         
NAME    NOT NULL VARCHAR2(100)  
SCORE            NUMBER(2,1)    
ADDRESS NOT NULL VARCHAR2(300)  
POSTER  NOT NULL VARCHAR2(4000) 
IMAGES           VARCHAR2(4000)
 */
public interface HotelMapper {
   @Select("SELECT no,name,poster,num "
		  +"FROM (SELECT no,name,poster,rownum as num "
		  +"FROM (SELECT no,name,poster "
		  +"FROM seoul_hotel ORDER BY no ASC)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<HotelVO> hotelListData(Map map);
   @Select("SELECT CEIL(COUNT(*)/12.0) FROM seoul_hotel")
   public int hotelTotalPage();
   // 상세보기 
   @Select("SELECT no,name,poster,address,images,score "
		  +"FROM seoul_hotel "
		  +"WHERE no=#{no}")
   public HotelVO hotelDetailData(int no);
}
