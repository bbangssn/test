package com.example.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

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
        recyclerView = findViewById(R.id.RecyclerViewRecommend);

        new Thread(() -> {
            dataList = GetFromApi.getKoSearchKeyword("강원도");

            runOnUiThread((Runnable) () -> {
                adapter = new RecommendItemAdapter(dataList);
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));//수직 배열 구분선 사용

                recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Linear Layout 형태로 item 관리
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
}