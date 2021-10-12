package com.sist.web;
import java.util.*;
import java.io.*;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.*;
/*
 *    public class DispatcherServlet extends HttpServlet
 *    {
 *       // web.xml => 사용자가 등록한 클래스 => application-*.xml  => 메모리 할당 (대기)
 *       HandlerMapping hm;
 *       public void init(ServletConfig config)
 *       {
 *          String path=config.getInitParameter("contextConfigLocation");
 *          hm=new HandlerMapping(path); //XML를 파싱 => 요청시마다 @RequestMapping을 찾아서 밑에 있는 메소드 호출 
 *       }
 *    }
 *    =============================WebApplicationContext=========================================
 *    public class HandlerMapping
 *    {
 *       public String jspFind(String uri)
 *       {
 *           => 메소드 호출후에 return => ViewResolver
 *           => model
 *       }
 *    }
 *    public class Model 
 *    {
 *       HttpServletRequest request;==> DispatcherServlet에서부터 받아 온다 
 *       public Model(HttpServletRequest request)
 *       {
 *           this.request=request;
 *       }
 *       public void addAttribute(String key,Object obj)
 *       {
 *           request.setAtrtribute(key, obj);
 *       }
 *    }
 *    ========================================================================
 *    
 */
@Controller // Model클래스(요청 => 데이터베이스 연결 => 결과 처리 => 데이터 전송)
public class BoardController {
    @Autowired
    private BoardDAO dao;
    
