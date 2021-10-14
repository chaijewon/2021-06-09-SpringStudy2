package com.sist.trans;
import java.util.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
public class SawonDAO {
	// setterDI
    private String url,username,password;
    private Connection conn;
    private PreparedStatement ps;
    // 생성자 DI
    public SawonDAO(String driverName)
    {
	   
    }
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(url,username,password);
		}catch(Exception ex){}
	}
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex){}
	}
	// Mybatis => 제작 (SqlSessionFactoryBean)
	// sawon등록 =>일괄처리 프로그램 (ERP,금융,공기업) => 트랜잭션 (기술면접) , 인성면접 (거리 - 포항) , 나주(한전)
	// 영화 / 맛집 / 레시피 (X) ==> 국세청 , 행정자치부 ...  
	// => AOP개념 이용 
	public void sawonInsert(SawonVO vo) // Card
	{
		// 트랜잭션 => 동시에 저장 , 동시에 취소 => 일괄처리 (오라클 => 비절차적 언어)
		try
		{
			getConnection();
			conn.setAutoCommit(false); // @Around
			String sql="INSERT INTO sawon VALUES(?,?,?)"; //CARD
			ps=conn.prepareStatement(sql);
			ps.setInt(1, 2);
			ps.setString(2, "박문수");
			ps.setString(3, "개발부");
			ps.executeUpdate(); //commit() => 정상저장 
			ps.close();
			
			sql="INSERT INTO sawon VALUES(?,?,?)";//POINT
			ps=conn.prepareStatement(sql);
			ps.setInt(1, 3);
			ps.setString(2, "심청이");
			ps.setString(3, "개발부");
			ps.executeUpdate();
			conn.commit();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			try
			{
				conn.rollback(); // @After-Throwing
			}catch(Exception e){}
		}
		finally
		{
			disConnection();
			try
			{
				conn.setAutoCommit(true); // @After 
			}catch(Exception ex){}
		}
	}
	// member등록 
	public void memberInsert(MemberSawon vo) //Point
	{
		
	}
   
}








