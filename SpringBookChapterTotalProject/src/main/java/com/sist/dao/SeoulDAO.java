package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import com.sist.vo.SeoulHotelVO;
import com.sist.vo.SeoulLocationVO;
import com.sist.vo.SeoulNatureVO;
@Repository
public class SeoulDAO {
  @Autowired
  private SeoulMapper mapper;
  
  public List<SeoulLocationVO> locationListData(Map map)
  {
	  return mapper.locationListData(map);
  }
  public List<SeoulNatureVO> natureListData(Map map)
  {
	  return mapper.natureListData(map);
  }
  public List<SeoulHotelVO> hotelListData(Map map)
  {
	  return mapper.hotelListData(map);
  }
  
  public int seoulTotalPage(Map map)
  {
	  return mapper.seoulTotalPage(map);
  }
  
  // 상세보기 
  public SeoulLocationVO locationDetailData(int no)
  {
	  return mapper.locationDetailData(no);
  }
  
  public SeoulHotelVO hotelDetailData(int no)
  {
	  return mapper.hotelDetailData(no);
  }
}
