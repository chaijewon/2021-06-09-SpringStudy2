package com.sist.dao;

public class MyDAO2 {
   public void select()
   {
	   // getConnection() => before
	   System.out.println("SELECT문장 수행");
	   // disConnection() => after
	    
   }
   public void insert()
   {
	   System.out.println("INSERT문장 수행");
   }
   public void update()
   {
	   System.out.println("UPDATE문장 수행");
   }
   public void delete()
   {
	   System.out.println("DELETE문장 수행");
   }
}
