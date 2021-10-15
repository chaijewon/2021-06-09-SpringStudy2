package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 *   // login처리  => 세션 처리 방법 , include => 쿠키 사용법 
  @Select("SELECT COUNT(*) FROM project_member "
		 +"WHERE id=#{id}")
  public int idCount(String id);
  @Select("SELECT pwd,id,name FROM project_member "
		 +"WHERE id=#{id}")
  public MemberVO isLogin(String id,String pwd);
  
  // 목록처리 
  @Select("SELECT no,subject,name,regdate,hit,num "
		 +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		 +"FROM (SELECT no,subject,name,regdate,hit "
		 +"FROM spring_databoard0 ORDER BY no DESC)) "
         +"WHERE num BETWEEN #{start} AND #{end}")
  public List<DataBoardVO> databoardListData(Map map);
  // 데이터 추가 
  // 시퀀스 마이바이티에서 작업 
  @SelectKey(keyProperty="no",resultType=int.class,before=true,
		  statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_board0")
  @Insert("INSERT INTO spring_board0 VALUES("
		 +"#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0,#{filename},#{filesize},#{filecount})")
  public void databoardInsert(DataBoardVO vo); 
 */
// 스프링에서 메모리 할당 
/*
 *   @Component  : 일반 클래스 (Jsoup , 셀레늄) => 실시간 데이터 수집 
 *                          ====== 정적 , 동적 (HTML 파서)
 *   @Repository : DAO(ORM) = 저장소 관리 
 *   @Service    : DAO + DAO => Service 
 *                 ReplyDAO , BoardDAO => BoardService
 *                 ========
 *                  => 영화 / 맛집 / 뮤직 ....
 *   @Controller : 요청처리 => 결과값 전송  (forward,sendRedirect => 화면 이동 (파일면 제시))
 *                 => @ResponseBody
 *   @RestCopntroller : 요청처리 => 결과값 전송 (문자열, 스크립트 , JSON) => 웹/앱 (16장) 
 *                                                               =====
 *                                                               Front (VueJS , ReactJS , Kotlin) 
 */
@Repository
public class BoardDAO {
	// 마이바티스로부터 인터페이스를 구현한 클래스를 읽어 온다 
	@Autowired // 자동 주입 ==> @Resource(name="") => 수동 주입(주소와 관련된 id지정)
	private BoardMapper mapper;
	
	public MemberVO isLogin(String id,String pwd)
	{
		MemberVO vo=new MemberVO();
		int count=mapper.idCount(id);
		if(count==0) //id가 없는 상태 
		{
			vo.setMsg("NOID");
		}
		else
		{
			MemberVO pvo=mapper.memberGetPassword(id);// 마이바티스는 매개변수 == #{}
			if(pwd.equals(pvo.getPwd()))
			{
				vo.setMsg("OK");
				//////////////////////////
				vo.setId(pvo.getId());
				vo.setName(pvo.getName());
				////////////////////////// Session에 저장 
			}
			else
			{
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
	public List<DataBoardVO> databoardListData(Map map)
	{
		return mapper.databoardListData(map);
	}
	
	public int databoardTotalPage()
	{
		return mapper.databoardTotalPage();
	}
	
	public void databoardInsert(DataBoardVO vo)
	{
		mapper.databoardInsert(vo);
	}
	public DataBoardVO databoardDetailData(int no)
	{
		mapper.databoardHitIncreement(no);// 조회수 증가
		return mapper.databoardDetailData(no);
	}
	// 삭제
	/*
	 *    @Select("SELECT pwd FROM spring_databoard0 "
		   +"WHERE no=#{no}")
		  public String databoardGetPassword(int no); // 수정하기에서도 재사용 
		  // 2. 삭제 , 파일명전송 
		  @Select("SELECT filename,filesize,filecount FROM spring_databoard0 "
				 +"WHERE no=#{no}")
		  public DataBoardVO databoardFileInfoData(int no);
		  @Delete("DELETE FROM spring_databoard0 WHERE no=#{no}")
		  public void databoardDelete(int no);
	 */
	public DataBoardVO databoardFileInfoData(int no)
	{
		return mapper.databoardFileInfoData(no);
	}
	// DAO에서는 필요한 데이터를 매개변수 여러개로 받을 수 있다 
	public boolean databoardDelete(int no,String pwd)
	{
		boolean bCheck=false;
		// 비밀번호 
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd))
		{
			bCheck=true;
			mapper.databoardDelete(no);
		}
		return bCheck;
	}
	// 수정할 데이터를 읽어 온다 
	public DataBoardVO databoardUpdateData(int no)
	{
		return mapper.databoardDetailData(no);
	}
	// 실제 수정
	public boolean databoardUpdate(DataBoardVO vo)
	{
		boolean bCheck=false;
		// 비밀번호 검색 
		String db_pwd=mapper.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			bCheck=true;
			mapper.databoardUpdate(vo);
		}
		return bCheck;
	}
	public List<DataBoardVO> databoardFindData(Map map)
	{
		return mapper.databoardFindData(map);
	}
} 












