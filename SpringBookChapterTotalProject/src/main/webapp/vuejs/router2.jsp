<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.2/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/vue-router@3.5.3/dist/vue-router.js"></script>
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
   <div id="app">
   <h1>router적용</h1>
     <ul style="list-style-type: none">
        <%--</router-link to=""> <a href=""> --%>
       <li style="display:inline-block;width:70px"><router-link to="/home">Home</router-link></li>
       <li style="display:inline-block;width:70px"><router-link to="/movie">영화목록</router-link></li>
       <li style="display:inline-block;width:70px"><router-link to="/board">커뮤니티</router-link></li>
     </ul>
     <!-- 출력 위치 지정 -->
     <router-view></router-view>
   </div>
   <script>
    // 1. 화면 디자인 
    var Home={template:'<div><h1>Home Page</h1></div>'};
    var Movie={template:'<div><h1>영화 목록 페이지</h1></div>'};
    var Board={template:'<div><h1>커뮤니티 페이지</h1></div>'};
    // 2. mapping => /home => Home , /movie => Movie , /board => Board
    var routes=[
    	{path:'/home' , component:Home},
    	{path:'/movie' , component:Movie},
    	{path:'/board' , component:Board}
    ]
    // VueRouter에 적용 
    var router=new VueRouter({
    	routes
    })
    // Vue에 연결
    var app=new Vue({
    	router
    }).$mount('#app'); // mounted
   </script>
</body>
</html>






