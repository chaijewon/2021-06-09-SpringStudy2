<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- 
     HTML/CSS (X)  => JavaScript (Jquery , Ajax , VueJS , ReactJS , NodeJS(스프링=>서버))
                                                                  => 실시간 채팅
     Oracle (SQL) 
     Spring , JSP 
     => XML 셋팅 , Java 셋팅 ( http / ws)
 -->
<style type="text/css">
.container{
   margin-top: 30px;
}
.row{
  margin: 0px auto;
  width:800px;
}
h1{
  text-align: center;
}
</style>
</head>
<body>
  <div class="container">
   <h1>멜론 뮤직 Top100</h1>
   <div class="row">
    <table class="table">
      <tr>
        <td>
        <form method=post action="find.do">
	        Search:
	        <select name=fs class="input-sm">
	          <option value="T">노래명</option>
	          <option value="S">가수명</option>
	          <option value="A">앨범</option>
	          <option value="TS">노래명+가수명</option>
	          <option value="SA">가수명+앨범</option>
	          <option value="TA">노래명+앨범</option>
	          <option value="TSA">가수명+노래명+앨범</option>
	        </select>
	        <input type=text name=ss size=15 class="input-sm">
	        <button class="btn btn-sm btn-danger">검색</button>
        </form>
        </td>
      </tr>
    </table>
    <table class="table">
      <tr class="success">
        <th>순위</th>
        <th></th>
        <th>곡명</th>
        <th>가수명</th>
        <th>앨범</th>
      </tr>
      <c:forEach var="vo" items="${list }">
        <tr>
         <td>${vo.no }</td>
         <td><img src="${vo.poster }" width=30 height=30></td>
         <td>${vo.title }</td>
         <td>${vo.singer }</td>
         <td>${vo.album }</td>
        </tr>
      </c:forEach>
    </table>
   </div>
  </div>
</body>
</html>













