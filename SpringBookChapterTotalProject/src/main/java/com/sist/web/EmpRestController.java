package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@RestController
public class EmpRestController {
   @Autowired
   private EmpDAO dao;
   
   @RequestMapping(value="vuejs/rest_emp_list.do",produces="text/plain;charset=UTF-8")
   public String emp_list()
   {
	   String json="";
	   try
	   {
		   // emp 목록 받기
		   List<EmpVO> list=dao.empListData();
		   // Vue,Ajax,React,Kotlin => JSON
		   // List => JSONArray
		   JSONArray arr=new JSONArray(); //[]
		   for(EmpVO vo:list)
		   {
			   // EmpVO => JSONObject
			   JSONObject obj=new JSONObject();
			   obj.put("empno", vo.getEmpno());
			   obj.put("ename", vo.getEname());
			   obj.put("job", vo.getJob());
			   obj.put("dname", vo.getDname());
			   obj.put("loc", vo.getLoc());
			   arr.add(obj);
		   }
		   json=arr.toJSONString();
	   }catch(Exception ex){}
	   return json;
   }
}










