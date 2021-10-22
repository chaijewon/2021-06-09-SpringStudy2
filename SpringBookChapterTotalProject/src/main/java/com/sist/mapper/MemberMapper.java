package com.sist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.MemberVO;

public interface MemberMapper {
   // 로그인 처리 
   // 1. ID존재여부 확인 
   @Select("SELECT COUNT(*) FROM project_member "
		  +"WHERE id=#{id}")
   public int memberIdCount(String id);
   // 2. 비밀번호 체크 
   @Select("SELECT pwd,name FROM project_member "
		  +"WHERE id=#{id}")
   public MemberVO memberGetPassword(String id);
   // 회원가입 
   // 1. 아이디 중복 
   // 2. 회원가입 
   /*
    *   ID       NOT NULL VARCHAR2(20)  
		PWD      NOT NULL VARCHAR2(10)  
		NAME     NOT NULL VARCHAR2(34)  
		SEX               VARCHAR2(10)  
		BIRTHDAY NOT NULL VARCHAR2(20)  
		EMAIL    NOT NULL VARCHAR2(100) 
		POST     NOT NULL VARCHAR2(7)   
		ADDR1    NOT NULL VARCHAR2(300) 
		ADDR2             VARCHAR2(300) 
		TEL      NOT NULL VARCHAR2(20)  
		ADMIN             CHAR(1)       
		CONTENT           CLOB    
    */
   @Insert("INSERT INTO project_member VALUES("
		  +"#{id},#{pwd},#{name},#{sex},#{birthday},"
		  +"#{email},#{post},#{addr1},#{addr2},#{tel},'n',#{content})")
   public void memberInsert(MemberVO vo);
   // 회원수정 
   @Select("SELECT * FROM project_member WHERE id=#{id}")
   public MemberVO memberUpdateData(String id);
   // 비밀번호 검색
   // => 재사용 
   // 실제 수정 
   /*
    *   NAME     NOT NULL VARCHAR2(34)  
	SEX               VARCHAR2(10)  
	BIRTHDAY NOT NULL VARCHAR2(20)  
	EMAIL    NOT NULL VARCHAR2(100) 
	POST     NOT NULL VARCHAR2(7)   
	ADDR1    NOT NULL VARCHAR2(300) 
	ADDR2             VARCHAR2(300) 
	TEL      NOT NULL VARCHAR2(20)  
	ADMIN             CHAR(1)       
	CONTENT           CLOB      
    */
   @Update("UPDATE project_member SET "
		  +"name=#{name},sex=#{sex},birthday=#{birthday},email=#{email},"
		  +"post=#{post},addr1=#{addr1},addr2=#{addr2},tel=#{tel},"
		  +"content=#{content} "
		  +"WHERE id=#{id}")
   public void memberJoinUpdate(MemberVO vo);
        
   //                              ============  #{}
   //                              #{} 여러개 일때 (매개변수 1개사용) => 묶어서 처리 (~VO,Map)
   //                              기본은 ~VO , VO에 없는 변수가 있는 경우는 Map에 이용해서 사용 
   // Spring MVC (동작 => 본인이 공부)
   // ID찾기 / PWD찾기 (JavaMail) => 직접 메일로 전송 
   // hong****** => RPAD
   // 회원 탈퇴 
   @Delete("DELETE FROM project_member WHERE id=#{id}")
   public void memberDelete(String id);
   
   // 검색어 한개가 변경 => 동적쿼리 <if test="tel!=null"> <if test="email!=null">
   @Select("SELECT COUNT(*) FROM project_member WHERE tel=#{tel}")
   public int memberIdFindTel(String tel);
   @Select("SELECT RPAD(SUBSTR(id,1,1),LENGTH(id),'#') FROM project_member WHERE tel=#{tel}")
   public String memberIdFindTelData(String tel);
   
   @Select("SELECT COUNT(*) FROM project_member WHERE email=#{email}")
   public int memberIdFindEmail(String email);
   @Select("SELECT RPAD(SUBSTR(id,1,1),LENGTH(id),'#') FROM project_member WHERE email=#{email}")
   public String memberIdFindEmailData(String email);
   
   // id존재 여부 
   // 비밀번호 찾기 
   @Select("SELECT RPAD(SUBSTR(pwd,1,1),LENGTH(pwd),'*') FROM project_member WHERE id=#{id}")
   public String memberPwdFindData(String id);
   // 댓글 (프로시저)
}