    // 로그인 폼 요청 
    @GetMapping("board/login.do")
    public String board_login()
    {
    	return "board/login";
    }
    // 로그인 처리 요청 
    @PostMapping("board/login_ok.do")
    public String board_login_ok(String id,String pwd,Model model,HttpSession session)
    {
    	System.out.println("id="+id);
    	System.out.println("pwd="+pwd);
    	// 사용자가 보내준 데이터를 request(X) , DispatcherServlet을 통해서 값을 받는다 (각 메소드릐 매개변수를 이용한다)
    	// Model => 전송 객체  addAttribute() => request.setAttribute()
    	// HttpSession session=request.getSession() => request를 이용해서 얻어온다 
    	// request를 사용하지 않기 때문에 DispatcherServlet을 통해서 session을 얻어온다  (반드시 매개변수를 이용해서 받아온다)
    	MemberVO vo=dao.isLogin(id, pwd);
    	if(vo.getMsg().equals("OK")) // 로그인 처리 완료  => session에 등록 
    	{
    		session.setAttribute("id", vo.getId());
    		session.setAttribute("name", vo.getName());
    	}
    	// 화면 이동 
    	model.addAttribute("vo", vo); // NOID , NOPWD , OK => 이동화면이 다르다 
    	return "board/login_ok"; // @RestController 
    }
    // 데이터보드 요청  ==> C/S ==> Client(요청)/Server(응답) => 요청을 받아서 화면에 출력 (페이지 요청)
    @GetMapping("board/list.do")
    public String board_list(String page,Model model) //첫페이지는 page가 존재하지 않는다 (null)
    {
    	// 모든 데이터는 데이터형에 맞게 설정하는 것이 아니라 => 필요하면 String
    	// String page=request.getParameter("page");
    	// => 모든 데이터가 존재 할때 (상세보기) 
    	if(page==null)
    		page="1"; // default 설정
    	// 현재 페이지 
    	int curpage=Integer.parseInt(page);
    	// 데이터 읽기 
    	Map map=new HashedMap(); // MyBatis => 데이터가 2개 이상 필요시는 Map,~VO
    	int rowSize=10;
    	int start=(rowSize*curpage)-(rowSize-1);
    	int end=rowSize*curpage;
    			/*
    			 *  1page 1~10
    			 *  2page 11~20
    			 *  3page=21~30
    			 */
    	map.put("start",start);
    	map.put("end", end);
    	List<DataBoardVO> list=dao.databoardListData(map);//map(start,end)
    	// 기본은 VO => VO에 없는 변수명이면 Map에 저장
    	// 총페이지 
    	int totalpage=dao.databoardTotalPage();
    	// 블록별 
    	final int BLOCK=5;//< [1][2][3][4][5] > < [6][7][8][9][10] > 
    	int startPage=((curpage-1)/BLOCK*BLOCK)+1;
    	//               (1-1)/5 => 0/5 =0+1 => 1, (5-1)/5 = 0 4/5*5
    	//              (6-1)/5*5 => 5 + 1 ==> 6
    	//              (11-1)/5*5  2*5+1 ==> 11
    	// curpage => 1,2,3,4,5 => startPage=1 => curpage=6 startPage=6
    	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; // 5 , 10 ,15 20.... => 13
    	if(endPage>totalpage)
    		endPage=totalpage;
    	// list.jsp => 값을 전송 
    	
    	model.addAttribute("curpage", curpage);
    	model.addAttribute("totalpage", totalpage);
    	model.addAttribute("BLOCK", BLOCK);
    	model.addAttribute("startPage", startPage);
    	model.addAttribute("endPage", endPage);
    	model.addAttribute("list", list);
    	return "board/list";
    }
    // 상세 요청 
    @GetMapping("board/detail.do")
    public String board_detail(int no,int page,Model model)
    {
    	DataBoardVO vo=dao.databoardDetailData(no);
    	
    	if(vo.getFilecount()>0) // 오류 방지 (예외처리 => 프로그램에서 구현이 어려운 상태)
    	{
	    	List<String> fList=new ArrayList<String>();
	    	List<String> sList=new ArrayList<String>();
	    	
	    	StringTokenizer st1=new StringTokenizer(vo.getFilename(),",");
	    	while(st1.hasMoreTokens())
	    	{
	    		fList.add(st1.nextToken());
	    	}
	    	
	    	st1=new StringTokenizer(vo.getFilesize(),",");
	    	while(st1.hasMoreTokens())
	    	{
	    		sList.add(st1.nextToken());
	    	}
	    	model.addAttribute("fList", fList);// a.jpg(1000bytes)
	    	model.addAttribute("sList", sList);
    	}
    	
    	model.addAttribute("vo", vo);
    	model.addAttribute("page", page);
    	return "board/detail";
    }
    // 글쓰기 요청 
    @GetMapping("board/insert.do")
    public String board_insert()
    {
    	return "board/insert";
    }
    // 글쓰기 완료 
    @PostMapping("board/insert_ok.do")  // 405(Get/Post가 틀리다)
    public String board_insert_ok(DataBoardVO vo) throws Exception
    {
    	/*
    	 *    매개변수 
    	 *    1. 일반 변수 
    	 *       detail.do?no=1
    	 *       => (int no)
    	 *       find.do?fs=name&ss=admin
    	 *       => (String fs,String ss)
    	 *    2. VO단위 => 커맨드객체 
    	 *       <input type=text name=name>
    	 *       <input type=text name=sex>
    	 *       <input type=text name=addr>
    	 *       
    	 *       public class MemberVO
    	 *       {
    	 *          private String name,sex,addr;
    	 *       }
    	 *       
    	 *       => (MemberVO vo)
    	 *    3. 배열 => name이 동일할 경우 
    	 *      <input type=checkbox name=hobby>
    	 *      <input type=checkbox name=hobby>
    	 *      <input type=checkbox name=hobby>
    	 *      => (String[] habby)
    	 *    4. List로 받는 경우 
    	 *      <input type=checkbox name=hobby[0]>
    	 *      <input type=checkbox name=hobby[1]>
    	 *      <input type=checkbox name=hobby[2]>
    	 *      => (List<String> hobby)
    	 *       
    	 */
    	File dir=new File("c:\\download");// 파일 업로드 저장 폴더 만들기 
    	if(!dir.exists())
    	{
    		dir.mkdir();
    	}
    	
    	// 데이터 받기 
    	// 파일 
    	List<MultipartFile> list=vo.getFiles(); // 임시 저장 장소 (파일을 이동=> c:\\download)
    	String files="";
    	String sizes="";
    	// 업로드 => NOT NULL(X) => 올린/올리지 않은 경우 
    	// 후기 게시판 , 레시피 이미지 
    	if(list!=null && list.size()>0) // 파일을 업로드 한 경우 
    	{
	    	for(MultipartFile mf:list) // download로 이동 
	    	{
	    		String fn=mf.getOriginalFilename(); // 사용자가 올린 파일명 
	    		File file=new File("c:\\download\\"+fn);
	    		mf.transferTo(file);// 업로드 라이브러리 
	    		files+=fn+","; // filename =>  a.jpg,b.jpg,c,jpg....
	    		sizes+=file.length()+","; // filesize => 1000,2000,3000,
	    	}
	    	vo.setFilename(files.substring(0,files.lastIndexOf(",")));
	    	vo.setFilesize(sizes.substring(0,sizes.lastIndexOf(",")));
	    	vo.setFilecount(list.size());
    	}
    	// /web/login/admin/1234
    	// https://sports.v.daum.net/v/20211012164726694  v?date=20211012164726694
    	// RedirectAttribute => sendRedirect
    	else // 업로드 안한 상태
    	{
    		vo.setFilename("");
    		vo.setFilesize("");
    		vo.setFilecount(0);
    	}
    	dao.databoardInsert(vo);
    	return "redirect:list.do";
    }
    // 다운로드 요청 
    // 화면 이동하는 부분이 아니다 => 메소드 안에서 처리 => 메소드 스타일 (String,void)=> GET/POST => RequestMapping
    @GetMapping("board/download.do")
    public void board_download(String fn,HttpServletResponse response)
    {
    	try
    	{
    		// response : => 기본 : HTML , Cookie전송 , File전송 => 한개의 JSP에서는 반드시 (3중에 1개 선택)
    		String path="c:\\download";
    		// 다운로드 창을 보여준다 (먼저 수행 => header)
    		File file=new File(path+"\\"+fn);
    		response.setContentLength((int)file.length());// 크기 (프로그래바)
    		response.setHeader("Content-Disposition", "attachment;filename="
    				     +URLEncoder.encode(fn, "UTF-8"));
    		
    		// 실제 다운로드 완료
    		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
    		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
    		byte[] buffer=new byte[1024];
    		int i=0;
    		while((i=bis.read(buffer, 0, 1024))!=-1)
    		{
    			bos.write(buffer, 0, i);
    		}
    		bis.close();
    		bos.close();
    	}catch(Exception ex){}
    }
}








