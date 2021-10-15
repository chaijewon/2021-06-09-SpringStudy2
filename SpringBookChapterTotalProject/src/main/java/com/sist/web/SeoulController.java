package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.manager.*;
import com.sist.vo.SeoulHotelVO;
import com.sist.vo.SeoulLocationVO;
import com.sist.vo.SeoulNatureVO;
// 클래스가 달라도 => RequestMapping("uri") => 같으면 안된다 
// 폴더명 ==> seoul/list.do   food/list.do 
@Controller
@RequestMapping("seoul/")
public class SeoulController {
	@Autowired
    private SeoulDAO dao;
	@Autowired
	private TwitterManager tm;
	@Autowired
	private WeatherManager wm;

	
	@RequestMapping("location_list.do")
	public String seoul_location_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		map.put("start", start);
		map.put("end", end);
		List<SeoulLocationVO> list=dao.locationListData(map);
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "../seoul/location_list.jsp");//실제 출력 
		return "main/main";
	}
	@RequestMapping("nature_list.do")
	public String seoul_nature_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		map.put("start", start);
		map.put("end", end);
		List<SeoulNatureVO> list=dao.natureListData(map);
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "../seoul/nature_list.jsp");// 실제 출력
		return "main/main";
	}
	@RequestMapping("hotel_list.do")
	public String seoul_hotel_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		map.put("start", start);
		map.put("end", end);
		List<SeoulHotelVO> list=dao.hotelListData(map);
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "../seoul/hotel_list.jsp");// 실제 내용을 출력 
		// request를 공유 : include , forward
		return "main/main";// main에서는 조립
	}
}





