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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=496e89654068a02be75aa025b201a3a7&libraries=services"></script>
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
#image:hover,#span:hover{
    cursor:pointer;
}
</style>
</head>
<%--
    문자열 제어 => substring() , split() , indexOf() , lastIndexOf() , slice()
  1. new Vue({}) : Vue 메모리 할당 생명주기 
     1) (beforeCreate) created => 메모리할당이 완료 , 멤버변수의 초기화 (생성자 호출)  
     2) (beforMount) mounted => HTML이 가상DOM에 저장된 경우
                 사용자 올릴때는 가상 메모리 => 비교 => 실제 DOM에 적용   
     3) (beforeUpdate) updated => 값이 변경 (검색변경 , 클릭) => repaint (HTML을 다시 수행)
     4) (beforeDestory) destoryed => category.jsp=>detail.jsp (메모리에서 객체를 삭제) => gc
 --%>
<body>
  <div class="container-fluid">
    <div class="row">
     <div class="col-md-5">
      <%-- 카테고리별 맛집 --%>
      <div class="jumbotron">
        <h3 class="text-center">{{info.title}}</h3>
        <h4 class="text-center">{{info.subject}}</h4>
      </div>
      <table class="table">
        <tr>
          <td class="text-right">
           <a href="../food/category.do" class="btn btn-sm btn-danger">목록</a>
          </td>
        </tr>
      </table>
      <table class="table" v-for="vo in food">
       <tr>
         <td class="text-center" width=30% rowspan="4">
            <!-- 
               v-on:click="함수명"
               $('img').click(function(){})
               <img onclick="함수명">
                              화면 이동이 아니라 => 데이터만 읽어 와서 옆에 출력
                              <태그>{{}}</태그>
                              <태그 :속성=""> v-bind
               v-on:이벤트 => 사용자 정의 함수 지정  
             -->
           <img :src="vo.poster" style="width:200px;height:150px" v-on:click="food_detail(vo.no)" id="image">
         </td>
         <td colspan="2"><h3 id="span"><b><span v-on:click="food_detail(vo.no)" >{{vo.name}}</span></b>&nbsp;<span style="color:orange">{{vo.score}}</span></h3></td>
       </tr>
       <tr>
         <td width=20% class="text-center">주소</td>
         <td width=50%>{{vo.address.substring(0,vo.address.indexOf('지'))}}
          <!-- <sub>{{vo.address.substring(vo.address.indexOf('지'))}}</sub> -->
         </td>
       </tr>
       <tr>
         <td width=20% class="text-center">전화</td>
         <td width=50%>{{vo.tel}}</td>
       </tr>
       <tr>
         <td width=20% class="text-center">음식종류</td>
         <td width=50%>{{vo.type}}</td>
       </tr>
      </table>
     </div>
     <div class="col-md-7">
      <%-- 맛집 상세보기  --%>
      <div v-show="isShow">
       <table class="table">
        <tr>
          <%-- <c:forTokens> --%>
          <td class="text-center" v-for="image in (detail_data.poster||'').split('^')">
            <img :src="image" width="100%">
          </td> <!-- 이미지 5개 출력 -->
        </tr>
       </table>
       <table class="table">
         <tr>
          <td colspan="2"><h3>{{detail_data.name}}&nbsp;
          <span style="color:orange">{{detail_data.score}}</span></h3></td>
         </tr>
         <tr>
           <td width=20%>주소</td>
           <td width=80%>{{detail_data.address}}</td>
         </tr>
         <tr>
           <td width=20%>전화</td>
           <td width=80%>{{detail_data.tel}}</td>
         </tr>
         <tr>
           <td width=20%>음식종류</td>
           <td width=80%>{{detail_data.type}}</td>
         </tr>
         <tr>
           <td width=20%>가격대</td>
           <td width=80%>{{detail_data.price}}</td>
         </tr>
         <tr>
           <td width=20%>영업시간</td>
           <td width=80%>{{detail_data.time}}</td>
         </tr>
         <tr>
           <td width=20%>주차</td>
           <td width=80%>{{detail_data.parking}}</td>
         </tr>
         <tr v-if="detail_data.menu!='no'">
           <td width=20%>메뉴</td>
           <td width=80%>
            <ul>
              <li v-for="won in (detail_data.menu||'').split('원')">{{won}}원</li>
            </ul>
           </td>
         </tr>
       </table>
       <!-- 코딩 (지도) -->
        
         <div id="map" style="width:100%;height:350px;"></div>
		
      </div>
     </div>
    </div>
  </div>
  <%-- VueJS를 이용해서 데이터를 받아서 출력  
       <script type="text/javascript"> ES5
       <script type="text/babel"> ES6
  --%>
  <script>
   /*
                자바스크립트 초기화
        1. 정수  no:0 
        2. 실수  no:0.0
        3. 문자열 name:''
        4. 배열   arr:[]
        5. 객체  obj:{}
        
        ==> 일반 변수 : 자바스크립트에서도 동일하게 받는다 VO,List => JSON
   */
   new Vue({
	   el:'.container-fluid',
	   // 멤버변수 => this.접근 (this.food,this.info,this.cno)
	   data:{
		   food:[], // List<FoodVO> => [] (JSONArray)
		   info:{}, // CategoryVO => {} (JSONObject)
		   cno:${cno},
		   isShow:false, // 상세보기 화면 출력 (true/출력 , false/감추기) v-show=true,v-show=false
		   // boolean 처리
		   detail_data:{} // FoodVO만 받는다 (1개) (JSONObject)
	   },
	   // 값을 받는다 (요청 / 응답 : axios)
	   mounted:function(){
		  // 여러개를 동시에 받을 수 있다 
		  axios.get("http://localhost:8080/web/food/rest_detail.do",{
			  params:{
				  cno:this.cno
			  }
		  }).then(res=>{
			  this.food=res.data;
		  });
		  
		  axios.get("http://localhost:8080/web/food/rest_category_info.do",{
			  params:{
				  cno:this.cno
			  }
		  }).then(res=>{
			  this.info=res.data;
		  })
		  // (detail_data.menu||'')
		  //this.addKakaoMapScript();
		  //this.initMap();
		  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('서울특별시 강남구 선릉로145길 14', function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;"></div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		})   
	   },
	   // 사용자 정의 함수 
	   /*
	             지역변수 , 멤버변수 (this.변수명)=> data:{멤버변수}
	       function(no,addr) => 지역변수 
	       => JSON을 저장 (몽고디비)
	       [{"cno":1,"subject":"\"제천, 단양 맛집 가보자고\"","title":"제천\/단양 맛집 베스트 5곳",
	    	   "poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/ltkypgxhkzqpxvfh.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"}
	       ,{"cno":2,"subject":"\"평점 높은 맛집이 가득가득!\"","title":"논현동 맛집 베스트 30곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/0rbimn3z8vfzd0xh.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":3,"subject":"\"냉모밀에 진심이신 분...?\"","title":"냉모밀 맛집 베스트 20곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/ymrbrvj6ynnvwcj1.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":4,"subject":"\"요즘 또 서촌이 핫하다던데...!\"","title":"경복궁역 맛집 베스트 20곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/otw2vrgejft1mdna.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":5,"subject":"\"만두를 먹고 싶을 만두 하지!\"","title":"만두 맛집 베스트 45곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/iowhiymuuk0y5oo3.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":6,"subject":"\"이번 주말엔 야외 테라스 고?\"","title":"이태원\/한남동 테라스 맛집 베스트 20곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/s1uxkyqejwinotgr.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":7,"subject":"\"부드러운 연어가 입안을 감싸네\"","title":"사케동 맛집 베스트 20곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/tw6ljh9r41tlfkaq.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":8,"subject":"\"경기,서울권 지하철 1호선 주변 맛.ZIP\"","title":"지하철 1호선 맛집 베스트 40곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/my2ncuuhe1flb8yt.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":9,"subject":"\"얼큰하게 취했을 땐, 여기서 해장!\"","title":"강남역 해장 맛집 베스트 10곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/1hrjb6ar4x4b5fbd.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":10,"subject":"\"커피도 분위기도 중요해\"","title":"분위기 좋은 강동구 카페 베스트 15곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/mphycwiu0wqlvjlb.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":11,"subject":"\"내가 찜해놓은 해물찜\"","title":"해물찜 맛집 베스트 10곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/vxqefqzokbavrwyl.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"},{"cno":12,"subject":"\"국물도 면도 어느 하나 빠지는 게 없군\"","title":"쌀국수 맛집 베스트 40곳","poster":"https:\/\/mp-seoul-image-production-s3.mangoplate.com\/keyword_search\/meta\/pictures\/njx2uca00oomt0fy.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80"}]
	       }
           
	   */
	   methods:{
		   food_detail:function(no){
			   this.isShow=true;
			   // 스프링 서버로부터 상세보기 정보를 JSON으로 받는다 ?no=1
			   let _this=this;
			   axios.get("http://localhost:8080/web/food/rest_detail_data.do",{
				   params:{
					   no:no
				   }
			   }).then(function(res){
				   _this.detail_data=res.data;
			   })
		   } /* ,
		       addKakaoMapScript() {
			      const script = document.createElement("script");
			      
			      script.onload = () => kakao.maps.load(this.initMap);
			      script.src =
			        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=676eb5fa2637b234997b24dd7566e9ba&libraries=services";
			      document.head.appendChild(script);
			    },
			    initMap() {
			    	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				    mapOption = {
				        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				        level: 3 // 지도의 확대 레벨
				    };  
				
				// 지도를 생성합니다    
				var map = new kakao.maps.Map(mapContainer, mapOption); 
				
				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new kakao.maps.services.Geocoder();
				
				// 주소로 좌표를 검색합니다
				geocoder.addressSearch(`${detail_data.address.substring(0,detail_data.address.indexOf("지"))}`, function(result, status) {
				
				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {
				
				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				
				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new kakao.maps.Marker({
				            map: map,
				            position: coords
				        });
				
				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        var infowindow = new kakao.maps.InfoWindow({
				            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+detail_data.name+'</div>'
				        });
				        infowindow.open(map, marker);
				
				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				});    
			    } */
			  
	   }
   })
  </script>
</body>
</html>





