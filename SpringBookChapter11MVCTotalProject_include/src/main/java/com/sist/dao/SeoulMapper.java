package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface SeoulMapper {
  // 명소 8
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
  public List<NatureVO> natureTopData();
  // 호텔 8
  @Select("SELECT no,name,poster,rownum "
			+"FROM (SELECT no,name,poster "
			+"FROM seoul_hotel ORDER BY no ASC) "
			+"WHERE rownum<=8")
  public List<HotelVO> hotelTopData();
}
