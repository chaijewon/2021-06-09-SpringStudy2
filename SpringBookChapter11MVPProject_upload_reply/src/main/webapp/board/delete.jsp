<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
  width:300px;
}
h1{
  text-align: center;
}
</style>
</head>
<body>
   <div class="container">
     <h1><spring:message code="delete.title"/></h1>
     <div class="row">
       <form method="post" action="delete_ok.do"><%-- .do : BoardController --%>
	       <table class="table">
	         <tr>
	           <td>
	                      비밀번호:<input type=password name=pwd size=20 class="input-sm" required>
	                  <input type=hidden name=no value="${no }">
	                  <input type=hidden name=page value="${page }">
	           </td>
	         </tr>
	         <tr>
	           <td class="text-center">
	             <button class="btn btn-sm btn-success">삭제</button>
	             <input type=button value="취소" class="btn btn-sm btn-success"
	             onclick="javascript:history.back()">
	           </td>
	         </tr>
	       </table>
       </form>
     </div>
   </div>
</body>
</html>









