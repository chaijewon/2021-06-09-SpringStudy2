<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
     <h1 class="text-center">묻고답하기</h1>
     <table class="table">
       <tr>
        <td>
         <a href="../replyboard/insert.do" class="btn btn-sm btn-danger">새글</a>
        </td>
       </tr>
     </table>
     <table class="table">
       <tr>
        <th class="text-center" width=10%>번호</th>
        <th class="text-center" width=45%>제목</th>
        <th class="text-center" width=15%>이름</th>
        <th class="text-center" width=20%>작성일</th>
        <th class="text-center" width=10%>조회수</th>
       </tr>
       <%--
             model.addAttribute("list",list) => request.setAttribute("a",list);
                                   ===============================================
                                   request.getAttribute("list") => EL ${a}
              JSP에서 출력을 할때 => 출력내용 (Controller에서 전송) => JSP는 출력만 담당
        --%>
       <c:forEach var="vo" items="${list }">
         <tr>
	        <td class="text-center" width=10%>${vo.no }</td>
	        <td width=45%>
	          <a href="../replyboard/detail.do?no=${vo.no }&page=${curpage}">${vo.subject }</a>
	          <c:if test="${today==vo.dbday }">
	            &nbsp;<sup style="color:red">new</sup>
	          </c:if>
	        </td>
	        <td class="text-center" width=15%>${vo.name }</td>
	        <td class="text-center" width=20%>${vo.dbday }</td>
	        <td class="text-center" width=10%>${vo.hit }</td>
	       </tr>
       </c:forEach>
     </table>
     <table class="table">
       <tr>
        <td class="text-left inline">
         <input type="checkbox" value="N" class="input-sm" name="fs">이름
         <input type="checkbox" value="S" class="input-sm" name="fs">제목
         <input type="checkbox" value="C" class="input-sm" name="fs">내용
         <input type=text name=ss size=15 class="input-sm">
         <input type=submit class="btn btn-sm btn-success" value="검색">
        </td>
        <td class="text-right">
         <a href="../replyboard/list.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-primary">이전</a>
         ${curpage } page / ${totalpage } pages
         <a href="../replyboard/list.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-primary">다음</a>
        </td>
       </tr>
     </table>
    </div>
  </main>
</div>
</body>
</html>





