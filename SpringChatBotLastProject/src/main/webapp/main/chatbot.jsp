<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>CodePen - Chatbot</title>
  <link rel="stylesheet" href="./style.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<style type="text/css">
	.container{
	   margin-top: 50px;
	}
	.row {
	   margin: 0px auto;
	   width:1000px;
	}
	h1{
	  text-align: center;
	}
	</style>
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
  <script type="text/javascript">
   $(function(){
	    $('div.main-card').toggleClass('active');
	    var $win = $(window);
	    var top = $(window).scrollTop(); // 현재 스크롤바의위치값을 반환합니다.

	    /*사용자 설정 값 시작*/
	    var speed          = 1000;     // 따라다닐 속도 : "slow", "normal", or "fast" or numeric(단위:msec)
	    var easing         = 'linear'; // 따라다니는 방법 기본 두가지 linear, swing
	    var $layer         = $('div.main-card'); // 레이어셀렉팅
	    var layerTopOffset = 0;   // 레이어 높이 상한선, 단위:px
	    $layer.css('position', 'absolute');
	    /*사용자 설정 값 끝*/

	    // 스크롤 바를 내린 상태에서 리프레시 했을 경우를 위해
	    if (top > 0 )
	      $win.scrollTop(layerTopOffset+top);
	    else
	      $win.scrollTop(0);

	    //스크롤이벤트가 발생하면
	    $(window).scroll(function(){

	      var yPosition = $win.scrollTop()+300;
	      if (yPosition< 0)
	      {
	        yPosition = $win.scrollTop()+300;
	      }
	      $layer.animate({"top":yPosition }, {duration:speed, easing:easing, queue:false});
	    });

   })
  </script>
</head>
<body>
<!-- partial:index.partial.html -->
<div class="container">
  <div class="row">
   <c:forEach var="vo" items="${list }">
     <div class="col-md-4">
	    <div class="thumbnail">
	      <a href="#">
	        <img src="${vo.poster }" alt="Lights" style="width:100%">
	        <div class="caption">
	          <p>${vo.name }</p>
	        </div>
	      </a>
	    </div>
	  </div>
   </c:forEach>
  <div class="main-card">
  <div class="main-title">
    <svg viewBox="0 0 24 24">
    <path fill="currentColor" d="M12,2A2,2 0 0,1 14,4C14,4.74 13.6,5.39 13,5.73V7H14A7,7 0 0,1 21,14H22A1,1 0 0,1 23,15V18A1,1 0 0,1 22,19H21V20A2,2 0 0,1 19,22H5A2,2 0 0,1 3,20V19H2A1,1 0 0,1 1,18V15A1,1 0 0,1 2,14H3A7,7 0 0,1 10,7H11V5.73C10.4,5.39 10,4.74 10,4A2,2 0 0,1 12,2M7.5,13A2.5,2.5 0 0,0 5,15.5A2.5,2.5 0 0,0 7.5,18A2.5,2.5 0 0,0 10,15.5A2.5,2.5 0 0,0 7.5,13M16.5,13A2.5,2.5 0 0,0 14,15.5A2.5,2.5 0 0,0 16.5,18A2.5,2.5 0 0,0 19,15.5A2.5,2.5 0 0,0 16.5,13Z" />
    </svg><span>Chatbot</span></div>
  <div class="chat-area" id="message-box">
  </div>
  <div class="line"></div>
  <div class="input-div">
    <input class="input-message" name="message" 
           type="text" id="message" placeholder="Type your message ..."/>
    <button class="input-send" onclick="send()">
   <svg style="width:24px;height:24px">
    <path d="M2,21L23,12L2,3V10L17,12L2,14V21Z" />
</svg>
    </button>
  </div>
 </div>
 </div>
</div>
<!-- partial -->
  <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
  <script type="text/javascript">
  var running = false;
  let p=0;
  function send() {
    if (running == true) return;
    var msg = document.getElementById("message").value;
    if (msg == "") return;
    running = true;
    addMsg(msg);
    $.ajax({
    	type:'post',
    	url:'chatbot_result.do',
    	data:{"msg":msg},
    	success:function(response)
    	{
    		window.setTimeout(addResponseMsg, 1000, response);
    	}
    })
    //DELEAY MESSAGE RESPOSE Echo
    
  }
  function addMsg(msg) {
    var div = document.createElement("div");
    div.innerHTML =
      "<span style='flex-grow:1'></span><div class='chat-message-sent'>" +
      msg +
      "</div>";
    div.className = "chat-message-div";
    document.getElementById("message-box").appendChild(div);
    //SEND MESSAGE TO API
    document.getElementById("message").value = "";
    document.getElementById("message-box").scrollTop =document.getElementById("message-box").scrollHeight
  }
  function addResponseMsg(msg) {
    var div = document.createElement("div");
    if(p==0)
    {
      div.innerHTML = "<div class='chat-message-received'>맛집 추천 챗봇입니다</div>";
      div.className = "chat-message-div";
      document.getElementById("message-box").appendChild(div);
      document.getElementById("message-box").scrollTop =document.getElementById("message-box").scrollHeight
      p=1;
    }
    else 
    {
    	div.innerHTML = "<div class='chat-message-received'>" + msg + "</div>";
        div.className = "chat-message-div";
        document.getElementById("message-box").appendChild(div);
        document.getElementById("message-box").scrollTop =document.getElementById("message-box").scrollHeight
    }
    running = false;
  }
  document.getElementById("message").addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
      event.preventDefault();
      send();
    }
  });
  document.addEventListener('DOMContentLoaded', function() {
    window.setTimeout(addResponseMsg, 100, "Hi");
  }, false);
  </script>

</body>
</html>
