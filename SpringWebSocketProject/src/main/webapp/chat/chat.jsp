<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:450px
}
h1 {
  text-align: center;
}
#chatArea{
  width:400px;
  height:200px;
  overflow-y:auto;
  border:1px solid black;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script type="text/javascript">
let websocket;
function connection() // 연결 
{
	 // 스프링 서버와 연결 => http(X) => ws
	 websocket=new WebSocket("ws://localhost:8080/web/chat-ws.do");
	 websocket.onopen=onOpen;
	 websocket.onclose=onClose;
	 websocket.onmessage=onMessage;
}
function onOpen(event) // 연결이 된 경우
{
	alert("서버와 연결되었습니다!!");
}
function onMessage(event) // 채팅 문장열이 전송 
{
	let data=event.data; // 스프링서버에서 보내준 데이터
	/*
	    채팅 문자열 ==> 'msg:' 
	    채팅방 만들기 ==> 'make_room:'
	    일대일 채팅 ==> 'mantoman:'
	*/
	if(data.substring(0,4)=='msg:') 
	{
		appendMessage(data.substring(4));
	}
}
function onClose(event) // 종료 완료
{
	alert("서버와 연결이 종료되었습니다!!");
}
function disconnection() // 서버와 종료
{
	 websocket.close();
}
function send()
{
	let name=$('#name').val();
	let msg=$('#sendMsg').val();
	websocket.send('msg:['+name+']:'+msg);
	$('#sendMsg').val("");
}
function appendMessage(msg)
{
	$('#recvMsg').append(msg+"<br>");
	// scroll조절
	let ch=$('#chatArea').height();
	let m=$('#recvMsg').height()-ch;
	$('#chatArea').scrollTop(m);
}
$(function(){
	// 이벤트 처리
	$('#startBtn').click(function(){
		let name=$('#name').val();
		if(name.trim()=="")
		{
			$('#name').focus();
			return;
		}
		connection();
	});
	$('#endBtn').click(function(){
		disconnection();
	})
	$('#sendBtn').click(function(){
		let msg=$('#sendMsg').val();
		if(msg.trim()=="")
		{
			$('#sendMsg').focus();
			return;
		}
		send();
	})
	// Enter
	$('#sendMsg').keydown(function(key){
		 if(key.keyCode==13)
		 {
		    send();	 
		 }
	 })
})
</script>
</head>
<body>
  <div class="container">
    <h1>Spring WebSocket 채팅</h1>
    <div class="row">
     <table class="table">
      <tr>
        <td>
          <input type=text id=name size=20 class="input-sm">
          <input type=button class="btn btn-sm btn-danger" value="입장" id="startBtn">
          <input type=button class="btn btn-sm btn-success" value="퇴장" id="endBtn">
        </td>
      </tr>
      <tr>
        <td>
          <div id="chatArea"> <%-- Scrollbar --%>
            <div id="recvMsg"></div>
          </div>
        </td>
      </tr>
      <tr>
        <td>
         <input type=text id="sendMsg" size=40 class="input-sm">
         <input type=button id="sendBtn" class="btn btn-sm btn-success" value="전송">
        </td>
      </tr>
     </table>
     
    </div>
  </div>
</body>
</html>











