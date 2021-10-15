<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!-- 날짜변환 (오라클/자바) -->
<%--
    <spring:~> <form: ~ 에러 처리> 검증 (유효성 검사)
 --%>
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
  width:800px;
}
h1{
  text-align: center;
}
</style>
</head>
<body>
   <div class="container">
    <%-- <spring:message code="list.title"> 주로 사용처 (메뉴) , 한글/영문
          title=자료실
          title_en=Title
    --%>
    <h1>검색결과</h1>
    <div class="row">
      <table class="table">
       <tr>
         <td>
          <a href="list.do" class="btn btn-sm btn-danger">목록</a>
         </td>
       </tr>
      </table>
      <table class="table">
        <tr class="success">
          <th class="text-center" width=10%>번호</th>
          <th class="text-center" width=45%>제목</th><%-- 댓글 갯수 --%>
          <th class="text-center" width=15%>이름</th>
          <th class="text-center" width=20%>작성일</th>
          <th class="text-center" width=10%>조회수</th>
        </tr>
        <c:forEach var="vo" items="${list }">
         <tr>
          <td class="text-center" width=10%>${vo.no }</td>
          <td class="text-left" width=45%>
           <a href="detail.do?no=${vo.no }&page=${curpage}">${vo.subject }</a>
           <%-- 모든 데이터에 값이 존재하면 (int no) --%>
          </td><%-- 댓글 갯수 --%>
          <td class="text-center" width=15%>${vo.name }</td>
          <td class="text-center" width=20%><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/></td>
          <%--
                  fmt:formatDate => SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd")
           --%>
          <td class="text-center" width=10%>${vo.hit }</td>
         </tr>
        </c:forEach>
      </table>
    </div>
   </div>
</body>
</html>
