<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%--제어문 for --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 날짜 변환 --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
   <h1><spring:message code="detail.title"/></h1>
   <div class="row"><%-- 내용출력 --%>
     <table class="table">
      <tr>
        <th width=20% class="success text-center">번호</th>
        <td width=30% class="text-center">${vo.no }</td> 
        <th width=20% class="success text-center">작성일</th>
        <td width=30% class="text-center"><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/></td> 
      </tr>
      <tr>
        <th width=20% class="success text-center">이름</th>
        <td width=30% class="text-center">${vo.name }</td> 
        <th width=20% class="success text-center">조회수</th>
        <td width=30% class="text-center">${vo.hit }</td> 
      </tr>
      <tr>
        <th width=20% class="success text-center">제목</th>
        <td colspan="3" class="text-left">${vo.subject }</td> 
      </tr>
      <c:if test="${vo.filecount>0 }"><%-- 업로드된 파일이 존재하면 --%>
	      <tr>
	        <th width=20% class="success text-center">첨부파일</th>
	        <td colspan="3" class="text-left">
	          <ul>
	           <c:forEach var="fn" items="${fList }" varStatus="s">
	           <%--
	                List의 인덱스 번호를 가지고 온다 (0부터 시작=> varStatus)
	                
	                => ().do ==>
	                   list.do
	                   detail.do
	                   download.do
	                   insert.do  ======> BoardController 
	                   .do (구분) ==> *.do
	            --%>
	            <li><a href="download.do?fn=${fn }">${fn }</a>&nbsp;(${sList[s.index]}Bytes)</li><%-- list를 두개 이상에서 출력 [인덱스 번호 이용] --%>
	           </c:forEach>
	          </ul>
	        </td>
	      </tr>
      </c:if>
      <tr>
        <td colspan="4" valign="top" height="200">
         <pre style="white-space: pre-wrap;border:none;background-color: white">${vo.content }</pre>
        </td>
      </tr>
      <tr>
        <td colspan="4" class="text-right">
         <a href="update.do?no=${vo.no }&page=${page}" class="btn btn-xs btn-danger">수정</a>
         <a href="delete.do?no=${vo.no }&page=${page}" class="btn btn-xs btn-primary">삭제</a>
         <a href="list.do?page=${page }" class="btn btn-xs btn-info">목록</a>
        </td>
      </tr>
     </table>
   </div>
   <div class="row"><%-- 댓글: PL/SQL 전환 (함수) = 재사용 (레시피(PL/SQL) , 맛집(PL/SQL)) , Trigger ,Index--%>
   
   </div>
  </div>
</body>
</html>













