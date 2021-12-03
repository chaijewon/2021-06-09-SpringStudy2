package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/*
 *     메모리 할당하는 어노테이션 
 *     @Component : 일반 클래스 (Manager,Jsoup..., OpenApi)
 *     ==============================
 *     @Repository : DAO(저장소)
 *     @Service : DAO+DAO
 *     ============================== 기술면접
 *     @Controller : 화면 이동 
 *                  ===========
 *                   forward
 *                   redirect
 *                  ===========
 *     @RestController : 다른 프로그램 (자바스크립트 , Kotlin...) JSON
 *     ===================  기능 구분 
 *     
 *     기술 면접 
 *     =======
 *      자바 :  1. 객체지향의 객체란?
 *            2. static VS instance
 *            3. 오버로딩 VS 오버라이딩 (new , modify)
 *            4. 추상클래스 VS 인터페이스 
 *            5. 상속 VS 포함 
 *               === 오버라이딩은 항상 상속이 있어야 가능 (O,X) ==> 익명의 클래스 (상속없이 오버라이딩)
 *            6. 예외 처리 : 목적/정의/방법(예외복구,예외회피)
 *            7. 제네릭스: 공통 형변환
 *            8. Collection => ArrayList VS Vector (비동기화,동기화)
 *            ========================================================
 *            기타 : 싱글턴 / 팩토리 패턴 
 *     오라클 : 1. i / g / c => (i:인터넷,g:그리드,c:컴포넌트) ==> 18c
 *               ==========
 *            2. BLOB / BFILE ==> 그림 파일 저장시 이용 ==> 폴더
 *               ==== 바이너리 / 파일 형태 (4기가)
 *            3. CHAR / VARCHAR ==> (고정 바이트 / 가변 바이트)
 *            4. JOIN / SUBQUERY 
 *            5. 페이징 기법(인라인뷰) 
 *            6. 제약조건 
 *            7. View 
 *     JSP : GET/POST ===> 한글변환 
 *          ==== server.xml , setCharacterEncoding 
 *           Session VS Cookie 
 *           = MVC => 프리젠테이션 로직과 비지니스로직의 분리해서 확장성(재사용)이 좋게 만드는 프로그램 
 *                   ============= (JSP) ==== (java) 
 *     Spring : 
 *              1. Container : 클래스 관리 (수정시에 다른 클래스에 영향이 없는 클래스)
 *              2. AOP / OOP => OOP가 할 수 없는 것을 보완 한 것이다 (반복제거)
 *              3. DI : 클래스생성시 필요한 데이터 주입나 메소드호출
 *              4. 트랜잭션 처리 (AOP)
 *                 setAutoCommit(false) 
 *              5. RestController ==> Kotlin,VueJS ==> JSON (자바스크립트 객체 표현법)
 *     면접 : 정의 / 부연 
 *           모르는 질문 => 잘 모르겠다 (웹쪽으로 공부=> 질문 (웹으로 돌린다)
 *     VueJS =======>  가상돔  / MVVM 
 *     기술면접 : 기술(기본) / 인성(기술) => 지원하는 회사 기술 (사이트) ==> 인재 채용 , 사용기술 
 *     ======= 계약서 
 *     1. 잠수 => 회사 면접 (전화) => 입문 => 기술 (실무) => Spring,Ajax,VueJS , Mybatis => 공통
 *                                                  SQL문장 => 길어진다 
 *     2. 당당(자신감 있게) => 잠재력  ==> 프로그래머(자신감,끈기) => 반복 
 *     
 */
// 데이터베이스 연동 
import com.sist.dao.*;
import java.util.*;
import com.sist.manager.*;
@Controller
public class BoardController {
   // 전체기능에서 DAO를 사용한다
   // 메모리 할당을 스프링에서 진행 => 스프링에 저장된 주소를 얻어 온다 (자동 주입)
   @Autowired
   private BoardDAO dao;
   
   @Autowired
   private WordManager wm; // 형태소 분석 (문자분석 => 테스트마이닝)
   
   // 사용자가 요청했을때 어떻게 처리 할지 .. => @RequestMapping(GET/POST) , @GetMapping(GET) , @PostMapping
   @GetMapping("board/list.do") // 게시판 목록을 보여 달라 요청 
   // 클라이언트가 요청할수 있는 부분 == 주소 : 해당되는 데이터만 전송 (데이터:오라클)
   // 웹(브라우저)
   public String board_list(Model model)
   {
	   List<BoardVO> list=dao.boardListData();
	   model.addAttribute("list", list);
	   return "board/list"; // 화면 이동 ==> board/list.jsp => 출력할 데이터를 보내준다 
   }
   
   @GetMapping("board/insert.do")
   public String board_insert()
   {
	   return "board/insert";
   }
   
   @PostMapping("board/insert_ok.do")
   public String board_insert_ok(BoardVO vo) // VO단위로 데이터를 받는다 (커맨드 객체)
   {
	   dao.boardInsert(vo);
	   return "redirect:list.do";
   }
   
   // 서빙 => 요청 / 응답 
   @GetMapping("board/detail.do")
   public String board_detail(int no,Model model)
   {
	   BoardVO vo=dao.boardDetailData(no);
	   List<WordVO> list=wm.wordData(vo.getContent());
	   model.addAttribute("list", list);
	   model.addAttribute("vo", vo);
	   return "board/detail";
   }
}








