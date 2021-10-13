package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
// 유효성 검사(12장) , 13장 (세션 , 쿠키 , 인터셉트)
import org.apache.ibatis.annotations.SelectKey;
// 매개변수는 한개만 설정 
import org.apache.ibatis.annotations.Update;
public interface BoardMapper {
  // login처리  => 세션 처리 방법 , include => 쿠키 사용법 
  @Select("SELECT COUNT(*) FROM project_member "
		 +"WHERE id=#{id}")
  public int idCount(String id);
  @Select("SELECT pwd,id,name FROM project_member "
		 +"WHERE id=#{id}")
  public MemberVO memberGetPassword(String id);
  // <select ~~ paramterType="String">
  
  // 목록처리 
  @Select("SELECT no,subject,name,regdate,hit,num "
		 +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		 +"FROM (SELECT no,subject,name,regdate,hit "
		 +"FROM spring_databoard0 ORDER BY no DESC)) "
         +"WHERE num BETWEEN #{start} AND #{end}")
  public List<DataBoardVO> databoardListData(Map map);
  
  // 총페이지 설정 
  @Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_databoard0")
  public int databoardTotalPage();
  // 데이터 추가 
  // 시퀀스 마이바이티에서 작업 
  @SelectKey(keyProperty="no",resultType=int.class,before=true,
		  statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_databoard0")
  @Insert("INSERT INTO spring_databoard0 VALUES("
		 +"#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0,#{filename},#{filesize},#{filecount})")
  public void databoardInsert(DataBoardVO vo);
  // 상세보기 => 댓글 (본인 => id를 세션)
  @Update("UPDATE spring_databoard0 SET "
		 +"hit=hit+1 "
		 +"WHERE no=#{no}")
  public void databoardHitIncreement(int no);// 조회수 증가 
  @Select("SELECT no,name,subject,content,regdate,hit,filename,filesize,filecount "
		 +"FROM spring_databoard0 "
		 +"WHERE no=#{no}")
  public DataBoardVO databoardDetailData(int no);
  // 찾기 => 동적 쿼리 (마이바티스)
  //////////////////////////// 댓글 (트랜젝션)
  // 삭제하기 
  // 1. 비밀번호 검색  (마이바티스의 단점 : 무조건 매개변수는 1개만 사용한다) => 여러개일 경우 (~VO,Map)
  @Select("SELECT pwd FROM spring_databoard0 "
		 +"WHERE no=#{no}")
  public String databoardGetPassword(int no); // 수정하기에서도 재사용 
  // 2. 삭제 , 파일명전송 
  @Select("SELECT filename,filesize,filecount FROM spring_databoard0 "
		 +"WHERE no=#{no}")
  public DataBoardVO databoardFileInfoData(int no);
  @Delete("DELETE FROM spring_databoard0 WHERE no=#{no}")
  public void databoardDelete(int no);
  
  // 수정하기 
  // 비밀번호 검색 : databoardGetPassword(int no)
  // 실제 수정 
  @Update("UPDATE spring_databoard0 SET "
		 +"name=#{name},subject=#{subject},content=#{content} "
		 +"WHERE no=#{no}")
  public void databoardUpdate(DataBoardVO vo);
}











