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
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/01.png');">
  <div id="breadcrumb" class="hoc clear"> 
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="hoc container clear"> 
    <!-- main body -->
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading">서울 호텔</header>
          <ul class="nospace clear">
           <c:forEach var="vo" items="${list }" varStatus="s">
             <c:if test="${s.index%4==0 }">
              <li class="one_quarter first"><a href="#"><img src="${vo.poster }" title="${vo.name }"
              style="width:250px;height:250px"></a></li>
             </c:if>
             <c:if test="${s.index%4!=0 }">
              <li class="one_quarter"><a href="#"><img src="${vo.poster }" title="${vo.name }"
              style="width:250px;height:250px"></a></li>
             </c:if>
           </c:forEach>
          </ul>
        </figure>
      </div>
      <!-- ################################################################################################ -->
      <!-- ################################################################################################ -->
      <nav class="pagination">
        <ul>
          <c:if test="${startPage>1 }">
            <li><a href="../seoul/hotel_list.do?page=${startPage-1 }">&laquo; Previous</a></li>
          </c:if>
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <c:if test="${i==curpage }">
              <li class="current"><a href="../seoul/hotel_list.do?page=${i }">${i }</a></li>
            </c:if>
            <c:if test="${i!=curpage }">
              <li><a href="../seoul/hotel_list.do?page=${i }">${i }</a></li>
            </c:if>
          </c:forEach>
          <c:if test="${endPage<totalpage }">
            <li><a href="../seoul/hotel_list.do?page=${endPage+1 }">Next &raquo;</a></li>
          </c:if>
        </ul>
      </nav>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>