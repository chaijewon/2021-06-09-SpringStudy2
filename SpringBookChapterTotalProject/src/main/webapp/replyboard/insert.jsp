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
</head>
<body>
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/01.png');">
  <div id="breadcrumb" class="hoc clear"> 
</div>
<div class="wrapper row3">
  <main class="hoc container clear">
    <div class="row">
      <h1 class="text-center">글쓰기</h1>
      <form method="post" action="../replyboard/insert_ok.do">
      <table class="table">
       <tr>
         <th width=25%>이름</th>
         <td width=75%>
          <input type=text name=name size=15 class="input-sm">
         </td> 
       </tr>
       <tr>
         <th width=25%>제목</th>
         <td width=75%>
          <input type=text name=subject size=55 class="input-sm">
         </td> 
       </tr>
       <tr>
         <th width=25%>내용</th>
         <td width=75%>
          <textarea rows="10" cols="55" name="content"></textarea>
         </td> 
       </tr>
       <tr>
         <th width=25%>비밀번호</th>
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
  </main>
</div>
</body>
</html>












