<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- 
     HTML/CSS (X)  => JavaScript (Jquery , Ajax , VueJS , ReactJS , NodeJS(스프링=>서버))
                                                                  => 실시간 채팅
     Oracle (SQL) 
     Spring , JSP 
     => XML 셋팅 , Java 셋팅 ( http / ws)
 -->
<style type="text/css">
.container{
   margin-top: 30px;
}
.row{
  margin: 0px auto;
  width:350px;
}
h1{
  text-align: center;
}
</style>
</head>
<body>
  <div class="container">
    <h1>Login</h1>
    <div class="row">
      <form method=post action="login_ok.do"><!-- 405 / GET,POST => RequestMapping -->
	      <table class="table">
	       <tr>
	         <td width=20% class="text-right">ID</td>
	         <%-- 유효성 검사 (Validation:12장) --%>
	         <td width=80%><input type=text name=id size=15 class="input-sm" required></td>
	       </tr>
	       <tr>
	         <td width=20% class="text-right">PWD</td>
	         <td width=80%><input type=password name=pwd size=15 class="input-sm" required></td>
	       </tr>
	       <tr>
	         <td colspan="2" class="text-center">
	          <button class="btn btn-sm btn-danger">로그인</button>
	         </td>
	       </tr>
	      </table>
      </form>
    </div>
  </div>
</body>
</html>











