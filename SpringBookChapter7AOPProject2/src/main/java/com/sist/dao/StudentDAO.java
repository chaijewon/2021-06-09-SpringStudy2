package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.sql.*;
@Repository
public class StudentDAO {
	@Autowired
    private DBConnection dbConn;
	private Connection conn;
	private PreparedStatement ps;
	//1. 전체 목록 출력 
	public List<StudentVO> studentListData(int page)
	{
		List<StudentVO> list=new ArrayList<StudentVO>();
		try
		{
			// getConnection => Before
			conn=dbConn.getConn();
			String sql="SELECT * FROM mybatis_student0";
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
				list.add(vo);
			}
			rs.close();
			ps.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		// finally { disConnection() => After}
		return list;
	}
	public StudentVO studentDetailData(int hakbun)
	{
		StudentVO vo=new StudentVO();
		try
		{
			conn=dbConn.getConn();
			String sql="SELECT * FROM mybatis_student0 WHERE hakbun="+hakbun;
			
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setHakbun(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setKor(rs.getInt(3));
			vo.setEng(rs.getInt(4));
			vo.setMath(rs.getInt(5));
			rs.close();
			ps.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vo;
	}
}





