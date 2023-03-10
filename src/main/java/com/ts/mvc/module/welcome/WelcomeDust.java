package com.ts.mvc.module.welcome;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

//public class WelcomeDust {
//    public static void main(String[] args) throws IOException {
//    	
//    	// 에어코리아 API 샘플코드
//        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=9T3qTsO4OQ%2FI%2BSMaRMqUi563%2BDC6hg6BNd8slhbXv1wRP1JswJX%2BmRbHmBmtu6wcNa8Wn4Yo0JnX38U7rCMBpg%3D%3D"); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*xml 또는 json*/
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수(조회 날짜로 검색 시 사용 안함)*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호(조회 날짜로 검색 시 사용 안함)*/
//        urlBuilder.append("&" + URLEncoder.encode("searchDate","UTF-8") + "=" + URLEncoder.encode("2020-11-14", "UTF-8")); /*통보시간 검색(조회 날짜 입력이 없을 경우 한달동안 예보통보 발령 날짜의 리스트 정보를 확인)*/
//        urlBuilder.append("&" + URLEncoder.encode("InformCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*통보코드검색(PM10, PM25, O3)*/
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//		  rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		  } else {
//		  rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		  }
//		  StringBuilder sb = new StringBuilder();
//		  String line;
//		  while ((line = rd.readLine()) != null) {
//		  sb.append(line);
//		  }
//		  rd.close();
//		  conn.disconnect();
//		  System.out.println(sb.toString());
//    }
//}