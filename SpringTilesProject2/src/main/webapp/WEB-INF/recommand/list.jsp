<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
       JSP를 찾아서 데이터를 전송
                 ===== request,json(XML)
                       ======= ==== Front-End (JavaScript) => JSON저장하는 데이터베이스 (몽고디비)
                       Model 
       ViewResolver 
         = ViewResolver
         = TilesView
         = MultipartResolver
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* 오버라이딩 : bootstrap.css : 내부 CSS,인라인 CSS <span style="">*/
.row{
   margin: 0px auto; /*가운데 정력*/
   width:100%
}
.list {
  display: -webkit-flex;
  display: flex;
  -webkit-flex-flow: row wrap;
  flex-flow: row wrap;
}

.item {
  -webkit-flex: 1 auto;
  flex: 1 auto;
  padding: 0.5rem;
  text-align: center;
}

.item a {
  display: block;
  background-color: #88D498;
  text-decoration: none;
  padding: 0.5rem 1rem;
  border-radius: 3px;
  color: #fff;
}


</style>
</head>
<body>
   <div class="container">
    <div class="row">
      <div class="list">
      
        <c:forEach var="gu" items="${list }">
          <div class="item"><a href="../recommand/list.do?gu=${gu }">${gu }</a></div>
        </c:forEach>
        
      </div>
    </div>
    <h3>명소 추천</h3>
    <hr>
    <div class="row">
      <c:if test="${size==0}">
                 명소가 없습니다!!
      </c:if>
      <c:if test="${size>0}">
        <c:forEach var="vo" items="${lvo }">
          <img src="${vo.poster }" style="width:250px;height: 250px">
        </c:forEach>
      </c:if>
      
    </div>
    <h3>호텔 추천</h3>
    <hr>
    <div class="row">
      <c:if test="${size1==0}">
                 명소가 없습니다!!
      </c:if>
      <c:if test="${size1>0}">
        <c:forEach var="vo" items="${hvo }">
          <img src="${vo.poster }" style="width:250px;height: 250px">
        </c:forEach>
      </c:if>
    </div>
   </div>
</body>
</html>













