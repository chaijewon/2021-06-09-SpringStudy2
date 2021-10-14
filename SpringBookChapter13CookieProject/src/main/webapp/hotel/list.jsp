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
.row{
  margin: 0px auto;
  width:1200px;
}
</style>
</head>
<body>
  <div class="container">
   <div class="row">
    <c:forEach var="vo" items="${list }">
      <div class="col-md-3">
	      <div class="thumbnail">
	        <a href="detail_before.do?no=${vo.no }&page=${curpage}">
	          <%--
	              detail.do => 상세보기 => HTML ==> response (두개를 동시에 전송이 불가능하다)
	              detail_before.do => 쿠키 => 전송
	           --%>
	          <img src="${vo.poster }" alt="Lights" style="width:300px;height: 250px">
	          <div class="caption">
	            <p style="font-size:8px">${vo.name }</p>
	          </div>
	        </a>
	      </div>
	    </div>
    </c:forEach>
   </div>
   <div class="row">
     <div class="text-center">
       <ul class="pagination">
         <c:if test="${startPage>1 }">
           <li><a href="list.do?page=${startPage-1 }">&lt;</a></li>
         </c:if>
         <c:forEach var="i" begin="${startPage }" end="${endPage }">
           <c:if test="${i==curpage }">
            <c:set var="style" value="class=active"/>
           </c:if>
           <c:if test="${i!=curpage }">
            <c:set var="style" value=""/>
           </c:if>
           <li ${style }><a href="list.do?page=${i }">${i }</a></li>
         </c:forEach> 
         <c:if test="${endPage<totalpage }">
           <li><a href="list.do?page=${endPage+1 }">&gt;</a></li>
         </c:if>
		</ul>
     </div>
   </div>
   <div style="height: 30px"></div>
   <hr>
   <h3>방문한 호텔</h3>
   <div class="row">
     <!-- 방문 (cookie) -->
     <c:forEach var="vo" items="${cList }">
       <img src="${vo.poster }" style="width:100px; height:100px">
     </c:forEach>
   </div>
  </div>
</body>
</html>






