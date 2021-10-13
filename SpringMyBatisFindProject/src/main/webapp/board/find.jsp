<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <h1>검색결과</h1>
    <div class="row">
     <table class="table">
      <tr class="success">
       <th width=10% class="text-center">번호</th>
       <th width=45% class="text-center">제목</th>
       <th width=15% class="text-center">이름</th>
       <th width=20% class="text-center">작성일</th>
       <th width=10% class="text-center">조회수</th>
      </tr>
      <c:forEach var="vo" items="${list }">
        <tr>
	       <td width=10% class="text-center">${vo.no }</td>
	       <td width=45% class="text-center">${vo.subject }</td>
	       <td width=15% class="text-center">${vo.name }</td>
	       <td width=20% class="text-center"><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/></td>
	       <td width=10% class="text-center">${vo.hit }</td>
	      </tr>
      </c:forEach>
     </table>
    </div>
   </div>
</body>
</html>






