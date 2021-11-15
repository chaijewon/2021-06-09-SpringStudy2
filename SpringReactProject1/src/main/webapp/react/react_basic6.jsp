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
  width: 1200px;
}
</style>
</head>
<body>
  <div class="container">
    <h1 class="text-center">여러개의 클래스(함수) 묶기(JSP:include)</h1>
    <div class="row">
      <!-- React에서 생성된 HTML을 첨부 -->
    </div>
  </div>
  <script type="text/babel">
    class App extends React.Component{
         render(){
           return (
            <div>
              <Header/>
              <Content/>
              <Footer/>
            </div>
            )
         }
    }
    class Header extends React.Component{
        render(){
           return (
           <h1>Header부분</h1>
           )
        }
    }
    class Content extends React.Component{
        render(){
           return (
           <h1>내용(실제 출력)부분</h1>
           )
        }
    }
    class Footer extends React.Component{
       render(){
           return (
           <h1>회사 정보 출력 부분</h1>
           )
        }
    }
    ReactDOM.render(<App/>,document.querySelector('.row'))
  </script>
</body>
</html>










