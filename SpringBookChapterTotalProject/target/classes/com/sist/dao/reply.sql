SELECT RPAD(SUBSTR(id,1,1),LENGTH(id),'*') FROM project_member WHERE tel='010-1111-1111';
SELECT * FROM project_member;
-- ��� �Խ��� 
/*
     no => ������ȣ (���� , ����)
     cno => ���� ��ȣ (���,�ڿ�,ȣ��, �Խ��� , ������ , ����)
     tno => ���� ��ȣ (���:1,�ڿ�:2,ȣ��:3....,��:7
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
-- �Լ� (� ���α׷� ���) - ������ 
-- Procedure
-- ��� ��� 
CREATE OR REPLACE PROCEDURE replyListData(
   pCno commonReply.cno%TYPE,
   pTno commonReply.tno%TYPE,
   pResult OUT SYS_REFCURSOR
)
IS
BEGIN
   -- cursor (ResultSet) => select���� ����� ��µ� ��� �����͸� �о� �´� 
   -- ����� ���(�������� �����͸� ��Ƽ� �Ѱ��ִ� ��� : CURSOR 
   -- INDEX_ASC(commonReply cr_no_pk)
   OPEN pResult FOR
    SELECT /*+ INDEX_DESC(commonReply cr_no_pk)*/ no,cno,tno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') 
    FROM commonReply
    WHERE cno=pCno AND tno=pTno;
    
    -- cno : ���� ��ȣ (�Խù� ��ȣ) , tno : ���� (�Խ���(1),ȣ��(2),���(3),������(4),����(5))
    
END;
/
-- ���� 
/*
   public String reply(�Ű�����) ==> IS
   { BEGIN
      
   } END
*/
CREATE OR REPLACE PROCEDURE replyUpdate(
   pNo commonReply.no%TYPE,
   pMsg commonReply.msg%TYPE
)
IS
-- �����
BEGIN
-- ������ 
   UPDATE commonReply SET
   msg=pMsg
   WHERE no=pNo;
   COMMIT;
END;
/
-- ����
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
-- �߰�
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
   
   ��� : 1 (tno)
   ȣ�� : 2 (tno)
*/
INSERT INTO commonReply VALUES(1,1,1,'shim','��û��','��ȭ���ܱ��μ����繦������ ���Ѹ��� ���������⿡ �츮 ������ ���� �ϻ��� ��ģ �ܱ��� ������� �� ���� 145���� �ٸ� �̵�� �Բ� ����Ǿ� �ֽ��ϴ�.
��������� ��� ������ �����̴� ��Corea���� ������ ���� ������ ���� ����Ͽ����ϴ�. �� �̵��� ������ �б��� �����Ͽ� �츮 ��ȸ ������ �⿩�Ͽ���, �̵� �� �Ϻδ� �츮������ ������ ���� ���� ������ �Ⲩ�� �����Ͽ����ϴ�. ���ó� �ѱ� ��ȸ�� ��ȸ�� ���캸��, �̵��� ���� �ѷ��� ������ ������ ���� ���ŵ��� Ȯ���� �� �ֽ��ϴ�.
������ �������� ��ȭ���ܱ��μ����繦���� ���� ������ �� �� �� ���� ���� ���� 200�ֳ��� ���� ���ư��� �ִ� �ѱ� ��ȸ�� ������ �ذŸ��Դϴ�. �̰��� �ѱ� ��ȸ�� ������ ���ٰ� ��Ű�� ���� �� ���� ��� �׸������ε鿡�� �ð��� ���� �Ҹ��Դϴ�.',SYSDATE);
INSERT INTO commonReply VALUES(2,1,1,'shim','��û��','��ȭ���ܱ��μ����繦������ ���Ѹ��� ���������⿡ �츮 ������ ���� �ϻ��� ��ģ �ܱ��� ������� �� ���� 145���� �ٸ� �̵�� �Բ� ����Ǿ� �ֽ��ϴ�.
��������� ��� ������ �����̴� ��Corea���� ������ ���� ������ ���� ����Ͽ����ϴ�. �� �̵��� ������ �б��� �����Ͽ� �츮 ��ȸ ������ �⿩�Ͽ���, �̵� �� �Ϻδ� �츮������ ������ ���� ���� ������ �Ⲩ�� �����Ͽ����ϴ�. ���ó� �ѱ� ��ȸ�� ��ȸ�� ���캸��, �̵��� ���� �ѷ��� ������ ������ ���� ���ŵ��� Ȯ���� �� �ֽ��ϴ�.
������ �������� ��ȭ���ܱ��μ����繦���� ���� ������ �� �� �� ���� ���� ���� 200�ֳ��� ���� ���ư��� �ִ� �ѱ� ��ȸ�� ������ �ذŸ��Դϴ�. �̰��� �ѱ� ��ȸ�� ������ ���ٰ� ��Ű�� ���� �� ���� ��� �׸������ε鿡�� �ð��� ���� �Ҹ��Դϴ�.',SYSDATE);
INSERT INTO commonReply VALUES(3,1,1,'shim','��û��','��ȭ���ܱ��μ����繦������ ���Ѹ��� ���������⿡ �츮 ������ ���� �ϻ��� ��ģ �ܱ��� ������� �� ���� 145���� �ٸ� �̵�� �Բ� ����Ǿ� �ֽ��ϴ�.
��������� ��� ������ �����̴� ��Corea���� ������ ���� ������ ���� ����Ͽ����ϴ�. �� �̵��� ������ �б��� �����Ͽ� �츮 ��ȸ ������ �⿩�Ͽ���, �̵� �� �Ϻδ� �츮������ ������ ���� ���� ������ �Ⲩ�� �����Ͽ����ϴ�. ���ó� �ѱ� ��ȸ�� ��ȸ�� ���캸��, �̵��� ���� �ѷ��� ������ ������ ���� ���ŵ��� Ȯ���� �� �ֽ��ϴ�.
������ �������� ��ȭ���ܱ��μ����繦���� ���� ������ �� �� �� ���� ���� ���� 200�ֳ��� ���� ���ư��� �ִ� �ѱ� ��ȸ�� ������ �ذŸ��Դϴ�. �̰��� �ѱ� ��ȸ�� ������ ���ٰ� ��Ű�� ���� �� ���� ��� �׸������ε鿡�� �ð��� ���� �Ҹ��Դϴ�.',SYSDATE);
COMMIT;