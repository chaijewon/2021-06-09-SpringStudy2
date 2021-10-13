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
  <div style="height: 30px"></div>
  <h1>사원 이름</h1>
  <c:forEach var="name" items="${list }">
   <input type="checkbox" name="names" value="${name }">${name }
  </c:forEach>
  <button>찾기</button>
</body>
</html>