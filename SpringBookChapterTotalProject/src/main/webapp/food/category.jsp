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
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/back.png');">
  <div id="breadcrumb" class="hoc clear"> 
  </div>
</div>
<div class="wrapper row3">
  <main class="hoc container clear">
  <div class="row">
   <div class="text-center">
    <button class="btn btn-lg btn-danger" v-on:click="change(1)">믿고 보는 맛집 리스트</button>
    <button class="btn btn-lg btn-success" v-on:click="change(2)">지역별 맛집 리스트</button>
    <button class="btn btn-lg btn-info" v-on:click="change(3)">메뉴별 맛집 리스트</button>
   </div>
  </div>
  <div style="height: 50px"></div>
  <div class="row">
    <div class="col-md-4" v-for="vo in cate_data">
	    <div class="thumbnail">
	      <%--
	                    속성값 => cate_data값을 사용하려면 => :속성
	                   태그사이에 출력 => {{}}
	       --%>
	      <a :href="'../food/detail.do?cno='+vo.cno">
	        <img :src="vo.poster" :title="vo.subject" style="width:100%">
	        <div class="caption">
	          <p>{{vo.title}}</p>
	        </div>
	      </a>
	    </div>
	  </div>
  </div>
  </main>
</div>
<script>
  new Vue({
	  el:'.container',
	  data:{
		  // Vue 클래스가 가지고 있는 멤버변수 => 반드시 this.접근해야 사용할 수 있다 
		  no:1,
		  cate_data:[] // this.no , this.cate_data (자바스크립트 문법) , 메소드 => this
	  },
	  // 생성주기 함수 : created ,( mounted : window.onload, $(function(){}) ), updated , destoryed
	  mounted:function(){
		  // $(function(){}) => componentDidMount() => React함수 => class형 (메소드형식:혹스)
		  // 요청 (axios.get()) ==> 처리결과를 읽어 온다(then()) => JSON, 일반데이터 
		  // model.addAttribute()=>(X) => JSP , JSON,XML(파싱)
		  // 목록 : [] (JSONArray), 객체 : {} (JSONObject)
		  // 다른 언어 데이터를 전송하는 서비스 (Rest Service) => @RestController
		  // 앱(모바일) => 모바일에 전송이 가능한 파일형식(XML(속도가 늦다):JSON) => 웹/앱 
		  /*
		      axios.get() => GET   => @GetMapping() 
		                                             => @RequestMapping
		      axios.post() => POST => @PostMapping()
		  */
		  axios.get("http://localhost/web/food/rest_category.do",{
			  params:{
				  no:this.no
			  }
		  }).then(response => {
			  // response가 결과값
			  console.log(response.data); // 디버깅
			  this.cate_data=response.data;
		  })
	  },
	  methods:{
		  change:function(no){
			  this.no=no;
			  axios.get("http://localhost/web/food/rest_category.do",{
				  params:{
					  no:this.no
				  }
			  }).then(response => {
				  // response가 결과값
				  console.log(response.data); // 디버깅
				  this.cate_data=response.data;
			  })
		  }
	  }
	  // 사용자 정의 함수 : methods:{} 
	  
  })
</script>
</body>
</html>







