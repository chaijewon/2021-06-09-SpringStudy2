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
  width: 1300px;
}
</style>
</head>
<body>
 <div class="container">
    <div class="row">
      <div class="text-center">
       <form method="post" action="chef_make.do">
        <input type=hidden name="chef" value="${chef }">
        <input type=text name=ss size=30 class="input-sm">
        <button class="btn btn-sm btn-danger">검색</button>
       </form>
      </div>
    </div>
    <div style="height:30px"></div>
    <div class="row">
      <c:forEach var="vo" items="${list }">
       <div class="col-md-3">
	      <div class="thumbnail">
	        <a href="detail.do?no=${vo.no }&page=${curpage}"><!-- 매개변수 int -->
	          <img src="${vo.poster }" alt="Lights" style="width:100%">
	          <div class="caption">
	            <p>${vo.title }</p>
	            <p>by&nbsp;${vo.chef }</p>
	          </div>
	        </a>
	      </div>
	    </div>
     </c:forEach>
    </div>
    <div class="row">
      <div class="text-center">
        <a href="chef_make.do?page=${curpage>1?curpage-1:curpage }&chef=${chef}" class="btn btn-sm btn-danger">이전</a>
        ${curpage } page / ${totalpage } pages
        <a href="chef_make.do?page=${curpage<totalpage?curpage+1:curpage }&chef=${chef}" class="btn btn-sm btn-primary">다음</a>
      </div>
    </div>
 </div>
</body>
</html>






