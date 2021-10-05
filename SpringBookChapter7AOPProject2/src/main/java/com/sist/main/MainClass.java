package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.util.*;
import com.sist.dao.*;
@Component
public class MainClass {
	@Autowired
    private StudentDAO dao;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext app=
        		new ClassPathXmlApplicationContext("app.xml");
        MainClass mc=(MainClass)app.getBean("mainClass");
        List<StudentVO> list=mc.dao.studentListData(1);
        
        StudentVO vo=mc.dao.studentDetailData(1);
        /*for(StudentVO vo:list)
        {
        	System.out.println(vo.getHakbun()+" "
        			+vo.getName()+" "
        			+vo.getKor()+" "
        			+vo.getEng()+" "
        			+vo.getMath());
        }*/
	}

}
