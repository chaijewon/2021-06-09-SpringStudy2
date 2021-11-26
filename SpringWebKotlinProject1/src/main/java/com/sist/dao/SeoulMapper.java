package com.sist.dao;
/*
 *   NO      NOT NULL NUMBER         
TITLE   NOT NULL VARCHAR2(200)  
POSTER  NOT NULL VARCHAR2(500)  
MSG     NOT NULL VARCHAR2(4000) 
ADDRESS NOT NULL VARCHAR2(300)
 */
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface SeoulMapper {
   @Select("SELECT no,title,poster,num "
		  +"FROM (SELECT no,title,poster,rownum as num "
		  +"FROM (SELECT /*+ INDEX_ASC(seoul_location sl_no_pk) */no,title,poster "
		  +"FROM seoul_location)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<SeoulLocationVO> seoulLocListData(Map map);
}
