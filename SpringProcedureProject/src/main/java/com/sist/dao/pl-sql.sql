SELECT * FROM project_food_location ORDER BY no ASC;
-- ORDER BY ��� INDEX_ASC, INDEX_DESC
SELECT /*+ INDEX_ASC(project_food_location pfl_no_pk)*/ * FROM project_food_location;
SELECT /*+ INDEX_DESC(project_food_location pfl_no_pk)*/ * FROM project_food_location;
-- INDEX (PK,UNIQUE) �ڵ����� (���� ���� , ������ ���� ���)
-- 1. ��� (PL/SQL) => ��� ��� => �������� ����� ���� (����Ŭ�� �Լ��� �����Ѵ�)
/*
      ����Ŭ PL/SQL : Procedure / Function / Trigger
            PL => Procedural Language (PROCEDURE�� ���鶧 ����ϴ� ���)
      �Լ� ���۽� 
      ***PROCEDURE : �������� ���� �Լ� (���ó��)  => ȣ�� (void)
      ***FUNCTION : �������� �ִ� �Լ� => �����Լ� (substr,trim,nvl) => SELECT 
      ***TRIGGER : �ڵ�ó�� (�̺�Ʈ(INSERT/DELETE/UPDATE)) => �ٸ� ���̺� �ڵ����� ���� (��������,�λ����...:ERP)
                   �԰�(insert) => ��� (insert/update)
                   ���(insert) => ��� (update/delete) 
                   ���� (�Ա�) => ����
                   ī��  => ����Ʈ , ��ǰ���� => ����Ʈ 
                   =================================
      PL/SQL�� �̿��ؼ� PROCEDURE�� �����ϱ� ���ؼ��� ����, ���� (�Ű�����)
      ���� : ��Į�󺯼� : NUMBER , VARCHAR2 , DATE ... (TABLE���� ��� ����)
                        age NUMBER , name VARCHAR2(10)..
             TYPE     : ���̺��.�÷���%TYPE   => emp.ename%TYPE
             ROWTYPE  : ���̺��%Rowtype => emp%ROWTYPE => emp���̺��� ������ �ִ� ��� �÷��� ���������� �о� �´� 
                        => ~VO
             RECORD  : JOIN , ���������� ��������Ƿ� ���� 
             CURSOR : Collection 
      ������ : ����Ŭ���� �����ϴ� ������ (���������,�񱳿�����,�������� , NOT , NULL , BETWEEN~AND , LIKE)
      ��� : 1. IF , IF~ELSE , IF~ ELSIF ~ ELSIF ELSE 
              2. FOR , LOOP , WHILE 
              
      ����
      DECLARE 
        ���� ���� 
        procedure : CREATE PROCEDURE pro_name(�Ű�����....) 
                    => Call By Value (�Ϲݺ���) => IN (name IN VARCHAR2(20))
                    => Call By Reference (������ ���� => �����͸� �б� �´�) => C��� (������) : ������ �ּҰ��� �־��ְ� ���� ä��� 
                       OUT (name OUT VARCHAR2(20))
                    => INOUT 
        function  : CREATE FUNCTION func_name(�Ű�����....) RETURN VARCHAR2
     ================================== 
      BEGIN
        ��� ó�� (SQL)
        EXECEPTION ex�� ���� 
      END;
      
      => Error : show error 
*/
-- ���� (�ڹ�,����Ŭ,��Ʋ��) => �޼ҵ�(�Լ�) => �ڹ�(Ŭ�����ȿ��� ����) => Ŭ�����ȿ� �ִ� ��� (�޼ҵ�)
/*
     = �ڹ�(WEB)
     class clsName
     {
        public ������ �޼ҵ��(�Ű�����...)
        {
        }
     }
     = Kotlin(�����)
     fun �޼ҵ��:������
     {
     }
     = C���(�Ӻ����)
     ������ �Լ���(){}
     
     = ����Ŭ 
     CREATE PROCEDURE pro_name(�Ű�����)
     IS
     BEGIN
      
     END;
     = ���̽� (AI) => �ڹٿ��� (���̽�)
*/
-- �ܵ����� ��� (�Լ�) , ��Ʋ�� (Ŭ������ ����޼ҵ� ����) , ���� �Լ� 
/*
     ����Ŭ 
       DML : ������ ���� 
         SELECT/DELETE/INSERT/UPDATE => �ʱ� (����)
         ====== ������ / �����Լ� 
         SELECT : JOIN / SUBQUERY , VIEW , SEQUENCE  => �߱�
         =================================================== �Թ� �䱸 ���� 
         PL/SQL,INDEX => ��� 
     ������ : Container , DI , AOP , MVC => �ʱ� (�䱸����) 
             webSocket , JSON => �߱� 
*/
-- PL/SQL ���� ����� 
SET SERVEROUTPUT ON;
DECLARE --�����
  -- page 572 - 573
  -- ��Į�󺯼� ���� (ũ��) => ���� ���̺��� �÷��� �������� ��� %TYPE
  vename VARCHAR2(20);
  vjob VARCHAR2(20);
  vhiredate DATE;
  vsal NUMBER(7,2);
