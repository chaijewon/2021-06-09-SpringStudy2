package com.sist.proxy;
// 153page => 공통모듈을 포함 내용의 메소드를 대신 호출 (Proxy패턴 => 응용해서 제작 AOP)
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MyDAO dao=new MyDAO();
        System.out.println("===== Proxy 호출 전 =====");
        dao.select();
        ProxyDAO pdao=new ProxyDAO(dao); // Target
        System.out.println("==== Proxy 적용 후 =====");
        pdao.select(); // 대리자 => 사용자 호출한 메소드 대신 공통으로 적용되는 기능을 포함해서 호출
        // Before / After / After-Returnning / After-Throwable / Around
	}

}
