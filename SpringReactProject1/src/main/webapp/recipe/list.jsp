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
<%--
        React 동작 (생명주기)
        1. constructor() => 메모리 할당 (생성자) (멤버 설정하는 위치 : react (props,state)
        2. componentWillMount() : HTML을 메모리에 저장하기 전 : 필요한 데이터를 스프링 서버로부터 읽어 온다 
        3. render() : 완료된 HTML을 보내준다 
        4. componentDidMount() : window.onload => 화면에 출력 
                                 => 다른 프레임워크와 연결 (JQuery , AngularJS...)
                                 
        => 5. setState({}) => state에 들어가 있는 데이터가 변경 ==> render()
        
 --%>
<body>
  <div class="container-fluid">
    <h1 class="text-center">레시피 목록</h1>
    <div class="row">
      <%-- 스프링서버 값을 받아서 출력위치  --%>
    </div>
  </div>
  <script type="text/babel">
    class Recipe extends React.Component{
        // 데이터를 받는 변수를 설정  => 변경이 가능 (state)
        /*
             new Vue({
                 el:
                 data: ==> constructor
                 beforeMount:function(){} => componentWillMount
                 mounted:function(){} => componentDidMount
             })
        */
        constructor(props) // 생성자를 만들때 사용하는 기본 형식
        {
            super(props)
            // 멤버변수 설정  => VueJS ==> data:{} 
            this.state={
                page:1,
                recipe_list:[],
                totalpage:0
            }
            // 이벤트 등록 (버튼 클릭, 이미지 클릭)
            this.prev=this.prev.bind(this)
            this.next=this.next.bind(this)
            console.log("constructor(props) Call...")
        }
        // 사용자 정의 함수 처리 
        prev(){
           this.state.page=this.state.page>1?this.state.page-1:this.state.page
           axios.get("http://localhost:8080/web/recipe/rest_list.do",{
                   params:{
                       page:this.state.page
                   }
            }).then(response=>{
                 console.log(response) // header(정보) 
                 console.log(response.data) // this.recipe_list=response.data
                 this.setState({recipe_list:response.data}) // render를 호출 
            })
        }
        next(){
           this.state.page=this.state.page<this.state.totalpage?this.state.page+1:this.state.page
           axios.get("http://localhost:8080/web/recipe/rest_list.do",{
                   params:{
                       page:this.state.page
                   }
            }).then(response=>{
                 console.log(response) // header(정보) 
                 console.log(response.data) // this.recipe_list=response.data
                 this.setState({recipe_list:response.data}) // render를 호출 
            })
        }
        // 스프링으로부터 데이터를 받아서 recipe_list에 저장 
        componentWillMount(){ 
            console.log("componentWillMount()")
            // axios => 요청 , then => 응답  : success:function(res)
            // 스프링에서 데이터를 받는다 
            axios.get("http://localhost:8080/web/recipe/rest_list.do",{
                   params:{
                       page:this.state.page
                   }
            }).then(response=>{
                 console.log(response) // header(정보) 
                 console.log(response.data) // this.recipe_list=response.data
                 this.setState({recipe_list:response.data}) // render를 호출 
            })

            // 총페이지 받기
            axios.get("http://localhost:8080/web/recipe/rest_total.do")
                .then(response=>{
                 console.log(response.data)
                 this.setState({totalpage:response.data.totalpage})
            });
        }
        componentDidMount(){ // $(function())=>window.onload
            console.log("componentDidMount()")
        }
        render(){
            console.log("render() Call...")
            const html=this.state.recipe_list.map((r)=>
                <div className="col-md-3">
                 <div className="thumbnail">
                  <a href="#">
                   <img src={r.poster} alt="Lights" style={{"width":"100%"}}/>
                   <div className="caption">
                     <p style={{"fontSize":"8px"}}>{r.title}</p>
                     <p>by.{r.chef}</p>
                   </div>
                 </a>
                </div>
               </div>
            )
            return (
               <div>
                 {html}
                 <div className="text-center">
                     <input type="button" value="이전" className="btn btn-sm btn-danger" onClick={this.prev}/>
                       {this.state.page} page / {this.state.totalpage} pages
                     <input type="button" value="다음" className="btn btn-sm btn-primary" onClick={this.next}/>
                 </div>
               </div>
            )
        }
    }

    // 실행 
    ReactDOM.render(<Recipe/>,document.querySelector('.row'))
  </script>
</body>
</html>









