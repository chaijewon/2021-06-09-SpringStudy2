<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../chillaid/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
</head>
<body id="top">
  <%-- 메뉴  --%>
  <jsp:include page="header.jsp"></jsp:include>
  <%-- 페이지 변경(변수명) --%>
  <jsp:include page="${main_jsp }"></jsp:include>
  <%-- Footer --%>
  <jsp:include page="footer.jsp"></jsp:include>
    <script src="../layout/scripts/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
	<script src="../layout/scripts/jquery.backtotop.js"></script>
	<script src="../layout/scripts/jquery.mobilemenu.js"></script>
	<!-- Homepage specific -->
	<script src="../layout/scripts/jquery.easypiechart.min.js"></script>
	<!-- / Homepage specific -->
</body>
</html>