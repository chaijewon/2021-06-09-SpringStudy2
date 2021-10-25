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
   width:800px;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
  <div class="container">
    <h1>영화 목록</h1>
    <div class="row">
      <!--  v-for -->
      <table class="table">
        <tr>
          <th class="text-center">순위</th>
          <th class="text-center"></th>
          <th class="text-center">영화명</th>
          <th class="text-center">장르</th>
        </tr>
        <%--
           for(MovieVO vo:movieList)
           {
              <tr >
                <td>~~
              </tr>
           }
           
           v-for="i in 숫자"  => 일반 for
           v-for="i in 배열명" => forEach구문
         --%>
        <tr v-for="vo in movie"><!--  vo = {} -->
          <td class="text-center">{{vo.rownum}}</td>
          <!-- img, a 태그 출력 ==> :속성 -->
          <td class="text-center"><img :src="'https://www.kobis.or.kr'+vo.thumbUrl" width=30 height=30></td>
          <td class="text-left">{{vo.movieNm}}</td>
          <td class="text-center">{{vo.genre}}</td>
        </tr>
      </table>
      <ul>
        <li v-for="i in 10">{{i}}</li>
      </ul>
    </div>
  </div>
  <!-- 
       [{},{},{},{}...]  {}(VO)=>영화 1개에 대한 정보 (객체)
       {},{},{}...         ==== List로 묶어서 사용 
       [{},{},{}..] => List
   -->
  <script>
  new Vue({
	 el:'.row',
	 data:{
		 // 스프링의 16장 => (JSON을 제작하는 방법) => Rest API (Ajax,Vue,React,Kotlin) (JSON,XML)
		 movie:[{"rownum":1,"saleTotcnt":4284,"movieCd":"20204117","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"모가디슈","opendt":"2021-07-28","repgenrenm":"액션","rank":1,"rankInten":0,"rankOldAndNew":"OLD","synop":"내전으로 고립된 낯선 도시, 모가디슈 \r\n지금부터 우리의 목표는 오로지 생존이다!\r\n \r\n대한민국이 UN가입을 위해 동분서주하던 시기 \r\n1991년 소말리아의 수도 모가디슈에서는 일촉즉발의 내전이 일어난다.\r\n통신마저 끊긴 그 곳에 고립된 대한민국 대사관의 직원과 가족들은\r\n총알과 포탄이 빗발치는 가운데, 살아남기 위해 하루하루를 버텨낸다.\r\n그러던 어느 날 밤, 북한 대사관의 일행들이 도움을 요청하며 문을 두드리는데…\r\n \r\n목표는 하나, 모가디슈에서 탈출해야 한다!\r\n","movieNmEn":"Escape from Mogadishu","repNationCd":"한국","movieType":"장편","moviePrdtStat":"개봉","genre":"액션,드라마","showTm":"121","showTs":"2","watchGradeNm":"15세이상관람가","openDt":"20210728","thumbUrl":"/common/mast/movie/2021/07/thumb/thn_9e61ddf5d3564062af12a3c141148936.jpg","prdtYear":"2021","indieYn":null,"artmovieYn":null,"multmovieYn":null,"director":"류승완","prNm":"(주)덱스터스튜디오,(주)외유내강,(주)필름케이","dtNm":"롯데컬처웍스(주)롯데엔터테인먼트"},{"rownum":2,"saleTotcnt":1004,"movieCd":"20218349","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"정글 크루즈","opendt":"2021-07-28","repgenrenm":"액션","rank":2,"rankInten":0,"rankOldAndNew":"OLD","synop":"<캐리비안의 해적> 디즈니 제작! 이번엔 아마존이다!\r\n\r\n미지의 세계 아마존에서 관광객들에게 최고의 스릴을 선사하는\r\n재치 넘치는 크루즈 선장 프랭크(드웨인 존슨).\r\n\r\n고대 아마존의 전설을 쫓아 영국에서 온 식물 탐험가 릴리 박사(에밀리 블런트)가\r\n의학의 미래를 바꿀 치유의 나무를 찾는 여정에 함께 할 것을 제안하면서,\r\n순탄치 않은 모험을 시작하게 된다.\r\n\r\n달라도 너무 다른 두 사람은 아름답지만 온갖 위험이 도사리는 열대우림으로 함께 모험을 떠나고\r\n수많은 역경과 초자연적인 힘을 마주하게 된다.\r\n\r\n고대 나무에 얽힌 비밀이 드러날수록 릴리와 프랭크는 더욱더 커다란 위험에 처하고\r\n인류의 운명도 위태로워지는데…\r\n\r\n전설을 믿는다면 저주도 믿어야 한다!","movieNmEn":"Jungle Cruise","repNationCd":"미국","movieType":"장편","moviePrdtStat":"개봉","genre":"액션,어드벤처","showTm":"127","showTs":"9","watchGradeNm":"12세이상관람가","openDt":"20210728","thumbUrl":"/common/mast/movie/2021/07/thumb/thn_0c8342c1d11d4f5d86285040c1db7cec.jpg","prdtYear":"2021","indieYn":null,"artmovieYn":null,"multmovieYn":null,"director":"자움 콜렛 세라","prNm":null,"dtNm":"월트디즈니컴퍼니코리아 유한책임회사"},{"rownum":3,"saleTotcnt":865,"movieCd":"20196270","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"싱크홀","opendt":"2021-08-11","repgenrenm":"코미디","rank":3,"rankInten":0,"rankOldAndNew":"OLD","synop":"사.상.초.유! 도심 속 초대형 재난 발생!\r\n\r\n서울 입성과 함께 내 집 마련의 꿈을 이룬 가장 ‘동원(김성균)’ \r\n이사 첫날부터 프로 참견러 ‘만수’(차승원)와 사사건건 부딪힌다.\r\n \r\n‘동원’은 자가취득을 기념하며 직장 동료들을 집들이에 초대하지만\r\n행복한 단꿈도 잠시, 순식간에 빌라 전체가 땅 속으로 떨어지고 만다.\r\n \r\n마주치기만 하면 투닥거리는 빌라 주민 ‘만수’와 ‘동원’\r\n‘동원’의 집들이에 왔던 ‘김대리’(이광수)와 인턴사원 ‘은주’(김혜준)까지!\r\n지하 500m 싱크홀 속으로 떨어진 이들은 과연 무사히 빠져나갈 수 있을까?\r\n\r\n“한 500m 정도는 떨어진 것 같아”\r\n“우리… 나갈 수 있을까요?” \r\n","movieNmEn":"Sinkhole","repNationCd":"한국","movieType":"장편","moviePrdtStat":"개봉","genre":"코미디","showTm":"113","showTs":"56","watchGradeNm":"12세이상관람가","openDt":"20210811","thumbUrl":"/common/mast/movie/2021/07/thumb/thn_1244e3eee57149aa8542763d572a1253.jpg","prdtYear":"2021","indieYn":null,"artmovieYn":null,"multmovieYn":null,"director":"김지훈","prNm":"(주)더타워픽쳐스","dtNm":"(주)쇼박스"},{"rownum":4,"saleTotcnt":680,"movieCd":"20218392","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"올드","opendt":"2021-08-18","repgenrenm":"공포(호러)","rank":4,"rankInten":1,"rankOldAndNew":"OLD","synop":"아침에는 아이, 오후에는 어른, 저녁에는 노인\r\n죽음은 시간의 문제다.","movieNmEn":"Old","repNationCd":"미국","movieType":"장편","moviePrdtStat":"개봉","genre":"공포(호러),스릴러","showTm":"108","showTs":"27","watchGradeNm":"12세이상관람가","openDt":"20210818","thumbUrl":"/common/mast/movie/2021/07/thumb/thn_4e8b0aa3119248ea959210b269e593f7.jpg","prdtYear":"2021","indieYn":null,"artmovieYn":null,"multmovieYn":null,"director":"M. 나이트 샤말란","prNm":null,"dtNm":"유니버설픽쳐스인터내셔널 코리아(유)"},{"rownum":5,"saleTotcnt":667,"movieCd":"20192194","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"인질","opendt":"2021-08-18","repgenrenm":"액션","rank":5,"rankInten":-1,"rankOldAndNew":"OLD","synop":"배우 황정민 '인질'로 잡혔다!\r\n\r\n평소와 똑같던 어느 새벽,\r\n서울 한복판에서 증거도, 목격자도 없이 대한민국 톱배우 '황정민'이 납치된다.\r\n\r\n한 치 앞을 알 수 없는 상황 속\r\n살기 위한 극한의 탈주가 시작되는데…\r\n\r\n올여름, 관객들을 사로잡을 리얼리티 액션스릴러가 온다!","movieNmEn":"Hostage: Missing Celebrity","repNationCd":"한국","movieType":"장편","moviePrdtStat":"개봉","genre":"액션,스릴러","showTm":"93","showTs":"34","watchGradeNm":"15세이상관람가","openDt":"20210818","thumbUrl":"/common/mast/movie/2021/09/thumb/thn_1f540d6b6a144e2da59bf7e378bc5478.jpg","prdtYear":"2021","indieYn":null,"artmovieYn":null,"multmovieYn":null,"director":"필감성","prNm":"(주)외유내강,(주)샘컴퍼니","dtNm":"(주)넥스트엔터테인먼트월드(NEW)"},{"rownum":6,"saleTotcnt":635,"movieCd":"20218758","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"수색자","opendt":"2021-09-29","repgenrenm":"스릴러","rank":6,"rankInten":0,"rankOldAndNew":"OLD","synop":"억울하게 죽은 영혼들의 무덤 DMZ\r\n\r\n어두운 밤 총성이 울린 후 파견 나온 교육장교가 사망하는 사건이 일어난다.\r\n같은 시각 출입통제구역 DMZ로 탈영병이 도주하는 일이 발생하고\r\n3소대는 DMZ 수색 작전에 긴급 투입된다.\r\n\r\n그곳에서 대원들은 탈영병도, 수색 대원도 아닌 정체불명의 병사를 목격한다.\r\n그리고 알 수 없는 죽음의 릴레이가 시작되는데...\r\n \r\n모든 건 바로 그날 시작되었다!","movieNmEn":"The Recon","repNationCd":"한국","movieType":"장편","moviePrdtStat":"개봉","genre":"스릴러","showTm":"110","showTs":"54","watchGradeNm":"15세이상관람가","openDt":"20210929","thumbUrl":"/common/mast/movie/2021/09/thumb/thn_9ecad3d347b24328af380508842ba23a.jpg","prdtYear":"2021","indieYn":"Y","artmovieYn":null,"multmovieYn":null,"director":"김민섭","prNm":"케이필름","dtNm":"(주)콘텐츠판다"},{"rownum":7,"saleTotcnt":557,"movieCd":"20218604","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"극장판 짱구는 못말려: 격돌! 낙서왕국과 얼추 네 명의 용사들","opendt":"2021-09-15","repgenrenm":"애니메이션","rank":7,"rankInten":1,"rankOldAndNew":"OLD","synop":"아이들의 낙서가 사라져 붕괴 위기에 처한 낙서왕국은 \r\n낙서 에너지를 모으기 위해 지구 침공을 시작한다. \r\n\r\n낙서왕국의 위험한 작전을 막기 위해 \r\n지상의 용사로 선택 받은 짱구는\r\n그림에 생명을 불어넣는 ‘미라클 크레용’을 얻게 된다. \r\n\r\n쓰윽 쓰윽~ 그려 그려~!\r\n짱구가 미라클 크레용으로 그림을 그리자\r\n브리프, 가짜 이슬이 누나, 부리부리 용사가\r\n스케치북 밖으로 튀어나오는데..!\r\n\r\n과연, 크레용 용사 짱구는 낙서 용사들과 함께\r\n위험에 빠진 떡잎마을과 세계를 구할 수 있을까?!","movieNmEn":"Crayon Shin-chan: Crash! Scribble Kingdom and Almost Four Heroes","repNationCd":"일본","movieType":"장편","moviePrdtStat":"개봉","genre":"애니메이션","showTm":"103","showTs":"8","watchGradeNm":"전체관람가","openDt":"20210915","thumbUrl":"/common/mast/movie/2021/09/thumb/thn_084b6dca2de449a1aee7757a90d30325.jpg","prdtYear":"2020","indieYn":null,"artmovieYn":null,"multmovieYn":null,"director":"쿄고쿠 타카히코","prNm":null,"dtNm":"(주)씨제이이엔엠"},{"rownum":8,"saleTotcnt":495,"movieCd":"20216801","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"극장판 포켓몬스터: 정글의 아이, 코코","opendt":"2021-09-15","repgenrenm":"애니메이션","rank":8,"rankInten":3,"rankOldAndNew":"OLD","synop":"인간들의 마을에서 멀리 떨어진 포켓몬들의 낙원 자부숲에서\r\n엄격한 법도를 지키며 무리들과 함께 살아가던 ‘자루도’는\r\n우연히 강가에서 인간의 아이를 발견하게 된다.\r\n\r\n차마 아이를 외면할 수 없었던 ‘자루도’는\r\n숲의 법도를 깨고 아이에게 ‘코코’라는 이름을 붙여주며\r\n무리에서 떨어져 둘이서만 살기로 결심한다.\r\n\r\n그로부터 10년 뒤,\r\n자신을 포켓몬이라고 믿는 소년 ‘코코’는\r\n자부숲을 찾아온 ‘지우’와 ‘피카츄’를 만나 새로운 세계를 알게 되는데…\r\n\r\n한편, 자부숲을 지켜주는 치유의 샘을 노리는\r\n위험한 인간들의 발소리가 들려오기 시작한다!\r\n정글의 아이 ‘코코’는 친구들과 힘을 모아 숲을 지킬 수 있을까?","movieNmEn":"Pokemon the Movie: Secrets of the Jungle","repNationCd":"일본","movieType":"장편","moviePrdtStat":"개봉","genre":"애니메이션","showTm":"98","showTs":"30","watchGradeNm":"전체관람가","openDt":"20210915","thumbUrl":"/common/mast/movie/2021/08/thumb/thn_860f45aa8bc4485684faafcad479113f.jpg","prdtYear":"2020","indieYn":null,"artmovieYn":null,"multmovieYn":null,"director":"야지마 테츠오","prNm":null,"dtNm":"(주)넥스트엔터테인먼트월드(NEW)"},{"rownum":9,"saleTotcnt":428,"movieCd":"20191951","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"블랙 위도우","opendt":"2021-07-07","repgenrenm":"액션","rank":9,"rankInten":0,"rankOldAndNew":"OLD","synop":"“모든 것을 바꾼 그녀의 선택”\r\n어벤져스의 운명을 바꾼 블랙 위도우, 그녀의 진짜 이야기가 시작된다!\r\n\r\n어벤져스의 히어로 블랙 위도우, ‘나타샤 로마노프’ (스칼렛 요한슨)는\r\n자신의 과거와 연결된 레드룸의 거대한 음모와 실체를 깨닫게 된다.\r\n\r\n상대의 능력을 복제하는 빌런 ‘태스크마스터’와 새로운 위도우들의 위협에 맞서\r\n목숨을 건 반격을 시작하는 ‘나타샤’는 스파이로 활약했던 자신의 과거 뿐 아니라,\r\n어벤져스가 되기 전 함께했던 동료들을 마주해야만 하는데…\r\n\r\n폭발하는 리얼 액션 카타르시스!\r\n7월, MCU의 새로운 시대를 시작할 첫 액션 블록버스터를 만끽하라!","movieNmEn":"Black Widow","repNationCd":"미국","movieType":"장편","moviePrdtStat":"개봉","genre":"액션,어드벤처,SF","showTm":"133","showTs":"36","watchGradeNm":"12세이상관람가","openDt":"20210707","thumbUrl":"/common/mast/movie/2021/06/thumb/thn_f5b9324a8a5b4c01977419a6e6442ab9.jpg","prdtYear":"2021","indieYn":null,"artmovieYn":null,"multmovieYn":null,"director":"케이트 쇼트랜드","prNm":null,"dtNm":"월트디즈니컴퍼니코리아 유한책임회사"},{"rownum":10,"saleTotcnt":368,"movieCd":"20192986","salesDate":"20211022","startDate":"2021년 10월 22일(금)","movieNm":"프리 가이 ","opendt":"2021-08-11","repgenrenm":"액션","rank":10,"rankInten":2,"rankOldAndNew":"OLD","synop":"“내 안의 히어로가 깨어난다!”\r\n\r\n평범한 직장, 절친 그리고 한 잔의 커피.\r\n평화로운 일상 속 때론 총격전과 날강도가 나타나는\r\n버라이어티한 ‘프리시티’에 살고 있는 ‘가이’.\r\n\r\n그에겐 뭐 하나 부족한 것이 없었다.\r\n우연히 마주친 그녀에게 한눈에 반하기 전까지는…\r\n\r\n갖은 노력 끝에 다시 만난 그녀는\r\n‘가이’가 비디오 게임 ‘프리시티’에 사는 배경 캐릭터이고,\r\n이 세상은 곧 파괴될 거라 경고한다.\r\n\r\n혼란에 빠진 ‘가이’\r\n그러나 그는 ‘프리시티’의 파괴를 막기 위해\r\n더 이상 배경 캐릭터가 아닌, 히어로가 되기로 결심한다.\r\n\r\n올여름, 시원하게 터지는 상상초월 엔터테이닝 액션 블록버스터\r\n8월, 인생의 판을 바꿀 짜릿한 반란이 시작된다!","movieNmEn":"Free Guy","repNationCd":"미국","movieType":"장편","moviePrdtStat":"개봉","genre":"액션,어드벤처","showTm":"115","showTs":"0","watchGradeNm":"12세이상관람가","openDt":"20210811","thumbUrl":"/common/mast/movie/2021/07/thumb/thn_f8b91a89cc974c8abb03b84fa3c22555.jpg","prdtYear":"2021","indieYn":null,"artmovieYn":null,"multmovieYn":null,"director":"숀 레비","prNm":null,"dtNm":"월트디즈니컴퍼니코리아 유한책임회사"}]
	 }
  })
  </script>
</body>
</html>











