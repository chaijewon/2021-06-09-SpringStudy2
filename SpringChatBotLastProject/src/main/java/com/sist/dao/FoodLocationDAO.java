package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 *   @Autowired 
 *     =>  CONSTRUCTOR, FIELD, METHOD, PARAMETER
 *         생성자                멤버변수   메소드       매개변수 
 *         
 *     class A
 *     {
 *         @Autowired
 *         private B b; ==> FIELD
 *         
 *         @Autowired
 *         public void setB(B b) ==> METHOD
 *         {
 *         }
 *         
 *         @Autowired
 *         public A(B b) ==> CONSTRUCTOR
 *         {
 *         }
 *         
 *         
 *         public void display(@Autowired ==  PARAMETER B b)
 *         {
 *         }
 *     }
 *   
 */
@Repository // class위에서만 사용이 가능 
/*
 *    <select id="foodGetNameData" resultType="FoodLocationVO">
		    SELECT DISTINCT name FROM project_food_location
		  </select>
		  <!-- 음식점 이름에 대한 정보를 읽어 온다  -->
		  <select id="foodInfoData" resultType="FoodLocationVO" parameterType="string">
		    SELECT no,name,address,tel,type,score 
		    FROM project_food_location
		    WHERE name=#{name}
		  </select>
 */
public class FoodLocationDAO extends SqlSessionDaoSupport{

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
 
	// 음식점 명칭 읽기
	/*
	 *   SELECT
	 *     => selectOne(int , String , VO) => row가 1일때 
	 *        SELECT CEIL(COUNT(*)/10.0) FROM board ==> selectOne  
	 *     => selectList (List)  ==> row가 여러개 
	 *   INSERT
	 *     => insert()
	 *   UPDATE
	 *     => update()
	 *   DELETE
	 *     => delete()  ==> commit
	 */
	public List<String> foodGetNameData()
	{
		return getSqlSession().selectList("foodGetNameData");
	}
	
	public FoodLocationVO foodInfoData(String name)
	{
		return getSqlSession().selectOne("foodInfoData", name); // #{name}
	}
	
	/*
	 *  <select id="foodListData" resultType="FoodLocationVO">
	 */
	public List<FoodLocationVO> foodListData()
	{
		return getSqlSession().selectList("foodListData");
	}
}







