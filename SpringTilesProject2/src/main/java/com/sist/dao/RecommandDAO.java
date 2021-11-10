package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
// 스프링에서 메모리 할당을 해야 => @Autowired
@Repository
public class RecommandDAO {
  @Autowired
  private RecommandMapper mapper;
  /*
   *   // 명소명 
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
  public SeoulLocationVO seoulLocationInfoData(String title);
  // 호텔명으로 정보 찾기 
  @Select("SELECT * FROM seoul_hotel "
		 +"WHERE name=#{name}")
  public SeoulLocationVO seoulHotelInfoData(String name);
   */
  public List<String> seoulLocationTitleData(String address)
  {
	  return mapper.seoulLocationTitleData(address);
  }
  public List<String> seoulLocationHotelData(String address)
  {
	  return mapper.seoulLocationHotelData(address);
  }
  public List<SeoulLocationVO> seoulLocationInfoData(String title)
  {
	  return mapper.seoulLocationInfoData(title);
  }
  public List<SeoulHotelVO> seoulHotelInfoData(String name)
  {
	  return mapper.seoulHotelInfoData(name);
  }
}






