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
<%--
      javascript => ES5 => <javascript type="text/javascript">
                    ES6 (VueJs,ReactJS) => <javascript type="text/babel">
      babel 
        JSX => JavaScript + XML => JSX
                추가
            1. 변수 
               var => let , const
               === 단점 : 사용범위가 명확하지 않는다 
               
               function func()
               {
                   var a=''; // 지역변수  => 블럭변수 ({}이 종료하면 자동으로 메모리에서 사라진다)
               }
               alert(a); ==> Java는 오류 , JavaScript는 오류발생이 없다 
                              일반 프로그램으로 지역변수를 설정 => let
               function func((
               {
                  let a='';
               }
               alert(a); ==> JavaScript에서 오류발생 => {}이 종료하면 메모리에서 사라진다 (지역변수의 사용 범위가 명확하다)
               const => 상수형 변수 (final) => 값이 한번 설정되면 변경할 수 없다
            2. 연산자에서 변경 
                              비교연산자 (같다 : ===) : ==사용이 가능 (=== 권장)
                              다중 if
                         if() ~ else if() ~ else if() ~ else
                         { 조건 && 값  , 조건 && 값......}   
                => ES8 => 어노테이션 
            3. 함수 
               function func_name(){} 
               let a=function(){}
               let a=()=>{}  ===> 화살표 함수 
               
            4. 제어문 => for(let i=0;i<10;i++) ==> forEach (map,foreach,each)
                                                         =====
                                                         
            5. JSX => 예전에는 Ajax ==> html태그에 "" => ""없이 사용이 가능 (JSX)
            
            function display()
            {
                let html="";
                html+="<html><body><div><h1>Hello</h1></div></body></html>"
            }
            
            => <html>
                <body>
                  <h1></h1>
                </body>
              </html>
              
            => JSX 문법이 존재한다 
               1. 반드시 전체를 감싸는 태그가 한개 필요하다 (최상위 태그)
                                예)
                      <div class="row"></div>
                      <div class="row"></div>  ==> 오류 (계층구조가 없다)
                     
                     <div>  
                      <div class="row"></div>
                      <div class="row"></div>
                     </div>                    ==> 정상 수행 
               2. 속성값은 반드시 ""를 사용한다 
               3. 여는태그와 닫는 태그가 명확해야한다 
                  <a><b><c></b></c></a> ==> 오류 
                  <a><b><c></c></b></a> ==> 정상 
               4. <a></a><br> ==> 오류 
                  <a></a><br/> ==> 단독태그 
                         ===== <img><input><hr> ==> <img/> <input /> <hr/>
               5. style ==> <div style="{{속성:갑,속성:값}}">
               6. 이클립스(STS) => react,vue편집기가 없다 (메모리 수준)
                  => 웹스톰 (편집기 : Front-End , 안드로이드 편집기) => 배포
          ===================================================================
          1. React 사용 위치 
             ======
             1) 출력이 많은 경우 
             2) 반복이 많은 경우 (페이지)   
                ==================== Facebook (검색) => 속도
             3) 실시간 변경 : 날씨, 증권, 배달 ... (5G)
          2. 최근 : 스프링(데이터연결 , 데이터 수집..) => 서버단 (Back-End)
                  Front-End : 속도(출력) , 변경되는 속도 => Ajax(Jquery) , VueJS, ReactJS
                  ==================================================================
                      + Full Stack
          3. React의 동작 순서  ==> el:'#app'
             <div id="app">
               => 들어갈 HTML을 만들어서 전송
             </div>
             <script type="text/babel">
               class Movie extends React.Component
               {
                                           생성자
                     componentWillMount(){}
                     componentDidMount(){}
                     render(){}
               }
               // 시작위치 
               ReactDOM.render(<Movie/>,document.getElementById("app"))
                               ======== new Movie() => 생성자 호출 
                                        new Movie().componentWillMount()
                                        new Movie().componentDidMount()
                                        new Movie().render() ==> return값을 받아서 #app인 곳에 출력
                                        ==================== HTML로 디자인
                                       
                                        
             </script> 
             *** 구분 
                 html태그(소문자) , xml태그(1. 클래스명 , 2. 함수명) => 반드시 대문자로 시작한다 
                 
             *** 기본 React => class형 , 함수형 
                 Hooks => MVC
                 Redux => MVC
                 => 화면 이동 : Router           
 --%>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
window.onload=function(){
	// 자동 실행 => 브라우저에 <html>이 실행된 경우 
	document.getElementById("app").innerHTML="<h1>Hello JavaScript!!</h1>";
}
$(function(){  // window.onload=function() , $(document).ready(function(){})
	$('#app2').html('<h1>Hello Jquert!!</h1>');
})
</script>
</head>
<body>
   <!-- 출력할 위치 지정  Aajx  $('#app').html() => document.getElementById("app").innerHTML="<html>" -->
   <!-- 일반 자바스크립트 -->
   <div id="app">
     
   </div>
   <!-- Jquery 라이브러리  -->
   <div id="app2">
   
   </div>
   <!-- React 라이브러리  -->
   <div id="app3">
    
   </div>
   <div class="app4">
   
   </div>
   <script type="text/babel">
    // function App() => 권장 사항 
    function App4()
    {
          return (
            <h1>Hello React4!!</h1>
          )
    }
    function App3()
    {
        return (
          <h1>Hello React Function으로 처리</h1>
        )
    }
    class App extends React.Component{
       // HTML 작성 
       render(){
           return (
              <h1>Hello React!!</h1>
           )
       }
    }
    //ReactDOM.render(<App3/>,document.getElementById('app3'))
    // <App3(클래스명),(함수명)/> => function App3 , class App3 extends React.Component
    // App3=>return값을 받아서 => 지정된 태그위치에 HTML을 첨부한다 
    ReactDOM.render(<App4/>,document.querySelector('.app4'))
    // class명 (.) , id명(#) , 일반 태그 (태그명)
   </script>
</body>
</html>







