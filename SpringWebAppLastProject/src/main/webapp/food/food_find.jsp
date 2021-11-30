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
      <%-- 검색기  --%>
      <form method="post" action="../food/find.do">
       <input type=text name="loc" size=25 class="input-sm" value="${loc }">
       <button class="btn btn-sm btn-primary">검색</button>
      </form>
    </div>
    <div style="height: 30px"></div>
    <div class="row">
      <%-- 검색된 데이터 출력  --%>
      <c:forEach var="vo" items="${list }">
        <div class="col-md-3">
		    <div class="thumbnail">
		      <a href="#">
		        <img src="${vo.poster }" alt="Lights" style="width:100%">
		        <div class="caption">
		          <p>${vo.name }</p>
		        </div>
		      </a>
		    </div>
		  </div>
      </c:forEach>
    </div>
    <div style="height: 30px"></div>
    <div class="row">
      <%-- 페이지 출력  --%>
      <div class="text-center">
        <a href="../food/find.do?page=${curpage>1?curpage-1:curpage }&loc=${loc}" class="btn btn-sm btn-success">이전</a>
          &nbsp;${curpage } page / ${totalpage } pages&nbsp;
        <a href="../food/find.do?page=${curpage<totalpage?curpage+1:curpgae}&loc=${loc}" class="btn btn-sm btn-info">다음</a>
      </div>
    </div>
  </div>
</body>
</html>