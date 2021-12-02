<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>WebAppLastProject</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style type="text/css">
  .container{
     margin-top: 50px
  }
  .row{
     margin:0px auto;
     width:1000px;
  }
  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="../main/main.do">Food & Recipe</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">맛집<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="../main/main.do">맛집 목록</a></li>
          <li><a href="../food/find.do">맛집 검색</a></li>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">레시피<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="../recipe/list.do">레시피 목록</a></li><%--Intent --%>
          <li><a href="../recipe/chef.do">쉐프 목록</a></li>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">서울 여행<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">명소</a></li>
          <li><a href="#">자연</a></li>
          <li><a href="#">호텔</a></li>
        </ul>
      </li>
      <li><a href="../news/news_find.do">뉴스</a></li>
      <li><a href="#">추천챗봇</a></li>
    </ul>
  </div>
</nav>
  
<jsp:include page="${main_jsp}"></jsp:include>

</body>
</html>







