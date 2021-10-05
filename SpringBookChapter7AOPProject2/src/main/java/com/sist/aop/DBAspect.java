package com.sist.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import com.sist.dao.*;
@Component
@Aspect  // ���� ��� (���) => ��� �޼ҵ忡�� ����ϴ� ��� 
public class DBAspect {
   @Autowired
   private DBConnection conn;
   
   // getConnection/disConnection
   @Before("execution(* com.sist..StudentDAO.*(..))")
   public void before()
   {
	   System.out.println("####### Before ���� ######");
	   conn.getConnection();
   }
   /*@After("execution(* com.sist.dao.StudentDAO.*(..))")
   public void after()
   {
	   System.out.println("####### After ���� ######");
	   conn.disConnection();
   }*/
   @After("within(com.sist.dao.StudentDAO)")
   public void after()
   {
	   System.out.println("####### After ���� ######");
	   conn.disConnection();
   }
   @Around("execution(* com.sist.dao.StudentDAO.*(..))")
   public Object around(ProceedingJoinPoint jp) throws Throwable
   {
	   System.out.println("####### Around ���� ######");
	   Object obj=null;
	   long start=System.currentTimeMillis();
	   System.out.println(jp.getSignature().getName()+"=>ȣ����...");
	   System.out.println("====== Ȯ�� (����͸�) log���� ======");
	   System.out.println("1. �Ű����� Ȯ��:"+Arrays.toString(jp.getArgs()));
	   System.out.println("2. �޼ҵ�� Ȯ��:"+jp.getSignature().getName());
	   System.out.println("3. Advice���� Ȯ��:"+jp.getKind());//Around
	   obj=jp.proceed(); // �޼ҵ� ȣ�� 
	   System.out.println(jp.getSignature().getName()+"=>ȣ����...");
	   long end=System.currentTimeMillis();
	   System.out.println(jp.getSignature().getName()+"=>���� �ð�:"+(end-start));
	   return obj;
   }
   //@AfterReturning :  ���� ����� => ����� Ȯ��
   @AfterReturning(value="execution(* com.sist.dao.StudentDAO.*(..))",returning="val")
   public void afterReturning(JoinPoint jp,Object val)
   {
	   System.out.println("####### AfterReturning ���� #########");
	   //System.out.println("���ϰ�:"+val);
	   String method=jp.getSignature().getName();
	   if(method.equals("studentListData"))
	   {
		   List<StudentVO> list=(List<StudentVO>)val;
		   for(StudentVO vo:list)
		   {
			   System.out.println(vo.getHakbun()+" "
	        			+vo.getName()+" "
	        			+vo.getKor()+" "
	        			+vo.getEng()+" "
	        			+vo.getMath());
		   }
	   }
	   else if(method.equals("studentDetailData"))
	   {
		   StudentVO vo=(StudentVO)val;
		   System.out.println("============ �󼼺��� ============");
		   System.out.println(vo.getHakbun()+" "
       			+vo.getName()+" "
       			+vo.getKor()+" "
       			+vo.getEng()+" "
       			+vo.getMath());
	   }
   }
   //@AfterThrowing  : catch�� ����(�����߻��� ó��)
   @AfterThrowing(value="execution(* com.sist.dao.StudentDAO.*(..))",throwing="ex")
   public void afterThrowing(JoinPoint jp,Exception ex)
   {
	   System.out.println("####### AfterThrowing ########");
	   ex.printStackTrace();
   }
   //158������ : ���� ��ü���� �������� ������ �� �ִ� ����� �и��ؼ� ���뼺�� �����ִ� ���α׷� ��� 
   //DAO���� ���� => getConnection/disConnection => catch , finally => ����
   /*
    *   ���α׷� : ������ (���� ��Ƽ� ����) => �ʿ�ø��� ����� ���� (����)
    */
   /*
    *   159page 
    *   AOP ��� 
    *   Advice : JoinPoint+PointCut
    *   JoinPoint : �������� �����ϴ� ����� ȣ�� ���� 
    *               ������ : Before
    *               ����� : After
    *               ���Ʒ� : Around
    *               =======(O) setAutoCommit(false)
    *               ���
    *               =======(O) commit()
    *               ������� : After-Returning
    *               ���� : After-Throwing
    *               
    *   PointCut : ������ �޼ҵ� ����
    *   Aspect   : �������� ���Ǵ� �������� ��Ƽ� ���� 
    *   Weaving  : Ÿ���� ã�� �ش� => ���Ͻ� (��� ����� �޼ҵ带 ȣ��)
    *   
    *   169page 
    *      execution("* ��Ű��.Ŭ������.�޼ҵ�(..)")
    *                               =====
    *                                 *
    *                                set*()
    *                                get*()
    *                                tx*() ==> Ʈ�������� ���� 
    *     execution("* ��Ű��.Ŭ������.�޼ҵ�(int,int)") => �Ű����� int,int 
    *     execution("* ��Ű��.Ŭ������.�޼ҵ�(String,..)") => �Ű����� int,int 
    *     
    *   169page
    *     ���� ���� 
    *     @Around
    *     @Before
    *     @After
    *     @After-Retruning
    *     
    *   AOP : Ư���� ��쿡�� ���� (������ ���� ����͸� : �α�����)
    *         ������ (������ ����=>�м�)
    *         �ٸ� ������ ���� ������ (NodeJS,���̽�(���̽�)=>AI) 
    *   => AOP�� ���� ���̺귯�� (Ʈ����� , ����)
    *   => �� => ������ ���� (JavaScript) => AOP������ ���� (�ǽð����� ����) : Ajax,ReactJS
    */
   
}







