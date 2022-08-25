package com.example.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

public class RecommendItemAdapter extends RecyclerView.Adapter<RecommendItemAdapter.RecommendItemAdapterViewholder> {

    private final static String TAG = "RecommendItemAdapter";

    public JSONArray dataList;

    public RecommendItemAdapter(JSONArray dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecommendItemAdapterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        return new RecommendItemAdapterViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendItemAdapterViewholder holder, int position) {
        //TODO dataList의 position번째 데이터를 RecommendItemAdapterViewholder에 대입해 아이템 구성
            try {
                String title = dataList.getJSONObject(position).getString("title");
                holder.Title.setText(title);
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    @Override
    public int getItemCount() {
        return (dataList != null? dataList.length() : 0 );
    }

    public static class RecommendItemAdapterViewholder extends RecyclerView.ViewHolder{
        //TODO item_recommend 형식에 따라 재구성 필요
        ImageView TitleImage;
        TextView Title, Content;

        public RecommendItemAdapterViewholder(@NonNull View itemView) {
            super(itemView);

            TitleImage = itemView.findViewById(R.id.TitleImage);
            Title = itemView.findViewById(R.id.Title);
            Content = itemView.findViewById(R.id.Content);
        }
    }
}
