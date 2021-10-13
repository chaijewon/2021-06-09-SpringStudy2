<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
       *** 기술면접
       1. Container : 클래스 관리(객체생성 , 필요한 값 주입 , 객체소멸) => 객체 생명주기 
                                          |
                                       프로그램에 필요한 메소드 호출 , 값설정 (프로그램 순서) => 개발자  (결합성이 낮은 프로그램)
                                          |
                                  1. 메모리 절약(싱글턴) => 단일 객체(한개의 객체로 재사용)
                                  2. 클래스에서 수정이 될때 다른 클래스에 영향을 미치지 않는 프로그램 (POJO:관계없는 클래스 제작)
                                     A == B => A를 클래스 수정시 B클래스에서 에러 발생 
                          BeanFactory (CORE => 기본 => 메모리 할당 , DI)
                              |
                       ApplicationContext (CORE => AOP , message...)
                              |
              ====================================
              |                                 |
          GenericApplicationContext     AnnotationConfigApplicationContext (어노테이션)
              |                                 |
           WebApplicationContext        AnnotationConfigWebApplicationContext ==> MVC
      ===> 핵심 : 클래스 관리 => 필요시마다 클래스를 컨테이너로부터 찾는다 (DL) => getBean("id명")
      2. DI (주입) => 객체 생성(필요할때 바로 사용)시에 동작에 필요한 멤보변수에 값을 주입 
                     = setter메소드를 이용한 주입
                     = 생성자 매개변수를 통해서 주입 
                     = 자동 주입(객체 주소만 주입) => @Autowired , @Inject
      3. AOP(공통모듈/핵심모듈) => 공통으로 사용되는 프로그램 소스를 저장했다가 필요한 위치에서 재사용이 가능 
         => 필요한 위치 확인 
            1) 어떤 메소드에 사용 할지 (PointCut) => 메소드설정 
            2) 어떤 위치에서 호출 (JoinPoint) 
               =========================
               1. Before
               2. After
               3. After-Throwing
               4. After-Retruning
               5. Around
                     예)
                     
           public void insert()
           {
              @Before => getConnection()
              try
              {
                  @Around conn.setAutoCommit(false)
                                       핵심 코딩  insert(입고),insert(재고)...
                  @Around conn.commit()
              }catch(Exception ex)
              {
                  @After-Throwing
                  conn.rollback()
              }
              finally
              {
                  @After
                  conn.setAutoCommit(true)
                  disConnection()
              }
              return; @After-Returning (거의 사용 빈도 없다) => 확인 => log파일 
           }
           
       4. MVC => 스프링 / 스트럿츠 / JSP / 마이플래폼 / 제우스 
                      공기업/금융권 = 스프링 (전자정부 프레임워크)
                      학교 = ASP.NET (자바 => C# , JSP => ASP , 오라클 => MS-SQL)
                      대기업   (ReactJS) 
          Spring에 지원(XML이나 어노테이션을 이용해서 동작 방법을 알려준다) => 동작만 수행 => 메뉴얼을 직접 만들어서 
                                                                스프링에서 동작이 가능하게 만든다 (기본 틀만 제공)
               => 컴퓨터 
                  Spring (메인보드) 
                    = CPU
                    = 메모리 
                    = 하드드스크
          ============
          DispatcherServlet : 요청 받기 => 규칙 (반드시 web.xml등록) => 요청을 받는 => 톰캣 => DispatcherServlet전송
                                                           톰캣 => 서블릿을 호출 (HttpServletRequest에 요청값을 실어서 전송)
          ============
          HandlerMapping : Model 찾기 => 요청처리 구분 @RequestMapping(), @GetMapping() @PostMapping()
          ============
          ViewResolver : JSP 찾기 => 반드시 (경로명/확장자) => application-context.xml
          ============
                      개발자 제작
          ============
            Model => @Controller => 요청에 대한 처리 , 결과값을 JSP 전송한 프로그램을 제작(개발자)
            ===== ~DAO,~Controller,~VO,~Manager,~Service
          ============
            View => JSP => 결과값을 받아서 출력만 한다 
          ============
           *** MyBatis는 별도 (DAO라이브러리) 스프링과 관련이 없다 (마이바티스에 필요한 클래스관리)
               
            
 --%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${bCheck==true }">
  <c:redirect url="list.do?page=${page }"/>
</c:if>
<c:if test="${bCheck==false }">
  <script>
  alert("비밀번호가 틀립니다!!");
  history.back();
  </script>
</c:if>







