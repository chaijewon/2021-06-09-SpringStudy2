<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<title>Insert title here</title>
</head>
<body>
 <!-- v-else , v-for : Spring과 연동 (JSTL : JSON파서를 할 수 없다) JSON:자바스크립트 객체 표현법  -->
 <div id="app">
   <h1 v-if="!message">데이터값이 없습니다!!</h1><!-- if(message==""){} else{} -->
   <h1 v-else>데이터값이 존재합니다!!</h1>
   <input type=text size=20 v-model="message">
   <br>
   <pre v-show="message">{{$data}}</pre><!-- v-show : message에 값이 입력이 되면 보여준다 -->
   <!-- 데이터값 존재 확인 : !message(값이 없는 경우) , message(값이 존재한다) 
        v-show (show()) , v-hide (hide())
        Jquery , React => 함수 중심 
        Vue => 속성으로 처리 (v-if , v-else , v-for , v-show , v-hide)
   -->
 </div>
 <script>
 // Vue를 여러개 생성해서 사용 할 수 있다 (부분마다 처리가 가능: 자주 변경, 중복이 많거나)
 new Vue({
	 el:'#app',
	 data:{
		 message:''
	 }
 })
 </script>
</body>
</html>
















