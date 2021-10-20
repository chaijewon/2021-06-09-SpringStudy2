SELECT * FROM project_food_location ORDER BY no ASC;
-- ORDER BY 대신 INDEX_ASC, INDEX_DESC
SELECT /*+ INDEX_ASC(project_food_location pfl_no_pk)*/ * FROM project_food_location;
SELECT /*+ INDEX_DESC(project_food_location pfl_no_pk)*/ * FROM project_food_location;
-- INDEX (PK,UNIQUE) 자동설정 (자주 변경 , 데이터 작은 경우)
-- 1. 댓글 (PL/SQL) => 댓글 사용 => 공통으로 사용이 가능 (오라클에 함수를 제작한다)
/*
      오라클 PL/SQL : Procedure / Function / Trigger
            PL => Procedural Language (PROCEDURE를 만들때 사용하는 언어)
      함수 제작시 
      ***PROCEDURE : 리턴형이 없는 함수 (기능처리)  => 호출 (void)
      ***FUNCTION : 리턴형이 있는 함수 => 내장함수 (substr,trim,nvl) => SELECT 
      ***TRIGGER : 자동처리 (이벤트(INSERT/DELETE/UPDATE)) => 다른 테이블에 자동으로 설정 (물류관리,인사관리...:ERP)
                   입고(insert) => 재고 (insert/update)
                   출고(insert) => 재고 (update/delete) 
                   은행 (입금) => 통장
                   카드  => 포인트 , 상품구매 => 포인트 
                   =================================
      PL/SQL을 이용해서 PROCEDURE를 제작하기 위해서는 형식, 변수 (매개변수)
      변수 : 스칼라변수 : NUMBER , VARCHAR2 , DATE ... (TABLE에서 사용 변수)
                        age NUMBER , name VARCHAR2(10)..
             TYPE     : 테이블명.컬럼명%TYPE   => emp.ename%TYPE
             ROWTYPE  : 테이블명%Rowtype => emp%ROWTYPE => emp테이블이 가지고 있는 모든 컬럼의 데이터형을 읽어 온다 
                        => ~VO
             RECORD  : JOIN , 데이터형을 사용자정의로 제작 
             CURSOR : Collection 
      연산자 : 오라클에서 제공하는 연산자 (산술연산자,비교연산자,논리연산자 , NOT , NULL , BETWEEN~AND , LIKE)
      제어문 : 1. IF , IF~ELSE , IF~ ELSIF ~ ELSIF ELSE 
              2. FOR , LOOP , WHILE 
              
      형식
      DECLARE 
        변수 선언 
        procedure : CREATE PROCEDURE pro_name(매개변수....) 
                    => Call By Value (일반변수) => IN (name IN VARCHAR2(20))
                    => Call By Reference (변수를 설정 => 데이터를 읽기 온다) => C언어 (포인터) : 변수의 주소값을 넣어주고 값을 채운다 
                       OUT (name OUT VARCHAR2(20))
                    => INOUT 
        function  : CREATE FUNCTION func_name(매개변수....) RETURN VARCHAR2
     ================================== 
      BEGIN
        기능 처리 (SQL)
        EXECEPTION ex의 종류 
      END;
      
      => Error : show error 
*/
-- 재사용 (자바,오라클,코틀린) => 메소드(함수) => 자바(클래스안에서 설정) => 클래스안에 있는 기능 (메소드)
/*
     = 자바(WEB)
     class clsName
     {
        public 리턴형 메소드명(매개변수...)
        {
        }
     }
     = Kotlin(모바일)
     fun 메소드명:리턴형
     {
     }
     = C언어(임베디드)
     리턴형 함수명(){}
     
     = 오라클 
     CREATE PROCEDURE pro_name(매개변수)
     IS
     BEGIN
      
     END;
     = 파이썬 (AI) => 자바연동 (자이썬)
*/
-- 단독으로 기능 (함수) , 코틀린 (클래스안 멤버메소드 설정) , 전역 함수 
/*
     오라클 
       DML : 데이터 조작 
         SELECT/DELETE/INSERT/UPDATE => 초급 (기초)
         ====== 연산자 / 내장함수 
         SELECT : JOIN / SUBQUERY , VIEW , SEQUENCE  => 중급
         =================================================== 입문 요구 사항 
         PL/SQL,INDEX => 고급 
     스프링 : Container , DI , AOP , MVC => 초급 (요구사항) 
             webSocket , JSON => 중급 
*/
-- PL/SQL 변수 사용방법 
SET SERVEROUTPUT ON;
DECLARE --선언부
  -- page 572 - 573
  -- 스칼라변수 선언 (크기) => 실제 테이블의 컬럼의 데이터형 사용 %TYPE
  vename VARCHAR2(20);
  vjob VARCHAR2(20);
  vhiredate DATE;
  vsal NUMBER(7,2);
