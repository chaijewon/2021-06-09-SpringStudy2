<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<%-- Ajax --%>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script>
  $( function() {
     $( "#tabs" ).tabs();
     $('#telBtn').click(function(){
    	 let tel=$('#tel').val();
    	 if(tel.trim()=="") // 전화번호가 입력이 안된 경우 
    	 {
    		 $('#tel').focus();
    		 return;
    	 }
    	 // 전송 => 결과값 : Ajax => Full Stack (Back,Front)
    	 $.ajax({
    		 type:'post', //PostMapping (Ajax,<form=> method>, axios.get() , axios.post() , await, asyn)
    		 url:'../member/idfind_tel_ok.do',
    		 data:{"tel":tel},
    		 success:function(res)
    		 {
    			 // 데이터값만 넘어오게 만든다 (@ResponseBody)
    			 $('#result_tel').html(res);
    		 }
    	 })
     });
     $('#emailBtn').click(function(){
    	 let email=$('#email').val();
    	 if(email.trim()=="") // 전화번호가 입력이 안된 경우 
    	 {
    		 $('#email').focus();
    		 return;
    	 }
    	 // 전송 => 결과값 : Ajax => Full Stack (Back,Front)
    	 $.ajax({
    		 type:'post', //PostMapping (Ajax,<form=> method>, axios.get() , axios.post() , await, asyn)
    		 url:'../member/idfind_email_ok.do',
    		 data:{"email":email},
    		 success:function(res)
    		 {
    			 // 데이터값만 넘어오게 만든다 (@ResponseBody)
    			 $('#result_email').html(res);
    		 }
    	 })
     })
  });
  </script>
</head>
<body>
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/back.png');">
  <div id="breadcrumb" class="hoc clear"> 
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="hoc container clear"> 
  <h1 class="text-center">아이디 찾기</h1><%-- RPAD --%>
  <div id="tabs">
  <ul>
    <li><a href="#tabs-1">전화번호</a></li>
    <li><a href="#tabs-2">이메일</a></li>
  </ul>
  <div id="tabs-1">
    <p class="inline">전화번호 입력:<input type=text id=tel size=20 class="input-sm">
    <input type=button value="검색" class="btn btn-sm" id="telBtn"></p>
    <p id="result_tel"></p>
  </div>
  <div id="tabs-2">
    <p class="inline">이메일 입력:<input type=text id=email size=20 class="input-sm">
    <input type=button value="검색" class="btn btn-sm" id="emailBtn"></p>
    <p id="result_email"></p>
  </div>
</div>
  </main>
</div>
</body>
</html>








