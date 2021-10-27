package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// 화면 변경 => insert,update,delete 
// @RestController => @ResponseBody
public class FoodController {
   @RequestMapping("food/category.do")
   public String food_category()
   {
	   return "food/category";
   }
   
   @RequestMapping("food/detail.do")
   
   public String food_detail(int cno,Model model)
   {
	   model.addAttribute("cno", cno);
	   return "food/detail";
   }
   @RequestMapping("food/search.do")
   public String food_search()
   {
	   return "food/search";
   }
   
   @RequestMapping("movie/list.do")
   public String movie_list()
   {
	   return "movie/list";
   }
}
