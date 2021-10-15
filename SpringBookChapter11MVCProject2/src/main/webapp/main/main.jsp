<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 사용자 값을 보낸다  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%--
        이름:<input type="text" name="name[0]" size=15><br>
        이름:<input type="text" name="name[1]" size=15><br>
        이름:<input type="text" name="name[2]" size=15><br>
        이름:<input type="text" name="name[3]" size=15><br>
        이름:<input type="text" name="name[4]" size=15><br>
    ============================== List(Collection)
    
        이름:<input type="text" name="name" size=15><br>
        이름:<input type="text" name="name" size=15><br>
        이름:<input type="text" name="name" size=15><br>
        이름:<input type="text" name="name" size=15><br>
        이름:<input type="text" name="name" size=15><br>
    ============================== String[]
    
        이름:<input type="text" name="name1" size=15><br>
        이름:<input type="text" name="name2" size=15><br>
        이름:<input type="text" name="name3" size=15><br>
        이름:<input type="text" name="name4" size=15><br>
        이름:<input type="text" name="name5" size=15><br>
    =========== String name1,String name2,String name3...
 --%>
 <%--
       여러개 사진을 동시에 올릴때 (파일 여러개 전송)
    
    297page : 리스트(List)타입 => 프로퍼티 (클래스 변수로 선언)
              리스트에 값을 채울때 => name[0]
  --%>
<body>
   <form method="post" action="print3.do"><!-- MainController -->
        이름:<input type="text" name="name[0]" size=15><br>
        이름:<input type="text" name="name[1]" size=15><br>
        이름:<input type="text" name="name[2]" size=15><br>
        이름:<input type="text" name="name[3]" size=15><br>
        이름:<input type="text" name="name[4]" size=15><br>
        <button>전송</button>
   </form>
</body>
</html>



