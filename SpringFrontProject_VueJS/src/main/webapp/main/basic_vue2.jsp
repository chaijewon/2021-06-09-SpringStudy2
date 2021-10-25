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
<script src="http://code.jquery.com/jquery.js"></script>
<!-- <script type="text/javascript">
$(function(){
	$('#msg').keyup(function(){
		let msg=$('#msg').val();
		$('h1').text(msg);
	})
})
</script> -->
</head>
<body>
  <div id="app">
   <!--  양방향  -->
   <input type=text id="msg" size=30 v-model="message">
   <h1>{{message}}</h1>
  </div>  
  <script>
   new Vue({
	 el:'#app',
	 data:{
		 message:''
	 }
   })
  </script>
</body>
</html>










