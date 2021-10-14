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
.container{
  margin-top: 30px;
}
.row{
  margin: 0px auto;
  width:800px;
}
</style>
</head>
<body>
  <div class="container">
   <h1 class="text-center">&lt;${vo.name }&nbsp;<span style="color:orange">${vo.score }</span>&gt; 상세보기</h1>
   <div class="row">
    <table class="table">
     <tr>
      <c:forTokens items="${vo.images }" delims="^" var="img">
       <td class="text-center">
        <img src="${img }" width=100%>
       </td>
      </c:forTokens>
     </tr>
     <tr>
      <td colspan="5">${vo.address }</td>
     </tr>
     <tr>
       <td colspan="5" class="text-right">
        <a href="list.do?page=${page }" class="btn btn-sm btn-primary">목록</a>
       </td>
     </tr>
    </table>
   </div>
  </div>
</body>
</html>










