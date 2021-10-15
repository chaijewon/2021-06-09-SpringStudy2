package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class MusicDAO extends SqlSessionDaoSupport{

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	/*
	 *  <select id="musicListData" resultType="MusicVO">
		   SELECT no,poster,title,singer,album,key FROM melon_cjw
		  </select>
		  <select id="musicFindData" resultType="MusicVO" parameterType="hashmap">
		   SELECT no,poster,title,singer,album,key FROM melon_cjw
		  </select>
	 */
	public List<MusicVO> musicListData()
	{
		return getSqlSession().selectList("musicListData");
	}
	public List<MusicVO> musicFindData(Map map)
	{
		return getSqlSession().selectList("musicFindData",map);
	}
    
}








