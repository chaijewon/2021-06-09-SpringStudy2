<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
  width:700px;
}
h1{
  text-align: center;
}
</style>
</head>
<body>
   <div class="container">
     <h1>글쓰기</h1>
     <div class="row">
      <form:form method="post" action="insert_ok.do" modelAttribute="boardVO">
      <table class="table">
       <tr>
         <th width=25% class="text-right danger">이름</th>
         <td width=75%>
          <input type=text name=name size=15 class="input-sm">
         </td> 
       </tr>
       <tr>
         <td colspan="2"><font color="red"><form:errors path="name"/></font></td>
       </tr>
       <tr>
         <th width=25% class="text-right danger">제목</th>
         <td width=75%>
          <input type=text name=subject size=55 class="input-sm">
         </td> 
       </tr>
       <tr>
         <td colspan="2"><font color="red"><form:errors path="subject"/></font></td>
       </tr>
       <tr>
         <th width=25% class="text-right danger">내용</th>
         <td width=75%>
          <textarea rows="10" cols="55" name="content"></textarea>
         </td> 
       </tr>
       <tr>
         <td colspan="2"><font color="red"><form:errors path="content"/></font></td>
       </tr>
       <tr>
         <th width=25% class="text-right danger">비밀번호</th>
         <td width=75%>
          <input type="password" name=pwd size=10 class="input-sm">
         </td> 
       </tr>
       <tr>
         <td colspan="2"><font color="red"><form:errors path="pwd"/></font></td>
       </tr>
       <tr>
         <td colspan="2" class="text-center">
          <button class="btn btn-sm btn-success">글쓰기</button>
          <input type=button value="취소" class="btn btn-sm btn-danger"
           onclick="javascript:history.back()">
         </td>
       </tr>
      </table>
      </form:form>
     </div>
   </div>
</body>
</html>









