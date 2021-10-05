package com.sist.dao;
// XML , Annotation 
// XML => 버전 
// ==> 특별한 경우 (데이터 읽기 , 로그인 자동처리,로그파일 만들기,트랜잭션) => 만들어져있다 
// ==> AI (데이터 수집) 
/*
 *    AOP (횡단지향적 프로그램) => 공통모듈을 모아서 필요한 위치에 자동호출이 가능하게 만든다 
 *                         => 프록시 패턴 (대신 호출)
 *      
 *     = JoinPoint => 위치 (어느 위치에서 호출할 것인 확인)
 *       public void movieDetail()
 *       {
 *           =====> 수행하기 전  ==> Before (먼저 수행이 가능하게 만든다)
 *           try
 *           {
 *              
 *           }catch(Exception ex)
 *           {
 *              =====> catch(에러 발생) AfterThrowing
 *           }
 *           finally
 *           {
 *              =====> 무조건 수행  ==> After
 *           }
 *           
 *           ===== 정상 수행  AfterReturning
 *       }
 *       
 *       public void movieDetail()
 *       {
 *           try
 *           {    Around
 *                ===================== (O) setAutoCommit(false)
 *                  기능 처리 
 *                ===================== (O)  ==> log파일 (수행시간,트랜잭션)
 *                                           commit
 *           }catch(Exception ex){}
 *       }
 *       Before : 기능 수행전 
 *       After  : finally
 *       AfterThrowing : catch 수행 ===> 에러 출력 
 *       AfterRetruning : 정상 수행    ===> 리턴값을 받아 볼 수 있다 
 *       Around : 수행전 / 수행후 
 *       ======================= 자동 호출이 가능하게 만드는 위치 
 *     = PointCut : 호출 메소드를 지정 => 어느 메소드가 호출될때 자동으로 수행 
 *     = Advice  : JointPoint + PointCut
 *     = Aspect  : Advice 여러개를 모아서  ======> 공통 모듈 
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
 *     p.display(); ===============> 대리자 
 *     ===================================
 *     위치 지정 , 어떤 메소드 호출 처리 
 *     ========================
 *     => 모든 메소드에서 적용하는 것은 아니고 (지정)
 *     => 모든 프로그램은 공통으로 사용되는 부분 / 핵심으로 사용하는 부분
 *                    ===============   ================  스프링 (생성/소멸/공통으로 사용하는 부분은 
 *                                                        처리) ==> 핵심만 코딩하기때문에 
 *                                                                 최대한 소스를 줄인다 
 *     => OOP에서는 공통부분을 제외하고 자동 호출이 가능하게 만들 수 없다 (이 부분을 보완해서 처리:AOP)
 *        === 객체지향프로그램 (면접)
 *        === 스프링 (DI , AOP VS OOP , MVC) => XML VS Annotation
 *     => DAO 
 *         = 공통 사용
 *           getConnection()
 *           disConnection()  ===> AOP 
 *           catch() => ex.printStackTrace()
 *         = 핵심 사용
 *           SELECT , INSERT , UPDATE , DELETE ==> 
 */
public class MyDAO {
	// OOP에서는 공통으로 사용되는 기능이 있는 경우 : 메소드 만들어서 호출 
	// 메소드 : 한개의 기능을 수행해서 재사용 , 공통으로 사용되는 메소드화 => 필요시마다 사용이 가능 
	// 공통으로 사용되는 기능이 여러개 있는 경우 => 클래스로 모아서 처리 
	// ===> 자동으로 호출이 가능하게 만든다 (시스템에 의해 자동 호출 : main,service,doGet,doPost...:callback)
	// ===> 모방해서 만드는 프로그램 : AOP
	// 오라클 연결
    public void getConnection()
    {
    	System.out.println("오라클 연결");
    }
    // 오라클 해제 
    public void disConnection()
    {
    	System.out.println("오라클 해제");
    }
    
    public void select()
    {
       getConnection();
       System.out.println("SELECT: 데이터 검색=>수행"); // 핵심
       disConnection();
    }
    public void insert()
    {
    	getConnection();
        System.out.println("INSERT: 데이터 추가=>수행"); // 핵심
        disConnection();
        
    }
    public void update()
    {
    	getConnection();
        System.out.println("UPDATE: 데이터 수정=>수행"); // 핵심
        disConnection();
    }
    public void delete()
    {
    	getConnection();
        System.out.println("DELETE: 데이터 삭제=>수행"); // 핵심
        disConnection();
    }
    
}









