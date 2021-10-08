package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SeoulDAO {
   @Autowired
   private SeoulMapper mapper;
   /*
    *  // 명소 8
	  @Select("SELECT no,title,poster,rownum "
			+"FROM (SELECT no,title,poster "
			+"FROM seoul_location ORDER BY no ASC) "
			+"WHERE rownum<=8")
	  public List<LocationVO> locationTopData();
	  // 자연 8
	  @Select("SELECT no,title,poster,rownum "
				+"FROM (SELECT no,title,poster "
				+"FROM seoul_nature ORDER BY no ASC) "
				+"WHERE rownum<=8")
	  public List<LocationVO> natureTopData();
	  // 호텔 8
	  @Select("SELECT no,name,poster,rownum "
				+"FROM (SELECT no,name,poster "
				+"FROM seoul_hotel ORDER BY no ASC) "
				+"WHERE rownum<=8")
	  public List<LocationVO> hotelTopData();
	    */
   public List<LocationVO> locationTopData()
   {
	   return mapper.locationTopData();
   }
   public List<NatureVO> natureTopData()
   {
	   return mapper.natureTopData();
   }
   public List<HotelVO> hotelTopData()
   {
	   return mapper.hotelTopData();
   }
   
}
