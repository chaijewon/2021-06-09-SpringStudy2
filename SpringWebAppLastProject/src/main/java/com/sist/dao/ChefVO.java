package com.sist.dao;
/*
 *   CHEF      NOT NULL VARCHAR2(200) 
	POSTER    NOT NULL VARCHAR2(260) 
	MEM_CONT1          NUMBER        
	MEM_CONT3          NUMBER        
	MEM_CONT7          NUMBER        
	MEM_CONT2          NUMBER        

 */
public class ChefVO {
    private String chef,poster;
    private int mem_cont1,mem_cont3,mem_cont7,mem_cont2;
	public String getChef() {
		return chef;
	}
	public void setChef(String chef) {
		this.chef = chef;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public int getMem_cont1() {
		return mem_cont1;
	}
	public void setMem_cont1(int mem_cont1) {
		this.mem_cont1 = mem_cont1;
	}
	public int getMem_cont3() {
		return mem_cont3;
	}
	public void setMem_cont3(int mem_cont3) {
		this.mem_cont3 = mem_cont3;
	}
	public int getMem_cont7() {
		return mem_cont7;
	}
	public void setMem_cont7(int mem_cont7) {
		this.mem_cont7 = mem_cont7;
	}
	public int getMem_cont2() {
		return mem_cont2;
	}
	public void setMem_cont2(int mem_cont2) {
		this.mem_cont2 = mem_cont2;
	}
	  
}
