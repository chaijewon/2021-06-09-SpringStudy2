<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>
<div id="comments">
        <h2>Comments</h2>
        <ul>
        <c:forEach var="rvo" items="${rList }">
          <li>
            <article>
              <header>
                <figure class="avatar"><img src="../images/demo/avatar.png" alt=""></figure>
                <address>
                By <a href="#">${rvo.name }</a>
                </address>
                <time datetime="2045-04-06T08:15+00:00">${rvo.dbday }</time>
                <c:forEach var="i" begin="1" end="20">
                 &nbsp;&nbsp;
                </c:forEach>
                <c:if test="${sessionScope.id==rvo.id }">
                 <a href="#" class="btn btn-xs btn-danger" style="color:black">수정</a>
                 <a href="#" class="btn btn-xs btn-success" style="color:black">삭제</a>
                </c:if>
              </header>
              <div class="comcont">
                <p>${rvo.msg }</p>
              </div>
            </article>
          </li>
         </c:forEach> 
        </ul>
        <%-- session에 등록 (회원가입) --%>
      </div>
      <c:if test="${sessionScope.id!=null }"><!-- 로그인된 경우만 사용이 가능  -->
	      <table class="table">
		       <tr>
		        <td class="inline">
		         <form method="post" action="../reply/insert.do">
		         <input type=hidden name="cno" value="${vo.no }">
		         <input type=hidden name="tno" value="1">
		         <textarea rows="5" cols="50" name="msg" style="float: left"></textarea>
		           <input type=submit value="댓글쓰기" style="height: 105px;float: left" class="btn btn-danger">
		        </td>
		       </tr>
		  </table>
	  </c:if>
</body>
</html>







