<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
      JSTL 
               태그를 이용해서 자바라이브러리 제작  
       core : 변수 설정 , 제어문 , URL , 출력
              1. 변수 설정
                 <c:set var="id" value="hong"> 
                                         자바변경:request.setAttribute("id","hong")  
              2. 제어문 
                 <c:if test="조건문"> => 단점 (<c:else> 존재하지 않는다)
                 <c:forEach var="i" begin="1" end="10" step="2"> => step="1" 생략이 가능
                    => for(int i=1;i<=10;i+=2)
                 <c:forEach var="vo" items="${list}">
                    => for(NewsVO vo:list)
                 <c:choose>
                   <c:when test="">처리</c:when>
                   <c:when test="">처리</c:when>
                   <c:otherwise></c:otherwise>
                 </c:choose>
              3. URL 
                 <c:redirect url=""> => response.sendRedirect()
              4. 출력 
                 javascript에서 => Jquery($) => <c:out value=""/>
       fmt : <fmt:formatDate> <fmt:formatNumber> ======> 오라클(SQL),자바 
                            날짜 변환 SimpleDateFormat()  => String.format()
       fn : String의 메소드를 제어   fn:substring , fn:trim ========> 오라클 / 자바
       
       =====> 
          request.setAttribute("id","admin") ==> model.addAttribute("id","admin")
          session.setAttribute("id","hong")
          session.setAttribute("name","홍길동")
            ==> ${id} ==> admin  ==> ${requestScope.id} ==> requestScope.생략이 가능 
            ==> ${sessionScope.id} ==> hong 
            ==> ${name} ==>홍길동  ===> sessionScope. 생략이 가능 
            ==> 생략이 가능한 우선순위  requestScope , sessionScope
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="container">
    <div class="row">
      <form method="post" action="../news/news_find.do">
       <input type="text" name="ss" size="30" class="input-sm">
       <button class="btn btn-sm btn-danger">검색</button>
      </form>
    </div>
    <div style="height: 30px"></div>
    <div class="row">
      <table class="table">
       <tr>
         <td>
           <c:forEach var="vo" items="${list }">
             <table class="table">
               <tr>
                <td width=30% class="text-center" rowspan="3">
                  <img src="${vo.poster }" width=300 height=150>
                </td>
                <td width=70%>${vo.title }</td>
               </tr>
               <tr>
                <td width=70%><a href="${vo.link }">${vo.description }</a></td>
               </tr>
               <tr>
                <td class="text-right" width=70%>${vo.author }</td>
               </tr>
             </table>
           </c:forEach>
         </td>
       </tr>
      </table>
    </div>
  </div>
</body>
</html>





