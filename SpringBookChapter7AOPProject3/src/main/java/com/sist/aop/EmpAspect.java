package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmpAspect {
  /*
   *    execution(public * ��Ű��.Ŭ������.())
   *              =========
   *                * => public ������(*=> ��� ������)
   *               Ŭ������.()   => Ŭ������.*(..) => ��� �޼ҵ� (�Ű������� ������� ����)
   *                           => Ŭ������.set*(int) => set���� �����ϴ� �żҵ� �Ű����� int)
   *                                     set(int no) => O
   *                                     set(String name) => X
   *                                     set*(String) => String
   *                                     set*(int,..) ==> aop������ �޼ҵ带 ã�� ���� 
   *                       ��Ű�� 
   *                        => com.sist.dao.EmpDAO 
   *                        => com...EmpDAO
   *                        => com..dao.EmpDAO
   *                        => com.sist..EmpDAO   ===> 169page ,159page �߿� (�׸�)
   */
  @Pointcut("execution(* com.sist.dao.EmpDAO.*(..))")
  public void publicTarget()
  {
	  
  }
  /*@Before("beforeMethod()")
  public void before()
  {
	  System.out.println("Before����");
  }*/
  //@Around("within(com.sist.dao.EmpDAO)")
  @Around("execution(* com.sist.dao.EmpDAO.*(..))")
  // EmpDAO�� ������ �ִ� ��� �޼ҵ忡 ���� , ����(execution())
  /*
   *       Throwable
   *       =========
   *          |
   *     ===============
   *     Error      Exception
   */
  public Object around(ProceedingJoinPoint jp) throws Throwable// log���� (����)
  {
	   Object obj=null;
	   long start=System.currentTimeMillis();
	   System.out.println(jp.getSignature().getName()+" ȣ����...");
	   obj=jp.proceed(); // �޼ҵ� ȣ�� 
	   System.out.println(jp.getSignature().getName()+" ȣ�� �Ϸ�...");
	   long end=System.currentTimeMillis();
	   System.out.println("�޼ҵ� ����ð�:"+(end-start));
	   // �ҽ�����(�����丵) => ���� ����Ʈ 
	   return obj;
  }
  /*
   *  ===================
   *   @Before : �ٸ� ���� ���� (NodeJS,���̽� ���)
   *   @After  : ���� ���� ����   =====================> DB(getConnection/disConnection)
   *  ===================
   *   @After-Returning : �������� ������� Ȯ�� 
   *   @After-Throwing : ����ó�� => ȭ�� �̵� 
   *   @Around : log���� 
   */
}










