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
   margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 960px;
}
</style>
</head>
<body>
  <div class="container">
   <div class="col-sm-6">
      <c:forEach var="i" begin="2" end="57">
       <div class="col-sm-2">
	          <input type="checkbox" value="${data[i-2] }"><img src="${i }.png" title="${data[i-2] }" style="width:60px;height:60px">
	    </div>
      </c:forEach>
   </div>
  </div>
</body>
</html>