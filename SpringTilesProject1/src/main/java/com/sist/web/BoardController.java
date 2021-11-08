package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
   @RequestMapping("board/list.do")
   public String board_list()
   {
	   return "board/list";
   }
}
