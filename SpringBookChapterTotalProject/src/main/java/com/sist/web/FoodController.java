package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;
@Controller
@RequestMapping("food/")
public class FoodController {
	@Autowired
    private FoodDAO dao;
}
