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
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<%--
     자바 : 로직 (알고리즘) 
    웹 : 흐름(틀에 맞쳐서 코딩) => SQL (외부 (X)) => 데이터(수집)
      형식(Spring,Mybatis) 
 --%>
<script type="text/javascript">
let u=0;
let r=0;
$(function(){
	$('.updates').click(function(){
		$('.up').hide(); // style.display='none'; getElementById , getElementClassName()
		$('.reply').hide();
		// 수정창을 연다 
		let no=$(this).attr("data-no");
		if(u==0) // show
		{
			$(this).text("취소");
			$('#u'+no).show();
			u=1;
		}
		else // hide
		{
			$('#u'+no).hide();
			$(this).text("수정");
			u=0;
		}
	})
	
	$('.replys').click(function(){
		$('.up').hide(); // style.display='none'; getElementById , getElementClassName()
		$('.reply').hide();
		// 수정창을 연다 
		let no=$(this).attr("data-no");
		if(r==0) // show
		{
			$(this).text("취소");
			$('#r'+no).show();
			r=1;
		}
		else // hide
		{
			$('#r'+no).hide();
			$(this).text("댓글");
			r=0;
		}
	})
})
</script>
</head>
<body>
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/back.png');">
  <div id="breadcrumb" class="hoc clear"> 
</div>
<div class="wrapper row3">
  <main class="hoc container clear">
   <div class="row">
     <h1 class="text-center">내용보기</h1>
   <table class="table">
    <tr>
     <th width=20% class="text-center">번호</th>
     <td width=30% class="text-center">${vo.no }</td>
     <th width=20% class="text-center">작성일</th>
     <td width=30% class="text-center">${vo.dbday }</td>
    </tr>
    <tr>
     <th width=20% class="text-center">이름</th>
     <td width=30% class="text-center">${vo.name }</td>
     <th width=20% class="text-center">조회수</th>
     <td width=30% class="text-center">${vo.hit }</td>
    </tr>
    <tr>
      <th width=20% class="text-center">제목</th>
      <td colspan="3">${vo.subject }</td>
    </tr>
    <tr>
      <td colspan="4" class="text-left" valign="top" height="200">
       <pre style="white-space: pre-wrap;background-color: white;border:none">${vo.content }</pre>
      </td>
    </tr>
    <tr>
      <td colspan="4" class="text-right">
       <a href="../freeboard/update.do?no=${vo.no }&page=${page}" class="btn btn-xs btn-info">수정</a>
       <%-- @RestController : 데이터자체를 보낼수 있다(script/결과값) : 16장 => AJAX,Front-End, Mobile --%>
       <a href="../freeboard/delete.do?no=${vo.no }&page=${page}" class="btn btn-xs btn-success">삭제</a><%-- 트랜잭션 --%>
       <a href="../freeboard/list.do?page=${page }" class="btn btn-xs btn-primary">목록</a>
      </td>
    </tr>
   </table>
   </div>
   <%--
              스프링 => 형식을 맞쳐서 코딩 (등록 : XML , 구분 : 어노테이션)
       ====
              컴퓨터 : 메인보드 (CPU , 하드 , 비디오...)
       1. 규칙 
          1) DispatcherServlet : web.xml
          2) 클래스 메모리 할당 => @Controller,@Repository... 
          3) 클래스주소 => @Autowired
          4) 쇼핑몰 / 영화 / 맛집 ==> 개인 사이트 
             ==========================
                          공기업(행정자치부 , 국세청...)
                          금융권(은행 , 증권 )
                          대기업(ERP)
             ========================== SI/SM
                          솔루션 : 자바는 솔루션이 극히 드물다 (startup)
    ===============================================================================================
         요청 (.do) 
     ==============================================================================================
     1. @Controller , @RestController 
        ============================== 메소드제작 (구분 => @RequestMapping, @GetMapping , @PostMapping)
     2. 데이터베이스 연결 
     ============================== HandlerMapping(DispactherServlet에서 자동 처리)
     3. JSP제작 
     ============================== ViewResolver (application-context.xml) => 경로명 / JSP명 
     =============================================================== DispatcherServlet(web.xml)
    --%>
   <div class="row"><%-- 댓글 출력 위치 --%>
    <table class="table">
     <tr>
      <td>
        <c:forEach var="rvo" items="${list }">
          <table class="table">
           <tr>
            <td class="text-left">
             <c:if test="${rvo.group_tab>0 }">
               <c:forEach var="i" begin="1" end="${rvo.group_tab }">
                 &nbsp;&nbsp;
               </c:forEach>
               <img src="../replyboard/re_icon.png">
             </c:if>
                             ◐${rvo.name }(<span style="color:blue">${rvo.dbday }</span>)
            </td>
            <td class="text-right">
             <c:if test="${sessionScope.id!=null }">
              <c:if test="${sessionScope.id==rvo.id }"><!-- 작성자 본인 -->
               <span class="btn btn-xs btn-danger updates" data-no="${rvo.no }">수정</span>
               <a href="../freeboard/reply_delete.do?no=${rvo.no }&bno=${vo.no}&page=${page}" class="btn btn-xs btn-success">삭제</a>
              </c:if>
              <span class="btn btn-xs btn-info replys" data-no="${rvo.no }">댓글</span>
             </c:if>
            </td>
           </tr>
           <tr>
            <td colspan="2" valign="top">
             <pre style="white-space: pre-wrap;background-color:white;border:none">${rvo.msg }</pre>
            </td>
           </tr>
          </table>
          <!-- 수정 폼 -->
          <table class="table up" style="display:none" id="u${rvo.no }">
		    <tr>
		      <td class="inline">
		       <form method="post" action="../freeboard/reply_update.do">
		        <input type="hidden" name="no" value="${rvo.no }">
		        <input type="hidden" name="bno" value="${vo.no }"><%-- 게시물 번호 --%>
		        <input type="hidden" name="page" value="${page }">
		        <textarea rows="4" cols="90" name=msg style="float: left">${rvo.msg }</textarea>
		        <input type=submit value="댓글수정" class="btn btn-danger" style="height: 80px;float:left">
		       </form>
		      </td>
		    </tr>
		   </table>
		   <!-- 댓글 폼 -->
		   <table class="table reply" style="display:none" id="r${rvo.no }">
		    <tr>
		      <td class="inline">
		       <form method="post" action="../freeboard/reply_reply_insert.do">
		        <input type="hidden" name="pno" value="${rvo.no }">
		        <input type="hidden" name="bno" value="${vo.no }"><%-- 게시물 번호 --%>
		        <input type="hidden" name="page" value="${page }">
		        <textarea rows="4" cols="90" name=msg style="float: left"></textarea>
		        <input type=submit value="댓글쓰기" class="btn btn-danger" style="height: 80px;float:left">
		       </form>
		      </td>
		    </tr>
		   </table>
        </c:forEach>
      </td>
     </tr>
    </table>
   </div>
   <c:if test="${sessionScope.id!=null }"><%--로그인이 된 상태  --%>
	   <div class="row">
		   <table class="table">
		    <tr>
		      <td class="inline">
		       <form method="post" action="../freeboard/reply_insert.do">
		        <input type="hidden" name="bno" value="${vo.no }"><%-- 게시물 번호 --%>
		        <input type="hidden" name="page" value="${page }">
		        <textarea rows="4" cols="90" name=msg style="float: left"></textarea>
		        <input type=submit value="댓글쓰기" class="btn btn-danger" style="height: 80px;float:left">
		       </form>
		      </td>
		    </tr>
		   </table>
	   </div>
   </c:if>
  </main>
</div>
</body>
</html>





