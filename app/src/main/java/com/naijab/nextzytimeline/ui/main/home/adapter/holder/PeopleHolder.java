package com.naijab.nextzytimeline.ui.main.home.adapter.holder;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.adapter.BaseViewHolder;
import com.naijab.nextzytimeline.ui.main.home.adapter.item.PeopleItem;

public class PeopleHolder extends BaseViewHolder {

    private TextView tvName;
    private TextView tvJob;
    private ImageView ivProfile;
    private ImageView ivPhoto;

    private OnClickPeopleHolderListener listener;

    public interface OnClickPeopleHolderListener {
        void onClickShow(PeopleHolder view, int position);

        void onClickEdit(PeopleHolder view, int position);

        void onClickDelete(PeopleHolder view, int position);
    }

    public PeopleHolder(ViewGroup itemView) {
        super(itemView, R.layout.holder_people_home);
    }

    @Override
    public void bindView(View view) {
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvJob = (TextView) view.findViewById(R.id.tv_job);
        ivProfile = (ImageView) view.findViewById(R.id.iv_profile);
        ivPhoto = (ImageView) view.findViewById(R.id.iv_photo);
    }

    public void onBind(PeopleItem item) {
        tvName.setText(item.getName());
        tvJob.setText(item.getJob());
        setProfileImage(item.getPhoto());
    }

    private void setProfileImage(String url) {
        Glide.with(getContext())
                .load(Uri.parse(url))
                .into(ivPhoto);
    }


    public void setOnClickCustomHolderListener(OnClickPeopleHolderListener listener) {
        this.listener = listener;
    }

    @NonNull
    private View.OnClickListener onClickShow() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickShow(PeopleHolder.this, getAdapterPosition());
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickEdit() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickEdit(PeopleHolder.this, getAdapterPosition());
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickDelete() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickDelete(PeopleHolder.this, getAdapterPosition());
                }
            }
        };
    }
}
