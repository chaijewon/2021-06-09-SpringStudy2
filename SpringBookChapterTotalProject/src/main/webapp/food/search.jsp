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
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="hoc container clear"> 
    <!-- main body -->
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading">지역별 맛집 찾기</header>
          <div class="inline">
            Search:<input type=text size="30" class="input-sm" v-model="ss">
            <button class="btn btn-sm btn-primary" v-on:click="search()">검색</button>
          </div>
          <div style="height: 50px"></div>
          <%--
                 v-for="data in find_data"
                 v-for="(data,index) in find_data"
                 v-for="(data,index,key) in find_data"
                 JSON => key:값
           --%> 
          <ul class="nospace clear">
             <span v-for="(vo,index) in find_data">
              <li class="one_quarter first"  v-if="index%4==0"><a href="#"><img :src="vo.poster" :title="vo.name"
              style="width:250px;height:250px"></a></li>
            
              <li class="one_quarter"  v-if="index%4!=0"><a href="#"><img :src="vo.poster" :title="vo.name"
              style="width:250px;height:250px"></a></li>
             </span>
          </ul>
        </figure>
      </div>
      <!-- ################################################################################################ -->
      <!-- ################################################################################################ -->
      <nav class="pagination">
        <ul>
           <span v-for="i in totalpage">
              <li class="current" v-if="curpage==i"><a href="#" v-on:click="pageChange(i)">{{i}}</a></li>
              <li v-if="curpage!=i"><a href="#" v-on:click="pageChange(i)">{{i}}</a></li>
           </span>
        </ul>
      </nav>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </main>
</div>
<script>
  new Vue({
	  el:'.container',
	  data:{
		  ss:'마포',
		  find_data:[],
		  curpage:1,
		  totalpage:0
	  },
	  mounted:function(){
		  //default (첫페이지)
		  this.getData();
	  },
	  methods:{
		  getData:function(){
			  axios.get("http://localhost:8080/web/food/rest_find.do",{
				  params:{
					  page:this.curpage,
					  ss:this.ss
				  }
			  }).then(response=>{
				  console.log(response.data);
				  this.find_data=response.data;
				  this.curpage=this.find_data[0].curpage;
				  this.totalpage=this.find_data[0].totalpage;
			  })
		  },
		  search:function(){
			  // 검색 => 페이지는 1부터 시작한다 
			  this.curpage=1;
			  this.getData();
		  },
		  pageChange:function(page)
		  {
			  this.curpage=page;
			  console.log("page="+page);
			  this.getData();
		  }
	  }
  })
</script>
</body>
</html>







