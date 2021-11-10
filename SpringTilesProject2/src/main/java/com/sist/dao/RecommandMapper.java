package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import java.util.*;
public interface RecommandMapper {
  // 명소명 
  @Select("SELECT DISTINCT title FROM seoul_location "
		 +"WHERE address LIKE '%'||#{address}||'%'")
  public List<String> seoulLocationTitleData(String address);
  // 호텔명
  @Select("SELECT DISTINCT name FROM seoul_hotel "
		 +"WHERE address LIKE '%'||#{address}||'%'")
  public List<String> seoulLocationHotelData(String address);
  // 명소명으로 정보 찾기
  @Select("SELECT * FROM seoul_location "
		 +"WHERE title=#{title}")
  public List<SeoulLocationVO> seoulLocationInfoData(String title);
  // 호텔명으로 정보 찾기 
  @Select("SELECT * FROM seoul_hotel "
		 +"WHERE name=#{name}")
  public List<SeoulHotelVO> seoulHotelInfoData(String name);
}
