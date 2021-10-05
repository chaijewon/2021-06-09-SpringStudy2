package com.sist.proxy;

public class ProxyDAO {
   private MyDAO dao;
   public void getConnection()
   {
	   System.out.println("오라클 연결");
   }
   public void disConnection()
   {
	   System.out.println("오라클 해제");
   }
   public ProxyDAO(MyDAO dao)
   {
	   this.dao=dao;
   }
   public void select()
   {
	   getConnection(); // before가 있다면
	   dao.select();
	   disConnection(); // after가 있다면 
   }
}
