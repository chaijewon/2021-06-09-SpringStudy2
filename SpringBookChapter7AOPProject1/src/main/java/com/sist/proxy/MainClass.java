package com.sist.proxy;
// 153page => �������� ���� ������ �޼ҵ带 ��� ȣ�� (Proxy���� => �����ؼ� ���� AOP)
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MyDAO dao=new MyDAO();
        System.out.println("===== Proxy ȣ�� �� =====");
        dao.select();
        ProxyDAO pdao=new ProxyDAO(dao); // Target
        System.out.println("==== Proxy ���� �� =====");
        pdao.select(); // �븮�� => ����� ȣ���� �޼ҵ� ��� �������� ����Ǵ� ����� �����ؼ� ȣ��
        // Before / After / After-Returnning / After-Throwable / Around
	}

}
