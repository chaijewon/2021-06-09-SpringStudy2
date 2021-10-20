<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
  <h1 class="text-center">회원가입</h1>
  <table class="table">
    <tr>
      <th align="right" width=20%>아이디</th>
      <td width=80% class="inline">
       <input type=text name=id size=15 class="input-sm">
       <button class="btn btn-xs">중복체크</button>
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>비밀번호</th>
      <td width=80% class="inline">
       <input type=password name=pwd1 size=15 class="input-sm">
       &nbsp;&nbsp;재입력:<input type=password name=pwd2 size=15 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>이름</th>
      <td width=80% class="inline">
       <input type=text name=name size=15 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>성별</th>
      <td width=80% class="inline">
       <input type="radio" name=sex value="남자" checked>남자
       <input type="radio" name=sex value="여자">여자
      </td> 
    </tr>
    <tr>
      <th align="right" width=20%>생년월일</th>
      <td width=80% class="inline">
       <input type="date" size=20 name=birthday>
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>우편번호</th>
      <td width=80% class="inline">
       <input type="text" name=post size=10 readonly>
       <button class="btn">우편번호검색</button>
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>주소</th>
      <td width=80% class="inline">
       <input type=text name=addr1 size=50 class="input-sm" readonly>
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>상세주소</th>
      <td width=80% class="inline">
       <input type=text name=addr2 size=50 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>전화번호</th>
      <td width=80% class="inline">
       <input type=text name=tel1 size=10 class="input-sm" readonly value="010">-
       <input type=text name=tel2 size=20 class="input-sm">
      </td>
    </tr>
    <tr>
      <th align="right" width=20%>소개</th>
      <td width=80% class="inline">
       <textarea rows="10" cols="55" name=content></textarea>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <button class="btn">가입</button>
        <input type=button class="btn" value="취소" 
         onclick="javascript:history.back()">
      </td>
    </tr>
  </table>
  </main>
</div>






</body>
</html>