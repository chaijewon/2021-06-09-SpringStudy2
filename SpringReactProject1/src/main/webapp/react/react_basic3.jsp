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
   <%--
        react에서 사용하는 변수 
        ================== let,const
        = props : 속성값을 전달 (불변 => 상수)
          ReactDOM.render(<App name="" sex=""/>,document.getElementById())
          class => 자동 인식
          function App(props) ==> 단일 데이터 , 여러개의 데이터 (JSON) => map,foreach를 이용하면 자동 파시;ㅇ
          {
             props.name 
             props.sex
          }
        = state : 외부에서 데이터를 읽어 올때 (변수 => 수시로 변경해서 사용이 가능)
    --%>
    <!-- 1. HTML을 만들어서 출력할 위치 확정 -->
    <!-- 
          VueJS
            beforeCreate
            created
            ======================= constuctor()
            beforeMount : HTML을 메모리에 저장하기 전 상태 (HTML이 메모리에 올라간 상태 : DOM=> 트리 형태(계층구조))
             => componentWillMount
            mounted => window.onload   ==> $(function())
             => componentDidMount
     -->
    <div class="container">
      <div class="row">
        
      </div>
    </div>
    <script type="text/babel">
      class App extends React.Component{
         // react/vue/jquery 역할 => View(화면 출력 목적)
         // 멤버변수 => constructor()
         // 외부에서 데이터 읽기 => 멤버변수에 값을 대입 componentWillMount() 
         // 외부 라이브러리 연결 (Jquery) => componentDidMount() => window.onload => $(function())
         // HTML을 조작해서 화면에 출력할 내용을 만들기 => render()
         // 멤버변수는 두개다 => props (속성값)=>상수형 , state (변수=> 수시로 값을 변경)=> data:{} => this.props.속성명 , this.state.변수명
         // <App name="홍길동" sex="남자"/> => props
         // 스프링 서버로 투터 값을 받는 경우 => this.state
         /*
                state는 선언 
                constructor()
                {
                                     멤버 변수
                   this.state={
                                         정수  => page:0
                                         실수  => score:0.0
                                         문자열 => msg:''
                                         객체    => detail:{}
                                         배열   => recipe_list:[]
                   }
                }

                new Vue({
                    data:{
                                        정수  => page:0
                                         실수  => score:0.0
                                         문자열 => msg:''
                                         객체    => detail:{} ==> JSON (자바스크립트 객체 표현법)
                                         배열   => recipe_list:[]
                })
         */
         render()
         {
             return (
               <div>
                <h1>이름:{this.props.name}</h1>
                <h1>성별:{this.props.sex}</h1>
               </div>
             )
         }
      }
      ReactDOM.render(<App name="홍길동" sex="남자"/>,document.querySelector('.row'))
    </script>
</body>
</html>















