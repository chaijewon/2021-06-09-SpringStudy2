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
.image:hover,.title:hover{
   cursor: pointer;
   border:2px solid red;
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
      <!-- main body -->
      
      <div class="content two_quarter first"> 
        <!-- 화면 분할  상세보기 출력 -->
        <div class="jumbotron">
          <h2 class="text-center">{{cate_info.title}}</h2>
          <h3 class="text-center">{{cate_info.subject}}</h3>
        </div>
        <table class="table" v-for="vo in cate_list">
          <tr>
            <td class="text-center" width=30% rowspan="4">
              <img :src="vo.poster" width=100% v-on:click="food_detail(vo.no,vo.address)" class="image">
            </td>
            <td width=70%><span v-on:click="food_detail(vo.no,vo.address)" class="title">{{vo.name}}</span>&nbsp;<span style="color:orange">{{vo.score}}</span></td>
          </tr>
          <tr>
            <td width=70%>주소:{{vo.address}}</td>
          </tr>
          <tr>
            <td width=70%>전화:{{vo.tel}}</td>
          </tr>
          <tr>
            <td width=70%>음식종류:{{vo.type}}</td>
          </tr>
        </table>
      </div>
        
      <div class="sidebar two_quarter"> 
        <!-- 지도 , 관련 데이터 (레시피 , 명소 , 호텔 )-->
        <div v-show="isShow">
	        <table class="table">
	          <tr>
	           <td class="text-center" v-for="img in (detail_data.poster||'').split('^')">
	            <img :src="img" width=100%>
	           </td>
	          </tr>
	        </table>
	        <table class="table">
	         <tr>
	           <td colspan="2"><h3>{{detail_data.name}}&nbsp;
	           <span style="color:orange">{{detail_data.score}}</span></h3></td>
	         </tr>
	         <tr>
	           <th class="text-right" width=15%>주소</th>
	           <td width=85%>{{detail_data.address}}</td>
	         </tr>
	         <tr>
	           <th class="text-right" width=15%>전화</th>
	           <td width=85%>{{detail_data.tel}}</td>
	         </tr>
	         <tr>
	           <th class="text-right" width=15%>음식종류</th>
	           <td width=85%>{{detail_data.type}}</td>
	         </tr>
	         <tr v-if="detail_data.time!='no'">
	           <th class="text-right" width=15%>영업시간</th>
	           <td width=85%>{{detail_data.time}}</td>
	         </tr>
	         <tr v-if="detail_data.parking!='no'">
	           <th class="text-right" width=15%>주차</th>
	           <td width=85%>{{detail_data.parking}}</td>
	         </tr>
	         <tr v-if="detail_data.menu!='no'">
	           <th class="text-right" width=15%>메뉴</th>
	           <td width=85%>
	            <ul>
	             <li v-for="m in (detail_data.menu||'').split('원')">{{m}}</li>
	            </ul>
	           </td>
	         </tr>
	        </table>
	        <!-- 인근 명소 -->
	        <table class="table">
	          <caption>인근명소</caption>
	          <tr>
	           <td class="text-center" v-for="vo in loc_data">
	             <img :src="vo.poster" style="width:103px;height:90px" :title="vo.title">
	           </td>
	          </tr>
	        </table>
	        <!-- 인근 호텔 -->
	        <table class="table">
	          <caption>인근호텔</caption>
	          <tr>
	           <td class="text-center" v-for="vo in hotel_data">
	             <img :src="vo.poster" style="width:103px;height:90px" :title="vo.name">
	           </td>
	          </tr>
	        </table>
	        <!-- 인근 자연 -->
	        <table class="table">
	          <caption>인근자연</caption>
	          <tr>
	           <td class="text-center" v-for="vo in nature_data">
	             <img :src="vo.poster" style="width:103px;height:90px" :title="vo.title">
	           </td>
	          </tr>
	        </table>
	      </div>
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
  		cate_list:[],  // 카테고리별 맛집 리스트
  		isShow:false,
  		loc_data:[],
  		hotel_data:[],
  		nature_data:[]
  	},
  	mounted:function(){
  		let _this=this;
  		axios.get("http://localhost/web/food/rest_detail.do",{
  			params:{
  				cno:_this.cno
  			}
  		}).then(function(response){
  			_this.cate_list=response.data;
  		})
  		
  		axios.get("http://localhost/web/food/rest_info.do",{
  			params:{
  				cno:_this.cno
  			}
  		}).then(function(response){
  			_this.cate_info=response.data;
  		})
  	},
  	methods:{
  		
  	    // .do?no=1
  		food_detail:function(no,addr){
  			this.isShow=true;
  			let _this=this;//Vue
  			axios.get("http://localhost/web/food/rest_food_detail.do",{
  				params:{
  					no:no
  				}
  			}).then(function(response){
  				console.log(response.data);
  				_this.detail_data=response.data
  			})
  			
  			axios.get("http://localhost/web/food/rest_loc_list.do",{
  				params:{
  					addr:addr
  				}
  			}).then(function(response){
  				console.log(response.data);
  				_this.loc_data=response.data
  			})
  			
  			axios.get("http://localhost/web/food/rest_hotel_list.do",{
  				params:{
  					addr:addr
  				}
  			}).then(function(response){
  				console.log(response.data);
  				_this.hotel_data=response.data
  			})
  			
  			axios.get("http://localhost/web/food/rest_nature_list.do",{
  				params:{
  					addr:addr
  				}
  			}).then(function(response){
  				console.log(response.data);
  				_this.nature_data=response.data
  			})
  		}
  	}
  })
  </script>    
</body>
</html>








