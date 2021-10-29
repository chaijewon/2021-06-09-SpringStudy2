<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
   margin-top: 30px;
}
.row {
   margin: 0px auto;
   width:100%;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
  <div class="container">
   <h1>Vue Template => 변수값 받는 방법</h1>
   <div class="row">
     <msg :message="message"></msg>
   </div>
  </div>
  <script>
  Vue.component('msg',{
	  props:['message'], // 속성
	  template:'<h1>{{message}}</h1>'
  })
  new Vue({
	  el:'.container',
	  data:{
		  message:'컴포넌트로 전송'
	  }
  })
  </script>
</body>
</html>







