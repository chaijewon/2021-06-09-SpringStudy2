package com.sist.trans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext app=
        	new ClassPathXmlApplicationContext("app.xml");
        SawonDAO dao=(SawonDAO)app.getBean("dao");
        SawonVO vo=new SawonVO();
        dao.sawonInsert(vo);
	}

}
