//package com.ts.mvc.infra.api;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.mail.FetchProfile.Item;
//
//import com.ts.mvc.infra.api.dto.WeatherDto;
//
//public class Weather {
//    public static Map<String, String> getWeatherList(String baseDate, String baseTime) {
//        Map<String, String> weatherMap = new HashMap<>();
//
//        try {
//            URL url = new URL(
//                    "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=9T3qTsO4OQ%2FI%2BSMaRMqUi563%2BDC6hg6BNd8slhbXv1wRP1JswJX%2BmRbHmBmtu6wcNa8Wn4Yo0JnX38U7rCMBpg%3D%3D");
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream(), "utf-8"));
//
//            String responseJson = br.readLine();
//            WeatherDto dto = fromJson(responseJson, WeatherDto.class);
//            List<com.ts.mvc.infra.api.dto.Item> result = dto.getResponse().getBody().getItems().getItem();
//
//            for (int i = 0; i < result.size(); i++) {
//                weatherMap.put(result.get(i).getCategory(), result.get(i).getObsrValue());
//            }
//            return weatherMap;
//
//        } catch (
//
//        Exception e) {
//            System.out.println("조회오류");
//        }
//        return null;
//    }
//
//	private static WeatherDto fromJson(String responseJson, Class<WeatherDto> class1) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}