package com.sist.proxy;

public class ProxyDAO {
   private MyDAO dao;
   public void getConnection()
   {
	   System.out.println("����Ŭ ����");
   }
   public void disConnection()
   {
	   System.out.println("����Ŭ ����");
   }
   public ProxyDAO(MyDAO dao)
   {
	   this.dao=dao;
   }
   public void select()
   {
	   getConnection(); // before�� �ִٸ�
	   dao.select();
	   disConnection(); // after�� �ִٸ� 
   }
}
