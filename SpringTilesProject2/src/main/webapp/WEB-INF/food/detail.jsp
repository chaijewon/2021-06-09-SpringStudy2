<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
   margin: 0px auto;
   width:100%
}
</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['리뷰', '리뷰현황'],
          ['맛있다',   <c:out value="${vo.good}"/>],
          ['괜찮다',   <c:out value="${vo.soso}"/>],
          ['별로',  <c:out value="${vo.bad}"/>]
        ]);

        var options = {
          title: '리뷰'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
</head>
<body>
  <div style="height: 30px"></div>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
         <c:forTokens items="${vo.poster }" delims="^" var="img">
           <td>
            <img src="${img }" width="100%">
           </td>
         </c:forTokens>
        </tr>
      </table>
    </div>
    <div class="row">
      <div class="col-sm-7">
        <table class="table">
          <tr>
           <td>
            <h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3>
           </td>
          </tr>
        </table>
        <table class="table">
          <tr>
            <th width=20% style="color:gray">주소</th>
            <td width=80%>${addr1 }<p>${addr2 }</td>
          </tr>
          <tr>
            <th width=20% style="color:gray">전화번호</th>
            <td width=80%>${vo.tel }</td>
          </tr>
          <tr>
            <th width=20% style="color:gray">음식종류</th>
            <td width=80%>${vo.type }</td>
          </tr>
          <tr>
            <th width=20% style="color:gray">가격대</th>
            <td width=80%>${vo.price }</td>
          </tr>
          <c:if test="${vo.parking!='no' }">
            <tr>
             <th width=20% style="color:gray">주차</th>
             <td width=80%>${vo.parking }</td>
            </tr>
          </c:if>
          <c:if test="${vo.time!='no' }">
            <tr>
             <th width=20% style="color:gray">영업시간</th>
             <td width=80%>${vo.time }</td>
            </tr>
          </c:if>
          <c:if test="${vo.menu!='no' }">
            <tr>
             <th width=20% style="color:gray">메뉴</th>
             <td width=80%>
               <ul>
                <c:forTokens items="${vo.menu }" delims="원" var="m">
                  <li>${m }</li>
                </c:forTokens>
               </ul>
             </td>
            </tr>
          </c:if>
        </table>
        <div id="piechart" style="width: 450px; height: 300px;"></div>
      </div>
      <div class="col-sm-5">
        <div id="map" style="width:100%;height:350px;"></div>

		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=676eb5fa2637b234997b24dd7566e9ba&libraries=services"></script>
		<script>
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
		geocoder.addressSearch('${addr1}', function(result, status) {
		
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
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name}</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
		</script>
      </div>
    </div>
    <div style="height: 20px"></div>
    <h1>맛집 관련 레시피</h1>
    <hr>
    <div class="row">
      <c:forEach var="rvo" items="${list }">
        <div class="col-md-3">
		    <div class="thumbnail">
		      <a href="../recipe/detail.do?no=${rvo.no }">
		        <img src="${rvo.poster }" title="${rvo.chef }" style="width:100%">
		        <div class="caption">
		          <p>${rvo.title }</p>
		        </div>
		      </a>
		    </div>
		  </div>
      </c:forEach>
    </div>
  </div>
</body>
</html>