BEGIN -- ������
  --������ ���� ���� 
  SELECT ename,job,hiredate,sal INTO vename,vjob,vhiredate,vsal -- 559page(PL/SQL���� SELECT��ɾ�� ������ ��ȸ�� ������ ����
  FROM emp
  WHERE empno=7788;
  -- ���� ��� 
  DBMS_OUTPUT.PUT_LINE('****** ������ ********'); --System.out.println()
  DBMS_OUTPUT.PUT_LINE('�̸�:'||vename);
  DBMS_OUTPUT.PUT_LINE('����:'||vjob);
  DBMS_OUTPUT.PUT_LINE('�Ի���:'||vhiredate);
  DBMS_OUTPUT.PUT_LINE('�޿�:'||vjob);
END;
/
-- �������� (%TYPE,%ROWTYPE)
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
-- �������� => %TYPE (���� ���̺��� �÷� ���������� �о�ö� ���) 579page
DECLARE
  vename emp.ename%TYPE; -- VARCHAR2(10)
  vjob emp.job%TYPE; -- VARCHAR2(9)  
  vhiredate emp.hiredate%TYPE; -- DATE   
  vsal emp.sal%TYPE; -- NUMBER(7,2)
  -- �Ѱ��� ���� �� �ִ� => �������� ���� ���ÿ� �޴´� : CURSOR (ArrayList)
BEGIN
  -- ������ : �����͸� SQL�� ���ؼ� ���� ������ ������ ����� ���� (SELECT => System.out.println())
  SELECT ename,job,hiredate,sal INTO vename,vjob,vhiredate,vsal
  FROM emp
  WHERE empno=7788;
  -- ���� ��� 
  DBMS_OUTPUT.PUT_LINE('****** ������ ********'); --System.out.println()
  DBMS_OUTPUT.PUT_LINE('�̸�:'||vename);
  DBMS_OUTPUT.PUT_LINE('����:'||vjob);
  DBMS_OUTPUT.PUT_LINE('�Ի���:'||vhiredate);
  DBMS_OUTPUT.PUT_LINE('�޿�:'||vjob);
END;
/
-- ���� Ÿ�� (��ü ������ ���� => %ROWTYPE (�ڹ��� VO) => ���̺��� �÷��� ��ü �о� �´�)
/*
     �ʿ��� �����͸� �Ѱ��� �о� �´� : %TYPE
     ���̺� �ִ� �����͸� ��ü�� �Ѱ��� �о� �´� :  %ROWTYPE
     �������� ���̺��� �ʿ��� �����͸� �о� �´� (JOIN) : RECORD 
     ======================================================== ����(1row)
     �������� �о� �´� : CURSOR
     
     ��� 
      ��ȣ / ������ȣ (����,����(���,ȣ��,�ڿ�),�Խ��� , ��ȭ....),ī�װ� ��ȣ
      ���̵� , �̸� , ��� , �ۼ��� 
      ==================================================================== 1���� ��۸� �д´� (%ROWTYPE)
*/
-- %ROWTYPE 580page
DECLARE
   vemp emp%ROWTYPE;
BEGIN
   SELECT * INTO vemp
   FROM emp
   WHERE empno=7788;
   -- ���� ��� 
  DBMS_OUTPUT.PUT_LINE('****** ������ ********'); --System.out.println()
  DBMS_OUTPUT.PUT_LINE('���:'||vemp.empno);
  DBMS_OUTPUT.PUT_LINE('�̸�:'||vemp.ename);
  DBMS_OUTPUT.PUT_LINE('����:'||vemp.job);
  DBMS_OUTPUT.PUT_LINE('���:'||vemp.mgr);
  DBMS_OUTPUT.PUT_LINE('�Ի���:'||vemp.hiredate);
  DBMS_OUTPUT.PUT_LINE('�޿�:'||vemp.sal);
  DBMS_OUTPUT.PUT_LINE('������:'||vemp.comm);
  DBMS_OUTPUT.PUT_LINE('�μ���ȣ:'||vemp.deptno);
END;
/
-- ���պ��� => �����ุ ���� �� �ִ� RECORD (JOIN�� ���) - 584page
/*
     ����� ���� �������� 
     TYPE ���������� IS RECORD
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
     -- ���� ��� 
     DBMS_OUTPUT.PUT_LINE('***** ����� *****');
     DBMS_OUTPUT.PUT_LINE('���:'||ed.empno);
     DBMS_OUTPUT.PUT_LINE('�̸�:'||ed.ename);
     DBMS_OUTPUT.PUT_LINE('����:'||ed.job);
     DBMS_OUTPUT.PUT_LINE('�Ի���:'||ed.hiredate);
     DBMS_OUTPUT.PUT_LINE('�޿�:'||ed.sal);
     DBMS_OUTPUT.PUT_LINE('�μ���:'||ed.dname);
     DBMS_OUTPUT.PUT_LINE('�ٹ���:'||ed.loc);
     DBMS_OUTPUT.PUT_LINE('���:'||ed.grade);
END;
/
-- CURSOR (FOR�� �̿��Ѵ�)
/*
    ������ 
      ��������� : + , - , * , /(�Ǽ�)
      �񱳿����� : = , <> (!=) , < , > , <= ,>=
      �������� : AND , OR , NOT
      NULL ������ : IS NULL , IS NOT NULL,
      BETWEEN : �Ⱓ, ���� (AND���� ����) ===>  >= AND <=
      LIKE : %(��������) , _(�ѱ���)
      IN : OR�� �������� �϶� ����ϴ� ������ 
      =================================== SQL���忡�� ����ϴ� ������ 
    ��� 
    ���ǹ� / �ݺ��� 
    ���ǹ� 
    1. ���� ���ǹ� (591page)
       ����)
            IF (���ǹ�) THEN 
              ó�� 
            END IF;
    2. ���� ���ǹ� (592page)
       ����)
            IF(���ǹ�) THEN
              ó�� 
            ELSE
              ó�� 
            END IF;
   3. ���� ���ǹ� (593page)
      ����)
            IF(���ǹ�) THEN
               ó�� 
            ELSIF (���ǹ�) THEN
               ó��
            ELSIF (���ǹ�) THEN
               ó��
            ELSIF (���ǹ�) THEN
               ó��
            ELSE 
               ó�� 
            END IF;
*/
-- ���ǹ� ó�� 
DECLARE
  vempno emp.empno%TYPE:=0;
  vdeptno emp.deptno%TYPE:=0;
  vdname dept.dname%TYPE:=''; -- ���� �ʱ�ȭ ( := )
  vename emp.ename%TYPE:='';
BEGIN
  SELECT ename,deptno INTO vename,vdeptno
  FROM emp
  WHERE empno=&empno; -- Scanner 
  
  -- ���ǹ� ó�� 
  IF (vdeptno=10) THEN
    vdname:='���ߺ�';
  END IF;
  IF (vdeptno=20) THEN
    vdname:='�ѹ���';
  END IF;
  IF (vdeptno=30) THEN
    vdname:='������';
  END IF;
  -- ��� 
  DBMS_OUTPUT.PUT_LINE('***** ��� *****');
  DBMS_OUTPUT.PUT_LINE(vename||'���� �μ��� '||vdname||'�Դϴ�'); -- �ڹٷ� ���� (����) 
END;
/
-- ���� ���ǹ� 
DECLARE
  vempno emp.empno%TYPE;
  vename emp.ename%TYPE;
  vcomm emp.comm%TYPE;
BEGIN
  SELECT empno,ename,comm INTO vempno,vename,vcomm
  FROM emp
  WHERE empno=&empno;
  -- ���ǹ��� ��� 
  IF (vcomm>0) THEN  -- ������ ó���� �Ǹ� (null�� ��쿡�� ����ó���� �ȵȴ�=ELSE�������� �Ѿ� ����)
    DBMS_OUTPUT.PUT_LINE(vename||'���� �������� '||vcomm||'�Դϴ�');
  ELSE
    DBMS_OUTPUT.PUT_LINE(vename||'���� �������� �����ϴ�');
  END IF;
  
END;
/
SELECT empno,comm FROM emp;
--  ���� ���ǹ� 
DECLARE
  vempno emp.empno%TYPE:=0;
  vdeptno emp.deptno%TYPE:=0;
  vdname dept.dname%TYPE:=''; -- ���� �ʱ�ȭ ( := )
  vename emp.ename%TYPE:='';
BEGIN
  SELECT ename,deptno INTO vename,vdeptno
  FROM emp
  WHERE empno=&empno; -- Scanner 
  
  -- ���ǹ� ó�� 
  IF (vdeptno=10) THEN
    vdname:='���ߺ�';
  ELSIF (vdeptno=20) THEN
    vdname:='�ѹ���';
  ELSIF (vdeptno=30) THEN
    vdname:='������';
  ELSE
    vdname:='����';
  END IF;
  -- ��� 
  DBMS_OUTPUT.PUT_LINE('***** ��� *****');
  DBMS_OUTPUT.PUT_LINE(vename||'���� �μ��� '||vdname||'�Դϴ�'); -- �ڹٷ� ���� (����) 
END;
/
-- 598page �ݺ��� (for)
/*
     1. BASIC LOOP
        LOOP
          ó��
          ó��
          ...
        END LOOP;
     2. WHILE
        WHILE ���� LOOP
          ó�� 
        END LOOP;
     3. FOR 
        FOR i IN 1..5 LOOP
        END LOOP;
*/
-- 599page(����) , 598page(����)
DECLARE
  i NUMBER:=1;
BEGIN
  LOOP
    DBMS_OUTPUT.PUT_LINE(i);
    i:=i+1; -- i++(X)
    -- ���� ����
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
-- REVERSE �������� ��� 
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

-- CURSOR : �ڹ� (ResultSet) => CURSOR�� �޴� ��� (ResultSet)���� ����ȯ => �Լ� (���)
/*
    Ŀ�� ���� 
    CURSOR Ŀ���� IS
      SELECT~
*/
-- FOR => foreach FOR ������ IN Ŀ���� (609page) => �Լ� (���ν���(���),�Լ�(�������� ��ü),Ʈ����(�ٸ� ���̺� HIT�ڵ� ����)
DECLARE
  --Ŀ�� ����
  CURSOR emp_cur IS
    SELECT empno,ename,job,hiredate,sal 
    FROM emp;
BEGIN
  -- for(BoardVO vo:list) => CURSOR (�ڹ�:ResultSet , Collection) => ����� �޾Ƽ� ��� 
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
     ���� : %TYPE , %ROWTYPE , RECORD , CURSOR 
     ��� : ���ǹ� (IF~ELSE, IF) , �ݺ��� (FOR)
     ������ ���� ä��� ��� (:=) , SELECT ~~ INTO ���� ���� 
*/
-- FUNCTION / PROCEDURE (���) 
/*
    FUNCTION ó�� = JOIN���� ������ ����� ���� ���  (�����Լ�) NVL,CEIL 
    ����� ���� 
         => CREATE FUNCTION => ������ ������ ���� �Ѵ� 
         => CREATE [OR REPLACE] FUNCTION => ������ �ʿ䰡 ���� 
    ����) CREATE [OR REPLACE] FUNCTION func_name(�Ű�����) RETURN ��������  => NUMBER , VARCHAR2 , DATE ...
         IS
          ���� ���� 
         BEGIN
           ó�� 
           RETURN �����
         END;
         /
*/
-- JOIN
SELECT empno,ename,job,dname,loc 
FROM emp,dept
WHERE emp.deptno=dept.deptno;
-- DNAME�� ������ ���� �Լ� (��� �Լ��� ���ϰ��� �ݵ�� 1���� ����)
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
-- LOC => SQL������ ������ ��� (�ǹ�) => ���� ����ϴ� JOIN => ȸ�縶�� FUNCTION (�޴���) => �ַ�� 
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
-- ����
DROP FUNCTION dnameGetData;
DROP FUNCTION locGetData;
-- �޼ҵ�(�Լ�) => ���ϰ�(�����) , �Ű����� 

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
SELECT title,singer,album,musicGetKey(title) FROM genie_cjw WHERE title<>'�����ϱ�' AND title<>'Celebrity';
-- PROCEDURE 
CREATE TABLE pro_student(
   hakbun NUMBER PRIMARY KEY,
   name VARCHAR2(20) NOT NULL,
   kor NUMBER,
   eng NUMBER,
   math NUMBER
);

-- ������ �߰� 
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
Call studentInsert('ȫ�浿',80,90,89); -- ����  => ��� , ���� 
-- ���� 
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
-- ���
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
-- �������߿� �ʿ��� ���� �ִ� ��� => Function(����� 1)
-- ���� , ���� ,������ ������,�󼼺��� => Procedure (���) => ���������� �ۿ��� ������ �� 
/*
    ���� , ������ , ����(ȣ��,���,�ڿ�) , �����Խ���  ==> ��� (�Լ�ȭ) => ���� ��� (ȣ��ȣ) => 
    ���	   JAVA,JAVASCRIPT, XML, HTML5
    DB	ORACLE, MongoDB
    �����ӿ�ũ	SPRING, MAVEN
    ��� ���	 APACHE, nodeJS, R, React, Redux, MyBatis, JQuery, Ajax, CSS, GIT
    ê�� : ũ�Ѹ�
*/
SELECT hakbun,name,kor,eng,math,studentGetTotal(hakbun),studentGetAvg(hakbun) FROM pro_student;
-- ��� ��� 
-- �󼼺��� 
-- CURD (INSERT/UPDATE/DELETE/SELECT) => ��� (cursor)
-- ���� �޾Ƽ� ��� => �Ű����� (OUT)
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
     �ڹ� : �ּҸ� �̿��ϴ� ���α׷� (�迭,Ŭ����)
     C/����Ŭ => �Ϲ� ������ �ּҸ� ������ �ִ�  ==> �ּ� (C: * , ����Ŭ : OUT)
     
     ** IN (�Ϲݺ���) : default 
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
-- �Ϲ� �ڹ�=> Call
CALL studentDetailData(1,:pname,:pkor,:peng,:pmath);
PRINT pname
PRINT pkor
PRINT peng
PRINT pmath
-- ���� 
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
CALL studentupdate(1,'��û��',100,90,85);
SELECT * FROM pro_student;
-- ���� 
CREATE OR REPLACE PROCEDURE studentDelete(vhakbun pro_student.hakbun%TYPE)
IS
BEGIN
  DELETE FROM pro_student
  WHERE hakbun=vhakbun;
  COMMIT;
END;
/

CALL studentdelete(1);








