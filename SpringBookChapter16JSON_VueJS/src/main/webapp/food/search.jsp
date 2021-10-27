<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
    <h1>맛집 검색</h1>
    <div class="row">
      <input type="text" size=30 class="input-sm" placeholder="검색어를 입력하세요" v-model="ss">
      <button class="btn btn-sm btn-primary" v-on:click="houseFind()">검색</button>
      <!-- 양방향 : v-model = 자동으로 data에 있는 변수 연결 -->
    </div>
    <div style="height:50px"></div>
    <div class="row">
      <div class="col-md-3" v-for="vo in find_data">
	    <div class="thumbnail">
	      <a href="#">
	        <img :src="vo.poster" style="width:100%">
	        <div class="caption">
	          <p>{{vo.name}}</p>
	        </div>
	      </a>
	    </div>
	  </div>
    </div>
    <div class="row">
      <div class="text-center">
        <ul class="pagination">
		  <li v-for="i in totalpage"><a href="#" v-on:click="pageChange(i)">{{i}}</a></li>
		</ul>
      </div>
    </div>
  </div>
  <script>
    new Vue({
    	el:'.container', // class => . , id => #
    	data:{
    		find_data:[],
    		page:1,
    		ss:'마포', //입력한 문자열 
    		totalpage:0
    	},
    	// 데이터 읽기(화면에 출력하기 전에 읽어 온다)
    	mounted:function(){
    		// 스프링 서버로 검색을 요청  then() => 스프링서버로부터 결과값을 읽어 온다 (데이터가 많은 경우 묶어서 전송 : JSON) 
    		// http://localhost:8080/web/rest_find.do?ss=마포&page=1
    		axios.get("http://localhost:8080/web/food/rest_find.do",{
    			params:{
    				ss:this.ss,
    				page:this.page
    			}
    		}).then(res=>{
    			console.log(res); // header,data...
    			this.find_data=res.data;
    			this.page=this.find_data[0].curpage;
    			this.totalpage=this.find_data[0].totalpage;
    		})
    	},
    	methods:{
    		houseFind:function(){
    			axios.get("http://localhost:8080/web/food/rest_find.do",{
        			params:{
        				ss:this.ss,
        				page:this.page
        			}
        		}).then(res=>{
        			console.log(res); // header,data...
        			this.find_data=res.data;
        			this.page=this.find_data[0].curpage;
        			this.totalpage=this.find_data[0].totalpage;
        		})
    		},
    		pageChange:function(p){
    			this.page=p;
    			axios.get("http://localhost:8080/web/food/rest_find.do",{
        			params:{
        				ss:this.ss,
        				page:this.page
        			}
        		}).then(res=>{
        			console.log(res); // header,data...
        			this.find_data=res.data;
        			this.page=this.find_data[0].curpage;
        			this.totalpage=this.find_data[0].totalpage;
        		})
    		}
    	}
    	
    })
  </script>
</body>
</html>











