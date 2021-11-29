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
  <div class="container">
    <div class="jumbotron">
      <h2 class="text-center">${cvo.title }</h2>
      <h4 class="text-center">${cvo.subject }</h4>
    </div>
    <div style="height: 30px"></div>
    <div class="row">
      <table class="table">
       <tr>
         <td>
          <c:forEach var="vo" items="${list }">
            <table class="table">
              <tr>
                <td width=30% class="text-center" rowspan="4">
                  <a href="../food/food_detail.do?no=${vo.no }"><img src="${vo.poster }" style="width:300px;height:240px"></a>
                </td>
                <td colspan="2">
                  <h3><a href="../food/food_detail.do?no=${vo.no }">${vo.name }</a>&nbsp;<span style="color:orange">${vo.score }</span></h3>
                </td>
              </tr>
              <tr>
                <td colspan="2">${vo.address }</td>
              </tr>
              <tr>
                <td width="20%">전화</td>
                <td width="50%">${vo.tel }</td>
              </tr>
              <tr>
                <td width="20%">음식종류</td>
                <td width="50%">${vo.type }</td>
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





