package com.sist.main;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

@Component
public class MainClass {
    // DAO를 자동 주입 
	@Autowired
	private EmpDAO dao;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext app=
        	new ClassPathXmlApplicationContext("app.xml");
        MainClass mc=(MainClass)app.getBean("mainClass");
        List<EmpVO> list=mc.dao.empListData();
        for(EmpVO vo:list)
        {
        	System.out.println(vo.getEname()+" "+vo.getDname()+" "
        			+vo.getLoc()+" "+vo.getSal()+" "+vo.getJob());
        }
        System.out.println("=========== 사원 상세보기 ==============");
        EmpVO vo=mc.dao.empDetailData(7788);
        System.out.println(vo.getEname()+" "+vo.getDname()+" "
    			+vo.getLoc()+" "+vo.getSal()+" "+vo.getJob()+" "
    			+vo.getDbday()+" "+vo.getComm());
        
	}

}
