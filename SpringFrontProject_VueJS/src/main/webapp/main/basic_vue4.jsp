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
</head>
<body>
  <!-- 제어하는 영역 -->
  <div id="app">
    <!-- 모든 태그를 제어하는 프로그램 -->
    <!-- 태그안에서 사용 -->
    <h1 v-if="!message">데이터값이 없습니다</h1><!-- v-if="!message" => message가 공백이거나 데이터가 없다면  -->
    <textarea rows="3" cols="20" v-model="message"></textarea>
    <button v-if="message">정상적으로 메세지를 보냈습니다</button><!-- message에 값이 들어 왔다면 출력한다 -->
    <pre>{{$data}}</pre>
  </div>
  <script>
   new Vue({
	   el: '#app', // 제어할 수 있는 영역의 태그 (el:Element(태그)) => JavaScript(태그 제어:selector:CSS)
	   data:{
		   message:''
	   }
   })
  </script>
</body>
</html>













