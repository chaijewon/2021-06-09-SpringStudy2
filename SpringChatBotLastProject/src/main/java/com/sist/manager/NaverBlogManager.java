package com.sist.manager;
// Blog로부터 추천 
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.*;
// => 딥러닝 (데이터 분석) => 통계
// 데이터 수집(트위터,페이스북, 인스타그램,카톡) ==> 분석후 필요한 데이터 저장 ==> 통계 ==> 해당 데이터 찾기가 가능 
// ==========================================================(빅데이터) 
// 데이터웨어하우스 ==> 클라우드 ====> 빅데이터 ===> AI ===> ?(차세대:감성컴퓨팅) 

@Component
public class NaverBlogManager {
	
	public List<String> naverBlogData(String fd) {
		
		List<String> list=new ArrayList<String>();
		
        String clientId = "xzCkjdHVMmHBQA5GsaNk"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "xpoZsdLvAt"; //애플리케이션 클라이언트 시크릿값"


        String text = null;
        try {
            text = URLEncoder.encode(fd+" 추천", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        //String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // json 결과
        String apiURL = "https://openapi.naver.com/v1/search/blog.xml?display=100&query="+ text; // xml 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        try
        {
        	// Jsoup=>URL 주소 connection, 문자열 => parse
        	Document doc=Jsoup.parse(responseBody);
        	Elements elem=doc.select("channel > item > description");
        	for(int i=0;i<elem.size();i++)
        	{
        		System.out.println(elem.get(i).text());
        		list.add(elem.get(i).text());
        	}
        	
        }catch(Exception ex){}

        //System.out.println(responseBody);
        
        return list;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
        
        
    }
}
