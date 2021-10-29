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
  <%--
       1. Basic 
          => new Vue({
                 1) 범위지정 (제어) => el(element:태그) => 태그명 , 클래스명(.) ,아이디명(#)
                 2) 멤버변수             => data:{변수}
                 3) 생명주기             => created : 생성이 완료 (메모리에 Vue객체가 저장) => 멤버변수에 대한 초기화
                                   ========== 자바에서 생성자 (자동 호출)
                                    mounted : 메모리에 DOM(HTML) => 가상 메모리 <===> 실제메모리
                                                                                                                               비교해서 변경된 부분만 바꿔준다
                                                                        실제메모리(HTML=>트리형태)
                                        # 리스트 형태 (테이블), #트리형태 (HTML,XML)=>DOM
                                        # 추가가 되면 한번 clear => 새로운 DOM 
                                        # 가상 메모리를 사용 => 변경(실제돔) => 속도 최적화
                                          String VS StringBuffer(메모리에 저장)
                                          ======
                                                                                    문자열 결합 : 새로운 메모리를 만들어준다  (+)
                                        # 목적 : 5G (속도에 맞게 화면제어 => Front)
                                   => Front(VueJS,JQuery,Ajax,ReactJS) => 클라이언트 (서버로부터 데이터를 읽어)
                                      Front <==> Back 
                                                 ===== 변경사항이 없다 
                                      ===== 환경에 따라 변경 (부상 => Jquery(Ajax) => Vue , React)
                                      updated : 변경시에 처리하는 영역 
                  4) 사용자 정의 함수 : 이벤트 
                                   => methods:{
                                                                               버튼 클릭 , 페이지 설정 
                                      }
             })
          => 디렉티브 (v-if , v-else , v-for , v-show)
          => 이벤트 처리 (v-on:이벤트)
                           ======
                                             자바 스크립트 : onclick,onmouseover,onmouseout,onchange,onhover....
          => 템플릿 : 재사용  (같은 데이터를 출력) => 공통모듈 
                    <template>
                                           공동으로 사용 HTML
                    </template>
                    Vue.component()
          => 사용자 이벤트 : $on , $send => 실시간 (Vue가 2개이상 => 데이터 전송 => $on) 
          => router : 화면 이동 (header) => 싱글 페이지 (한개 파일로 제작)
          => 실제 => 기반 스프링 (데이터만 출력 Front)
          
                    퍼블리셔 : HTML/CSS
          **Front : javascript기반 (Jquery(Ajax),Vue , React , Node(서버=스프링))    
          **Back : 자바기반 (자바,스프링,마이바티스,오라클) => AI(분석,통계) (파이썬 , 자바) 
                    아이템 설계 : 사이버다임       
                    
          => React (Redux) => webpack (war,jar)   
                   ======= Front MVC 
                   ======= saga , mobx (배민)
          => vue (vuex)                 
   --%>
   <div class="container">
     <h1>{{message}}</h1>
     <template id="aaa">
       <h1>Hello Template1</h1>
       <h2>Hello Template2</h2>
       <h3>Hello Template3</h3>
     </template>
     <div class="row">
       <my-component></my-component>
       <my-component1></my-component1>
       <my-component></my-component>
       <my-component1></my-component1>
       <my-com></my-com>
     </div>
   </div>
   <script>
     Vue.component('my-com',{
    	 template:'#aaa'
     })
     Vue.component('my-component',{
    	 template:'<h3>Hello Component</h3>'
     })
     Vue.component('my-component1',{
    	 template:'<h3>Hello Component1</h3>'
     })
     new Vue({
    	 el:'.container',
    	 data:{
    		 message:'Vue Template예제'
    	 }
     })
   </script>
</body>
</html>







