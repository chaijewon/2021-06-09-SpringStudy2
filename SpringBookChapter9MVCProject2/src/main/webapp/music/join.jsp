<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  import  -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 30px;
}
.row {
   margin: 0px auto;
   width:960px;
}
</style>
</head>
<body>
   <div class="container">
     <h1 class="text-center">Genie Music Top 200</h1>
     <div class="row">
       <table class="table">
        <tr class="danger">
          <th class="text-center">순위</th>
          <th class="text-center"></th>
          <th class="text-center">곡명</th>
          <th class="text-center">가수명</th>
        </tr>
        <%-- for(MusicVO vo:list) --%>
        <c:forEach var="vo" items="${list }">
          <tr>
            <td class="text-center">${vo.no }</td>
            <td class="text-center"><img src="${vo.poster }" width=30 height=30></td>
            <td><a href="detail.do?no=${vo.no }">${vo.title }</a></td>
            <td>${vo.singer }</td>
          </tr>
        </c:forEach>
       </table>
     </div>
   </div>
</body>
</html>
