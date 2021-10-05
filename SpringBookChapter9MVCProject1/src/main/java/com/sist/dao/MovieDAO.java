package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MovieDAO {
   @Autowired
   private MovieMapper mapper;
   
   public List<MovieVO> movieListData(Map map)
   {
	   return mapper.movieListData(map);
   }
   
   public int movieTotalPage()
   {
	   return mapper.movieTotalPage();
   }
}
