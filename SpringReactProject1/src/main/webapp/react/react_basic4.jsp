<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/react@15/dist/react.js"></script>
<script src="https://unpkg.com/react-dom@15/dist/react-dom.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.34/browser.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 960px;
}
</style>
</head>
<body>
   <div class="container">
     <h1 class="text-center">사원 목록</h1>
     <div class="row">
       <!-- 사원의 목록 : react에서 만들어서 출력 (배포:jar,war, webpack(자바스크립트를 한개의 파일)) -->
     </div>
   </div>
   <script type="text/babel">
     class Sawon extends React.Component{
         // 화면 UI ==> 원하는 영역에 출력을 요청 
         render(){
            return (
               <ul>
                 <li>사번:{this.props.info.sabun}</li>
                 <li>이름:{this.props.info.name}</li>
                 <li>성별:{this.props.info.sex}</li>
                 <li>부서명:{this.props.info.dept}</li>
                 <li>근무지:{this.props.info.loc}</li>
               </ul>
            )
         }
     }

     // 사원 목록 
     const sawon={"sabun":1,"name":"홍길동","sex":"남자","dept":"개발부","loc":"서울"}
     // 속성값으로 전송 
     ReactDOM.render(<Sawon info={sawon}/>,document.querySelector('.row'))
   </script>
</body>
</html>











