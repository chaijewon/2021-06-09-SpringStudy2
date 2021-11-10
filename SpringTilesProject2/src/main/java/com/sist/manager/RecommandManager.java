package com.sist.manager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.*;
/*
 *    클래스 구분 
 *    데이터베이스 관련 : @Repository 
 *    외부데이터 읽기    : @Component
 *    JSP로 데이터 전송 : @Controller
 *    
 */
@Component
public class RecommandManager {

    public static void main(String[] args) {
		RecommandManager r=new RecommandManager();
		r.recommandData("겨울");
	}
    public List<String> recommandData(String fd){
    	
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
        	Document doc=Jsoup.parse(responseBody); // URL => connection
        	// 문자열 => parse
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
