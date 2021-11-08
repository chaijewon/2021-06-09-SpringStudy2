<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <div class="jumbotron">
      <h2 class="text-center">${vo.title }</h2>
      <h4 class="text-center">${vo.subject }</h4>
    </div>
    <div class="row">
      <table class="table">
       <tr>
         <td class="text-center">
           <c:forEach var="vo" items="${list }">
             <table class="table">
               <tr>
                 <td width=30% class="text-center" rowspan="4">
                  <a href="../food/detail.do?no=${vo.no }"><img src="${vo.poster }" style="width:280px;height:200px"></a>
                  <%--
                       food/detail.do (URI) 
                       ====================
                       Model (@Controller) => 요청 처리 => 화면 이동 (forward/redirect)
                                                               =================== 기술면접 
                   --%>
                 </td>
                 <td colspan="2" class="text-left"><h3><a href="../food/detail.do?no=${vo.no }">${vo.name }</a>&nbsp;<span style="color:orange">${vo.score }</span></h3></td>
               </tr>
               <tr>
                <td class="text-center" width=20%>주소:</td>
                <td width=50% class="text-left">${vo.address }</td>
               </tr>
               <tr>
                <td class="text-center" width=20%>전화:</td>
                <td width=50% class="text-left">${vo.tel }</td>
               </tr>
               <tr>
                <td class="text-center" width=20%>음식종류</td>
                <td width=50% class="text-left">${vo.type }</td>
               </tr>
             </table>
           </c:forEach>
         </td>
       </tr>
      </table>
    </div>
  </div>
</body>
</html>







