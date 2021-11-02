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
  width:300px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){ //window.onload (main)
	$('#delBtn').click(function(){
		let pwd=$('#pwd').val();
		if(pwd.trim()=="")// 입력이 안된 경우
		{
			$('#pwd').focus();
			return; // click이벤트 종료
		}
		let no=$('#no').val();
		let page=$('#page').val();
		// 전송 => 요청 , 응답을 동시에 처리 => 화면변경이 없이 한 화면에 처리가 가능하다 
		// HTML추가 => 시간이 많이 걸린다 => Ajax (web2.0) => web3.0 => TypeScript(Vue,React=Ajax가 포함,가상메모리)
		// AngularJS (제임스 고슬링:GO) => 운영체제 , facebook,MS,리눅스
		$.ajax({
			type:'get', // @GetMapping
			url:'../replyboard/delete_ok.do',
			data:{"no":no,"pwd":pwd}, //JSON  ==> 자바(Controller) JSON == JavaScript (Ajax,Jquery),Vue,React
		    success:function(res) // 정상수행시에 => 400(controller에서 값을 잘못받는 경우),405(GET/POST),404,415,500
		    {
		    	let result=res.trim(); // 결과값 전송 (0=비밀번호가 틀린경우,1=비밀번호 정상 , 실제 삭제가 된 경우)
		    	// let result=Number(res.trim()) => parseInt(res.trim())
		    	if(result==0)
		    	{
		    		alert("비밀번호가 틀립니다!!");
		    		$('#pwd').val("");
		    		$('#pwd').focus();
		    	}
		    	else
		    	{
		    		location.href="../replyboard/list.do?page="+page;//정상수행시에 목록으로 이동 
		    		// 답변 , 삭제 , 추가 => list.do
		    		// 수정 => 내용이 변경될 수 있다 (detail.do)
		    	}
		    }
		    // Error => 전체적으로 에러 처리 (@ControllerAdvice (14장):톰캣에서 에러 처리:web.xml,)
		    // https://sports.v.daum.net/v/20211019091404910 => PathValiable
		    // v.do?code=20211019091404910  => login/admin/1234
		})
	})
})
</script>
</head>
<body>
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/back.png');">
  <div id="breadcrumb" class="hoc clear"> 
</div>
<div class="wrapper row3">
  <main class="hoc container clear">
   <div class="row">
       <!-- <form method="post" action="delete_ok.do"> --><%-- .do : BoardController --%>
	       <table class="table">
	         <tr>
	           <td>
	                      비밀번호:<input type=password name=pwd size=20 class="input-sm" id="pwd">
	                  <input type=hidden name=no value="${no }" id="no">
	                  <input type=hidden name=page value="${page }" id="page">
	           </td>
	         </tr>
	         <tr>
	           <td class="text-center">
	             <button class="btn btn-sm btn-success" id="delBtn">삭제</button>
	             <input type=button value="취소" class="btn btn-sm btn-success"
	             onclick="javascript:history.back()">
	           </td>
	         </tr>
	       </table>
       <!-- </form> -->
     </div>
  </main>
</div>
</body>
</html>








