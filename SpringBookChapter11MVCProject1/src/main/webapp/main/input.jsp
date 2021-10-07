<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <!-- @RequestMapping() = GetMapping()+PostMapping() -->
   <a href="print.do?page=1">전송</a><!-- @GetMapping() : GET방식 : default (목록출력 , 입력창)-->
   <br>
   <form method="post" action="print2.do"><!-- POST방식 @PostMapping() -->
     <input type=text name=id size=10>
     <input type=submit value="전송">
   </form>
</body>
</html>