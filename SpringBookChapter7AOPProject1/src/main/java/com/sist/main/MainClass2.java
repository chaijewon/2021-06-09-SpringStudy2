package com.sist.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext app=
        		new ClassPathXmlApplicationContext("app.xml");
        MyDAO2 dao2=new MyDAO2();
        System.out.println("======= AOP 적용전 ======");
        dao2.select();
        dao2.insert();
        dao2.update();
        dao2.delete();
        System.out.println("====== AOP 적용 ======");
        MyDAO2 dao=(MyDAO2)app.getBean("dao");
        dao.select();
        dao.insert();
        dao.update();
        dao.delete();
        
	}

}






