package com.naijab.nextzytimeline.ui.main.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.manager.PeopleModel;

import java.util.List;

public class PeopleAdapter
        extends RecyclerView.Adapter<PeopleViewHolder> {

    private List<PeopleModel> mList;
    private OnAdapterListener listener;

    public PeopleAdapter(List<PeopleModel> item) {
        this.setResult(item);
    }

    public void setResult(List<PeopleModel> item) {
        this.mList = item;
        notifyDataSetChanged();
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_people_home, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        PeopleModel item = mList.get(position);
        holder.tvName.setText(item.getName());
        holder.tvJob.setText(item.getJob());
        holder.setImageProfile(item.getProfile());
        holder.setImagePhoto(item.getPhoto());
        holder.setOnItemClickListener(clickPeople(item));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void setOnClickPeopleItem(OnAdapterListener listener) {
        this.listener = listener;
    }

    public interface OnAdapterListener {
        void onClickAdapter(PeopleModel item);
    }

    private PeopleViewHolder.OnItemClickListener clickPeople(final PeopleModel item) {
        return new PeopleViewHolder.OnItemClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickAdapter(item);
                }
            }
        };
    }

}
