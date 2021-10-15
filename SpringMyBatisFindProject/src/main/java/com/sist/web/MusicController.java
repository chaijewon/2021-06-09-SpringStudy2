package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.OutputStream;
import java.util.*;
import com.sist.dao.*;
@Controller
public class MusicController {
    @Autowired
    private MusicDAO dao;
    
    @RequestMapping("music/list.do")
    public String music_list(Model model)
    {
    	List<MusicVO> list=dao.musicListData();
    	model.addAttribute("list", list);
    	return "music/list";
    }
    @RequestMapping("music/find.do")
    public String music_find(FindVO vo,Model model)
    {
    	Map map=new HashMap();
    	map.put("ss", vo.getSs());
    	map.put("fsArr", vo.getFsArr());
    	List<MusicVO> list=dao.musicFindData(map);
    	model.addAttribute("list", list);
    	return "music/find";
    }
}









