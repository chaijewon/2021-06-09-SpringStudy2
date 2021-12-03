var running = false;
let p=0;
function send() {
  if (running == true) return;
  var msg = document.getElementById("message").value;
  if (msg == "") return;
  running = true;
  addMsg(msg);
  // 상담 내용을 Ajax를 통해서 전송 
  // 데이터를 받아 오면 ==> 
  window.setTimeout(addResponseMsg, 1000, msg);
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
  let data=["Hi","Hello","안녕"];
  let print1="안녕하세요!! 맛집 여행 챗봇입니다";
  let k=0;
  for(var i=0;i<data.length;i++)
  {
	  if(msg.indexOf(data[i])!=-1)
	  {
		  k=1;
		  break;
	  }
  }
  if(k==0)
	  print1="다시 말씀해 주세요.<br>무엇을 도와 드릴까요?"
  else if(k==1)
	  print1="안녕하세요!! 맛집/여행 챗봇입니다"	
  var div = document.createElement("div");
  if(p==0)
  {
	  div.innerHTML = "<div class='chat-message-received'>맛집/여행 챗봇을 시작합니다</div>";
	  div.className = "chat-message-div";
	  document.getElementById("message-box").appendChild(div);
	  document.getElementById("message-box").scrollTop =document.getElementById("message-box").scrollHeight
	  p=1;
  }
  else
  {
	  div.innerHTML = "<div class='chat-message-received'>"+print1+"</div>";
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
  window.setTimeout(addResponseMsg, 100, "ㅇㅇ");
}, false);