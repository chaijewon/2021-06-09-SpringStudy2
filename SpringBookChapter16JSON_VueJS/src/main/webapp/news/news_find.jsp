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
     <h1>네이버 뉴스 검색</h1>
     <div class="row">
      <input type=text size=30 class="input-sm" v-model="ss">
      <button class="btn btn-sm btn-primary" v-on:click="newsFind()">검색</button>
     </div>
     <div style="height: 50px"></div>
     <div class="row">
       <table class="table" v-for="vo in news_data">
        <tr>
         <th colspan="2">{{vo.title}}</th>
        </tr>
        <tr>
         <td colspan="2"><a :href="vo.link">{{vo.desc}}</a></td>
        </tr>
        <tr>
         <td class="text-left">{{vo.date}}</td>
         <td class="text-right">{{vo.author}}</td>
        </tr>
       </table>
     </div>
   </div>
   <script>
     new Vue({
    	 el:'.container',
    	 data:{
    		 ss:'맛집',
    		 news_data:[]
    	 },
    	 // 출력전에 데이터 읽기 
    	 /*
    	     created => mounted => updated => destoryed
    	                           ======== 검색어가 변경되었을 때 
    	     =======    ======== 서버로부터 데이터 읽기 
    	    	 생성자 : 멤버변수 초기화 
    	    	 
    	     mounted : jquery 연동 , 일반자바스크립트 연동 => repiant
    	     updated : 변경된 값을 가지고 repaint
    	 */
    	 mounted:function(){
    		 axios.get("http://localhost:8080/web/news/news_find.do",{
    			 params:{
    				 ss:this.ss
    			 }
    		 }).then(res=>{
    			 this.news_data=res.data
    		 })
    		 console.log("mounted Call... (브라우저에서 HTML을 출력 완료 : 한번만 호출된다)")
    	 },
    	 updated:function(){
    		 console.log("updated Call... (검색어 변경) ss="+this.ss+" 갱신=화면 변경");
    		 
    	 },
    	 methods:{
    		 newsFind:function(){
    			 axios.get("http://localhost:8080/web/news/news_find.do",{
        			 params:{
        				 ss:this.ss
        			 }
        		 }).then(res=>{
        			 this.news_data=res.data
        		 })
    		 }
    	 }
     })
   </script>
</body>
</html>






