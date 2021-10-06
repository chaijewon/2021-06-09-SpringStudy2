package com.sist.dao;
// music-mapper.xml => 대신 사용 
import java.util.*;

import org.apache.ibatis.annotations.Select;
// 오라클에서 데이터 얻기 (JDBC) : 직접 처리 (JDBC) , 이미 제작이 된 상태에서 필요한 데이터 보내면 자체에서 처리(ORM)
// ORM (MyBatis , Hibernate , JPA)
// 오라클 필요한 내용 (드라이버명 , URL,username,password) => SQL문장 , VO 
// 라이브러리는 이미 검증 (신뢰성)
public interface MusicMapper {
   
   @Select("SELECT no,poster,title,singer FROM melon_cjw ORDER BY no ASC")
   public List<MusicVO> musicListData();//sql문장을 읽어서 마이바티스에서 자동 구현 => selectList()
   /*
    *   <select id="musicListData" resultType="MusicVO">
    *     SELECT no,poster,title,singer FROM melon_cjw
    *   </select>
    */
   @Select("SELECT no,poster,title,singer,album,key "
		  +"FROM melon_cjw "
		  +"WHERE no=#{no}")
   public MusicVO musicDetailData(int no); // selectOne()
   /*
    *   <select id="musicDetailData" resultType="MusicVO" parameterType="int">
    *     SELECT no,poster,title,singer,album,key
    *     FROM melon_cjw
    *     WHERE no=#{no}
    *   </select>
    *   => 스칼라 서브쿼리 : 컬럼 대신 => 데이터값 1개
    *   => 서브쿼리 (스칼라서브쿼리 , JOIN) 
    *   => JOIN을 사용하면 NULL값일 경우에는 제외,제외없이 모든 데이터를 읽어 온다 (Outer JOIN)
    *   => 진화된 SQL문장을 사용
    */
   @Select("SELECT no,poster,title,singer,album,state,idcrement,"
		 +"(SELECT key FROM melon_cjw WHERE melon_cjw.title=genie_cjw.title AND title<>'Celebrity') as key "
		 +"FROM genie_cjw WHERE title<>'상상더하기' ORDER BY no ASC")
   public List<GenieVO> genieListData();
   
}






