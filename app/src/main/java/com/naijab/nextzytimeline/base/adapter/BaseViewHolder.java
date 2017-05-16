package com.naijab.nextzytimeline.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{

    public BaseViewHolder( ViewGroup parent, int layout ){
        super( LayoutInflater
                .from( parent.getContext() )
                .inflate( layout, parent, false ) );
        bindView( itemView );
    }

    public abstract void bindView( View view );

    protected Context getContext(){
        return itemView.getContext();
    }
}
