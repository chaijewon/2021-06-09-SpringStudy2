package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
// map.put("boardDAO",Class.forName("com.sist.dao.BoardDAO"))
@Repository
public class BoardDAO  extends SqlSessionDaoSupport{

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public List<BoardVO> databoardListData(Map map)
	{
		return getSqlSession().selectList("daraboardListData", map);
	}
	public int databoardTotalPage()
	{
		return getSqlSession().selectOne("databoardTotalPage");
	}
	
	public List<BoardVO> boardFindData(Map map)
	{
		return getSqlSession().selectList("boardFindData",map);
	}
    
}
