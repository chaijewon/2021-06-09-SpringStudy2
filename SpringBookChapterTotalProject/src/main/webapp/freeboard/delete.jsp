<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:300px;
}
</style>
</head>
<body>
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/back.png');">
  <div id="breadcrumb" class="hoc clear"> 
</div>
<div class="wrapper row3">
  <main class="hoc container clear">
   <div class="row">
       <form method="post" action="../freeboard/delete_ok.do"><%-- .do : BoardController --%>
	       <table class="table">
	         <tr>
	           <td class="inline">
	                      비밀번호:<input type=password name=pwd size=20 class="input-sm" id="pwd">
	                  <input type=hidden name=no value="${no }" id="no">
	                  <input type=hidden name=page value="${page }" id="page">
	           </td>
	         </tr>
	         <tr>
	           <td class="text-center">
	             <button class="btn btn-sm btn-success">삭제</button>
	             <!-- submit 
	                <input type=submit>
	                <button>
	                <input type=image>
	              -->
	             <input type=button value="취소" class="btn btn-sm btn-success"
	             onclick="javascript:history.back()">
	           </td>
	         </tr>
	       </table>
       </form> 
     </div>
  </main>
</div>
</body>
</html>








