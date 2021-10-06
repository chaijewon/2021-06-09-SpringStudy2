<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 30px;
}
.row {
   margin: 0px auto;
   width:960px;
}
</style>
</head>
<body>
  <div class="container">
    <h1 class="text-center">&lt;${vo.title }&gt; 상세보기</h1>
    <div class="row">
      <table class="table">
        <tr>
          <td class="text-center">
           <embed src="http://youtube.com/embed/${vo.key }" width=900 height=450/>
          </td>
        </tr>
        <tr>
          <td>곡명:${vo.title }</td>
        </tr>
        <tr>
          <td>가수명:${vo.singer }</td>
        </tr>
        <tr>
          <td>앨범:${vo.album }</td>
        </tr>
        <tr>
          <td class="text-right">
            <a href="list.do" class="btn btn-lg btn-success">목록</a>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>








