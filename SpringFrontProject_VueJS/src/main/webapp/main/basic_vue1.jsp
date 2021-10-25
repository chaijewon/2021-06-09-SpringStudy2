<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- HTML 5 -->
<%--
      VueJS
      Jquery 
      ReactJS  ==> ReactJS 
            최신 기술 : JS / AI   
      ===== 형식 (AngularJS:구글에서 만든 JavaScript 라이브러리 : IE(엣지:AngularJS)) => 보완(속도,양방향) => VueJS
            1. 형식
            2. 디렉티브 
            3. 이벤트 처리 
            4. 컴포넌트 
            5. 사용자 정의 이벤트 : 이벤트 버스 (실시간 채팅)
            6. 스프링 서버와 연결 : axios
            7. 라우트 (화면 이동)
     1) 장점 : 양방향 통신 , 속도가 빠르다 (가상 돔) , 사용하기 편리하다 
      
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<!-- 
     출력 : {{변수}}
   Vue를 생성 => new Vue({
                  el:'' =>: 적용할 태그를 저장  => ID(#), Class(.),
                  data: => 화면에 출력될 데이터 
                           1) 일반 문자열  name:'' 
                           2) 일반 숫자     page:1
                           3) 배열            movie:[]
                           4) 객체단위      movie_detail:{}
                  지원하는 메소드 (생명주기 함수)
                  beforeCreate:function(){} => 생성전 
                  created:function(){}      => 메모리가 할당 완료
                  beforeMount:function(){}  => 화면에 출력하기 전 
                  ***mounted:function(){}      => 화면에 출력 (window.onload => $(function())
                     ======== 서버연결 (데이터를 읽기온다)
                  beforeUpdate:function(){} => 수정하기 전 
                  ***updated:function(){}      => 수정이 완료가 된 상태
                     ======== 페이지나누기 , 검색어
                  beforeDestory(){}         => 화면이동 전 (메모리가 해제 전)
                  destoryed(){}             => 화면이동 (메모리 해제)
                  사용정의 메소드 
                  methods:{
                                         사용자 정의 함수 (이전/다음/상세보기 데이터 읽기/ 목록 출력 ....)
                  }
              })
              
                디렉티브
                     변수 : data 
                     제어문 :
                      1) 조건문   v-if , v-else 
                      2) 숨긴/출력 v-show , v-hide
                      3) 반복문 v-for 
                      4) 양방향 : v-model 
              서버 연결 (스프링 서버 (JSON:16장),NodeJS(프론트 서버:DB연결 가능)) 
              axios.get("사이트~list.do") => 한글변환 
       =========================================================== Basic (템플릿) 
 -->
<div id="app">
  {{$data}}
  <h1>이름:{{name}}</h1>
  <h1>나이:{{age}}</h1>
  <h1>성별:{{sex}}</h1>
</div>
<div class="info">
  {{$data}}
  <h1>이름:{{name}}</h1>
  <h1>나이:{{age}}</h1>
  <h1>성별:{{sex}}</h1>
</div>
<script>
  // VUE 생성 
  new Vue({
	  el:'#app',
	  data:{
		  name:'홍길동',
		  age:30,
		  sex:'남자'
	  }
  })
  new Vue({
	  el:'.info',
	  data:{
		  name:'심청이',
		  age:25,
		  sex:'여자'
	  }
  })
</script>
</body>
</html>








