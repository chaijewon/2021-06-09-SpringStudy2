package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;
import com.sist.vo.*;
/*
 *    1. 스프링 (클래스 관리자)
 *             클래스 (객체) = 컴포넌트 
 *             컴포넌트 여러개를 모아서 관리 => 컨테이너 
 *             ===> 스프링을 한마디로 정의: 컨테이너다 ...
 *       => 등록된 클래스 메모리 할당 (Map에 저장 => 객체의 주소는 변경되지 않는다) => 싱글턴
 *       => DI (setter,constructor) 
 *       => AOP는 메소드 호출시에만 적용이 된다 
 */
@RestController
public class FreeBoardRestController {
	@Autowired
    private FreeBoardDAO dao; // 단일 객체 (싱글턴) 
	
	@PostMapping("freeboard/update_ok.do")
	// RestController => JSON,XML,JavaScript,일반 데이터 
	// Ajax , update_ok.jsp , @RestController 
	public String freeboard_update_ok(int page,FreeBoardVO vo)
	{
	      // vo에 없는 변수값을 vo로 받을 수 없다 , 따로 받는다 	
		  String url="";
		  boolean bCheck=dao.freeboardUpdate(vo);
		  if(bCheck==true) // 비밀번호 => 수정이된 상태
		  {
			  url="<script>"
				 +"location.href=\"../freeboard/detail.do?no="+vo.getNo()+"&page="+page+"\";"
				 +"</script>";
		  }
		  else // 비밀번호가 틀린 상태 
		  {
			  url="<script>"
				 +"alert(\"비밀번호가 틀립니다!!\");"
				 +"history.back();"
				 +"</script>";
		  }
		  return url;
	}
	
	@PostMapping("freeboard/delete_ok.do")
	public String freeboard_delete_ok(int no,int page,String pwd)
	{
		String url="";
		boolean bCheck=dao.freeboardDelete(no, pwd);
		if(bCheck==true)
		{
			url="<script>"
			   +"location.href=\"../freeboard/list.do?page="+page+"\";"
			   +"</script>";
		}
		else
		{
			url="<script>"
			   +"alert(\"비밀번호가 틀립니다!!\");"
			   +"history.back();"
			   +"</script>";
		}
		return url;
	}
	
}













