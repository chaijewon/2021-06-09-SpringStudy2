package com.sist.dao;

public class MyDAO2 {
   public void select()
   {
	   // getConnection() => before
	   System.out.println("SELECT���� ����");
	   // disConnection() => after
	    
   }
   public void insert()
   {
	   System.out.println("INSERT���� ����");
   }
   public void update()
   {
	   System.out.println("UPDATE���� ����");
   }
   public void delete()
   {
	   System.out.println("DELETE���� ����");
   }
}
