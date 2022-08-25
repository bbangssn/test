package com.example.test;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GetFromApi {
    //TODO api 관련 헬퍼 조잡하게 모아둔 class. 나중에 꼭 정리하자!

    private final static String TAG = "GetFromApi";
    private final static String key = "hdm+J7CIiigXHZKn6gTL8LAEIcBLdy8UHWpq/IeWN8b9tLVasMcCiUmfyaoRRwhPqEH2pQQpUxqC2swg8jx46A==";


    public static JSONObject getKoDetailCommon(String contentID) {

        String URL = "http://apis.data.go.kr/B551011/KorService/detailCommon";

        JSONObject output;

        try {
            String urlBuilder = URL + "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(key, "UTF-8") + //Service Key

                    "&" + URLEncoder.encode("contentId", "UTF-8") + "=" + URLEncoder.encode(contentID, "UTF-8") + //Content ID

                    "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + //현재 페이지 번호
                    "&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("AND", "UTF-8") + //현재 OS
                    "&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8") + //서비스명
                    "&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8") +
                    "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"); //한 페이지 결과수 : 최대로 땡겨와서 한 배열에 넣기

            java.net.URL url = new URL(urlBuilder);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new Exception("Response Code : " + conn.getResponseCode());
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;


            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            Log.d(TAG, sb.toString());

            output = (new JSONObject(sb.toString())).getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item");
        } catch (Exception e) {
            Log.e(TAG, "error : ", e);
            output = new JSONObject();
        }
        return output;
    }

    public static JSONArray getKoSearchKeyword(String keyword) {
        String URL = "http://apis.data.go.kr/B551011/KorService/searchKeyword";

        JSONArray output;

        try {
            String urlBuilder = URL + "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(key, "UTF-8") + //Service Key

                    "&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8") + //keyword

                    "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + //현재 페이지 번호
                    "&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("AND", "UTF-8") + //현재 OS
                    "&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8") + //서비스명
                    "&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8") +
                    "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"); //한 페이지 결과수 : 최대로 땡겨와서 한 배열에 넣기

            java.net.URL url = new URL(urlBuilder);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new Exception("Response Code : " + conn.getResponseCode());
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            Log.d(TAG, sb.toString());

            output =(new JSONObject(sb.toString())).getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
        }catch (Exception e){
            Log.e(TAG, "error : ", e);
            output = new JSONArray();
        }
        return output;
    }
}
