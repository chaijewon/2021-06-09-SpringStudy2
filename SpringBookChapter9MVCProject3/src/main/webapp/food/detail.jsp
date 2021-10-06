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
.row {
   margin: 0px auto;
   width:960px;
}
</style>
</head>
<body>
   <div class="container">
     <div class="row">
      <table class="table">
        <tr>
         <c:forTokens items="${vo.poster }" delims="^" var="img">
          <td class="text-center">
           <img src="${img }" width=100%>
          </td>
         </c:forTokens>
        </tr>
      </table>
     </div>
   </div>
</body>
</html>









