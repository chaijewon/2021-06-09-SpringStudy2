<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let u=0;// 전역변수
$(function(){
	$('.updates').click(function(){
		$('.up').hide();// 출력된 수정창을 닫는다
		let no=$(this).attr("data-value"); // 출력할 위치를 확인 (어떤것이 수정할 지 확인 )
		if(u==0) // 열고
		{
			u=1; // 닫기
			$('#u'+no).show();
			$(this).text("취소");
		}
		else // 닫기
		{
			u=0; // 열기 
			$('#u'+no).hide();
			$(this).text("수정");
		}
	})
})
</script>
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
                 <span class="btn btn-xs btn-danger updates" style="color:black" data-value="${rvo.no }">수정</span>
                 <a href="../reply/delete.do?no=${rvo.no }&cno=${vo.no}&tno=${tno}" class="btn btn-xs btn-success" style="color:black">삭제</a>
                </c:if>
                <table class="table up" style="display:none" id="u${rvo.no }">
			       <tr>
			        <td class="inline">
			         <form method="post" action="../reply/update.do">
			         <%-- 댓글 출력 위치 : tno(사이트 주소) 
			            hotel_detail.do => 2
			            location_detail.do => 1
			            freeboard_detail.do => 3
			            => 상세보기 => 게시물 번호 / 명소번호 => cno
			            => no= 댓글 번호 
			         ,cno --%>
			         <input type=hidden name="cno" value="${vo.no }">
			         <input type=hidden name="tno" value="${tno }">
			         <input type=hidden name="no" value="${rvo.no }">
			         <textarea rows="5" cols="50" name="msg" style="float: left">${rvo.msg }</textarea>
			           <input type=submit value="댓글수정" style="height: 105px;float: left" class="btn btn-danger">
			        </form>
			        </td>
			       </tr>
			   </table>
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
		           <input type=hidden name="tno" value="${tno }">
		           <textarea rows="5" cols="50" name="msg" style="float: left"></textarea>
		           <input type=submit value="댓글쓰기" style="height: 105px;float: left" class="btn btn-danger">
		          </form>
		        </td>
		       </tr>
		  </table>
	  </c:if>
</body>
</html>







