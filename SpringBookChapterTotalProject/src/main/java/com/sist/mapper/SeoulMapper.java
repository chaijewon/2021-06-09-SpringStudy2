package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface SeoulMapper {
	// 명소 => seoul_location
  @Select("SELECT no,title,poster,num "
		+"FROM (SELECT no,title,poster,rownum as num "
		+"FROM (SELECT no,title,poster "
		+"FROM seoul_location ORDER BY no ASC)) "
		+"WHERE num BETWEEN #{start} AND #{end}")
  public List<SeoulLocationVO> locationListData(Map map);
  // seoulTotalPage("seoul_location")
  // seoulTotalPage("seoul_nature")
  // seoulTotalPage("seoul_hotel")
  // 3개의 테이블의 총페이지를 동시에 구한다
  @Select("SELECT CEIL(COUNT(*)/12.0) FROM ${table_name}")
  public int seoulTotalPage(Map map);
  
  @Select("SELECT no,title,poster,num "
			+"FROM (SELECT no,title,poster,rownum as num "
			+"FROM (SELECT no,title,poster "
			+"FROM seoul_nature ORDER BY no DESC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
  public List<SeoulNatureVO> natureListData(Map map);
  
  @Select("SELECT no,name,poster,score,num "
			+"FROM (SELECT no,name,poster,score,rownum as num "
			+"FROM (SELECT no,name,poster,score "
			+"FROM seoul_hotel ORDER BY no ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
  public List<SeoulHotelVO> hotelListData(Map map);
}
