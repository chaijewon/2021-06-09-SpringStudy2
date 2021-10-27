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
   width:1200px;
}
h1{
   text-align: center;
}
</style>
</head>
<%--
 artmovieYn: null
audiCnt: 29152
director: "앤디 서키스"
dtNm: "소니픽쳐스엔터테인먼트코리아주식회사극장배급지점"
endDate: "2021년 10월 26일(화)"
endYearDate: "2021.10.26"
genre: "액션,SF,스릴러"
indieYn: null
movieCd: "20218052"
movieNm: "베놈 2: 렛 데어 비 카니지"
movieNmEn: "Venom: Let There Be Carnage"
moviePrdtStat: "개봉"
movieType: "장편"
multmovieYn: null
openDt: "20211013"
prNm: null
prdtYear: "2021"
rank: 1
rankInten: 0
rankOldAndNew: "OLD"
repNationCd: "미국"
rownum: 1
salesAmt: 279183860
scrCnt: 1347
showCnt: 4960
showDt: "20211026"
showTm: "97"
showTs: "10"
startDate: "2021년 10월 26일(화)"
startYearDate: "2021.10.26"
synop: "히어로의 시대는 끝났다\r\n‘베놈’과 완벽한 파트너가 된 ‘에디 브록’(톰 하디) 앞에 ‘클리터스 캐서디’(우디 해럴슨)가 ‘카니지’로 등장, \r\n앞으로 닥칠 대혼돈의 세상을 예고한다. \r\n대혼돈의 시대가 시작되고, \r\n악을 악으로 처단할 것인가?"
thumbUrl: "/common/mast/movie/2021/09/thumb/thn_7f0692add69b4b328c23bc88839bb63d.jpg"
watchGradeNm: "15세이상관람가"
 --%>
<body>
  <div class="container">
    <h1>{{title}}</h1>
    <div class="row">
      <div class="text-center">
        <button class="btn btn-success btn-sm" v-on:click="movieData(1,'일별 박스오피스')">일별 박스오피스</button>
        <button class="btn btn-danger btn-sm" v-on:click="movieData(2,'실시간 예매율')">실시간 예매율</button>
        <button class="btn btn-info btn-sm" v-on:click="movieData(3,'좌석 점유율')">좌석 점유율</button>
        <button class="btn btn-primary btn-sm" v-on:click="movieData(4,'온라인 이용순위')">온라인 이용순위</button>
      </div>
    </div>
    <div style="height:50px"></div>
    <div class="row">
      <div class="col-md-6">
        <!-- 상세보기  -->
        <table class="table">
          <tr>
           <td class="text-center" width=30% rowspan="10">
             <img :src="'https://www.kobis.or.kr'+movie_detail.thumbUrl" width=100%>
           </td>
           <td colspan="2">
             {{movie_detail.movieNm}}
             <sup>{{movie_detail.movieNmEn}}</sup>
           </td>
          </tr>
          <tr>
            <td width=20%>개봉일</td>
            <td width=50%>{{movie_detail.startDate}}</td>
          </tr>
          <tr>
            <td width=20%>제작상태</td>
            <td width=50%>{{movie_detail.moviePrdtStat}}</td>
          </tr>
          <tr>
            <td width=20%>영화구분</td>
            <td width=50%>{{movie_detail.movieType}}</td>
          </tr>
          <tr>
            <td width=20%>관람등급</td>
            <td width=50%>{{movie_detail.watchGradeNm}}</td>
          </tr>
          <tr>
            <td width=20%>상영시간</td>
            <td width=50%>{{movie_detail.showTm}}</td>
          </tr>
          <tr>
            <td width=20%>제작국가</td>
            <td width=50%>{{movie_detail.repNationCd}}</td>
          </tr>
          <tr>
            <td width=20%>감독</td>
            <td width=50%>{{movie_detail.director}}</td>
          </tr>
          <tr>
            <td width=20%>장르</td>
            <td width=50%>{{movie_detail.genre}}</td>
          </tr>
          <tr>
           <td colspan="3">
            {{movie_detail.synop}}
           </td>
          </tr>
        </table>
      </div>
      <div class="col-md-6">
        <table class="table">
         <tr>
           <th class="text-center">순위</th>
           <th class="text-center"></th>
           <th class="text-center">영화명</th>
           <th class="text-center">감독</th>
           <th class="text-center">장르</th>
         </tr>
         <tr v-for="vo in movie_data">
           <td class="text-center">{{vo.rank}}</th>
           <td class="text-center">
            <img :src="'https://www.kobis.or.kr'+vo.thumbUrl" width=30 height=30 v-on:mouseover="mouseData(vo.rank)">
           </th>
           <td class="text-left">{{vo.movieNm}}</th>
           <td class="text-left">{{vo.director}}</th>
           <td class="text-left">{{vo.genre}}</th>
         </tr>
        </table>
      </div>
    </div>
  </div>
  <script>
   new Vue({
	   el:'.container',
	   data:{
		   title:'일별 박스오피스',
           no:1,
           movie_data:[],
           movie_detail:{}
	   },
	   mounted:function(){
		 // default 출력  
		 this.commonFunc();
	   },
	   // 사용자 정의 함수 => 이벤트 처리 
	   methods:{
		   movieData:function(no,title){
			   this.no=no;
			   this.title=title;
			   this.commonFunc();
		   },
		   commonFunc:function(){
			// 스프링에 요청 
			axios.get("http://localhost:8080/web/movie/rest_list.do",{
					 params:{
						 no:this.no
					 }
				 }).then(res=>{
					 // 응답값을 받는다 => Ajax success:function(res)
					 console.log(res.data);
					 this.movie_data=res.data;
					 console.log(this.movie_data[0].thumbUrl);
				 })
		   },
		   mouseData:function(rank){
			   console.log("rank="+rank);
			   this.movie_detail=this.movie_data[rank-1];
		   }
	   }
   })
  </script>
</body>
</html>








