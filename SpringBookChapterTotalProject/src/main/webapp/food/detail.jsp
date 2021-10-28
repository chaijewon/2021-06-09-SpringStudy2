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
	<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/01.png');">
	  <div id="breadcrumb" class="hoc clear"> 
	  </div>
	</div>
	
    <div class="wrapper row3">
     <main class="hoc container clear"> 
      <!-- main body -->
      
      <div class="content two_quarter first"> 
        <!-- 화면 분할  상세보기 출력 -->
      </div>
   
      <div class="sidebar two_quarter"> 
        <!-- 지도 , 관련 데이터 (레시피 , 명소 , 호텔 )-->
      </div>
    <!-- ################################################################################################ -->
    <!-- / main body -->
    
     <div class="clear"></div><!-- 화면 통합 -->
    </main>
  </div>
  <script>
    new Vue({
    	el:'.container',
    	data:{
    		detail_data:{}, // {} = vo , [] = list
    		cno:${cno},
    		cate_info:{},
    		cate_list:[]  // 카테고리별 맛집 리스트
    	},
    	mounted:function(){
    		axiox.get("http://localhost:8080/web/food/rest_detail.do",{
    			params:{
    				cno:this.cno
    			}
    		}).then(response=>{
    			this.cate_list=response.data;
    		})
    		
    		axiox.get("http://localhost:8080/web/food/rest_info.do",{
    			params:{
    				cno:this.cno
    			}
    		}).then(response=>{
    			this.cate_info=response.data;
    		})
    	},
    	methods:{
    		
    	}
    })
  </script>    
</body>
</html>








