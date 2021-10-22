<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script><!-- Jquery lib -->
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function postfind()
{
	new daum.Postcode({
		oncomplete:function(data){
			$('#post').val(data.zonecode);
			$('#addr1').val(data.address)
		}
	}).open();
}
</script>
</head>
<body>
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/01.png');">
  <div id="breadcrumb" class="hoc clear"> 
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="hoc container clear"> 
  <h1 class="text-center">회원수정</h1>
  <form method=post action="join_update_ok.do" id="joinFrm">
  <table class="table">
    <tr>
      <th align="right" width=20%>아이디</th>
      <td width=80% class="inline">
       <input type=text name=id size=15 class="input-sm" readonly value="${vo.id }">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>비밀번호</th>
      <td width=80% class="inline">
       <input type=password name=pwd size=15 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>이름</th>
      <td width=80% class="inline">
       <input type=text name=name size=15 class="input-sm" value="${vo.name }">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>성별</th>
      <td width=80% class="inline">
       <input type="radio" name=sex value="남자" ${vo.sex=='남자'?"checked":"" }>남자
       <input type="radio" name=sex value="여자" ${vo.sex=='여자'?"checked":"" }>여자
      </td> 
    </tr>
    <tr>
      <th align="right" width=20%>생년월일</th>
      <td width=80% class="inline">
       <input type="date" size=20 name=birthday value="${vo.birthday }">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>이메일</th>
      <td width=80% class="inline">
       <input type=text name=email size=50 class="input-sm" value="${vo.email }">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>우편번호</th>
      <td width=80% class="inline">
       <input type="text" name=post size=10 readonly id=post class="input-sm" value="${vo.post }">
       <input type=button class="btn" value="우편번호검색" onclick="postfind()">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>주소</th>
      <td width=80% class="inline">
       <input type=text name=addr1 size=50 class="input-sm" readonly id=addr1 value="${vo.addr1 }">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>상세주소</th>
      <td width=80% class="inline">
       <input type=text name=addr2 size=50 class="input-sm" value="${vo.addr2 }">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>전화번호</th>
      <td width=80% class="inline">
       <input type=text name=tel1 size=10 class="input-sm" readonly value="010">-
       <input type=text name=tel2 size=20 class="input-sm" value="${vo.tel }">
       <%--
             010-(1111-1111)
        --%>
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>소개</th>
      <td width=80% class="inline">
       <textarea rows="10" cols="55" name=content>${vo.content }</textarea>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type=submit class="btn" value="수정" id="joinBtn">
        <input type=button class="btn" value="취소" 
         onclick="javascript:history.back()">
      </td>
    </tr>
  </table>
  </form>
  </main>
</div>
</body>
</html>