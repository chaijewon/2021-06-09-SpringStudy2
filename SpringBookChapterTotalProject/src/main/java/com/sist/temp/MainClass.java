package com.sist.temp;

import java.lang.reflect.Method;
import java.util.Scanner;

class BoardController
{
	@RequstMapping("insert.do")
	public String insert(String name)
	{
		return name+"=>insert메소드 호출";
	}
	@RequstMapping("update.do")
	public String update(String name)
	{
		return name+"=>update메소드 호출";
	}
	@RequstMapping("delete.do")
	public String delete(String name)
	{
		return name+"=>delete메소드 호출";
	}
}
// HandlerMapping 구현 
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        System.out.print("URI입력:");
        String uri=scan.next();
        System.out.print("요청값 입력:");
        String name=scan.next(); // delete.do?name=...
        
        // 찾기 => 매개변수 설정 
        try
        {
        	Class clsName=Class.forName("com.sist.temp.BoardController");
        	Object obj=clsName.getDeclaredConstructor().newInstance(); // 메모리 할당 
        	Method[] methods=clsName.getDeclaredMethods();
        	for(Method m:methods)
        	{
        		//System.out.println(m.getName());
        		RequstMapping rm=m.getAnnotation(RequstMapping.class);
        		// GetMapping g=m.getAnnotation(GetMapping.class);
        		// PostMapping p=m.getAnnotation(PostMapping.class);
        		/*
        		 *   if(rm.value().equals(uri)||g.value().equals(uri)||p.value().equals(uri))
        		 */
        		if(rm.value().equals(uri))
        		{
        			String s=(String)m.invoke(obj, name);//invoke (메소드명으로 호출) => 매개변수 가변형 
        			// Object invoke(Object obj, Object... args) => []
        			System.out.println(s);
        		}
        	}
        }catch(Exception ex){}
        
	}

}






