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
.row {
   margin: 0px auto;
   width:800px;
}
h1{
  text-align: center;
}
</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['단어', '언급횟수'],
          <c:forEach var="wvo" items="${list}">
           ['<c:out value="${wvo.word}"/>',     <c:out value="${wvo.count}"/>],
          </c:forEach>
        ]);

        var options = {
          title: '문장 분석',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
</head>
<body>
   <div class="container">
    <h1>내용보기</h1>
    <div class="row">
     <table class="table">
      <tr>
        <th width=20% class="text-center">번호</th>
        <td width=30% class="text-center">${vo.no }</td>
        <th width=20% class="text-center">작성일</th>
        <td width=30% class="text-center"><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <th width=20% class="text-center">이름</th>
        <td width=30% class="text-center">${vo.name }</td>
        <th width=20% class="text-center">조회수</th>
        <td width=30% class="text-center">${vo.hit }</td>
      </tr>
      <tr>
        <th width=20% class="text-center">제목</th>
        <td colspan="3">${vo.subject }</td>
      </tr>
      <tr>
        <td colspan="4" height="200" valign="top" class="text-left">
<pre style="white-space: pre-wrap;background-color: white;border:none">${vo.content }</pre></td>
      </tr>
      <tr>
        <td colspan="4" class="text-right">
         <a href="list.do" class="btn btn-sm btn-success">목록</a>
        </td>
      </tr>
     </table>
    </div>
    <div style="height: 30px"></div>
    <div class="row">
     <div id="piechart_3d" style="width: 900px; height: 500px;"></div>
    </div>
   </div>
</body>
</html>








