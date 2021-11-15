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
     <div class="row">
       <!-- react에서 조작한 출력 위치 -->
     </div>
   </div>
   <script type="text/babel">
     //클래스형 컴포넌트 제작 ==> 클래스형 => render는 생략할 수 없다 (HTML을제작)
     class Movie extends React.Component
     {
         // 외부 CSS를 적용 할때 (react안에서는 반드시 => class(X) , className
         render(){
            return (
              <div>
               <h1 className="text-center">두개의 태그를 이용한다</h1>
               <h3 className="text-center">서브 태그= Data출력</h3>
               <br/>
               <h5 className="text-center" style={{"color":"red"}}>서브3 태그</h5>
               <h4 className="text-center">서브2 태그</h4>
              </div>
            )
         }
     }
     ReactDOM.render(<Movie/>,document.querySelector('.row'))
   </script>
</body>
</html>









