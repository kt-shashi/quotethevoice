package com.shashi.qtvapp.ui.socialmedia;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shashi.qtvapp.R;
import com.shashi.qtvapp.model.SocialMediaModelClass;

import java.util.ArrayList;

class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<SocialMediaModelClass> arrayList = new ArrayList<>();

    public SocialMediaAdapter(Context context) {
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public SocialMediaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_social_media, parent, false);

        return new MyViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull SocialMediaAdapter.MyViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext()).load(arrayList.get(position).getImageUrl()).into(holder.imageView);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void updateSocialMediaList(ArrayList<SocialMediaModelClass> updatedList) {
        arrayList.clear();
        arrayList.addAll(updatedList);

        notifyDataSetChanged();
    }

    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view_social_media_row);

            itemView.setOnClickListener(view -> {
                Uri uri = Uri.parse(arrayList.get(getAdapterPosition()).getAccountUrl());
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage(arrayList.get(getAdapterPosition()).getAppPackage());

                try {
                    context.startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(arrayList.get(getAdapterPosition()).getAccountUrl())));
                }
            });
        }

    }

}