package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Component;

public class DBConnection {
   private Connection conn;
   private PreparedStatement ps;
   private String url,username,password; //DI (������ => XML�� ���ؼ� ���� ����)
   public DBConnection(String driverName)
   {
	   try
	   {
		   Class.forName(driverName);
	   }catch(Exception ex){}
   }
   // ���� => �������� ��� => DAO�� ��� �޼ҵ忡�� ���� 
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Connection getConn() {
		return conn;
	}
	public PreparedStatement getPs() {
		return ps;
	}
	   
}
