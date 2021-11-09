<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Tiles이용</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">맛집
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="../food/category.do">맛집 목록</a></li>
          <li><a href="../food/location.do">지역별 맛집</a></li>
          <li><a href="../food/recommand.do">맛집 추천</a></li>
        </ul>
      </li>
       <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">레시피
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="../recipe/list.do">레시피 목록</a></li>
          <li><a href="../recipe/chef.do">쉐프 목록</a></li>
        </ul>
      </li>
      <li><a href="../recommand/list.do">추천 경로</a></li>
      <li><a href="../chat/chat.do">실시간 채팅</a></li>
    </ul>
  </div>
</nav>
</body>
</html>