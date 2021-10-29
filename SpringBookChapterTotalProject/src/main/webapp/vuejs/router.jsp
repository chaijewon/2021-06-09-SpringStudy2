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
      <h1>뷰 라우터 예제</h1>
      <p>
        <router-link to="/main">Main 컴포넌트로 이동</router-link>
        <router-link to="/login">회원가입 컴포넌트로 이동</router-link>
        <!-- <router-link to="/board">게시판으로 이동</router-link> -->
      </p>
      <router-view></router-view> <!--갱신된 url에 해당하는 화면 출력-->
    </div>

   
    <script>
      // 3. Main. Login 컴포넌트 내용 정의
      var Main = { template: '<div>Home 페이지</div>' };
      var Login = { template: '<div>회원 가입 페이지</div>' };
      /* var Board = { template: '<div>게시판 페이지</div>' }; */
      // 4. 각 url에 해당하는 컴포넌트 등록
      var routes = [
        { path: '/main', component: Main },
        { path: '/login', component: Login }/* ,
        {paht:'/board',component:Board} */
      ];
      // 5. 뷰 라우터 정의
      var router = new VueRouter({
        routes
      });
      // 6. 뷰 라우터를 인스턴스에 등록
      var app = new Vue({
        router
      }).$mount('#app'); //$mount()는 el 속성과 동일하게 인스터스를 화면에 붙이는 역할
    </script>
</body>
</html>