BEGIN -- 구현부
  --변수에 값을 설정 
  SELECT ename,job,hiredate,sal INTO vename,vjob,vhiredate,vsal -- 559page(PL/SQL에서 SELECT명령어로 데이터 조회후 변수값 설정
  FROM emp
  WHERE empno=7788;
  -- 변수 출력 
  DBMS_OUTPUT.PUT_LINE('****** 실행결과 ********'); --System.out.println()
  DBMS_OUTPUT.PUT_LINE('이름:'||vename);
  DBMS_OUTPUT.PUT_LINE('직위:'||vjob);
  DBMS_OUTPUT.PUT_LINE('입사일:'||vhiredate);
  DBMS_OUTPUT.PUT_LINE('급여:'||vjob);
END;
/
-- 참조변수 (%TYPE,%ROWTYPE)
-- %TYPE 
--DESC emp;
/*
    EMPNO    NOT NULL NUMBER(4)    
    ENAME             VARCHAR2(10) 
    JOB               VARCHAR2(9)  
    MGR               NUMBER(4)    
    HIREDATE          DATE         
    SAL               NUMBER(7,2)  
    COMM              NUMBER(7,2)  
    DEPTNO            NUMBER(2)   
*/
-- 참조변수 => %TYPE (실제 테이블의 컬럼 데이터형을 읽어올때 사용) 579page
DECLARE
  vename emp.ename%TYPE; -- VARCHAR2(10)
  vjob emp.job%TYPE; -- VARCHAR2(9)  
  vhiredate emp.hiredate%TYPE; -- DATE   
  vsal emp.sal%TYPE; -- NUMBER(7,2)
  -- 한개만 받을 수 있다 => 여러개의 값을 동시에 받는다 : CURSOR (ArrayList)
BEGIN
  -- 구현부 : 데이터를 SQL을 통해서 값을 변수에 대입후 기능을 수행 (SELECT => System.out.println())
  SELECT ename,job,hiredate,sal INTO vename,vjob,vhiredate,vsal
  FROM emp
  WHERE empno=7788;
  -- 변수 출력 
  DBMS_OUTPUT.PUT_LINE('****** 실행결과 ********'); --System.out.println()
  DBMS_OUTPUT.PUT_LINE('이름:'||vename);
  DBMS_OUTPUT.PUT_LINE('직위:'||vjob);
  DBMS_OUTPUT.PUT_LINE('입사일:'||vhiredate);
  DBMS_OUTPUT.PUT_LINE('급여:'||vjob);
END;
/
-- 참조 타입 (전체 데이터 설정 => %ROWTYPE (자바의 VO) => 테이블의 컬럼을 전체 읽어 온다)
/*
     필요한 데이터를 한개를 읽어 온다 : %TYPE
     테이블에 있는 데이터를 전체를 한개만 읽어 온다 :  %ROWTYPE
     여러개의 테이블에서 필요한 데이터를 읽어 온다 (JOIN) : RECORD 
     ======================================================== 단일(1row)
     여러줄을 읽어 온다 : CURSOR
     
     댓글 
      번호 / 참조번호 (맛집,여행(명소,호텔,자연),게시판 , 영화....),카테고리 번호
      아이디 , 이름 , 댓글 , 작성일 
      ==================================================================== 1개의 댓글만 읽는다 (%ROWTYPE)
*/
-- %ROWTYPE 580page
DECLARE
   vemp emp%ROWTYPE;
BEGIN
   SELECT * INTO vemp
   FROM emp
   WHERE empno=7788;
   -- 변수 출력 
  DBMS_OUTPUT.PUT_LINE('****** 실행결과 ********'); --System.out.println()
  DBMS_OUTPUT.PUT_LINE('사번:'||vemp.empno);
  DBMS_OUTPUT.PUT_LINE('이름:'||vemp.ename);
  DBMS_OUTPUT.PUT_LINE('직위:'||vemp.job);
  DBMS_OUTPUT.PUT_LINE('사수:'||vemp.mgr);
  DBMS_OUTPUT.PUT_LINE('입사일:'||vemp.hiredate);
  DBMS_OUTPUT.PUT_LINE('급여:'||vemp.sal);
  DBMS_OUTPUT.PUT_LINE('성과급:'||vemp.comm);
  DBMS_OUTPUT.PUT_LINE('부서번호:'||vemp.deptno);
END;
/
-- 복합변수 => 단일행만 받을 수 있다 RECORD (JOIN일 경우) - 584page
/*
     사용자 정의 데이터형 
     TYPE 데이터형명 IS RECORD
     (
         empno emp.empno%TYPE,
         ename emp.ename%TYPE,
         job   emp.job%TYPE,
         hiredate emp.hiredate%TYPE,
         sal   emp.sal%TYPE,
         dname dept.dname%TYPE,
         loc   dept.loc%TYPE,
         grade salgrade.grade%TYPE
     );
*/
DECLARE
     TYPE emp_dept IS RECORD
     (
         empno emp.empno%TYPE,
         ename emp.ename%TYPE,
         job   emp.job%TYPE,
         hiredate emp.hiredate%TYPE,
         sal   emp.sal%TYPE,
         dname dept.dname%TYPE,
         loc   dept.loc%TYPE,
         grade salgrade.grade%TYPE
     );
     ed emp_dept;
BEGIN
     SELECT empno,ename,job,hiredate,sal,dname,loc,grade INTO ed
     FROM emp,dept,salgrade
     WHERE emp.deptno=dept.deptno 
     AND sal BETWEEN losal AND hisal
     AND ename='KING';
     -- 실행 결과 
     DBMS_OUTPUT.PUT_LINE('***** 결과값 *****');
     DBMS_OUTPUT.PUT_LINE('사번:'||ed.empno);
     DBMS_OUTPUT.PUT_LINE('이름:'||ed.ename);
     DBMS_OUTPUT.PUT_LINE('직위:'||ed.job);
     DBMS_OUTPUT.PUT_LINE('입사일:'||ed.hiredate);
     DBMS_OUTPUT.PUT_LINE('급여:'||ed.sal);
     DBMS_OUTPUT.PUT_LINE('부서명:'||ed.dname);
     DBMS_OUTPUT.PUT_LINE('근무지:'||ed.loc);
     DBMS_OUTPUT.PUT_LINE('등급:'||ed.grade);
END;
/
-- CURSOR (FOR을 이용한다)
/*
    연산자 
      산술연산자 : + , - , * , /(실수)
      비교연산자 : = , <> (!=) , < , > , <= ,>=
      논리연산자 : AND , OR , NOT
      NULL 연산자 : IS NULL , IS NOT NULL,
      BETWEEN : 기간, 범위 (AND까지 포함) ===>  >= AND <=
      LIKE : %(여러글자) , _(한글자)
      IN : OR가 여러개ㅔ 일때 사용하는 연산자 
      =================================== SQL문장에서 사용하는 연산자 
    제어문 
    조건문 / 반복문 
    조건문 
    1. 단일 조건문 (591page)
       형식)
            IF (조건문) THEN 
              처리 
            END IF;
    2. 선택 조건문 (592page)
       형식)
            IF(조건문) THEN
              처리 
            ELSE
              처리 
            END IF;
   3. 다중 조건문 (593page)
      형식)
            IF(조건문) THEN
               처리 
            ELSIF (조건문) THEN
               처리
            ELSIF (조건문) THEN
               처리
            ELSIF (조건문) THEN
               처리
            ELSE 
               처리 
            END IF;
*/
-- 조건문 처리 
DECLARE
  vempno emp.empno%TYPE:=0;
  vdeptno emp.deptno%TYPE:=0;
  vdname dept.dname%TYPE:=''; -- 변수 초기화 ( := )
  vename emp.ename%TYPE:='';
BEGIN
  SELECT ename,deptno INTO vename,vdeptno
  FROM emp
  WHERE empno=&empno; -- Scanner 
  
  -- 조건문 처리 
  IF (vdeptno=10) THEN
    vdname:='개발부';
  END IF;
  IF (vdeptno=20) THEN
    vdname:='총무부';
  END IF;
  IF (vdeptno=30) THEN
    vdname:='영업부';
  END IF;
  -- 출력 
  DBMS_OUTPUT.PUT_LINE('***** 결과 *****');
  DBMS_OUTPUT.PUT_LINE(vename||'님의 부서는 '||vdname||'입니다'); -- 자바로 전송 (변수) 
END;
/
-- 선택 조건문 
DECLARE
  vempno emp.empno%TYPE;
  vename emp.ename%TYPE;
  vcomm emp.comm%TYPE;
BEGIN
  SELECT empno,ename,comm INTO vempno,vename,vcomm
  FROM emp
  WHERE empno=&empno;
  -- 조건문을 사용 
  IF (vcomm>0) THEN  -- 연산자 처리가 되면 (null일 경우에는 연산처리가 안된다=ELSE문장으로 넘어 간다)
    DBMS_OUTPUT.PUT_LINE(vename||'님의 성과급은 '||vcomm||'입니다');
  ELSE
    DBMS_OUTPUT.PUT_LINE(vename||'님의 성과급은 없습니다');
  END IF;
  
END;
/
SELECT empno,comm FROM emp;
--  다중 조건문 
DECLARE
  vempno emp.empno%TYPE:=0;
  vdeptno emp.deptno%TYPE:=0;
  vdname dept.dname%TYPE:=''; -- 변수 초기화 ( := )
  vename emp.ename%TYPE:='';
BEGIN
  SELECT ename,deptno INTO vename,vdeptno
  FROM emp
  WHERE empno=&empno; -- Scanner 
  
  -- 조건문 처리 
  IF (vdeptno=10) THEN
    vdname:='개발부';
  ELSIF (vdeptno=20) THEN
    vdname:='총무부';
  ELSIF (vdeptno=30) THEN
    vdname:='영업부';
  ELSE
    vdname:='신입';
  END IF;
  -- 출력 
  DBMS_OUTPUT.PUT_LINE('***** 결과 *****');
  DBMS_OUTPUT.PUT_LINE(vename||'님의 부서는 '||vdname||'입니다'); -- 자바로 전송 (변수) 
END;
/
-- 598page 반복문 (for)
/*
     1. BASIC LOOP
        LOOP
          처리
          처리
          ...
        END LOOP;
     2. WHILE
        WHILE 조건 LOOP
          처리 
        END LOOP;
     3. FOR 
        FOR i IN 1..5 LOOP
        END LOOP;
*/
-- 599page(예문) , 598page(문법)
DECLARE
  i NUMBER:=1;
BEGIN
  LOOP
    DBMS_OUTPUT.PUT_LINE(i);
    i:=i+1; -- i++(X)
    -- 종료 시점
    EXIT WHEN i>10;
 END LOOP;
END;
/
-- WHILE (600page)
DECLARE
  i NUMBER:=1;
BEGIN
  WHILE i<=10 LOOP
    DBMS_OUTPUT.PUT_LINE(i);
    i:=i+1;
  END LOOP;
END;
/
-- FOR (601page)
-- REVERSE 역순으로 출력 
/*
     FOR i IN REVERSE 1..10 LOOP  => 10~1
     FOR i IN  1..10 LOOP => 1~10
*/
DECLARE
BEGIN
  FOR i IN REVERSE 1..10 LOOP
   DBMS_OUTPUT.PUT_LINE(i);
  END LOOP;
END;
/

-- CURSOR : 자바 (ResultSet) => CURSOR를 받는 경우 (ResultSet)으로 형변환 => 함수 (목록)
/*
    커서 선언 
    CURSOR 커서명 IS
      SELECT~
*/
-- FOR => foreach FOR 변수명 IN 커서명 (609page) => 함수 (프로시저(댓글),함수(서브쿼리 대체),트리거(다른 테이블에 HIT자동 증가)
DECLARE
  --커서 선언
  CURSOR emp_cur IS
    SELECT empno,ename,job,hiredate,sal 
    FROM emp;
BEGIN
  -- for(BoardVO vo:list) => CURSOR (자바:ResultSet , Collection) => 목록을 받아서 출력 
  FOR i IN emp_cur LOOP
    DBMS_OUTPUT.PUT_LINE(i.empno);
    DBMS_OUTPUT.PUT_LINE(i.ename);
    DBMS_OUTPUT.PUT_LINE(i.job);
    DBMS_OUTPUT.PUT_LINE(i.hiredate);
    DBMS_OUTPUT.PUT_LINE(i.sal);
    DBMS_OUTPUT.PUT_LINE('===========');
  END LOOP;
END;
/
/*
     변수 : %TYPE , %ROWTYPE , RECORD , CURSOR 
     제어문 : 조건문 (IF~ELSE, IF) , 반복문 (FOR)
     변수에 값을 채우는 경우 (:=) , SELECT ~~ INTO 변수 지정 
*/
-- FUNCTION / PROCEDURE (댓글) 
/*
    FUNCTION 처리 = JOIN으로 데이터 출력을 안할 경우  (내장함수) NVL,CEIL 
    사용자 정의 
         => CREATE FUNCTION => 수정시 삭제를 먼저 한다 
         => CREATE [OR REPLACE] FUNCTION => 삭제할 필요가 없다 
    형식) CREATE [OR REPLACE] FUNCTION func_name(매개변수) RETURN 데이터형  => NUMBER , VARCHAR2 , DATE ...
         IS
          변수 선언 
         BEGIN
           처리 
           RETURN 결과값
         END;
         /
*/
-- JOIN
SELECT empno,ename,job,dname,loc 
FROM emp,dept
WHERE emp.deptno=dept.deptno;
-- DNAME을 가지고 오는 함수 (기억 함수는 리턴값이 반드시 1개만 설정)
CREATE OR REPLACE FUNCTION dnameGetData(pdeptno dept.deptno%TYPE) RETURN VARCHAR2
IS
  vdname dept.dname%TYPE;
BEGIN
  SELECT dname INTO vdname 
  FROM dept
  WHERE deptno=pdeptno;
  RETURN vdname;
END;
/
-- LOC => SQL문장이 복잡할 경우 (실무) => 자주 사용하는 JOIN => 회사마다 FUNCTION (메뉴얼) => 솔루션 
CREATE OR REPLACE FUNCTION locGetData(pdeptno dept.deptno%TYPE) RETURN VARCHAR2
IS
  vloc dept.dname%TYPE;
BEGIN
  SELECT loc INTO vloc
  FROM dept
  WHERE deptno=pdeptno;
  RETURN vloc;
END;
/
SELECT empno,ename,job,dnameGetData(deptno),locGetData(deptno) FROM emp;
-- 삭제
DROP FUNCTION dnameGetData;
DROP FUNCTION locGetData;
-- 메소드(함수) => 리턴값(결과값) , 매개변수 

SELECT title,singer,album,key FROM melon_cjw;
CREATE OR REPLACE FUNCTION musicGetKey(ptitle melon_cjw.title%TYPE) RETURN VARCHAR2
IS
  vkey melon_cjw.key%TYPE;
BEGIN
  SELECT key INTO vkey FROM melon_cjw 
  WHERE title=ptitle;
  RETURN vkey;
END;
/
SELECT title,singer,album,musicGetKey(title) FROM genie_cjw WHERE title<>'상상더하기' AND title<>'Celebrity';
-- PROCEDURE 
CREATE TABLE pro_student(
   hakbun NUMBER PRIMARY KEY,
   name VARCHAR2(20) NOT NULL,
   kor NUMBER,
   eng NUMBER,
   math NUMBER
);

-- 데이터 추가 
CREATE OR REPLACE PROCEDURE studentInsert(
      vname pro_student.name%TYPE,
      vkor pro_student.kor%TYPE,
      veng pro_student.eng%TYPE,
      vmath pro_student.math%TYPE)
IS
BEGIN
    INSERT INTO pro_student VALUES(
      (SELECT NVL(MAX(hakbun)+1,1) FROM pro_student),
      vname,vkor,veng,vmath
    );
    COMMIT;
END;
/
Call studentInsert('홍길동',80,90,89); -- 보안  => 평균 , 총점 
-- 총점 
CREATE OR REPLACE FUNCTION studentGetTotal(vhabun pro_student.hakbun%TYPE) RETURN NUMBER
IS
  vtotal NUMBER;
BEGIN
  SELECT kor+eng+math INTO vtotal
  FROM pro_student
  WHERE hakbun=vhabun;
  RETURN vtotal;
END;
/
-- 평균
CREATE OR REPLACE FUNCTION studentGetAvg(vhabun pro_student.hakbun%TYPE) RETURN NUMBER
IS
  vavg NUMBER;
BEGIN
  SELECT ROUND((kor+eng+math)/3,2) INTO vavg
  FROM pro_student
  WHERE hakbun=vhabun;
  RETURN vavg;
END;
/
-- 데이터중에 필요한 값이 있는 경우 => Function(결과값 1)
-- 수정 , 삭제 ,페이지 나누기,상세보기 => Procedure (기능) => 공통적으로 작용이 가능할 때 
/*
    맛집 , 레시피 , 여행(호텔,명소,자연) , 자유게시판  ==> 댓글 (함수화) => 재사용 기법 (호불호) => 
    언어	   JAVA,JAVASCRIPT, XML, HTML5
    DB	ORACLE, MongoDB
    프레임워크	SPRING, MAVEN
    사용 기술	 APACHE, nodeJS, R, React, Redux, MyBatis, JQuery, Ajax, CSS, GIT
    챗봇 : 크롤링
*/
SELECT hakbun,name,kor,eng,math,studentGetTotal(hakbun),studentGetAvg(hakbun) FROM pro_student;
-- 목록 출력 
-- 상세보기 
-- CURD (INSERT/UPDATE/DELETE/SELECT) => 목록 (cursor)
-- 값을 받아서 출력 => 매개변수 (OUT)
/*
     void getData(int* p)
     {
        *p=100;
     }
     
     int a=10;
     int* p=&a;
     getData(p);
     a=100;
     
     int[] arr=new int[3];
     public void getData(int[] a)
     {
        a[0]=10;
        a[1]=20;
        a[2]=30;
     }
     
     getData(arr); arr[0]=10,arr[1]=20,arr[2]=30 => Call by Reference 
     자바 : 주소를 이용하는 프로그램 (배열,클래스)
     C/오라클 => 일반 변수도 주소를 가지고 있다  ==> 주소 (C: * , 오라클 : OUT)
     
     ** IN (일반변수) : default 
*/
CREATE OR REPLACE PROCEDURE studentDetailData(
   phakbun pro_student.hakbun%TYPE,
   pname OUT pro_student.name%TYPE,
   pkor OUT pro_student.kor%TYPE,
   peng OUT pro_student.eng%TYPE,
   pmath OUT pro_student.math%TYPE
)
IS
BEGIN
   SELECT name,kor,eng,math INTO pname,pkor,peng,pmath
   FROM pro_student
   WHERE hakbun=phakbun;
END;
/

VARIABLE pname VARCHAR2;
VARIABLE pkor NUMBER;
VARIABLE peng NUMBER;
VARIABLE pmath NUMBER;
-- 일반 자바=> Call
CALL studentDetailData(1,:pname,:pkor,:peng,:pmath);
PRINT pname
PRINT pkor
PRINT peng
PRINT pmath
-- 수정 
CREATE OR REPLACE PROCEDURE studentUpdate(
      vhakbun pro_student.hakbun%TYPE,
      vname pro_student.name%TYPE,
      vkor pro_student.kor%TYPE,
      veng pro_student.eng%TYPE,
      vmath pro_student.math%TYPE)
IS
BEGIN
    UPDATE pro_student SET
    name=vname,kor=vkor,eng=veng,math=vmath
    WHERE hakbun=vhakbun;
    COMMIT;
END;
/
CALL studentupdate(1,'심청이',100,90,85);
SELECT * FROM pro_student;
-- 삭제 
CREATE OR REPLACE PROCEDURE studentDelete(vhakbun pro_student.hakbun%TYPE)
IS
BEGIN
  DELETE FROM pro_student
  WHERE hakbun=vhakbun;
  COMMIT;
END;
/

CALL studentdelete(1);








