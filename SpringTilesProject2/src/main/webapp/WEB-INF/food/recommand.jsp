<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
  margin: 0px auto;
  width:960px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.menus').click(function(){
		let fd=$(this).text();
		alert(fd);
		$.ajax({
			type:'get',
			url:'../food/ajax/result.do',
			data:{"fd":fd},
			success:function(result)
			{
				console.log(result);
				$('#data_print').html(result);
			}
		})
	})
})
</script>
</head>
<body>
 <div class="contanier">
  <div class="row">
   <h1>상황</h1>
   <hr>
   <c:forEach var="fd" items="${sub1 }">
     <span class="btn btn-sm btn-danger menus">${fd }</span>
   </c:forEach>
   <h1>감성</h1>
   <hr>
   <c:forEach var="fd" items="${sub2 }">
     <span class="btn btn-sm btn-info menus">${fd }</span>
   </c:forEach>
   <h1>스타일</h1>
   <hr>
   <c:forEach var="fd" items="${sub3 }">
     <span class="btn btn-sm btn-success menus">${fd }</span>
   </c:forEach>
   <h1>날씨/계절</h1>
   <hr>
   <c:forEach var="fd" items="${sub4 }">
     <span class="btn btn-sm btn-primary menus">${fd }</span>
   </c:forEach>
 </div>
 <div style="height: 20px"></div>
 <div class="row" id="data_print">
   
 </div>
 </div>
</body>
</html>








