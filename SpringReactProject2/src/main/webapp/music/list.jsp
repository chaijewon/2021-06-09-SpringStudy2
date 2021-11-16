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
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="http://code.jquery.com/jquery.js"></script>

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
     <h1 class="text-center">Music Top100</h1>
     <div class="row">
       <!-- react에서 만든 UI첨부 영역 -->
     </div>
   </div>
   <script type="text/babel">
     // type="text/babel"(ES6)  type="text/javascript"(ES5)
     class Music extends React.Component{ // function Music() => Hooks
         // 변수 선언 = 생성자 
         constructor(props)
         {
             super(props) // 속성값을 받을 수 있다 = <Music data=""/> => this.props.data
             // 멤버변수 2개 => props(불변) / state(변경이 가능한 값)
             this.state={
                // data:{}
                music_list:[],  // List => JavaScript (Array)
                music_detail:{} // MusicVO => JavaScript (Object=>{}) => 자바스크립트 객체 표현법 (JSON)
                // JSONP
             }
             // 이벤트 등록하는 위치 
         }
         // 서버연결 = 데이터 읽기
         componentWillMount()
         {
             // music_list에 값을 채운다 
             // 요청 => 응답 : axios => async / await => 비동기적 
             axios.get("http://localhost:8080/web/music/rest_list.do")
             .then(response=>{
                 console.log(response.data)
                 // 실제 변수에 값을 대입 
                 // this.state.music_list=response.data (X) => 값을 설정시에는 반드시 setState() => render()
                 this.setState({music_list:response.data})
             })
             
         }
         // 화면 UI  ==> 1. 처음에 한번 호출 , 2. setState({})=>데이터 변경 => render() => componentDidMount
         render()
         {
             // for(MusicVO vo:list)
             let html=this.state.music_list.map((vo)=>
                  <tr>
                   <td className="text-center">{vo.no}</td>
                   <td className="text-center">
                     <img src={vo.poster} style={{"width":"30px","height":"30px"}}/>
                   </td>
                   <td>{vo.title}</td>
                   <td>{vo.singer}</td>
                   <td>{vo.album}</td>
                  </tr>
             )
             return (
              <div>
                <table className="table">
                 <tr>
                   <td>
                    <input type="text" size="30" className="input-sm" id="keyword"/>
                   </td>
                 </tr>
                </table>
                <table className="table" id="user-table">
                  <thead>
                   <tr className="danger">
                     <th className="text-center">순위</th>
                     <th className="text-center"></th>
                     <th>곡명</th>
                     <th>가수명</th>
                     <th>앨범</th>
                   </tr>
                  </thead>
                  <tbody>
                    {html}
                  </tbody>
                </table>
               </div>
             )
         }
         // Jquery 연결  ==> $(function(){}) => window.onload
         componentDidMount()
         {
             $('#keyword').keyup(function(){
	             let k=$('#keyword').val()
	             $('table#user-table > tbody > tr').hide()
	             let temp=$('table#user-table > tbody > tr > td:nth-child(5n+3):contains("'+k+'")')
	             $(temp).parent().show()
             })
         }
     }

   // 리액트 실행 
   ReactDOM.render(<Music/>,document.querySelector('.row'))
   </script>
</body>
</html>









