package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*
 *   한개의 모델 , JSP를 가지고 여러개 사용 (재사용) => 공통모듈 
 *   => 중복이 많은 경우 (공통 모듈을 만들고 사용) 
 */
//  전송 담당 (JSP)
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class ReplyController {
    @Autowired
    private CommonReplyDAO dao;
    private String[] jsp={"","../seoul/location_detail.do","../seoul/hotel_detail.do",
    		"../seoul/nature_detail.do","../freeboard/detail.do","../food/detail.do",
    		"../recipe/detail.do","../cart/detail.do"};
    // 댓글 목록 
   /* @RequestMapping("reply/list.do")
    public String reply_list(int cno,int tno,Model model)
    {
    	List<CommonReplyVO> list=dao.replyListData(cno, tno);
    	model.addAttribute("list", list);
    	return "reply/list";
    }*/
    @RequestMapping("reply/insert.do")
    public String reply_insert(CommonReplyVO vo,RedirectAttributes attr,HttpSession session)
    {
    	String id=(String)session.getAttribute("id");
    	String name=(String)session.getAttribute("name");
    	vo.setId(id);
    	vo.setName(name);
    	// http://localhost:8080/web/seoul/location_detail.do?no=1 => tno=1
    	// http://localhost:8080/web/seoul/hotel_detail.do?no=1 => tno=2
    	dao.replyInsert(vo);
    	attr.addAttribute("no", vo.getCno()); // cno => 명소 번호 , 호텔번호 
    	return "redirect:"+jsp[vo.getTno()]; 
    }
    @RequestMapping("reply/update.do")
    public String reply_update(CommonReplyVO vo,RedirectAttributes attr)
    {
    	System.out.println("tno="+vo.getTno());
    	System.out.println("cno="+vo.getCno());
    	System.out.println("msg="+vo.getMsg());
    	dao.replyUpdate(vo.getNo(),vo.getMsg());
    	attr.addAttribute("no", vo.getCno()); // cno => 명소 번호 , 호텔번호 
    	return "redirect:"+jsp[vo.getTno()];
    }
    @RequestMapping("reply/delete.do")
    public String reply_delete(int no,int cno,int tno,RedirectAttributes attr)
    {
    	dao.replyDelete(no);
    	attr.addAttribute("no", cno);
    	return "redirect:"+jsp[tno];
    }
}








