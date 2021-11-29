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
   <div class="container">
     <div class="row">
       <div class="text-center">
         <a href="../main/main.do?no=1" class="btn btn-sm btn-danger">믿고 보는 맛집 리스트</a>
         <a href="../main/main.do?no=2" class="btn btn-sm btn-info">지역별 맛집 리스트</a>
         <a href="../main/main.do?no=3" class="btn btn-sm btn-success">메뉴별 인기 맛집 리스트</a>
       </div>
     </div>
     <div style="height:50px"></div>
     <div class="row">
       <c:forEach var="vo" items="${list }">
         <div class="col-md-4">
		    <div class="thumbnail">
		      <a href="../food/category_list.do?cno=${vo.cno }">
		        <img src="${vo.poster }" alt="Lights" style="width:100%">
		        <div class="caption">
		          <p>${vo.title }</p>
		        </div>
		      </a>
		    </div>
		  </div>
       </c:forEach>
       
     </div>
   </div>
</body>
</html>