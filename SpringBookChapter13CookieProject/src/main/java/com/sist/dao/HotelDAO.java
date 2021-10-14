package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
// mapper => 
@Repository
public class HotelDAO {
	 @Autowired
     private HotelMapper mapper;
	 /*
	  *   @Select("SELECT no,name,poster,num "
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
	  */
	 public List<HotelVO> hotelListData(Map map)
	 {
		 return mapper.hotelListData(map);
	 }
	 public int hotelTotalPage()
	 {
		 return mapper.hotelTotalPage();
	 }
	 public HotelVO hotelDetailData(int no)
	 {
		 return mapper.hotelDetailData(no);
	 }
	 
}







