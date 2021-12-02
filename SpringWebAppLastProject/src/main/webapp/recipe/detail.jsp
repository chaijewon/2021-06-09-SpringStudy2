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
  width: 1000px;
}
</style>
</head>
<body>
   <div class="container">
    <div class="row">
     <table class="table">
      <tr>
       <td class="text-center">
        <img src="${vo.poster }" style="width:800px;height:350px" class="img-rounded">
       </td>
      </tr>
      <tr>
        <td class="text-center"><h3>${vo.title }</h3></td>
      </tr>
      <tr>
        <td class="text-center" style="color:gray">${vo.content }</td>
      </tr>
     </table>
     <table class="table">
       <tr>
         <td class="text-center"><img src="info1.png"></td>
         <td class="text-center"><img src="info2.png"></td>
         <td class="text-center"><img src="info3.png"></td>
       </tr>
       <tr>
         <td class="text-center">${vo.info1 }</td>
         <td class="text-center">${vo.info2 }</td>
         <td class="text-center">${vo.info3 }</td>
       </tr>
     </table>
     <h3>조리방법</h3>
     <hr>
     <table class="table">
       <c:forEach var="m" items="${mList }" varStatus="s">
        <tr>
         <td width=70%><b style="font-size: 20px">${m }</b></td>
         <td width=30% class="text-right"><img src="${iList[s.index] }" width=200 height=150></td>
        </tr>
       </c:forEach>
     </table>
     <table class="table">
       <caption>레시피 작성자</caption>
       <tr>
         <td>
          <img src="${vo.chef_poster }" width=100 height=100 class="img-circle">
         </td>
         <td>${vo.chef } &nbsp; ${vo.chef_profile }</td>
       </tr>
     </table>
    </div>
   </div>
</body>
</html>









