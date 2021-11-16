package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface MusicMapper {
  @Select("SELECT no,poster,title,singer,album,key "
		 +"FROM melon_cjw ORDER BY no ASC")
  public List<MusicVO> musicListData();
  
  @Select("SELECT * FROM daum_movie")
  public List<MovieVO> movieListData();
}
