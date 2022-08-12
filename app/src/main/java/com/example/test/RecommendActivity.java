package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
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

    private JSONArray dataList; // api로부터 가져온 데이터 저장
    private RecommendItemAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        Intent intent = getIntent();

        new Thread(() -> dataList = GetFromApi.getData(intent.getStringExtra("keyword")));

        adapter = new RecommendItemAdapter(dataList);

        recyclerView = findViewById(R.id.RecyclerViewRecommend);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));//수직 배열 구분선 사용

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Linear Layout 형태로 item 관리
        recyclerView.setAdapter(adapter);
    }



}