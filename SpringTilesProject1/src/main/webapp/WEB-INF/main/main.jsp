<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <center>
   <h1>Tiles를 사용한 화면 변경(include를 대체)</h1>
   <table border=1 bordercolor="black" width="800" height="700">
     <tr>
      <td width=800 colspan="2" height="100">
        <tiles:insertAttribute name="header"/> 
      </td>
     </tr>
     <tr>
       <td width=200 height=500>
         <tiles:insertAttribute name="menu"/>
       </td>
       <td width=600 height=500>
         <tiles:insertAttribute name="content"/>
       </td>
     </tr>
     <tr>
      <td width=800 colspan="2" height="100">
         <tiles:insertAttribute name="footer"/>
      </td>
     </tr>
   </table>
  </center>
</body>
</html>