package com.sist.dao;
// XML , Annotation 
// XML => ���� 
// ==> Ư���� ��� (������ �б� , �α��� �ڵ�ó��,�α����� �����,Ʈ�����) => ��������ִ� 
// ==> AI (������ ����) 
/*
 *    AOP (Ⱦ�������� ���α׷�) => �������� ��Ƽ� �ʿ��� ��ġ�� �ڵ�ȣ���� �����ϰ� ����� 
 *                         => ���Ͻ� ���� (��� ȣ��)
 *      
 *     = JoinPoint => ��ġ (��� ��ġ���� ȣ���� ���� Ȯ��)
 *       public void movieDetail()
 *       {
 *           =====> �����ϱ� ��  ==> Before (���� ������ �����ϰ� �����)
 *           try
 *           {
 *              
 *           }catch(Exception ex)
 *           {
 *              =====> catch(���� �߻�) AfterThrowing
 *           }
 *           finally
 *           {
 *              =====> ������ ����  ==> After
 *           }
 *           
 *           ===== ���� ����  AfterReturning
 *       }
 *       
 *       public void movieDetail()
 *       {
 *           try
 *           {    Around
 *                ===================== (O) setAutoCommit(false)
 *                  ��� ó�� 
 *                ===================== (O)  ==> log���� (����ð�,Ʈ�����)
 *                                           commit
 *           }catch(Exception ex){}
 *       }
 *       Before : ��� ������ 
 *       After  : finally
 *       AfterThrowing : catch ���� ===> ���� ��� 
 *       AfterRetruning : ���� ����    ===> ���ϰ��� �޾� �� �� �ִ� 
 *       Around : ������ / ������ 
 *       ======================= �ڵ� ȣ���� �����ϰ� ����� ��ġ 
 *     = PointCut : ȣ�� �޼ҵ带 ���� => ��� �޼ҵ尡 ȣ��ɶ� �ڵ����� ���� 
 *     = Advice  : JointPoint + PointCut
 *     = Aspect  : Advice �������� ��Ƽ�  ======> ���� ��� 
 *     
 *     public class DAO
 *     {
 *         public void display()
 *         {
 *            System.out.println("display Call...");
 *         }
 *     }
 *     public class Proxy
 *     {
 *          DAO dao;
 *          public Proxy(DAO dao)
 *          {
 *             this.dao=dao;
 *          }
 *          public void display()
 *          {
 *             System.out.println("Before");
 *             dao.display();
 *             System.out.println("After");
 *          }
 *     }
 *     
 *     DAO dao=new DAO();
 *     dao.display();
 *     Proxy p=new Proxy(dao);
 *     p.display(); ===============> �븮�� 
 *     ===================================
 *     ��ġ ���� , � �޼ҵ� ȣ�� ó�� 
 *     ========================
 *     => ��� �޼ҵ忡�� �����ϴ� ���� �ƴϰ� (����)
 *     => ��� ���α׷��� �������� ���Ǵ� �κ� / �ٽ����� ����ϴ� �κ�
 *                    ===============   ================  ������ (����/�Ҹ�/�������� ����ϴ� �κ��� 
 *                                                        ó��) ==> �ٽɸ� �ڵ��ϱ⶧���� 
 *                                                                 �ִ��� �ҽ��� ���δ� 
 *     => OOP������ ����κ��� �����ϰ� �ڵ� ȣ���� �����ϰ� ���� �� ���� (�� �κ��� �����ؼ� ó��:AOP)
 *        === ��ü�������α׷� (����)
 *        === ������ (DI , AOP VS OOP , MVC) => XML VS Annotation
 *     => DAO 
 *         = ���� ���
 *           getConnection()
 *           disConnection()  ===> AOP 
 *           catch() => ex.printStackTrace()
 *         = �ٽ� ���
 *           SELECT , INSERT , UPDATE , DELETE ==> 
 */
public class MyDAO {
	// OOP������ �������� ���Ǵ� ����� �ִ� ��� : �޼ҵ� ���� ȣ�� 
	// �޼ҵ� : �Ѱ��� ����� �����ؼ� ���� , �������� ���Ǵ� �޼ҵ�ȭ => �ʿ�ø��� ����� ���� 
	// �������� ���Ǵ� ����� ������ �ִ� ��� => Ŭ������ ��Ƽ� ó�� 
	// ===> �ڵ����� ȣ���� �����ϰ� ����� (�ý��ۿ� ���� �ڵ� ȣ�� : main,service,doGet,doPost...:callback)
	// ===> ����ؼ� ����� ���α׷� : AOP
	// ����Ŭ ����
    public void getConnection()
    {
    	System.out.println("����Ŭ ����");
    }
    // ����Ŭ ���� 
    public void disConnection()
    {
    	System.out.println("����Ŭ ����");
    }
    
    public void select()
    {
       getConnection();
       System.out.println("SELECT: ������ �˻�=>����"); // �ٽ�
       disConnection();
    }
    public void insert()
    {
    	getConnection();
        System.out.println("INSERT: ������ �߰�=>����"); // �ٽ�
        disConnection();
        
    }
    public void update()
    {
    	getConnection();
        System.out.println("UPDATE: ������ ����=>����"); // �ٽ�
        disConnection();
    }
    public void delete()
    {
    	getConnection();
        System.out.println("DELETE: ������ ����=>����"); // �ٽ�
        disConnection();
    }
    
}









