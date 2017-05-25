package com.naijab.nextzytimeline.ui.main.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder> {

    private List<PeopleModel> mList;
    private Context context;

    public PeopleAdapter(Context context, List<PeopleModel> item) {
        this.context = context;
        this.setResult(item);
    }

    public void setResult(List<PeopleModel> item){
        this.mList = item;
        notifyDataSetChanged();
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_people_home, parent, false);
        PeopleViewHolder viewHolder = new PeopleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        holder.tvName.setText(mList.get(position).getName());
        holder.tvJob.setText(mList.get(position).getJob());
        String url = mList.get(position).getPhoto();
        setImage(holder, url);
    }

    private void setImage(PeopleViewHolder holder, String url){
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }



}
