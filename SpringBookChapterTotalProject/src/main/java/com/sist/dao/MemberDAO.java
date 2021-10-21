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
}









