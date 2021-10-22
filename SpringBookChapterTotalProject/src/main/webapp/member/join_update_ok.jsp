<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- JSP에서 처리 => 서버에 직접 처리 : @ResponseBody = @RestController  --%>
<c:if test="${bCheck==true }">
 <script>
 alert("정상적으로 수정되었습니다!!");
 location.href="../main/main.do";
 </script>
</c:if>
<c:if test="${bCheck==false }">
 <script>
 alert("비밀번호가 틀립니다!!");
 history.back();
 </script>
</c:if>