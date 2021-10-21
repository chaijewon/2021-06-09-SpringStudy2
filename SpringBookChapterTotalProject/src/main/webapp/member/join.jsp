<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script><!-- Jquery lib -->
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function postfind()
{
	new daum.Postcode({
		oncomplete:function(data){
			$('#post').val(data.zonecode);
			$('#addr1').val(data.address)
		}
	}).open();
}
</script>
<script type="text/javascript">
$(function(){
	    $('#idBtn').click(function(){
		   $("#dialog_idcheck").dialog({
		         autoOpen:false,
		         width:420,
		         height:150,
		         modal : true
		    }).dialog("open");
	   });
	   // 다이얼로그가 닫기는 부분
	   $('#idcanBtn').click(function(){
		   $('#dialog_idcheck').dialog("close");
	   });
	   $('#idcheckBtn').click(function(){
		   let id=$('#id_check').val();
		   if(id.trim()=="")
		   {
			   $('#id_check').focus();
			   return; //id는 반드시 입력을 한다 
		   }   
		   // 서버로 ID를 전송 하고 => 존재 여부 확인 받기 => 클라이언트에서 출력 
		   $.ajax({
			   type:'get', //GetMapping
			   url:'../member/idcheck.do',
			   data:{"id":id},
			   success:function(res)
			   {
				   // 1 , 0
				   let result=res.trim();
				   if(result==0)
				   {
					  alert("사용가능한 아이디입니다"); 
					  $('#okBtn').show();
				   }
				   else
				   {
					   alert("이미 사용중인 아이디입니다\n다시 입력하세요");
					   $('#id_check').val("");
					   $('#id_check').focus();
				   }
			   }
		   })
	   });
	   $('#okBtn').click(function(){
		   let id=$('#id_check').val();
		   $('#id_1').val(id);//join.jsp(id)
		   $('#dialog_idcheck').dialog("close");//다이얼로그 닫기
	   })
	   $('#joinBtn').click(function(){
		   
	   });
	   
})
</script>

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
  <h1 class="text-center">회원가입</h1>
  <form method=post action="join_ok.do" id="joinFrm">
  <table class="table">
    <tr>
      <th align="right" width=20%>아이디</th>
      <td width=80% class="inline">
       <input type=text name=id size=15 class="input-sm" readonly id=id_1>
       <input type=button class="btn btn-xs" id="idBtn" value="중복체크">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>비밀번호</th>
      <td width=80% class="inline">
       <input type=password name=pwd size=15 class="input-sm">
       &nbsp;&nbsp;재입력:<input type=password name=pwd2 size=15 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>이름</th>
      <td width=80% class="inline">
       <input type=text name=name size=15 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>성별</th>
      <td width=80% class="inline">
       <input type="radio" name=sex value="남자" checked>남자
       <input type="radio" name=sex value="여자">여자
      </td> 
    </tr>
    <tr>
      <th align="right" width=20%>생년월일</th>
      <td width=80% class="inline">
       <input type="date" size=20 name=birthday>
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>이메일</th>
      <td width=80% class="inline">
       <input type=text name=email size=50 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>우편번호</th>
      <td width=80% class="inline">
       <input type="text" name=post size=10 readonly id=post>
       <input type=button class="btn" value="우편번호검색" onclick="postfind()">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>주소</th>
      <td width=80% class="inline">
       <input type=text name=addr1 size=50 class="input-sm" readonly id=addr1>
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>상세주소</th>
      <td width=80% class="inline">
       <input type=text name=addr2 size=50 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>전화번호</th>
      <td width=80% class="inline">
       <input type=text name=tel1 size=10 class="input-sm" readonly value="010">-
       <input type=text name=tel2 size=20 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>소개</th>
      <td width=80% class="inline">
       <textarea rows="10" cols="55" name=content></textarea>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type=submit class="btn" value="가입" id="joinBtn">
        <input type=button class="btn" value="취소" 
         onclick="javascript:history.back()">
      </td>
    </tr>
  </table>
  </form>
  </main>
</div>
<!-- ID중복체크  -->
<div id="dialog_idcheck" title="아이디 중복체크" style="display:none">
  
  <table class="table">
   <tr>
    <th width=20% align="right">ID</th>
    <td width=80% class="inline">
      <input type=text id="id_check" size=15 class="input-sm">
      <input type=button value="중복체크" id="idcheckBtn">
    </td>
   </tr>
   <tr>
     <td colspan="2" align="center">
      <input type=button value="확인" id="okBtn" style="float: left;display:none">
      <input type=button value="취소" id="idcanBtn" style="float: left">
     </td>
   </tr>
  </table>
 
</div>
</body>
</html>