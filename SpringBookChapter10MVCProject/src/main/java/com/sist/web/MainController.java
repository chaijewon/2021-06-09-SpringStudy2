package com.sist.web;

import org.springframework.stereotype.Controller;
/*
 *     스프링 구조 
 *     ========
 *                              WebApplicationContext (AnnotationConfigWebApplicationContext)
 *                              (XML사용시:4버전)                  (자바=>5버전)
 *                                ==> 등록된 클래스를 관리 
 *     DispatcherServlet       ======================
 *                               ***HandlerMapping : 사용자 만든 클래스를 찾아주는 역할 
 *                                                @RequestMapping(URL) 메소드 호출 
 *                             ======================
 *                               ***HandlerAdapter : 자동형변환   ==> MovieDAO dao=(MovieDAO)app.getBean("dao");
 *                               => 형변환 패턴 (100 => 110)
 *                             ======================
 *                               @Controller ==> 사용자 정의 (여러개) => 처리 (request에 값을 담는다)
 *                                => 프로그래머가 담당 
 *                             ======================
 *                               ***ViewResolver ==> JSP찾아서 request를 전송 
 *                             ======================
 *                             
 *                             1. 스프링에서 요청처리 클래스를 찾게 설정 
 *                                ===================
 *                                @Controller => 구분 (@RequestMapping())
 *                             2. JSP를 찾기위해서는 경로명/확장자 저장 
 *                           
 *                             XML로 설정 : WebApplicationContext
 *                                       => HandlerMapping/HandlerAdapter(자동 셋팅)
 *                             Java로 설정  : AnnotationConfigWebApplicationContext
 *                                       => 자동 셋팅이 안된다 
 *                                       @EnableWebMvc 
 *                                       
 *        ==> 250page (그림) , 253page => web.xml
 *        ==> 254page => 자바로 설정 (HandlerMapping,HandlerAdapter) => @EnableWebMvc (260page)
 *            ======= 1줄 ~ 3줄 
 *        ==> 256page JSP를 찾기위한 ViewResolver설정 (경로/확장자)
 *            => setPrefix() , setSuffix()
 *               ===========   ===========
 *                 접두어(경로)     접미어(확장자)  ==> 스프링에서 JSP => JSP이름 설정 
 *        ==> ViewResolver => InternalResourceViewResolver , TilesView ==> Order=0,Order=1
 *        
 *        *** 흐름 (스프링에서 동작이 가능하게 만들기 위해서)
 *            DispatcherServlet ==> HandlerMapping ==> ViewResolver
 *                   |                   |                 |        ==> 동작할 수 있게 만드는 과정(사용자 정의)
 *                 web.xml에 등록               Model
 *                                    @Controller          application-context.xml , Config파일
 *                                                          확장자 / 경로명 지정  
 *                                    @RequestMapping        
 *                  |
 *              요청한 request를 받는다       처리하기 위해서 메소드 호츌    화면 출력을 위해 JSP찾기
 *             ====================   ==================== ===================
 *                 *.do                 Model <==> DAO              request==> JSTL/EL
 *                                      request.setAttribute()
 *        ========================================================================================
 *        정리 : 
 *             DispatcherServlet : 등록 (web.xml) : 사용자의 요청을 받는다 (HandlerMapping=>ViewResolver)
 *             <servlet>
                <servlet-name>dispatcher</servlet-name>
                <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
                DispatcherServlet 메모리 할당 => Class.forName("org.springframework.web.servlet.DispatcherServlet")
               </servlet>
               <servlet-mapping><!-- URL주소를 이용해서 Controller를 찾는다 -->
                  <servlet-name>dispatcher</servlet-name><!-- Map (키,값) -->
                  <url-pattern>*.do</url-pattern>
              </servlet-mapping> ==> URL가 반드시 .do => DispatcherServlet이 동작을 한다 
                                               *.do
                                               = 어떤 단어가 들어와도 상관없다 
                                               list.do
                                               main.do
                                               detail.do  =====> DispatcherServlet 호출
                                               ========= 구분 (요청)
               HandlerMapping ==> @RequestMapping을 가지고 있는 메소드 호출 (처리)
               ViewReslover ==> JSP를 찾아서 request(요청결과) ===> JSP 
               261page 셋팅 => 화,수 (셋팅)
               ===========
                                스프링 동작 : web.xml , pom.xml , application-context.xml(사용자 정의 클래스 등록:Mybatis)
                          ========   ======= 스프링 라이브러리
                          DispatcherServlet
                          => URL주소 찾는 방법 (*.do)
                             *.nhn , *.daum , *.sist ,*.aaa
                Model   ===> DAO   ===> JSP (JSTL/EL) : Front-End (JS)
                =====       ===== MyBatis(SQL)
                @Controller
                @RequestMapping
                
                => 1. 셋팅 , 2. 스프링 MVC 흐름 (동작순서)
                => 사이트에 필요한 DB설정 , 템플릿 
                => XML , Annotation(찾기)
                   ===
                                   등록 / 동작  / 설정파일 (순서) => 메뉴얼 ==> 반드시 스프링에서 요구하는 사항으로 제작 
                   => XML (태그/속성)이 이미 제작
                   => 어노테이션도 이미 제작 
                   => 스프링 (XML,어노테이션) => DTD
                                            등록 : 클래스마다 등록 <bean> : 라이브러리 등록 (마이바티스)
                                            패키지 단위 등록 <context:component-scan base-package=""> : 사용자 정의
                      @SessionAttribute,@CrossDomain():PORT가 틀린 경우
                                        ============================
                                         VueJS , ReactJS (JavaScript=>Spring)
                                         ================ 3000 / 8080 (연결 라인선)
                   => 어노테이션 
                       1) 클래스 메모리 할당 
                          @Component , @Service , @Controller , @RestController, @Repository
                       2) 주입 관련 
                          @Autowired , @Resource , @Inject
                       3) 공통 모듈 
                          @Aspect , @Before , @ After ...
                       4) 기타 
                          @NotNull , @Size , @NotEmpty , @Max , @Min ... 데이터 유효성
                       5) 요청 처리 
                          @RequestMapping() => GetMapping(GET)/PostMapping(POST)
                          =================
                           HTML전송 (GET/POST) => 동시 처리 
                          
                   => 마이바티스(XML,어노테이션) => DTD
 */
@Controller // Model
public class MainController {

}
