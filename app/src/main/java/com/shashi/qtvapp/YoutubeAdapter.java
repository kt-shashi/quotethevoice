package com.shashi.qtvapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shashi.qtvapp.model.DataSetList;

import java.util.ArrayList;

public class YoutubeAdapter extends RecyclerView.Adapter<com.shashi.qtvapp.YoutubeViewHolder> {
    ArrayList<DataSetList> arrayList;
    Context context;

    public YoutubeAdapter(ArrayList<DataSetList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public com.shashi.qtvapp.YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.video_per_row, viewGroup, false);
        return new com.shashi.qtvapp.YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.shashi.qtvapp.YoutubeViewHolder youtubeViewHolder, int i) {

        final DataSetList current = arrayList.get(i);

        youtubeViewHolder.webView.loadUrl(current.getLink());
        youtubeViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, VideoFullScreen.class);
                i.putExtra("link", current.getLink());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.getApplicationContext().startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}