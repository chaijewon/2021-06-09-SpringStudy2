<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- 
     HTML/CSS (X)  => JavaScript (Jquery , Ajax , VueJS , ReactJS , NodeJS(스프링=>서버))
                                                                  => 실시간 채팅
     Oracle (SQL) 
     Spring , JSP 
     => XML 셋팅 , Java 셋팅 ( http / ws)
 -->
<style type="text/css">
.container{
   margin-top: 30px;
}
.row{
  margin: 0px auto;
  width:700px;
}
h1{
  text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let fileIndex=0; // 전역변수 
$(function(){
	$('#add').click(function(){
		$('#fileView').append(
		    '<tr id=m'+(fileIndex)+'>'
		  + '<td width=25% class="text-right">파일 '+(fileIndex+1)+'</td>'
		  + '<td width=75%><input type=file name=files['+fileIndex+'] size=20></td>'
		  + '</tr>'
		)
		fileIndex++;
	});
	$('#remove').click(function(){
		if(fileIndex>0)
		{
		   	$('#m'+(fileIndex-1)).remove();
		   	fileIndex--;
		}
	})
});
</script>
</head>
<body>
   <div class="container">
     <h1>글쓰기</h1>
     <div class="row">
      <form method="post" action="insert_ok.do" enctype="multipart/form-data">
      <table class="table">
       <tr>
         <th width=25% class="text-right danger">이름</th>
         <td width=75%>
          <input type=text name=name size=15 class="input-sm">
         </td> 
       </tr>
       <tr>
         <th width=25% class="text-right danger">제목</th>
         <td width=75%>
          <input type=text name=subject size=55 class="input-sm">
         </td> 
       </tr>
       <tr>
         <th width=25% class="text-right danger">내용</th>
         <td width=75%>
          <textarea rows="10" cols="55" name="content"></textarea>
         </td> 
       </tr>
       <tr>
         <th width=25% class="text-right danger">첨부파일</th>
         <td width=75%>
          <table class="table">
           <tr>
             <td class="text-right">
              <input type=button value="추가" class="btn btn-xs btn-danger" id="add">
              <input type=button value="삭제" class="btn btn-xs btn-info" id="remove">
             </td>
           </tr>
          </table>
          <table class="table" id="fileView">
          
          </table>
         </td>
       </tr>
       <tr>
         <th width=25% class="text-right danger">비밀번호</th>
         <td width=75%>
          <input type="password" name=pwd size=10 class="input-sm">
         </td> 
       </tr>
       <tr>
         <td colspan="2" class="text-center">
          <button class="btn btn-sm btn-success">글쓰기</button>
          <input type=button value="취소" class="btn btn-sm btn-danger"
           onclick="javascript:history.back()">
         </td>
       </tr>
      </table>
      </form>
     </div>
   </div>
</body>
</html>









