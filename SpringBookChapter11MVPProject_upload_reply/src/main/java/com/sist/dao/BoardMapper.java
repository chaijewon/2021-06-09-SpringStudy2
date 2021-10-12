package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
// 유효성 검사(12장) , 13장 (세션 , 쿠키 , 인터셉트)
import org.apache.ibatis.annotations.SelectKey;
public interface BoardMapper {
  // login처리  => 세션 처리 방법 , include => 쿠키 사용법 
  @Select("SELECT COUNT(*) FROM project_member "
		 +"WHERE id=#{id}")
  public int idCount(String id);
  @Select("SELECT pwd,id,name FROM project_member "
		 +"WHERE id=#{id}")
  public MemberVO memberGetPassword(String id);
  
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
  // 상세보기 => 댓글 (본인 => id를 세션)
  // 찾기 => 동적 쿼리 (마이바티스)
  //////////////////////////// 댓글 (트랜젝션)
}











