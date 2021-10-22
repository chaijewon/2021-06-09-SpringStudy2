SELECT RPAD(SUBSTR(id,1,1),LENGTH(id),'*') FROM project_member WHERE tel='010-1111-1111';
SELECT * FROM project_member;
-- 댓글 게시판 
/*
     no => 고유번호 (수정 , 삭제)
     cno => 참조 번호 (명소,자연,호텔, 게시판 , 레시피 , 맛집)
     tno => 구분 번호 (명소:1,자연:2,호텔:3....,북:7
*/
DROP TABLE commomReply;
CREATE TABLE commonReply(
   no NUMBER,
   cno NUMBER,
   tno NUMBER,
   id VARCHAR2(20),
   name VARCHAR2(34) CONSTRAINT cr_name_nn NOT NULL,
   msg CLOB CONSTRAINT cr_msg_nn NOT NULL,
   regdate DATE DEFAULT SYSDATE,
   CONSTRAINT cr_no_pk PRIMARY KEY(no),
   CONSTRAINT cr_id_fk FOREIGN KEY(id)
   REFERENCES project_member(id)
);
-- 함수 (어떤 프로그램 사용) - 공통모듈 
-- Procedure
-- 목록 출력 
CREATE OR REPLACE PROCEDURE replyListData(
   pCno commonReply.cno%TYPE,
   pTno commonReply.tno%TYPE,
   pResult OUT SYS_REFCURSOR
)
IS
BEGIN
   -- cursor (ResultSet) => select문장 실행시 출력된 모든 데이터를 읽어 온다 
   -- 목록을 출력(여러개의 데이터를 모아서 넘겨주는 경우 : CURSOR 
   -- INDEX_ASC(commonReply cr_no_pk)
   OPEN pResult FOR
    SELECT /*+ INDEX_DESC(commonReply cr_no_pk)*/ no,cno,tno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') 
    FROM commonReply
    WHERE cno=pCno AND tno=pTno;
    
    -- cno : 참조 번호 (게시물 번호) , tno : 구분 (게시판(1),호텔(2),명소(3),레시피(4),맛집(5))
    
END;
/
-- 수정 
/*
   public String reply(매개변수) ==> IS
   { BEGIN
      
   } END
*/
CREATE OR REPLACE PROCEDURE replyUpdate(
   pNo commonReply.no%TYPE,
   pMsg commonReply.msg%TYPE
)
IS
-- 선언부
BEGIN
-- 구현부 
   UPDATE commonReply SET
   msg=pMsg
   WHERE no=pNo;
   COMMIT;
END;
/
-- 삭제
CREATE OR REPLACE PROCEDURE replyDelete(
   pNo commonReply.no%TYPE
)
IS
BEGIN
  DELETE FROM commonReply 
  WHERE no=pNo;
  COMMIT;
END;
/
-- 추가
/*
    no , cno ,tno ,id , name , msg , regdate
    ==                               ========
*/
CREATE OR REPLACE PROCEDURE replyInsert(
   pCno commonReply.cno%TYPE, -- commonReply.cno%TYPE cno NUMBER
   pTno commonReply.tno%TYPE,
   pId commonReply.id%TYPE,  -- id VARCHAR2(20)
   pName commonReply.name%TYPE, -- name VARCHAR2(34)
   pMsg commonReply.msg%TYPE  -- msg CLOB
)
IS
BEGIN
   INSERT INTO commonReply VALUES(
     (SELECT NVL(MAX(no)+1,1) FROM commonReply),
     pCno,pTno,pId,pName,pMsg,SYSDATE
   );
   COMMIT;
END;
/
/*
    no NUMBER,
   cno NUMBER,
   tno NUMBER,
   id VARCHAR2(20),
   name VARCHAR2(34) CONSTRAINT cr_name_nn NOT NULL,
   msg CLOB CONSTRAINT cr_msg_nn NOT NULL,
   regdate DATE DEFAULT SYSDATE,
   
   명소 : 1 (tno)
   호텔 : 2 (tno)
*/
INSERT INTO commonReply VALUES(1,1,1,'shim','심청이','양화진외국인선교사묘원에는 구한말과 일제강점기에 우리 민족을 위해 일생을 바친 외국인 선교사와 그 가족 145명이 다른 이들과 함께 안장되어 있습니다.
선교사들은 당시 세상의 변방이던 ‘Corea’에 복음의 빛을 나누기 위해 헌신하였습니다. 또 이들은 병원과 학교를 설립하여 우리 사회 발전에 기여하였고, 이들 중 일부는 우리나라의 독립을 위해 많은 위험을 기꺼이 감수하였습니다. 오늘날 한국 교회와 사회를 살펴보면, 이들을 통해 뿌려진 복음의 씨앗이 맺은 열매들을 확인할 수 있습니다.
복음의 씨앗으로 양화진외국인선교사묘원에 묻힌 선교사 한 분 한 분의 삶은 선교 200주년을 향해 나아가고 있는 한국 교회의 소중한 밑거름입니다. 이곳을 한국 교회의 성지로 가꾸고 지키는 일은 이 땅의 모든 그리스도인들에게 맡겨진 귀한 소명입니다.',SYSDATE);
INSERT INTO commonReply VALUES(2,1,1,'shim','심청이','양화진외국인선교사묘원에는 구한말과 일제강점기에 우리 민족을 위해 일생을 바친 외국인 선교사와 그 가족 145명이 다른 이들과 함께 안장되어 있습니다.
선교사들은 당시 세상의 변방이던 ‘Corea’에 복음의 빛을 나누기 위해 헌신하였습니다. 또 이들은 병원과 학교를 설립하여 우리 사회 발전에 기여하였고, 이들 중 일부는 우리나라의 독립을 위해 많은 위험을 기꺼이 감수하였습니다. 오늘날 한국 교회와 사회를 살펴보면, 이들을 통해 뿌려진 복음의 씨앗이 맺은 열매들을 확인할 수 있습니다.
복음의 씨앗으로 양화진외국인선교사묘원에 묻힌 선교사 한 분 한 분의 삶은 선교 200주년을 향해 나아가고 있는 한국 교회의 소중한 밑거름입니다. 이곳을 한국 교회의 성지로 가꾸고 지키는 일은 이 땅의 모든 그리스도인들에게 맡겨진 귀한 소명입니다.',SYSDATE);
INSERT INTO commonReply VALUES(3,1,1,'shim','심청이','양화진외국인선교사묘원에는 구한말과 일제강점기에 우리 민족을 위해 일생을 바친 외국인 선교사와 그 가족 145명이 다른 이들과 함께 안장되어 있습니다.
선교사들은 당시 세상의 변방이던 ‘Corea’에 복음의 빛을 나누기 위해 헌신하였습니다. 또 이들은 병원과 학교를 설립하여 우리 사회 발전에 기여하였고, 이들 중 일부는 우리나라의 독립을 위해 많은 위험을 기꺼이 감수하였습니다. 오늘날 한국 교회와 사회를 살펴보면, 이들을 통해 뿌려진 복음의 씨앗이 맺은 열매들을 확인할 수 있습니다.
복음의 씨앗으로 양화진외국인선교사묘원에 묻힌 선교사 한 분 한 분의 삶은 선교 200주년을 향해 나아가고 있는 한국 교회의 소중한 밑거름입니다. 이곳을 한국 교회의 성지로 가꾸고 지키는 일은 이 땅의 모든 그리스도인들에게 맡겨진 귀한 소명입니다.',SYSDATE);
COMMIT;