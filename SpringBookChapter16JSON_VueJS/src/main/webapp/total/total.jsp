<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
   width:800px;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
  <div class="container">
   <h1>종합</h1>
   <div class="row" id="category">
      <h1>{{title}}</h1>
   </div>
   <div style="height: 30px"></div>
   <div class="row" id="news">
      <h1>{{title}}</h1>
   </div>
   <div style="height: 30px"></div>
   <div class="row" id="movie">
      <h1>{{title}}</h1>
   </div>
  </div>
  <!-- 
       class A
       {
          String title;
       }
       
       A a1=new A();
       a1.title="a";
       
       A a2=new A();
       a2.title="b"; 
       
        A a3=new A();
       a3.title="c";
   -->
  <script>
    new Vue({
    	el:'#category',
    	data:{
    		title:'맛집 카테고리'
    	}
    })
    
    new Vue({
    	el:'#news',
    	data:{
    		title:'네이버 뉴스'
    	}
    })
    
    new Vue({
    	el:'#movie',
    	data:{
    		title:'영화 정보'
    	}
    })
  </script>
</body>
</html>






