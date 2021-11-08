<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row {
   margin: 0px auto;
   width:100%
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="text-center">
        <a href="../food/category.do?cno=1" class="btn btn-sm btn-danger">믿고 보는 맛집 리스트</a>
        <a href="../food/category.do?cno=2" class="btn btn-sm btn-danger">지역별 맛집 리스트</a>
        <a href="../food/category.do?cno=3" class="btn btn-sm btn-danger">메뉴별 맛집 리스트</a>
      </div>
    </div>
    <div style="height: 30px"></div>
    <div class="row">
      <c:forEach var="vo" items="${list }">
        <div class="col-md-4">
		    <div class="thumbnail">
		      <a href="../food/category_list.do?cno=${vo.cno }">
		        <img src="${vo.poster }" title="${vo.subject }" style="width:100%">
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











