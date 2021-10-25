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
  <!--  v-if , v-else , v-for , v-show , v-hide : v-model (페이지) -->
  <div class="app">
    <!-- 태그를 제어한다  -->
    <h1>데이터 출력!!</h1>
    <input type="text" size=20 v-model="message">
    <h1>{{message}}</h1>
  </div>
  <div>
    <!-- 제어를 영역이 아니다  (메뉴한개를 제어 : 맛집 : 데이터 여러개 => JSON(16장,@RestController:코틀린(모바일):BSON)-->
  </div>
  <script>
    // 제어를 위한 메모리 할당 (Jquery,VueJS,ReactJS:DomScript(DOM:HTML태그:태그를 만든다 , 태그의 값을 변경, 이벤트 처리))
    new Vue({
    	el: '.app',  //태그  <div id="app"> : el:'#app' , <div class="app"> : el:'.app' => CSS
    	data:{
    		message:'Hello VueJS!!'
    	},
    	// 생명주기 함수 
    	beforeCreate:function(){
    		console.log('beforeCreate:이벤트 등록 , 인스턴스 초기화 전..'); // Syastem.out.println()
    	},
    	created:function(){
    		console.log("***(template생성) created:인스턴스 메모리 할당...")
    	},
    	beforeMount:function(){
    		console.log('***beforeMount:HTML를 메모리에 저장하기 전상태..., 서버에서 데이터를 읽어 와서 변수에 대입..')
    	},
    	mounted:function(){
    		console.log('***mounted:HTML이 메모리에 저장 완료... 브라우저에 화면을 출력 ($(function(){}),window.onload())')
    	},
    	beforeUpdate:function(){
    		console.log('beforeUpdate:데이터 수정 전(HTML이 아직은 변경되지 않은 상태).. 데이터 갱신준비: 한페이지(Ajax)')
    	},
    	updated:function(){
    		console.log("***updated:변경된 데이터를 출력하는 상태...")
    	},
    	beforeDestory:function(){
    		console.log("beforeDestory:다른 페이지로 이동하기 전 (new Vue() : 메모리 해제 전 상태)")
    	},
    	destoryed:function(){
    		console.log("destoryed:메모리 해제 상태...")
    		// Front , Back => (Front+Back) => 290~3200
    		// 퇴직 => 2400 => 13개월 ==> 180 (45) => 135 
    	},
    	methods:{
    		// 사용자 정의 함수 => $('#btn').click(function(){})
    	}
    })    
  </script>
</body>
</html>












