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
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/back.png');">
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
          <header class="heading">상품 스토어</header>
          <ul class="nospace clear">
           <c:forEach var="vo" items="${list }" varStatus="s">
             <c:if test="${s.index%4==0 }">
              <li class="one_quarter first"><a href="../cart/detail_before.do?no=${vo.product_id}"><img src="${vo.product_poster }" title="${vo.product_name }"
              style="width:250px;height:250px"></a></li>
             </c:if>
             <c:if test="${s.index%4!=0 }">
              <li class="one_quarter"><a href="../cart/detail_before.do?no=${vo.product_id}"><img src="${vo.product_poster }" title="${vo.product_name }"
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
            <li><a href="../cart/list.do?page=${startPage-1 }">&laquo; Previous</a></li>
          </c:if>
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <c:if test="${i==curpage }">
              <li class="current"><a href="../cart/list.do?page=${i }">${i }</a></li>
            </c:if>
            <c:if test="${i!=curpage }">
              <li><a href="../cart/list.do?page=${i }">${i }</a></li>
            </c:if>
          </c:forEach>
          <c:if test="${endPage<totalpage }">
            <li><a href="../cart/list.do?page=${endPage+1 }">Next &raquo;</a></li>
          </c:if>
        </ul>
      </nav>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <!-- / main body -->
    <div class="wrapper row3">
     <h1>최신 본 상품 목록</h1>
     <hr>
     <c:forEach var="vo" items="${dList }" varStatus="s">
      <c:if test="${s.index<10 }">
       <a href="../cart/detail.do?no=${vo.product_id }"><img src="${vo.product_poster }" style="width:100px;height:100px" title="${vo.product_name }"></a>
      </c:if>
     </c:forEach>
    </div>
    <div class="clear"></div>
  </main>
</div>
</body>
</html>