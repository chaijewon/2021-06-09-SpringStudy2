package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// HandlerMapping => @RequestMapping,@GetMapping,@PostMapping
/*
 *    100개 클래스 => for으로 찾아서 대입 
 *    
 */
import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;
import com.sist.dao.EmpDAO;

import java.util.*;
@Controller
//@RequestMapping("board/")
public class BoardController {
   @Autowired
   @Qualifier("boardDAO") // 자동 (메모리 할당된 객체주소 대입) => @Resource(name="boardDAO") 
   private BoardDAO dao;
   
   
   @Autowired
   private EmpDAO eDao;
   
   @RequestMapping("list.do")
   // list.do?page=1
   public String board_list(String page,Model model)
   {
	   if(page==null)
		   page="1"; // default page
	   
	   int curpage=Integer.parseInt(page);
	   int rowSize=10;
	   int start=(rowSize*curpage)-(rowSize-1);// rownum => 1번부터 시작 => 1,11,21...
	   int end=rowSize*curpage;//   10 ,20 30
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);
	   List<BoardVO> list=dao.databoardListData(map);
	   // 총페이지 
	   int totalpage=dao.databoardTotalPage();
	   
	   // 블럭별 페이지 나누기 [startPage][][][][endPage]
	   final int BLOCK=5;// [1]~[5] , [6]~[10] , [11]~[15]
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1; // curpage=1~5 startPage=1
	   /*
	    *    ((curpage-1)/5*5)+1
	    *     =============
	    *     1~5 => 0*5+1 => 1
	    *     
	    *     ((curpage-1)/5*5)+1
	    *     ==============
	    *     6~10  => 1*5+1 ==> 6
	    */
	   // curpage=6~10 startPage=6
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; // 5,10,15,20...
	   if(endPage>totalpage)
		    endPage=totalpage;
	   
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("BLOCK", BLOCK);
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   model.addAttribute("list", list);
	   return "board/list";
   }
   // http://localhost:8080/web/(board/list.do)
   @RequestMapping("find.do")
   public String board_find(String[] fs,String ss,Model model)
   {
	    Map map=new HashMap();
	    map.put("fsArr", fs);
	    map.put("ss", ss);
	    List<BoardVO> list=dao.boardFindData(map);
	    model.addAttribute("list", list);
	    return "board/find";
   }
   
   @RequestMapping("emp/list.do")
   public String emp_list(Model model)
   {
	   List<String> list=eDao.empNameListData();
	   model.addAttribute("list", list);
	   return "emp/list";
   }
   
}









