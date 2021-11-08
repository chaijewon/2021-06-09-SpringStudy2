package com.sist.data;
import java.io.*;
import java.net.URL;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class RecipeMain {
	@Autowired
	private RecipeDAO dao;
	
	@Autowired
	private CartDAO cdao;
	
	public void recipeAllData()
	{
	     
	     int k=1;
	     try
	     {
	    	 /*
	    	  * 3357 => 1  4177
	    	  */
	    	 for(int i=1;i<=4177;i++)
	    	 {
	    		 Document doc=Jsoup.connect("http://www.10000recipe.com/recipe/list.html?order=accuracy&page="+i).get();
	    		 Elements title=doc.select("div.common_sp_caption div.common_sp_caption_tit");
	    		 Elements poster=doc.select("img[src*=/recipe/]");
	    		 Elements chef=doc.select("div.common_sp_caption_rv_name");
	    		 Elements link=doc.select("div.common_sp_thumb a.common_sp_link");
	    		 
	    		 for(int j=0;j<title.size();j++)
	    		 {
	    			try
	    			{
		    			 RecipeVO vo=new RecipeVO();
		    			 vo.setNo(k);
		    			 vo.setTitle(title.get(j).text());
		    			 vo.setPoster(poster.get(j).attr("src"));
		    			 vo.setChef(chef.get(j).text());
		    			 vo.setLink(link.get(j).attr("href"));
		    			 System.out.println("번호:"+k);
		    			 System.out.println("Title:"+vo.getTitle());
		    			 System.out.println("Chef:"+vo.getChef());
		    			 System.out.println("Poster:"+vo.getPoster());
		    			 System.out.println("Link:"+vo.getLink());
		    			 System.out.println("k="+k);
		    			 //dao.recipeInsert(vo);
		    			 //recipeDetailData(vo.getLink(),k);
		    			 k++;
		    			 
	    			}catch(Exception e) {e.printStackTrace();}
	    		 }
	    	 }
	    	 System.out.println("End...");
	     }catch(Exception ex){ex.printStackTrace();}
	    
	}
	/*
	 *  <div class="chef_list4_in">

    
			 <div class="list_lump">
            <div class="list_ranking2">
                <p class="list_ranking2_num ">1</p>
                <p class="list_ranking2_num2"><span style="color:#fff;" class="glyphicon "></span></p>
            </div>
            <div class="list_mem3">
                <a href="/profile/index.html?uid=jun8707" class="mem_pic"><img src="https://recipe1.ezmember.co.kr/cache/rpf/2016/01/19/3ebaebc5e49f53dd2f66b71932e5a33d1.jpg" ></a>
            </div>
            <div class="list_cont4"><b>
                <a href="/profile/index.html?uid=jun8707" id="folFriend_jun8707" class="info_name_f">
                    시크제이맘                </a>
                    <button type="button" class="btn btn-default btn-sm" id="btnActFriend_jun8707" fact="insert" onClick="doActFriend('jun8707')">
                        <span class="glyphicon glyphicon-plus"></span>소식받기                    </button>
                </b>
                <span class="mem_cont1">1,712</span><span class="mem_cont3">1,188,441</span><span class="mem_cont7">40,430,313</span><span class="mem_cont2">24,467</span>
            </div>
        </div>
	 */
	public ArrayList<ChefVO> chefAllData()
	{
		ArrayList<ChefVO> list=new ArrayList<ChefVO>();
		try
		{
			int k=1;
			for(int i=1;i<=30;i++)
			{
				// https://www.10000recipe.com/chef/chef_list.html?order=chef_no_follower&term=all&page=2
				Document doc=Jsoup.connect("http://www.10000recipe.com/chef/chef_list.html?order=chef_no_follower&term=all&page="+i).get();
				Elements poster=doc.select("div.list_mem3 a.mem_pic img");
				Elements chef=doc.select("div.list_cont4 a.info_name_f");
				Elements mem_cont1=doc.select("div.list_cont4 span.mem_cont1");
				Elements mem_cont3=doc.select("div.list_cont4 span.mem_cont3");
				Elements mem_cont7=doc.select("div.list_cont4 span.mem_cont7");
				Elements mem_cont2=doc.select("div.list_cont4 span.mem_cont2");
				
				for(int j=0;j<poster.size();j++)
				{
					try
					{
						ChefVO vo=new ChefVO();
						vo.setPoster(poster.get(j).attr("src"));
						vo.setChef(chef.get(j).text());
						vo.setMem_cont1(Integer.parseInt(mem_cont1.get(j).text().replace(",", "")));
						vo.setMem_cont3(Integer.parseInt(mem_cont3.get(j).text().replace(",", "")));
						vo.setMem_cont7(Integer.parseInt(mem_cont7.get(j).text().replace(",", "")));
						vo.setMem_cont2(Integer.parseInt(mem_cont2.get(j).text().replace(",", "")));
						System.out.println("Poster:"+vo.getPoster());
						System.out.println("Chef:"+vo.getChef());
						System.out.println("Mem-cont1:"+vo.getMem_cont1());
						System.out.println("Mem-cont3:"+vo.getMem_cont3());
						System.out.println("Mem-cont7:"+vo.getMem_cont7());
						System.out.println("Mem-cont2:"+vo.getMem_cont2());
						System.out.println("k="+k);
						System.out.println("---------------------------------------------------------");
						dao.chefInsert(vo);
					    k++;
					}catch(Exception ex){ex.printStackTrace();}
					//list.add(vo);
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		return list;
	}
	public void recipeDetailData()
    {
    	RecipeDetailVO vo=new RecipeDetailVO();
    	// http://www.10000recipe.com/recipe/6907454
    	List<RecipeVO> list=dao.recipeLinkData();
    	
    	int k=1;
    	try
    	{
    		for(RecipeVO vo1:list)
    		{
    		  try
    		  {
    			System.out.println("Link:"+vo1.getLink());
    			System.out.println("No:"+vo1.getNo());
	    		Document doc=Jsoup.connect("http://www.10000recipe.com"+vo1.getLink()).get();
	    		Element poster=doc.selectFirst("div.centeredcrop img");// doc.select("").get(0)
	    		
	    		Element title=doc.selectFirst("div.view2_summary h3");
	    		Element chef=doc.selectFirst("div.profile_cont p.cont_name");
	    		Element chef_poster=doc.selectFirst("div.profile_pic img");
	    		Element chef_profile=doc.selectFirst("div.profile_cont p.cont_intro");
	    		Element content=doc.selectFirst("div.view2_summary_in");
	    		Elements foodmake=doc.select("div.view_step_cont");
	    		Element info1=doc.selectFirst("span.view2_summary_info1");
	    		Element info2=doc.selectFirst("span.view2_summary_info2");
	    		Element info3=doc.selectFirst("span.view2_summary_info3");
	    		Elements foodImg=doc.select("div[id*=stepimg] img");
	    		/*
	    		 * <div id="stepimg3">
	                <img src="https://recipe1.ezmember.co.kr/cache/recipe/2020/08/28/b645dd92c59678c0aa8526edd84a95b21.jpg">
	               </div>
	    		 */
	    		
	    		String food="";
	    		for(int i=0;i<foodmake.size();i++)
	    		{
	    			food+=(i+1)+"."+foodmake.get(i).text()+"\n";
	    		}
	    		
	    		String img="";
	    		for(int i=0;i<foodmake.size();i++)
	    		{
	    			img+=foodImg.get(i).attr("src")+"^";
	    			System.out.println(foodImg.get(i).attr("src"));
	    		}
	    		
	    		food=food.substring(0,food.lastIndexOf("\n"));
	    		img=img.substring(0,img.lastIndexOf("^"));
	    		
	    		vo.setPoster(poster.attr("src"));
	    		vo.setChef(chef.text());
	    		vo.setChef_poster(chef_poster.attr("src"));
	    		vo.setChef_profile(chef_profile.text());
	    		vo.setContent(content.text());
	    		vo.setFoodmake(food);
	    		vo.setFoodimg(img);
	    		vo.setTitle(title.text());
	    		vo.setInfo1(info1.text());
	    		vo.setInfo2(info2.text());
	    		vo.setInfo3(info3.text());
	    		vo.setNo(vo1.getNo());
	    		
	    		System.out.println("제목:"+vo.getTitle());
	    		System.out.println("쉐프:"+vo.getChef());
	    		System.out.println("내용:"+vo.getContent());
	    		System.out.println("조리법:"+vo.getFoodmake());
	    		System.out.println("정보1:"+vo.getInfo1());
	    		System.out.println("정보2:"+vo.getInfo2());
	    		System.out.println("정보3:"+vo.getInfo3());
	    		
	    		dao.recipeDetailInsert(vo);
	    		
	    		System.out.println("k="+k);
	    		k++;
    		  }catch(Exception ex){}
    		}
    	}catch(Exception ex){ex.printStackTrace();}
    	
    }
	public void goodsListData()
	{
		/*
		 *  PRODUCT_NAME   NOT NULL VARCHAR2(1000) 
			PRODUCT_PRICE  NOT NULL NUMBER         
			PRODUCT_POSTER NOT NULL VARCHAR2(260) 
			<ul class="common2_sp_list_ul ea3">
                <li class="common2_sp_list_li"><a href="https://shop.10000recipe.com/goods/goods_view.php?goodsNo=1000021160&utm_source=10k_web&utm_medium=category_list&utm_campaign=g%5B%EB%A7%8C%EA%B0%9C%ED%8A%B9%EA%B0%80%5D%EC%98%81%EC%96%91%EA%B0%84%EC%8B%9D%EC%8A%A4%ED%8A%B8%EB%A1%B1%EC%88%8F%EB%8B%A4%EB%A6%AC1%EB%B4%8924-32%EB%AF%B8%28250g%29" class="common2_sp_link"></a><div class="common2_sp_thumb"><img src="https://recipe1.ezmember.co.kr/cache/data/goods/21/07/28/1000021160/1000021160_detail_042.jpg"></div>
                  <div class="common2_sp_caption">
                      <div class="common2_sp_caption_tit line2">[만개특가] 영양간식 스트롱숏다리 1봉 24-32미(250g)</div>
                      <div class="common2_sp_caption_price_box"><span class="common2_sp_caption_per"><b>31</b>%</span> <strong class="common2_sp_caption_price"><span>8,900</span><small>원</small></strong></div><div class="common2_sp_caption_rv2">
                            <span class="caption_rv2_img"></span><b class="caption_rv2_pt">4.6</b><span class="caption_rv2_ea">(573)</span>
                        </div><div class="common2_sp_caption_icon"></div></div></li><li class="common2_sp_list_li"><a href="https://shop.10000recipe.com/goods/goods_view.php?goodsNo=1000023475&utm_source=10k_web&utm_medium=category_list&utm_campaign=g%EC%BC%80%EC%96%B4%ED%8C%9F%EC%8A%A4%ED%85%8C%EC%9D%B8%EB%A6%AC%EC%8A%A4%ED%81%90%EB%B8%8C%EA%B0%80%EC%8A%B5%EA%B8%B0x40" class="common2_sp_link"></a><div class="common2_sp_thumb"><img src="https://recipe1.ezmember.co.kr/cache/data/goods/21/10/42/1000023475/1000023475_detail_016.jpg"></div>
		 */
		try
		{
			// 사이트 연결 => HTML데이터 읽기 
			int k=1;
			for(int i=1;i<=244;i++)
			{
			    Document doc=Jsoup.connect("https://www.10000recipe.com/shop/category.html?sort=popular&page="+i).get();
			    Elements name=doc.select("div.common2_sp_caption div.common2_sp_caption_tit");
			    Elements price=doc.select("div.common2_sp_caption_price_box strong.common2_sp_caption_price span");
			    Elements poster=doc.select("div.common2_sp_thumb img");
			    
			    for(int j=0;j<name.size();j++)
			    {
			    	try
			    	{
			    		System.out.println(name.get(j).text());
			    		System.out.println(price.get(j).text());
			    		System.out.println(poster.get(j).attr("src"));
			    		System.out.println("==============================================");
			    		GoodsVO vo=new GoodsVO();
			    		vo.setProduct_name(name.get(j).text());
			    		// 21900
			    		vo.setProduct_price(Integer.parseInt(price.get(j).text().replace(",", "")));
			    		vo.setProduct_poster(poster.get(j).attr("src"));
			    		vo.setProduct_desc("");
			    		// 정수/실수 
			    		cdao.goodsInsert(vo);
			    		System.out.println("k="+k);
			    		k++;
			    	}catch(Exception ex){ex.printStackTrace();}
			    }
			}
			
		}catch(Exception ex){}
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		// Web => DispatcherServlet에서 처리 
		String[] xml={"application-context.xml","application-datasource.xml"};
		ApplicationContext app=new ClassPathXmlApplicationContext(xml);
		RecipeMain rm=(RecipeMain)app.getBean("recipeMain");
		rm.chefAllData();
		//rm.recipeAllData();
		//rm.recipeDetailData();
		//rm.goodsListData();
	}
	
}












