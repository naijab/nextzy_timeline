package com.naijab.nextzytimeline.ui.main.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.naijab.nextzytimeline.R;

public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvName, tvJob;
    ImageView ivPhoto, ivProfile;

    OnItemClickListener clickListener;

    public PeopleViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvJob = (TextView) itemView.findViewById(R.id.tv_job);
        ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
        ivProfile = (ImageView) itemView.findViewById(R.id.iv_profile);
        itemView.setOnClickListener(this);
    }

    public void setImageProfile(String urlProfile) {
        Glide.with(itemView.getContext())
                .load(urlProfile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(ivProfile);
    }

    public void setImagePhoto(String url) {
        Glide.with(itemView.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(ivPhoto);
    }

    @Override
    public void onClick(View v) {
//        int position = getAdapterPosition();
        clickListener.onClick(v);
    }

    public interface OnItemClickListener {
        void onClick(View view);
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
