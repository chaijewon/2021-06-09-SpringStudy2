<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- String 메소드  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
  margin: 0px auto;
  width:100%
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <form method="get" action="../food/location.do">
        Search:<input type=text name=ss size=25 class="input-sm" value="${ss }">
        <button class="btn btn-sm btn-primary">검색</button>
      </form>
    </div>
    <div style="height: 30px"></div>
    <div class="row">
      <%--이미지 출력 --%>
      <c:forEach var="vo" items="${list }">
        <div class="col-md-3">
		    <div class="thumbnail">
		      <a href="#">
		        <img src="${fn:substring(vo.poster,0,fn:indexOf(vo.poster,'^')) }" style="width:100%">
		        <div class="caption">
		          <p>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></p>
		        </div>
		      </a>
		    </div>
		  </div>
      </c:forEach>
    </div>
    <div style="height:30px"></div>
    <div class="row">
      <%-- 페이지 출력 --%>
      <div class="text-center">
        <ul class="pagination">
         <c:forEach var="i" begin="1" end="${totalpage }">
          <c:if test="${curpage==i }">
		    <li class="active"><a href="../food/location.do?page=${i }&ss=${ss}">${i }</a></li>
		  </c:if>
		  <c:if test="${curpage!=i }">
		    <li><a href="../food/location.do?page=${i }&ss=${ss}">${i }</a></li>
		  </c:if>
		 </c:forEach>
		</ul>
      </div>
    </div>
  </div>
</body>
</html>







