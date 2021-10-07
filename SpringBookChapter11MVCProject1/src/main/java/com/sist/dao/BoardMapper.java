package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
public interface BoardMapper {
  // 1. 목록 
  @Select("SELECT no,subject,name,regdate,hit,num "
		 +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		 +"FROM (SELECT no,subject,name,regdate,hit "
		 +"FROM spring_board0 ORDER BY no DESC)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
  public List<BoardVO> boardListData(Map map);
  // 2. 상세보기 
  @Update("UPDATE spring_board0 SET "
		 +"hit=hit+1 "
		 +"WHERE no=#{no}") //조회수 증가  => 각 어노테이션별로 sql문장 1개만 사용이 가능 
  public void hitIncrement(int no);
  // subquery 
  @Select("SELECT no,name,subject,content,regdate,hit "
		 +"FROM spring_board0 "
		 +"WHERE no=#{no}")
  public BoardVO boardDetailData(int no);
  // 3. 게시물 추가
  @SelectKey(keyProperty="no",resultType=int.class,before=true,
		  statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_board0") //자동 증가 번호 (sequence) => 서브쿼리 이용 
  @Insert("INSERT INTO spring_board0(no,name,subject,content,pwd) "
		  +"VALUES(#{no},#{name},#{subject},#{content},#{pwd})")
  //     #{name} ==> vo.getName()
  public void boardInsert(BoardVO vo);
  // 4. 게시물 수정
  // 5. 게시물 삭제 
  // 6. 찾기 ==> #{} (${})
  // => 다음 => 동적쿼리 => <script>
}
