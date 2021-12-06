<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script>
  $( function() {
   // 로그인 메뉴 클릭 => 다이얼로그를 보여준다 
   $('#login').click(function(){
	   $("#dialog").dialog({
	         autoOpen:false,
	         width:280,
	         height:230,
	         modal : true
	    }).dialog("open");
   });
   // 다이얼로그가 닫기는 부분
   $('#canBtn').click(function(){
	   $('#dialog').dialog("close");
   });
   
   // 로그인 버튼 클릭 (hong / shim)
   $('#logBtn').click(function(){
	  // ID 입력 확인  var => let , const (상수) => JavaScript (버전 : 5.0 , 6.0 => 8.0)
	  let id=$('#id').val();// 사용자가 입력한 값 읽기
	  if(id.trim()=="") // trim() => 좌우의 공백 제거 (사용자 실수 space)
	  {
		  $('#id').focus();
		  return; 
	  }
	  // Password 입력 확인 
	  let pwd=$('#pwd').val(); 
	  //  $('#pwd') ==> 태그 <input type=password id=pwd> : Jquery (DOMScript) DOM(HTML)
	  // 태그를 제어하는 프로그램 => id(),class속성
	  // 실무 (입사) => Jquery , CSS => 6개월 후 (Spring , DB)
	  if(pwd.trim()=="")
	  {
		  $('#pwd').focus();
		  return;
	  }
	  
	  // 데이터를 전송하고 (id,pwd) => 응답을 받아 본다 (결과값 => @ResponseBody) => JSP출력하고 출력된 내용을 읽기 
	  // 클라이언트(브라우저) ==== 서버(Spring:DispatcherServlet)
	  // 서버  ===== 클라이언트 
	  $.ajax({
		  type:'post', // @PostMapping
		  url:'../member/login_ok.do',
		  data:{"id":id,"pwd":pwd}, // ?id=hong&pwd=1234
		  success:function(res) // @ResponseBody에 보낸값을 가지고 온다 (res) => NOID,NOPWD,OK
		  {
			  let result=res;
			  if(result=='NOID')
			  {
				  alert("아이디가 존재하지 않습니다\n다시 입력하세요!!");
				  $('#id').val("");
				  $('#pwd').val(""); // <input value="">
				  $('#id').focus();
			  }
			  else if(result=='NOPWD')
			  {
				  alert("비밀번호가 틀립니다\n다시입력하세요!!");
				  $('#pwd').val("");
				  $('#pwd').focus();
			  }
			  else
			  {
				  // 로그인 된 상태 (완료) => main으로 이동 
				  location.href="../main/main.do";
			  }
		  }
	  })
	  
   });
   $('#logout').click(function(){
	  location.href="../member/logout.do"; // MemberController (처리)
   });
  });
</script>
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
        <c:if test="${sessionScope.id==null }">
          <li><a href="#" id="login">로그인</a></li>
        </c:if>
        <c:if test="${sessionScope.id!=null }">
          <li><a href="#" id="logout">로그아웃</a></li>
        </c:if>
        <li><a class="drop" href="#">회원</a>
         <c:if test="${sessionScope.id==null }"><!-- 로그인이 안된 경우 -->
	          <ul>
	            <li><a href="../member/join.do">회원가입</a></li>
	            <li><a href="../member/idfind.do">아이디찾기</a></li>
	            <li><a href="../member/pwdfind.do">비밀번호찾기</a></li>
	          </ul>
          </c:if>
          <c:if test="${sessionScope.id!=null }">
	          <ul>
	            <li><a href="../member/join_update.do">회원수정</a></li>
	            <li><a href="../member/join_delete.do">회원탈퇴</a></li>
	          </ul>
          </c:if>
        </li>
        <li><a class="drop" href="#">맛집</a>
          <ul>
            <li><a href="../food/category.do">카테고리</a></li>
            <li><a href="../food/find.do">맛집검색</a></li>
            <c:if test="${ sessionScope.id!=null}">
             <li><a href="../food/recommand.do">맛집추천</a></li>
            </c:if>
          </ul>
        </li>
        <li><a class="drop" href="#">레시피</a>
          <ul>
            <li><a href="#">레시피목록</a></li>
            <li><a href="#">쉐프</a></li>
           <c:if test="${sessionScope.id!=null }">
            <li><a href="#">레시피만들기</a></li>
           </c:if>
            <%-- 댓글 : 재사용 (PL/SQL) = 컴퓨터(오라클) / INDEX (정렬) --%>
          </ul>
        </li>
        <li><a class="drop" href="#">여행</a>
          <ul>
            <li><a href="../seoul/location_list.do">명소</a></li>
            <li><a href="../seoul/nature_list.do">자연 & 관광</a></li>
            <li><a href="../seoul/hotel_list.do">호텔</a></li>
           <c:if test="${sessionScope.id!=null }">
            <li><a href="#">코스 추천(지능형웹)</a></li>
           </c:if>
            <%-- 댓글 : 재사용 (PL/SQL) = 컴퓨터(오라클) / INDEX (정렬) --%>
          </ul>
        </li>
        <li><a class="drop" href="#">커뮤니티</a>
          <ul>
            <li><a href="../freeboard/list.do">자유게시판</a></li>
            <li><a href="../replyboard/list.do">묻고답하기</a></li>
            <c:if test="${sessionScope.id!=null }">
              <li><a href="../chat/chat.do">실시간 상담(webSocket)</a></li>
            </c:if>
          </ul>
        </li>
        <li><a href="../cart/list.do">상품스토어</a></li>
        <c:if test="${sessionScope.id!=null && sessionScope.admin=='n'}">
         <li><a href="../page/mypage.do">마이페이지</a></li>
        </c:if>
        <c:if test="${sessionScope.id!=null && sessionScope.admin=='y'}">
         <li><a href="../page/adminpage.do">어드민페이지</a></li>
        </c:if>
      </ul>
      <!-- ################################################################################################ -->
    </nav>
  </header>
</div>
<div id="dialog" title="로그인" style="display:none">
  
  <table class="table">
   <tr>
    <th width=20% align="right">ID</th>
    <td width=80%><input type=text id="id" size=15 class="input-sm"></td>
   </tr>
   <tr>
    <th width=20% align="right">PW</th>
    <td width=80%><input type=password id="pwd" size=15 class="input-sm"></td>
   </tr>
   <tr>
     <td colspan="2" align="center">
      <input type=button value="로그인" id="logBtn" style="float: left">
      <input type=button value="취소" id="canBtn" style="float: left">
     </td>
   </tr>
  </table>
</div>
</body>
</html>




