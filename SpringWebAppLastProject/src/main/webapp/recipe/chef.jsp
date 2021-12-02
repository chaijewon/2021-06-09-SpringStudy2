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
.container{
   margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 800px;
}
</style>
</head>
<body>
   <div class="container">
    <div class="row">
      <c:forEach var="vo" items="${list }">
       <table class="table">
         <tr>
          <td class="text-center" width=30% rowspan="2">
            <a href="../recipe/chef_make.do?chef=${vo.chef }"><img src="${vo.poster }" width=110 height=110 class="img-circle"></a>
          </td>
          <td colspan="4"><h3 style="color:orange"><a href="../recipe/chef_make.do?chef=${vo.chef }">${vo.chef }</a></h3></td>
         </tr>
         <tr>
           <td class="text-center"><img src="mc1.png">&nbsp;${vo.mem_cont1 }</td>
           <td class="text-center"><img src="mc2.png">&nbsp;${vo.mem_cont2 }</td>
           <td class="text-center"><img src="mc7.png">&nbsp;${vo.mem_cont7 }</td>
           <td class="text-center"><img src="mc3.png">&nbsp;${vo.mem_cont3 }</td>
         </tr>
       </table>
      </c:forEach>    
    </div>
    <div class="row">
      <div class="text-center">
        <a href="chef.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-danger">이전</a>
        ${curpage } page / ${totalpage } pages
        <a href="chef.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-primary">다음</a>
      </div>
    </div>
   </div>
</body>
</html>






