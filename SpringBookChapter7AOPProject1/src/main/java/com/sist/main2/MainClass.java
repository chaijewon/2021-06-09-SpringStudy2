package com.sist.main2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.sist.dao2.*;
@Component("mc")
public class MainClass {
	@Autowired
    private MyDAO dao;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext app=
        	new ClassPathXmlApplicationContext("app2.xml");
        MainClass mc=(MainClass)app.getBean("mc");
        mc.dao.delete();
        mc.dao.select();
        mc.dao.update();
        mc.dao.insert(100);
	}

}
