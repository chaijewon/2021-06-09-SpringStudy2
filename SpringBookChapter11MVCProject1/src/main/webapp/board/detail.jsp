<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 30px;
}
.row {
   margin: 0px auto;
   width:800px;
}
</style>
</head>
<body>
  <div class="container">
   <div class="row">
     <h1 class="text-center">내용보기</h1>
     <table class="table">
       <tr>
         <th class="text-center success" width=20%>번호</th>
         <td class="text-center" width=30%>${vo.no }</td>
         <th class="text-center success" width=20%>작성일</th>
         <td class="text-center" width=30%>${vo.regdate }</td>
       </tr>
       <tr>
         <th class="text-center success" width=20%>이름</th>
         <td class="text-center" width=30%>${vo.name }</td>
         <th class="text-center success" width=20%>조회수</th>
         <td class="text-center" width=30%>${vo.hit }</td>
       </tr>
       <tr>
         <th class="text-center success" width=20%>제목</th>
         <td colspan="3">${vo.subject }</td>
       </tr>
       <tr>
         <td colspan="4" height="200" valign="top" class="text-left">
          <pre  style="white-space: pre-wrap;background-color:white;border:none">${vo.content }</pre>
         </td>
       </tr>
       <tr>
         <td colspan="4" class="text-right">
          <a href="update.do?no=${vo.no }" class="btn btn-xs btn-success">수정</a>
          <a href="delete.do?no=${vo.no }" class="btn btn-xs btn-danger">삭제</a>
          <a href="list.do" class="btn btn-xs btn-info">목록</a>
         </td>
       </tr>
     </table>
   </div>
  </div>
</body>
</html>









