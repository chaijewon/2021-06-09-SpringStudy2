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
table#detail > tbody > tr:hover{
  background-color: #CCFFCC;
  cursor:pointer
}
</style>
</head>
<body>
   <div class="container">
     <h1 class="text-center">영화 목록</h1>
     <div class="row">
       <!-- 출력 위치 (React => UI) -->
     </div>
   </div>
   <!-- 
         1. 자바 
            int age; String name;
         2. 자바스크립트 
            let age , let name;
         3. VueJS
            data:{
               age:1,
               name:''
            }
         4. React 
            this.state={
                age:1,
                name:''
            }
         5. Kotlin
            var age:Int=1
            var name:String=""
         6. Oracle 
            age NUMBER
            name VARCHAR2(10)
    -->
   <script type="text/babel">
     class MovieList extends React.Component
     {
         // 변수 선언 
         constructor(props)
         {
              super(props)
              this.state={
                 movie_list:[], // List
                 movie_detail:{}, // MovieVO
                 open:false
              }
         }
         // 데이터 읽기 => 저장 
         componentWillMount()
         {
              axios.get("http://localhost:8080/web/movie/rest_list.do")
              .then(response=>{
                  console.log(response.data)
                  this.setState({movie_list:response.data})  
              })
         }
         movieDetailData(m)
         {
              this.setState({movie_detail:m,open:true})
         }
         // 읽기 데이터 출력
         render()
         {
             let html=this.state.movie_list.map((m)=>
                   <tr onClick={this.movieDetailData.bind(this,m)}>
                        <td className="text-center">
                         <img src={m.poster} style={{"width":"30px","height":"30px"}}/>
                        </td>
                        <td>{m.title}</td>
                        <td>{m.grade}</td>
                        <td>{m.genre}</td>
                   </tr>
             )
             return (
               <div>
                 <div className="col-sm-8">
                   <table className="table" id="detail">
                     <thead>
                       <tr className="info">
                        <th className="text-center"></th>
                        <th className="text-center">영화명</th>
                        <th className="text-center">등급</th>
                        <th className="text-center">장르</th>
                       </tr>
                     </thead>
                     <tbody>
                       {html}
                     </tbody>
                   </table>
                 </div>
                 <div className="col-sm-4">
                   {this.state.open?<MovieDetail movie={this.state.movie_detail}/>:null}
                 </div>
               </div>
             )
         } 
         // 이벤트 처리 ==> 포함하고 있는 클래스가 있는 경우에 값을 전송시 => 속성값을 이용해서 값을 전달
         // <MovieDetail (movie={this.state.movie_detail})/>
         // 단방향 통신만 가능  Movie => MovieDetail => Sub....   => 페이스북 , 인스타그램 , 항공사 ...
         // 5G
     }
     class MovieDetail extends React.Component
     {
         // JSX => JavaScript+XML
         /*
                           클래스 제작
             class className extends React.Component
             {
                                 변수설정:constructor() => 변수가 없는 경우에는 생략 
                                 데이터값을 서버에 읽기 : componentWillMount() => 읽을 값이 없으면 생략 
                                 화면 UI (HTML) : render() => 생략이 불가능 
                =============== XML형식의 제작 ("" => 생략)
                  HTML의 형식 
                  1. 전체를 감싸는 태그가 필요하다 (최상위 태그 , 루트 태그 => 트리형태로 저장)
                     <div></div>
                     <div></div>  ==> 오류 

                    <div>
                     <div></div>
                     <div></div>  
                    </div>
                  2. 속성값은 반드시 ""를 사용 
                  3. 외부 CSS를 적용할때  className=""
                  4. 여는 태그 , 닫는 태기가 동일  ==> 단독 태그는 뒤에서 닫는다 
                     <img/> <input/> <br/> <hr/> 
                  5. 대소문자 구분 
                                          클래스는 대소문자 , html 태그 소문자로 한다 
                  6. 각 클래스의 render()에서 실행된 결과값을 받을 때
                     <클래스 /> => render()의 return값을 받는다 (HTML)
                   ** rowSpan , colSpan , style={{}}

                                  데이터를 갱신시 반드시 this.setState({}) => render()를 호출해서 HTML을 변경  
             } 
         */
         render()
         {
             return (
                <table className="table">
                  {/* render() */}
                  <tr>
                    <td className="text-center" width="30%" rowSpan="8">
                      <img src={this.props.movie.poster} width="100%"/>
                    </td>
                    <td colSpan="2">{this.props.movie.title}</td>
                  </tr>
                  <tr>
                    <td width="20%" className="text-center">국가</td>
                    <td width="50%">{this.props.movie.nation}</td>
                  </tr>
                  <tr>
                    <td width="20%" className="text-center">장르</td>
                    <td width="50%">{this.props.movie.genre}</td>
                  </tr>
                  <tr>
                    <td width="20%" className="text-center">등급</td>
                    <td width="50%">{this.props.movie.grade}</td>
                  </tr>
                  <tr>
                    <td width="20%" className="text-center">평점</td>
                    <td width="50%">{this.props.movie.score}</td>
                  </tr>
                  <tr>
                    <td width="20%" className="text-center">상영일</td>
                    <td width="50%">{this.props.movie.regdate}</td>
                  </tr>
                  <tr>
                    <td width="20%" className="text-center">시간</td>
                    <td width="50%">{this.props.movie.time}</td>
                  </tr>
                  <tr>
                    <td width="20%" className="text-center">누적관객</td>
                    <td width="50%">{this.props.movie.showUser}</td>
                  </tr>
                </table>
             )
         }
     }

     ReactDOM.render(<MovieList /> , document.querySelector('.row'))
     /*
             <MovieList />   => render() return값을 받는다(HTML)
             $('.row').html(html)
     */
   </script>
</body>
</html>










