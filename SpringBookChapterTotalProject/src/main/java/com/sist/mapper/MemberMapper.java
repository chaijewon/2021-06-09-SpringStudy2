package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

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
   // 회원수정 
   // ID찾기 / PWD찾기 (JavaMail) => 직접 메일로 전송 
   // hong****** => RPAD
   // 회원 탈퇴 
   // 댓글 (프로시저)
}
