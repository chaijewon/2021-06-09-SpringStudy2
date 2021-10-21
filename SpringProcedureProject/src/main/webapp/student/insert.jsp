<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <center>
     <h1>등록하기</h1>
     <form method="post" action="insert_ok.do"> <!-- .do 처리(서버가 처리) : DispatcherServlet => HandlerMapping-->
     <table border="1" bordercolor="black">
       <tr>
        <td width=30%>이름</td>
        <td width=70%><input type=text name=name size=15></td>
       </tr>
       <tr>
        <td width=30%>국어</td>
        <td width=70%><input type=text name=kor size=15></td>
       </tr>
       <tr>
        <td width=30%>영어</td>
        <td width=70%><input type=text name=eng size=15></td>
       </tr>
       <tr>
        <td width=30%>수학</td>
        <td width=70%><input type=text name=math size=15></td>
       </tr>
       <tr>
         <td colspan="2" align=center>
          <button>등록</button>
          <input type=button value="취소" onclick="javascript:history.back()">
         </td>
       </tr>
     </table>
   </center>
</body>
</html>