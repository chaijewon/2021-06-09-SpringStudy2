<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- react 라이브러리  --%>
<script src="https://unpkg.com/react@15/dist/react.js"></script>
<script src="https://unpkg.com/react-dom@15/dist/react-dom.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.34/browser.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<%-- react에서 jquery 연동 --%>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
   margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 1600px;
}
</style>
</head>
<body>
  <div class="container">
    <h1 class="text-center">쉐프 목록</h1>
    <div class="row">
     <%-- 출력 위치  --%>
    </div>
  </div>
  <script type="text/babel">
    /*
         실행시  ==> constructor(props) ==> componentWillMount() ==> render() ==> componentDidMount()
     state(데이터)를 변경 (이벤트 : 버튼 클릭 , 데이터 입력 ....) 
          state ==> setState() ==> render() ==> componentDidMount()
    */
    class Chef extends React.Component{
       constructor(props) // 변수 선언 (this.state)
       {
       }
       componentWillMount() // 첫페이지 데이터 읽기 
       {
       }
       componentDidMount() // Jquery연동 => 사용자 이벤트로 처리 
       {
       }
       render() // HTML을 변경하는 위치  (1. 처음에 한번 , 2. setState()) => 데이터가 변경되면 호출 
       {
       }
    }
  </script>
</body>
</html>






