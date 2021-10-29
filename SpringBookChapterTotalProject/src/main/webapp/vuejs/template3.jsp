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
   width:100%;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
   <div class="container">
    <h1>사원 목록</h1>
    <div class="row">
     <emp-list :emp="emp_data"></emp-list>
    </div>
   </div>
   <!-- javascript를 따로 제작  => .vue -->
   <script>
    Vue.component('emp-list',{
    	props:['emp'],
    	template:'<table class="table">'
    	        +'<tr>'
    	        +'<th class="text-center">사번</th>'
    	        +'<th class="text-center">이름</th>'
    	        +'<th class="text-center">직위</th>'
    	        +'<th class="text-center">부서</th>'
    	        +'<th class="text-center">근무지</th>'
    	        +'</tr>'
    	        +'<tr v-for="vo in emp">'
    	        +'<td class="text-center">{{vo.empno}}</td>'
    	        +'<td class="text-center">{{vo.ename}}</td>'
    	        +'<td class="text-center">{{vo.job}}</td>'
    	        +'<td class="text-center">{{vo.dname}}</td>'
    	        +'<td class="text-center">{{vo.loc}}</td>'
    	        +'</tr>'
    	        +'</table>'
    })
    new Vue({
    	el:'.container',
    	data:{
    		emp_data:[]
    	},
    	mounted:function(){
    		axios.get("http://localhost:8080/web/vuejs/rest_emp_list.do")
    		.then(res => {
    			console.log(res.data);
    			this.emp_data=res.data;
    		})
    	}
    })
   </script>
</body>
</html>









