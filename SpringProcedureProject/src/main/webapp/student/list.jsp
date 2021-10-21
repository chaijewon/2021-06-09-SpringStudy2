<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <center>
    <h1>학생 성적 목록</h1>
    <table>
     <tr>
      <td>
       <a href="insert.do">등록</a>
      </td>
     </tr>
    </table>
    <table border="1" bordercolor="black">
     <tr>
       <th>학번</th>
       <th>이름</th>
       <th>국어</th>
       <th>영어</th>
       <th>수학</th>
       <th>총점</th>
       <th>평균</th>
       <th>비고</th>
     </tr>
     <c:forEach var="vo" items="${list }">
       <tr>
         <td align=center>${vo.hakbun }</td>
         <td align=center>${vo.name }</td>
         <td align=center>${vo.kor }</td>
         <td align=center>${vo.eng }</td>
         <td align=center>${vo.math }</td>
         <td align=center>${vo.total }</td>
         <td align=center>${vo.avg }</td>
         <td>
          <a href="update.do?hakbun=${vo.hakbun }">수정</a>
          <a href="delete.do?hakbun=${vo.hakbun }">삭제</a>
         </td>
       </tr>
     </c:forEach>
    </table>
  </center>
</body>
</html>