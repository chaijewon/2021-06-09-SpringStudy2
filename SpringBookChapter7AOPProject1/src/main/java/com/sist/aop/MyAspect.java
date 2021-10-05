package com.sist.aop;

public class MyAspect {
	// 오라클 연결
	public void getConnection()
    {
    	System.out.println("오라클 연결");
    }
    // 오라클 해제 
    public void disConnection()
    {
    	System.out.println("오라클 해제");
    }
    
}
