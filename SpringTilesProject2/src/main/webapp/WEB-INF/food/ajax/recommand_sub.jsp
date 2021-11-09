<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.fd').click(function(){
		let fd=$(this).text();
		$.ajax({
			type:'get',
			url:'../food/recommand_result.do',
			data:{"fd":fd},
			success:function(result)
			{
				$('#print').html(result);
			}
		})
	})
})
</script>
</head>
<body>
  <table class="table">
    <tr>
      <td class="text-center">
       <c:forEach var="sub" items="${list }">
         <span class="btn btn-sm btn-success fd">${sub }</span>
       </c:forEach>
      </td>
    </tr>
  </table>
  <div id="print">
    
  </div>
</body>
</html>






