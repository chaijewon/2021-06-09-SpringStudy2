<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
      <h1 class="text-center">검색 결과</h1>
      <table class="table">
        <tr>
         <th width=10% class="text-center">번호</th>
         <th width=45% class="text-center">제목</th>
         <th width=15% class="text-center">이름</th>
         <th width=20% class="text-center">작성일</th>
         <th width=10% class="text-center">조회수</th>
        </tr>
        <c:forEach var="vo" items="${list }">
         <tr>
	         <td width=10% class="text-center">${vo.no }</td>
	         <td width=45%>${vo.subject }</td>
	         <td width=15% class="text-center">${vo.name }</td>
	         <td width=20% class="text-center">${vo.dbday }</td>
	         <td width=10% class="text-center">${vo.hit }</td>
	        </tr>
        </c:forEach>
        <tr>
          <td colspan="5" class="text-right">
           <a href="list.do" class="btn btn-sm btn-primary">목록</a>
          </td>
        </tr>
      </table>
    </div>
  </main>
</div>
</body>
</html>







