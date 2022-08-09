package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class RecommendActivity extends AppCompatActivity {
    private String TAG = "RecommendActivity";

    private ArrayList<JSONObject> dataList; // api로부터 가져온 데이터 저장
    private RecommendItemAdapter adapter;
    private RecyclerView recyclerView;

    String URL = "http://apis.data.go.kr/B551011/KorService/areaCode";//TODO
    String key = "hdm+J7CIiigXHZKn6gTL8LAEIcBLdy8UHWpq/IeWN8b9tLVasMcCiUmfyaoRRwhPqEH2pQQpUxqC2swg8jx46A==";//TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        dataList = new ArrayList<>();
        //TODO 데이터 수집 및 dataList에 나열 필요.

        new Thread(()->{
            JSONObject object = getData();
            try {
                Log.d("For Test", object.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item").getJSONObject(1).getString("name"));
            } catch (JSONException e) {
                Log.e("For test", "error : ", e);
            }
        }).start();


        adapter = new RecommendItemAdapter(dataList);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewRecommend);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));//수직 배열 구분선 사용

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Linear Layout 형태로 item 관리
        recyclerView.setAdapter(adapter);
    }


    public JSONObject getData() {

        try {
            /*URL*/
            String urlBuilder = URL + "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(key, "UTF-8") + /*Service Key*/
                    "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8") + /*한 페이지 결과수*/
                    "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + /*현재 페이지 번호*/
                    "&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("AND", "UTF-8") + /*IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC*/
                    "&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8") + /*서비스명=어플명*/
                    "&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + /*지역코드, 시군구코드*/
                    "&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8");
            java.net.URL url = new URL(urlBuilder);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            Log.d(TAG, sb.toString());

            return new JSONObject(sb.toString());
        } catch (Exception e) {
            Log.e(TAG, "error : ", e);
            return new JSONObject();
        }
    }
}