<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/vue"></script>
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
   <div class="row">
     <div class="text-center">
       <button class="btn btn-lg btn-danger" v-on:click="change(1)">믿고 보는 맛집 리스트</button>
       <button class="btn btn-lg btn-success" v-on:click="change(2)">지역별 맛집 리스트</button>
       <button class="btn btn-lg btn-primary" v-on:click="change(3)">인기별 맛집 리스트</button>
     </div>
   </div>
   <div style="height:30px"></div>
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
  </div>
  <script>
    new Vue({
    	el:'.container',
    	data:{
    		cate_data:[]
    	},
    	mounted:function(){
    		// 시작가 동시에 스프링으로부터 데이터 얻어 온다 => window.onload (화면 출력)
    		// axios => ajax (요청 => 응답값을 받을 수 있다)
    		// rest_category.do?no=1 ==> ?뒤에 데이터 전송 :params 
    		
    		axios.get("http://localhost:8080/web/food/rest_category.do",{
    			params:{
    				no:1
    			}
    		// then() => 응답을 받을때 사용 
    		}).then(response=>{
    			// axios => 객체 , Vue객체 
    			/*
    			    function(response){this(자신의 객체)=>axios}
    			    response=>{this => Vue}
    			*/
    			this.cate_data=response.data;
    		})
    	},
    	// 사용자 정의 함수 
    	methods:{
    		
    	    // 함수명:function() => prototype
    	    /*
    	       change:(no)=>{} => function , return 포함 => 화살표 함수 (ES5=>ES6) 외국(ES8)
    	       => 사용하는 함수 (=>)
    	       => 변수 
    	          5 => var
    	          6 => let , const
    	          6 => for => foreach , map 
    	          change(no)
    	    */
    		change:function(no){
    			let _this=this;
    			axios.get("http://localhost:8080/web/food/rest_category.do",{
    				params:{
    					no:no
    				}
    			}).then(response=>{
    				_this.cate_data=response.data;
    			})
    		}
    	}
    })
   </script>
</body>
</html>





