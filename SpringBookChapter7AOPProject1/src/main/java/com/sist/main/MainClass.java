package com.sist.main;

import com.sist.dao.MyDAO;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MyDAO dao=new MyDAO();
        dao.select();
        dao.insert();
        dao.update();
        dao.delete();
	}

}
