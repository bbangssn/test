package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecommendActivity extends AppCompatActivity {

    private ArrayList<RecommendItemData> dataList; // api로부터 가져온 데이터 저장
    private RecommendItemAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        dataList = new ArrayList<>();
        //TODO 데이터 수집 및 dataList에 나열 필요.

        adapter = new RecommendItemAdapter(dataList);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewRecommend);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));//수직 배열 구분선 사용

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Linear Layout 형태로 item 관리
        recyclerView.setAdapter(adapter);
    }
}