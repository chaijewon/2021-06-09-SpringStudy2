package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Component;

public class DBConnection {
   private Connection conn;
   private PreparedStatement ps;
   private String url,username,password; //DI (스프링 => XML을 통해서 값을 주입)
   public DBConnection(String driverName)
   {
	   try
	   {
		   Class.forName(driverName);
	   }catch(Exception ex){}
   }
   // 연결 => 공통으로 사용 => DAO의 모든 메소드에서 적용 
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
