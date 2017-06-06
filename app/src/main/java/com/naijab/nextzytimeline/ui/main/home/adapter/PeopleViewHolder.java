package com.naijab.nextzytimeline.ui.main.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        clickListener.onClick(v, position);
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
