<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
     <%--
        <fmt:formatNumber value="" pattern="0,000,000"> 숫자변환 
        <fmt:formatDate value="" pattern="yyyy-MM-dd hh:mm:ss"> 날짜변환
        => TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday
        => TO_CHAR(count,'999,999') as count
      --%>
     <h3>총 <span style="color:green;font-size:35px"><fmt:formatNumber value="${count }" pattern="000,000"/></span>개의 맛있는 레시피가 있습니다.</h3>
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
        <ul class="pagination pagination-lg">
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
   </div>
</body>
</html>











