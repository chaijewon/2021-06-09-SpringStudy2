<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <center>
     <h1>멜론뮤직 Top100</h1>
     <table border=1 bordercolor=black>
       <tr>
        <th>번호</th>
        <th></th>
        <th>곡명</th>
        <th>가수명</th>
        <th>앨범</th>
       </tr>
       <c:forEach var="vo" items="${list }">
         <tr>
          <td>${vo.melon_no }</td>
          <td><img src=${vo.melon_poster } width=30 height=30></td>
          <td>${vo.melon_title }</td>
          <td>${vo.melon_singer }</td>
          <td>${vo.melon_album }</td>
         </tr>
       </c:forEach>
     </table>
   </center>
</body>
</html>