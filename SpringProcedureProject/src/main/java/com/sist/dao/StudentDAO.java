package com.sist.dao;
import java.util.*;

import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

import java.sql.*;
@Repository
public class StudentDAO {
    private Connection conn;
    private PreparedStatement ps;// SQL문장 전송 
    private CallableStatement cs;// Procedure 호출 
    private final String URL="jdbc:oracle:thin:@211.238.142.181:1521:XE";
    // SqlSessionFactoryBean
    // 드라이버 
    public StudentDAO()
    {
    	try
    	{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    	}catch(Exception ex){}
    }
    // 연결
    public void getConnection()
    {
    	try
    	{
    		conn=DriverManager.getConnection(URL,"hr","happy");
    	}catch(Exception ex){}
    }
    // 해제
    public void disConnection()
    {
    	try
    	{
    		if(ps!=null) ps.close();
    		if(cs!=null) cs.close();
    		if(conn!=null) conn.close();
    	}catch(Exception ex){}
    }
    
    // 목록 읽기
    public List<StudentVO> studentListData()
    {
    	List<StudentVO> list=new ArrayList<StudentVO>(); // selectList()
    	try
    	{
    		getConnection();
    		String sql="SELECT hakbun,name,kor,eng,math,studentGetTotal(hakbun),studentGetAvg(hakbun) FROM pro_student";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			StudentVO vo=new StudentVO();
    			vo.setHakbun(rs.getInt(1));
    			vo.setName(rs.getString(2));
    			vo.setKor(rs.getInt(3));
    			vo.setEng(rs.getInt(4));
    			vo.setMath(rs.getInt(5));
    			vo.setTotal(rs.getInt(6));
    			vo.setAvg(rs.getDouble(7));
    			list.add(vo);
    		}
    		rs.close();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    	return list;
    }
    // 추가  Call studentInsert('홍길동',80,90,89)
    public void studentInsert(StudentVO vo)
    {
    	try
    	{
    		getConnection();
    		String sql="{CALL studentInsert(?,?,?,?)}";
    		cs=conn.prepareCall(sql); 
    		// ?에 값을 채워서 실행 
    		cs.setString(1, vo.getName());
    		cs.setInt(2, vo.getKor());
    		cs.setInt(3, vo.getEng());
    		cs.setInt(4, vo.getMath());
    		cs.executeQuery(); // 실행 요청 
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    }
    // 상세보기 
    /*
     * CREATE OR REPLACE PROCEDURE studentDetailData(
		   phakbun pro_student.hakbun%TYPE,
		   pname OUT pro_student.name%TYPE,
		   pkor OUT pro_student.kor%TYPE,
		   peng OUT pro_student.eng%TYPE,
		   pmath OUT pro_student.math%TYPE
		)
     */
    public StudentVO studentDetailData(int hakbun)
    {
    	StudentVO vo=new StudentVO();
    	try
    	{
    		getConnection();
    		String sql="{CALL studentDetailData(?,?,?,?,?)}";
    		cs=conn.prepareCall(sql);
    		cs.setInt(1,hakbun);
    		cs.registerOutParameter(2, OracleTypes.VARCHAR);
    		cs.registerOutParameter(3, OracleTypes.INTEGER);
    		cs.registerOutParameter(4, OracleTypes.INTEGER);
    		cs.registerOutParameter(5, OracleTypes.INTEGER);
    		cs.executeQuery();
    		// 값을 메모리에서 가지고 온다 
    		vo.setName(cs.getString(2));
    		vo.setKor(cs.getInt(3));
    		vo.setEng(cs.getInt(4));
    		vo.setMath(cs.getInt(5));
    		// CURSOR => ResultSet 처리 
    		
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    	return vo;
    }
    
    // 삭제  CREATE OR REPLACE PROCEDURE studentDelete(vhakbun pro_student.hakbun%TYPE)
    public void studentDelete(int hakbun)
    {
    	try
    	{
    		getConnection();
    		String sql="{CALL studentDelete(?)}";
    		cs=conn.prepareCall(sql);
    		cs.setInt(1, hakbun);
    		cs.executeQuery();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    }
    
    // 수정 : 
    /*
     *  CREATE OR REPLACE PROCEDURE studentUpdate(
	      vhakbun pro_student.hakbun%TYPE,
	      vname pro_student.name%TYPE,
	      vkor pro_student.kor%TYPE,
	      veng pro_student.eng%TYPE,
	      vmath pro_student.math%TYPE)
     */
    public void studentUpdate(StudentVO vo)
    {
    	try
    	{
    		getConnection();
    		String sql="{CALL studentUpdate(?,?,?,?,?)}";
    		cs=conn.prepareCall(sql);
    		cs.setInt(1, vo.getHakbun());
    		cs.setString(2, vo.getName());
    		cs.setInt(3, vo.getKor());
    		cs.setInt(4, vo.getEng());
    		cs.setInt(5, vo.getMath());
    		cs.executeQuery();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    }
    
}






