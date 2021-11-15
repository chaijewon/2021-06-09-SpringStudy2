package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
   @RequestMapping("recipe/list.do")
   public String recipe_list()
   {
	   return "recipe/list";
   }
}
