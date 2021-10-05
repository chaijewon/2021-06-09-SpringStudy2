package com.sist.dao2;

import org.springframework.stereotype.Repository;
// DAO�޸� �Ҵ� 
/*
 *   ���� : ã��
 *   @Component : �Ϲ� Ŭ����
 *   @Repository : DAO
 *   @Controller : Model
 *   @RestController : Model(JSON, �Ϲ�)
 *   
 *   ==> MyBatis,Hibernate
 *               save() => �ڵ����� insert
 *               modify() => �ڵ����� update()
 *   ==> ���� (��������) => �ٸ� ������ (��� �߰�)
 */
@Repository
public class MyDAO {
   public String select()
   {
	   System.out.println("SELECT���� ����");
	   return "����Ŭ SELECT���� ���� �Ϸ�";
   }
   public void insert(int no)
   {
	   System.out.println("INSERT���� ����:"+no+"���� ����");
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
