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
.row {
   margin: 0px auto;
   width:1200px;
}
</style>
</head>
<body>
   <div class="container">
     <div class="row">
      <table class="table">
       <tr>
         <td class="text-left">
           <form method="post" action="list.do">
            <input type="text" class="input-sm" size=20 name="address" value="${address }">
            <input type=submit value="검색" class="btn btn-sm btn-primary">
           </form>
         </td>
         <td class="text-right">
                     검색 결과:<span style="color:red">${count }</span>개
         </td>
       </tr>
      </table>
      <div style="height:30px"></div>
      <c:forEach var="vo" items="${list }">
       <div class="col-md-3">
		    <div class="thumbnail">
		      <a href="detail.do?no=${vo.no }">
		        <img src="${vo.poster }" alt="Lights" style="width:240px;height:180px;">
		        <div class="caption">
		          <p style="font-weight: bold">${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></p>
		          <p style="font-size:9px">${vo.type }(${vo.address })</p>
		        </div>
		      </a>
		    </div>
		  </div>
      </c:forEach>
     </div>
     <div class="row">
       <div class="text-center">
         <a href="list.do?page=${curpage>1?curpage-1:curpage }&address=${address}" class="btn btn-sm btn-danger">이전</a>
           ${curpage } page / ${totalpage } pages
         <a href="list.do?page=${curpage<totalpage?curpage+1:curpage }&address=${address}" class="btn btn-sm btn-danger">다음</a>
       </div>
     </div>
   </div>
</body>
</html>










