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
  <div class="container-fluid">
    <div class="row">
     <div class="text-center">
       <button class="btn btn-lg btn-danger" v-on:click="change(1)">믿고 보는 맛집 리스트</button>
       <button class="btn btn-lg btn-success" v-on:click="change(2)">지역별 맛집 리스트</button>
       <button class="btn btn-lg btn-info" v-on:click="change(3)">인기별 맛집 리스트</button>
     </div>
    </div>
    <div style="height:30px"></div>
    <div class="row">
      <div class="col-md-4" v-for="vo in cate_data"><!-- cate_data=[{}=vo,{},{}] -->
	    <div class="thumbnail">
	      <a href="#">
	        <!--  v-bind:title="" v-bind는 생략이 가능 => :title -->
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
    	el:'.container-fluid',
    	data:{
    		cate_data:[]
    	},
    	mounted:function(){
    		axios.get("http://localhost:8080/web/main/category.do",{
    			params:{
    				cno:1
    			}
    		}).then(res=>{
    			console.log(res.data);
    			this.cate_data=res.data;
    		})
    	},
    	methods:{
    		change(no){
    			axios.get("http://localhost:8080/web/main/category.do",{
        			params:{
        				cno:no
        			}
        		}).then(res=>{
        			console.log(res.data);
        			this.cate_data=res.data;
        		})
    		}
    	}
    	
    })
  </script>
</body>
</html>









