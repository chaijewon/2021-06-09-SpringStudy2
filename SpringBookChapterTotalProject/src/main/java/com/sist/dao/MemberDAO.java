package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;

@Repository
public class MemberDAO {
    @Autowired // 스프링에서 구현된 클래스를 생성 => 생성된 클래스의 주소를 받아서 처리 
    private MemberMapper mapper; 
    
    /*
     *     @Select("SELECT COUNT(*) FROM project_member "
		    +"WHERE id=#{id}")
		   public int memberIdCount(String id);
		   // 2. 비밀번호 체크 
		   @Select("SELECT pwd,name FROM project_member "
				  +"WHERE id=#{id}")
		   public MemberVO memberGetPassword(String id);
     */
    public MemberVO isLogin(String id,String pwd)
    {
    	MemberVO vo=new MemberVO();
    	//ID체크 
    	int count=mapper.memberIdCount(id);
    	if(count==0) // ID가 없는 상태 
    	{
    		vo.setMsg("NOID");
    	}
    	else
    	{
    		MemberVO dbVO=mapper.memberGetPassword(id);
    		if(pwd.equals(dbVO.getPwd())) //  비밀번호가 일치
    		{
    			vo.setMsg("OK");
    			vo.setId(id);
    			vo.setName(dbVO.getName());
    			vo.setAdmin(dbVO.getAdmin());
    		}
    		else //비밀번호가 틀릴때
    		{
    			vo.setMsg("NOPWD");
    		}
    	}
    	return vo;
    }
    
    // 아이디 중복체크 
    public int memberIdCheck(String id)
    {
    	return mapper.memberIdCount(id);
    }
    // 회원가입
    public void memberInsert(MemberVO vo)
    {
    	mapper.memberInsert(vo);
    }
    // 회원탈퇴
    public void memberDelete(String id)
    {
    	mapper.memberDelete(id);
    }
    // 회원 수정 데이터 읽기
    public MemberVO memberUpdateData(String id)
    {
    	return mapper.memberUpdateData(id);
    }
    // 실제 수정 
    public boolean memberJoinUpdate(MemberVO vo)
    {
    	boolean bCheck=false;
    	MemberVO dbVO=mapper.memberGetPassword(vo.getId());
    	if(dbVO.getPwd().equals(vo.getPwd()))
    	{
    		bCheck=true;// 비밀번호가 맞는 경우
    		mapper.memberJoinUpdate(vo);
    	}
    	else
    	{
    		bCheck=false;//비밀번호가 틀린 경우
    	}
    	return bCheck;
    }
    
    public String memberIdFindTelData(String tel)
    {
    	String msg="";
    	int count=mapper.memberIdFindTel(tel);
    	// 전화번호 존재여부 확인
    	if(count==0)
    	{
    		msg="전화번호가 존재하지 않습니다!!";
    	}
    	else
    	{
    		msg=mapper.memberIdFindTelData(tel); // h***
    	}
    	return msg;
    }
    // tel / email => UNIQUE 
    public String memberIdFindEmailData(String email)
    {
    	String msg="";
    	int count=mapper.memberIdFindEmail(email);
    	// 전화번호 존재여부 확인
    	if(count==0)
    	{
    		msg="이메일이 존재하지 않습니다!!";
    	}
    	else
    	{
    		msg=mapper.memberIdFindEmailData(email); // h***
    	}
    	return msg;
    }
    public String memberPwdFindData(String id)
    {
    	String msg="";
    	int count=mapper.memberIdCount(id);
    	if(count==0)
    	{
    		msg="아이디가 존재하지 않습니다!!";
    	}
    	else
    	{
    		msg=mapper.memberPwdFindData(id);
    	}
    	return msg;
    }
}









