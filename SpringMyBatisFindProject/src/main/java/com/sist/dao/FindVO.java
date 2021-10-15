package com.sist.dao;

public class FindVO {
	public static void main(String[] args) {
		FindVO vo=new FindVO();
		vo.setFs("TSA");
		//System.out.println(vo.getFsArr());
		for(String s:vo.getFsArr())
		{
			System.out.println(s);
		}
	}
    private String ss;
    private String fs;
	public String getSs() {
		return ss;
	}
	public void setSs(String ss) {
		this.ss = ss;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
    public String[] getFsArr()
    {
    	return fs==null?new String[]{}:fs.split("");
    }
   
} 
