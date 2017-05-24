package com.naijab.nextzytimeline.ui.main.home.adapter.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.adapter.BaseViewHolder;
import com.naijab.nextzytimeline.ui.main.home.adapter.item.PeopleItem;

public class PeopleHolder extends BaseViewHolder {

    private OnClickCustomHolderListener listener;

    public interface OnClickCustomHolderListener{
        void onClick1(PeopleHolder view, int position);

        void onClick2(PeopleHolder view, int position);

        void onClick3(PeopleHolder view, int position);
    }

    public PeopleHolder(ViewGroup itemView ){
        super( itemView, R.layout.activity_main );
    }

    @Override
    public void bindView( View view ){

    }

    public void onBind( PeopleItem item ){

    }


    public void setOnClickCustomHolderListener( OnClickCustomHolderListener listener ){
        this.listener = listener;
    }

    @NonNull
    private View.OnClickListener onClick1(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClick1( PeopleHolder.this, getAdapterPosition() );
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener onClick2(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClick2( PeopleHolder.this, getAdapterPosition() );
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener onClick3(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClick3( PeopleHolder.this, getAdapterPosition() );
                }
            }
        };
    }
}
