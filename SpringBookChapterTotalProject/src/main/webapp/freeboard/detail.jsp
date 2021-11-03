<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:800px;
}
</style>
</head>
<body>
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/back.png');">
  <div id="breadcrumb" class="hoc clear"> 
</div>
<div class="wrapper row3">
  <main class="hoc container clear">
   <div class="row">
     <h1 class="text-center">내용보기</h1>
   <table class="table">
    <tr>
     <th width=20% class="text-center">번호</th>
     <td width=30% class="text-center">${vo.no }</td>
     <th width=20% class="text-center">작성일</th>
     <td width=30% class="text-center">${vo.dbday }</td>
    </tr>
    <tr>
     <th width=20% class="text-center">이름</th>
     <td width=30% class="text-center">${vo.name }</td>
     <th width=20% class="text-center">조회수</th>
     <td width=30% class="text-center">${vo.hit }</td>
    </tr>
    <tr>
      <th width=20% class="text-center">제목</th>
      <td colspan="3">${vo.subject }</td>
    </tr>
    <tr>
      <td colspan="4" class="text-left" valign="top" height="200">
       <pre style="white-space: pre-wrap;background-color: white;border:none">${vo.content }</pre>
      </td>
    </tr>
    <tr>
      <td colspan="4" class="text-right">
       <a href="../freeboard/update.do?no=${vo.no }&page=${page}" class="btn btn-xs btn-info">수정</a>
       <%-- @RestController : 데이터자체를 보낼수 있다(script/결과값) : 16장 => AJAX,Front-End, Mobile --%>
       <a href="../freeboard/delete.do?no=${vo.no }&page=${page}" class="btn btn-xs btn-success">삭제</a><%-- 트랜잭션 --%>
       <a href="../freeboard/list.do?page=${page }" class="btn btn-xs btn-primary">목록</a>
      </td>
    </tr>
   </table>
   </div>
  </main>
</div>
</body>
</html>





