<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="hoc clear">
    <div id="logo" class="fl_left"> 
      <!-- ################################################################################################ -->
      <h1 class="logoname"><a href="../main/main.do">여행<span>맛</span>집<span>레</span>시피</a></h1>
      <!-- ################################################################################################ -->
    </div>
    <nav id="mainav" class="fl_right"> 
      <!-- ################################################################################################ -->
      <ul class="clear">
        <li class="active"><a href="../main/main.do">Home</a></li>
        <li><a href="#">로그인</a></li>
        <li><a class="drop" href="#">회원</a>
          <ul>
            <li><a href="../member/join.do">회원가입</a></li>
            <li><a href="../member/idfind.do">아이디찾기</a></li>
            <li><a href="../member/pwdfind.do">비밀번호찾기</a></li>
          </ul>
        </li>
        <li><a class="drop" href="#">맛집</a>
          <ul>
            <li><a href="../food/category.do">카테고리</a></li>
            <li><a href="../food/find.do">맛집검색</a></li>
            <li><a href="../food/recommand.do">맛집추천</a></li>
          </ul>
        </li>
        <li><a class="drop" href="#">레시피</a>
          <ul>
            <li><a href="#">레시피목록</a></li>
            <li><a href="#">쉐프</a></li>
            <li><a href="#">레시피만들기</a></li>
            <%-- 댓글 : 재사용 (PL/SQL) = 컴퓨터(오라클) / INDEX (정렬) --%>
          </ul>
        </li>
        <li><a class="drop" href="#">여행</a>
          <ul>
            <li><a href="../seoul/location_list.do">명소</a></li>
            <li><a href="../seoul/nature_list.do">자연 & 관광</a></li>
            <li><a href="../seoul/hotel_list.do">호텔</a></li>
            <li><a href="#">코스 추천(지능형웹)</a>
            <%-- 댓글 : 재사용 (PL/SQL) = 컴퓨터(오라클) / INDEX (정렬) --%>
          </ul>
        </li>
        <li><a class="drop" href="#">커뮤니티</a>
          <ul>
            <li><a href="#">자유게시판</a></li>
            <li><a href="../replyboard/list.do">묻고답하기</a></li>
            <li><a href="#">실시간 상담(webSocket)</a></li>
          </ul>
        </li>
        
        <li><a href="#">마이페이지</a></li>
      </ul>
      <!-- ################################################################################################ -->
    </nav>
  </header>
</div>
</body>
</html>