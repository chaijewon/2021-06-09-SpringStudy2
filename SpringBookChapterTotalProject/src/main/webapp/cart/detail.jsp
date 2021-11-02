<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	/*
	     JavaScript => Jquery , VueJS , ReactJS
	     1. click : button,img ...
	     2. hover 
	     3. change : select 
	     4. mouseover , mouseout 
	     5. keyup 
	*/
	$('#sel').change(function(){
		let count=$(this).val();
		let price=$('#price').text();
		let total=parseInt(count)*parseInt(price)+3000; //parseInt() : 정수 변환
		$('#total').text(total+"원");
		$('#amount').val(count);
	})
})
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/back.png');">
  <div id="breadcrumb" class="hoc clear"> 
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="hoc container clear"> 
    <!-- main body -->
    <!-- ################################################################################################ -->
    <div class="content two_quarter first"> 
      <!-- ################################################################################################ -->
      
      <img src="${vo.product_poster }" width=120%>
      
      <!-- 댓글 -->
      <jsp:include page="../reply/list.jsp"></jsp:include>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <!-- ################################################################################################ -->
    <div class="sidebar two_quarter"> 
      <!-- ################################################################################################ -->
      <h6>상품 상세 정보</h6>
      <table class="table">
        <tr>
         <td colspan="2">${vo.product_name }</td>
        </tr>
        <tr>
          <th width=20%>금액</th>
          <td width=80%>${vo.product_price }</td>
        </tr>
        <tr>
          <th width="20%">배송료</th>
          <td width=80%>3,000원 (30,000원이상 무료배송)</td>
        </tr>
        </table>
        <table class="table">
        <tr>
          <td class="inline" align="center">
           <select id="sel">
            <c:forEach var="i" begin="1" end="10">
              <option value="${i }">${i }개</option>
            </c:forEach>
           </select> 
           &nbsp;&nbsp; <span id="price">${vo.product_price }</span>원
          </td>
        </tr>
      </table>
      <hr>
           총 합계금액 : <span id="total" style="color:red"></span>
      <!-- ################################################################################################ -->
     <div style="text-align:center;width: 100%" class="inline">
	     <c:if test="${sessionScope.id!=null }">
		     <form method="post" action="../cart/cart_ok.do">
		       <input type=hidden name="product_id" value="${vo.product_id }">
		       <input type=hidden name="amount" value="" id="amount">
		       <input type="submit" class="btn btn-sm" value="장바구니">
		     </form>
	     </c:if>
	     <a href="../cart/list.do" class="btn btn-sm">상품 목록</a>
	    </div>
      
    </div>
     
    <!-- ################################################################################################ -->
    <!-- / main body -->
   
    
    <div class="clear"></div>
  </main>
</div>

</body>
</html>