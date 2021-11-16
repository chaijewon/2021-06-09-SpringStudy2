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
     // 메인 보드 
     class Main extends React.Component // 조립기 (Container)=> 기능별로 분리해서 작업 (Component=>한개의 기능)
     {
         // 데이터를 받을 변수 설정 ==> 하위 컴포넌트로 전송 (props) 
         constructor(props)
         {
            super(props)
            this.state={
               music_list:[],
               ss:'',
               key:''
            }
            // 이벤트 등록 
            this.handlerUseInput=this.handlerUseInput.bind(this)
            this.handlerKeyInput=this.handlerKeyInput.bind(this)
         }
         handlerKeyInput(key)
         {
            this.setState({key:key})
         }
         // 구현 
         handlerUseInput(ss)
         {
            this.setState({ss:ss})
         }
         // 스프링으로부터 데이터를 받는다 
         componentWillMount()
         {
             axios.get("http://localhost:8080/web/music/rest_list.do") // 요청 (JSON을 달라)
             .then(response=>{
                 console.log(response.data)
                 this.setState({music_list:response.data})
             })
         }
         // 화면 UI 제작 
         render()
         {
            // 하위에 값을 넘기기 위해서는 반드시 속성을 이용해서 전송 
            
            return (
               <div>
                 <div className="col-sm-8">
                   <SearchBar ss={this.state.ss} onUserInput={this.handlerUseInput}/>
                   <div style={{"height":"30px"}}></div>
                   <MusicTable music={this.state.music_list} ss={this.state.ss} onKeyInput={this.handlerKeyInput}/>
                  </div>
                 <div className="col-sm-4">
                    <iframe src={"http://www.youtube.com/embed/"+this.state.key} style={{"width":"350px","height":"350px"}}></iframe>
                 </div>
                 </div>
            )
         }
     }
     // CPU
     class MusicTable extends React.Component
     {
          constructor(props)
          {
              super(props)
              this.handlerKey=this.handlerKey.bind(this)
          }
          handlerKey(key)
          {
               this.props.onKeyInput(key)
          }
          render()
          {
             let row=[]
             this.props.music.map((m)=>{
                if(m.title.indexOf(this.props.ss)==-1)
                {
                    return
                }
                row.push(<MusicRow music={m} onKeyInput={this.props.onKeyInput}/>)
             })
             return (
               <table className="table">
                 <thead>
                   <tr className="success">
                     <th className="text-center">순위</th>
                     <th className="text-center"></th>
                     <th className="text-center">곡명</th>
                     <th className="text-center">가수명</th>
                     <th className="text-center">앨범</th>
                   </tr>
                 </thead>
                 <tbody>
                   {row}
                 </tbody>
               </table>
             )
          }
     }
     // 하드 디스크 (First camp) => JSP(React) , MVC(Redux)
     class MusicRow extends React.Component
     {
          constructor(props)
          {
              super(props)
              this.state={
                 isopen:true
              }
              //this.keyDataInput=this.keyDataInput.bind(this)
          }
          
          keyDataInput(key)
          {
              this.props.onKeyInput(key)
          }
          render()
          {
             return (
               <tr>
                 <td className="text-center">{this.props.music.no}</td>
                 <td className="text-center">
                    <img src={this.props.music.poster} style={{"width":"30px","height":"30px"}}
                      onClick={this.keyDataInput.bind(this,this.props.music.key)}
                    />
                 </td>
                 <td>{this.props.music.title}</td>
                 <td>{this.props.music.singer}</td>
                 <td>{this.props.music.album}</td>
               </tr>
             )
          }
     }
     // 비디오 카드
     class SearchBar extends React.Component
     {
          constructor(props)
          {
              super(props)
              // 이벤트 등록 
              this.handlerChange=this.handlerChange.bind(this)
          }
          handlerChange(e)
          {
             this.props.onUserInput(e.target.value)
          }
          render()
          {
              return (
                <table className="table">
                  <tr>
                   <td>
                     <input type="text" size="50" className="input-sm"
                        value={this.props.ss}
                        onChange={this.handlerChange}
                     />
                   </td>
                  </tr>
                </table>
              )
          }
     }

     // 동영상을 실행한다 
     class MusicMovie extends React.Component
     {
         // 생략 할 수 없는 함수 => render()
         render()
         {
             return (
                 <div className="col-sm-4">
                  <iframe src={"http://youtube.com/embed/"+this.props.key} style={{"width":"350px","height":"350px"}}></iframe>
                 </div>
             )
         }
     }
   ReactDOM.render(<Main/>,document.querySelector('.row'))
   </script>
</body>
</html>












