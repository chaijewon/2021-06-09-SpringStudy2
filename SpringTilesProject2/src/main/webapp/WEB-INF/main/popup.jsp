<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
font-family: SF Pro KR, SF Pro Display, SF Pro Icons, AOS Icons, Apple Gothic, HY Gulim, MalgunGothic, HY Dotum, Lexi Gulim, Helvetica Neue, Helvetica, Arial, sans-serif;
.layerPopup img{
	margin-bottom : 20px;
}
.layerPopup:before {
	display:block;
	content:""; 
	position:fixed; 
	left:0; 
	top:0; 
	width:100%; 
	height:100%; 
	background:rgba(0,0,0,.5); 
	z-index:9000
}
.layerPopup .layerBox {    
	z-index:20000;   
	position:fixed; 
	left:50%; 
	top:48%; 
	transform:translate(-50%, -50%);
	 padding:30px; 
	 background:#fff; 
	 border-radius:6px; 
 }
.layerPopup .layerBox .title {
	margin-bottom:10px; 
	padding-bottom:10px; 
	font-weight:600; 
	border-bottom:1px solid #d9d9d9;
}
.layerPopup .layerBox .btnTodayHide {
	font-size:14px; 
	font-weight:600; 
	color:black; 
	float: left;
	text-decoration:none;
	width: 150px; 
	height : 30px;
	line-height:30px;
	border:black solid 1px; 
	text-align : center;
	text-decoration:none;
}
.layerPopup div{
	display : inline;
}
.layerPopup form{
	margin-top : 5px;
	font-size:16px; font-weight:600;
	weight: 100%;
	height : 30px;
	line-height:30px
}
.layerPopup #close {
	font-size:16px; 
	font-weight:600; 
	width: 40px; 
	height : 30px;
	color:black; 
	float: right; 
	line-height:30px;
	text-align : center;
	text-decoration:underline;
}
.layerPopup a{
	text-decoration : none;
	color : black;
	width: 50px;
	height : 40px;
}
</style>

</head>
<body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	cookiedata = document.cookie;   
	if ( cookiedata.indexOf("maindiv=done") < 0 ){      
		
			$('#layer_popup').css("visibility","visibility");
		//$("#layer_popup").css("visibility","visible");
	}
	else {
	    //document.getElementById('layer_popup').style.display = "none";
		$("#layer_popup").css("visibility","hidden");
	}
})
function setCookie( name, value, expiredays ) {
    var todayDate = new Date();
    todayDate.setDate(todayDate.getDate() + expiredays ); 
    document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}
function closePop() {
	//alert("1")
    if ( document.pop_form.chkbox.checked ){
    	//alert("12")
        setCookie( "maindiv", "done" , 1);
    	i=1;
    }
    document.getElementById('layer_popup').style.display = "none";
}
</script>
<!-- <div class="layerPopup" id="layer_popup" style="display:'block';"> -->
<div class="layerPopup" id="layer_popup" style="visibility: visible;">
    <div class="layerBox">
        <h4 class="title">Organi ????????????</h4>
        <div class="cont">
            <p>
			  <img src="popup.jpg" width=400 height=550 usemap="#popup" alt="event page">
            </p>
        </div>
        <form name="pop_form">
        <div id="check" ><input type="checkbox" name="chkbox" value="checkbox" id="chkbox" >
        <label for="chkbox">&nbsp;&nbsp;?????? ???????????? ?????? ??????</label></div>
		<div id="close" ><a href="javascript:closePop();">??????</a></div>    
		</form>
	</div>
</div>


</body>
</html>