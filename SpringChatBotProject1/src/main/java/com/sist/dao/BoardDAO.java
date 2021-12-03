package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class BoardDAO extends SqlSessionDaoSupport{
    /*
     *   <select id="boardListData" resultType="BoardVO">
		    SELECT no,subject,name,regdate,hit FROM chatbotBoard
		  </select>
     */
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public List<BoardVO> boardListData()
	{
		return getSqlSession().selectList("boardListData");
	}
	
	/*
	 *  <insert id="boardInsert" parameterType="BoardVO">
		    INSERT INTO chatbotBoard VALUES(
		      (SELECT NVL(MAX(no)+1,1) FROM chatbotBoard),#{name},#{subject},#{content},#{pwd},
		      SYSDATE, 0
		    )
		  </insert>
	 */
	public void boardInsert(BoardVO vo)
	{
		getSqlSession().insert("boardInsert",vo);
	}
	
	public BoardVO boardDetailData(int no)
	{
		getSqlSession().update("boardHitIncrement",no);
		return getSqlSession().selectOne("boardDetailData",no);
		
	}
}
