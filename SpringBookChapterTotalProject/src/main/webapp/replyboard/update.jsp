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
  width:800px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#updateBtn').click(function(){
		let name=$('#name').val();
		if(name.trim()=="")
		{
			$('#name').focus(); 
			return; // 유효성 검사 (Spring-Validation)
		}
		let subject=$('#subject').val();
		if(subject.trim()=="")
		{
		   $('#subject').focus();
		   return;
		}
		let content=$('#content').val();
		if(content.trim()=="")
		{
		   $('#content').focus();
		   return;
		}
		let pwd=$('#pwd').val();
		if(pwd.trim()=="")
		{
		   $('#pwd').focus();
		   return;
		}
		
		let no=$('#no').val();
		let page=$('#page').val();
		
		let sendData={"no":no,"page":page,"name":name,"subject":subject,"content":content,"pwd":pwd};
		// ?no=1&page=2&name=&su..... ==> JSON(JavaScript Object Nontation:자바스크립트 객체 표현법)
		// Rest-Service => @RestController (JSON=Web/App) => XML을 사용 (크기가 크다 : JSON)
		$.ajax({
			type:'get', //@PostMapping , axios.get() GetMapping ,axios.post() => PostMapping = Vue,React
			url:'../replyboard/update_ok.do',
			data:sendData,
			success:function(res)
			{
				let result=res.trim();
				if(result==0)
				{
					alert("비밀번호가 틀립니다!!");
					$('#pwd').val("");
					$('#pwd').focus();
				}
				else
				{
					location.href="../replyboard/detail.do?no="+no+"&page="+page;
				}
			}
			
		});
	})
});
</script>
</head>
<body>
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/back.png');">
  <div id="breadcrumb" class="hoc clear"> 
</div>
<div class="wrapper row3">
  <main class="hoc container clear">
    <div class="row">
      <h1 class="text-center">수정하기</h1>
      <!-- <form method="post" action="../replyboard/update_ok.do"> -->
      <table class="table">
       <tr>
         <th width=25%>이름</th>
         <td width=75%>
          <input type=text name=name size=15 class="input-sm" value="${vo.name }" id="name">
          <input type=hidden name=no value="${vo.no }" id="no">
          <input type=hidden name=page value="${page }" id="page">
         </td> 
       </tr>
       <tr>
         <th width=25%>제목</th>
         <td width=75%>
          <input type=text name=subject size=55 class="input-sm" value="${vo.subject }" id="subject">
         </td> 
       </tr>
       <tr>
         <th width=25%>내용</th>
         <td width=75%>
          <textarea rows="10" cols="55" name="content" id="content">${vo.content }</textarea>
         </td> 
       </tr>
       <tr>
         <th width=25%>비밀번호</th>
         <td width=75%>
          <input type="password" name=pwd size=10 class="input-sm" id="pwd">
         </td> 
       </tr>
       <tr>
         <td colspan="2" class="text-center">
          <input type=button class="btn btn-sm btn-success" value="수정" id="updateBtn">
          <input type=button value="취소" class="btn btn-sm btn-danger"
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
