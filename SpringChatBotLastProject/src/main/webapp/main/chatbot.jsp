<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>CodePen - Chatbot</title>
  <link rel="stylesheet" href="./style.css">

</head>
<body>
<!-- partial:index.partial.html -->
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
<!-- partial -->
  <script>
  var running = false;
  function send() {
    if (running == true) return;
    var msg = document.getElementById("message").value;
    if (msg == "") return;
    running = true;
    addMsg(msg);
    //DELEAY MESSAGE RESPOSE Echo
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
    var div = document.createElement("div");
    div.innerHTML = "<div class='chat-message-received'>" + msg + "</div>";
    div.className = "chat-message-div";
    document.getElementById("message-box").appendChild(div);
    document.getElementById("message-box").scrollTop =document.getElementById("message-box").scrollHeight
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
