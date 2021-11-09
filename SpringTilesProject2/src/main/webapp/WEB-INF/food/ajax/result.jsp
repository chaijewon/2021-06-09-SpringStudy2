<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <c:forEach var="vo" items="${lList }">
        <div class="col-md-4">
		    <div class="thumbnail">
		      <a href="../food/detail.do?no=${vo.no }&type=1">
		        <img src="${vo.poster }" title="${vo.address }" style="width:100%">
		        <div class="caption">
		          <p>${vo.name }</p>
		        </div>
		      </a>
		    </div>
		  </div>
      </c:forEach>
</body>
</html>