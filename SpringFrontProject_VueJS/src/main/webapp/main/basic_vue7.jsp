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
.container{
   margin-top: 30px;
}
.row {
   margin: 0px auto;
   width:1200px;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
  <div class="container">
   <h1>{{message}}</h1>
   <div class="row">
     <div class="col-md-3" v-for="vo in movie">
	    <div class="thumbnail">
	      <a href="#">
	        <img :src="vo.poster" alt="Lights" style="width:100%">
	        <div class="caption">
	          <p>{{vo.title}}</p>
	        </div>
	      </a>
	    </div>
	  </div>
   </div>
   <div class="row">
     <div class="text-center">
       <button class="btn btn-sm btn-danger" v-on:click="prev()">이전</button>
       <%-- onclick  v-on:mouseover="" v-on:change="" --%>
         {{curpage}} page / {{totalpage}} pages
       <button class="btn btn-sm btn-danger" v-on:click="next()">다음</button>
     </div>
   </div>
  </div>
  <script>
   new Vue({
	   el:'.container',
	   data:{
		   message:'영화목록',
		   movie:[], // 스프링으로부터 값을 읽어 온다 
		   curpage:1,
		   totalpage:0
	   },
	   mounted:function(){
		   // 데이터 읽기 => 스프링으로부터 
		   // 요청 
		   axios.get("http://localhost:8080/web/main/movie_list.do",{
			     params:{
			    	 page:this.curpage
			     }	   
		   }).then(response=>{
			  // 실행 결과값을 받아 온다 (요청 / 응답을 받아온다 = 동시 처리 (화면 변경없이 자체에서 처리 ))
			  console.log(response.data)
			  this.movie=response.data;//JSON
			  this.curpage=this.movie[0].curpage;
			  this.totalpage=this.movie[0].totalpage;
		   })
	   },
	   methods:{
		   // 사용자 정의 
		   // 이전 버튼 
		   prev:function(){
			   this.curpage=this.curpage>1?this.curpage-1:this.curpage;
			   axios.get("http://localhost:8080/web/main/movie_list.do",{
				   params:{
					   page:this.curpage
				   }
			   }).then(response=>{
				   this.movie=response.data;
				   this.curpage=this.movie[0].curpage;
					  this.totalpage=this.movie[0].totalpage;
			   })
		   },
		   // 다음 버튼
		   /*
		        function display(){}
		        let display:function(){}
		        let display=>{}
		                  ===
		                	  함수 포인터 (람다식)
		   */
		   next:function(){
			   this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage;
			   axios.get("http://localhost:8080/web/main/movie_list.do",{
				   params:{
					   page:this.curpage
				   }
			      // .then(function(response)) success:function(res)
			      // 화살표 => (function / return을 사용하지 않는 경우 )
			      // => this가 본인 (Vue) , function(response) => this(axios)
			   }).then(response=>{
				   this.movie=response.data;
				   this.curpage=this.movie[0].curpage;
					  this.totalpage=this.movie[0].totalpage;
			   })
		   }
	   }
   })
  </script>
</body>
</html>







