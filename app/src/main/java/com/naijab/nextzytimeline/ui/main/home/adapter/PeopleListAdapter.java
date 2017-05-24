package com.naijab.nextzytimeline.ui.main.home.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.naijab.nextzytimeline.base.adapter.BaseItem;
import com.naijab.nextzytimeline.base.adapter.BaseViewHolder;
import com.naijab.nextzytimeline.base.adapter.loadmore.LoadmoreAdapter;
import com.naijab.nextzytimeline.ui.main.home.adapter.holder.PeopleHolder;
import com.naijab.nextzytimeline.ui.main.home.adapter.item.PeopleItem;

public class PeopleListAdapter
        extends LoadmoreAdapter<BaseViewHolder, PeopleListAdapterInterface.Presenter>
        implements PeopleListAdapterInterface.Adapter{
    private final static String TAG = PeopleListAdapter.class.getSimpleName();

    public static final int CUSTOM_TYPE = 10;

    private OnClickCustomItemListener listener;

    public interface OnClickCustomItemListener{
        void onClick1(PeopleItem item, int position);

        void onClick2(PeopleItem item, int position);

        void onClick3(PeopleItem item, int position);
    }

    public void setOnClickCustomItemListener( OnClickCustomItemListener listener ){
        this.listener = listener;
    }

    public PeopleListAdapter(){
    }

    @Override
    public PeopleListAdapterInterface.Presenter createPresenter(){
        return PeopleListAdapterPresenter.create();
    }

    @Override
    public int getItemCount(){
        return getPresenter().getItemCount();
    }

    @Override
    public int getItemViewType( int position ){
        return getPresenter().getItemViewType( position );
    }

    @Override
    public BaseViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType ){
        switch( viewType ){
            case CUSTOM_TYPE:
                return new PeopleHolder( viewGroup );
        }
        return super.onCreateViewHolder( viewGroup, viewType ); // use loadmore
    }

    @Override
    public void onBindViewHolder( BaseViewHolder viewHolder, int position ){
        super.onBindViewHolder( viewHolder, position ); // use loadmore
        BaseItem i = getPresenter().getItem( position );
        if( getItemViewType( position ) == CUSTOM_TYPE ){
            PeopleItem item = (PeopleItem) i;
            PeopleHolder holder = (PeopleHolder) viewHolder;
            holder.onBind( item );
            holder.setOnClickCustomHolderListener( onClickHolder( item ) );
        }
    }

    @NonNull
    private PeopleHolder.OnClickCustomHolderListener onClickHolder(final PeopleItem item ){
        return new PeopleHolder.OnClickCustomHolderListener(){
            @Override
            public void onClick1(PeopleHolder view, int position ){
                if( listener != null ){
                    listener.onClick1( item, position );
                }
            }

            @Override
            public void onClick2(PeopleHolder view, int position ){
                if( listener != null ){
                    listener.onClick2( item, position );
                }
            }

            @Override
            public void onClick3(PeopleHolder view, int position ){
                if( listener != null ){
                    listener.onClick3( item, position );
                }
            }
        };
    }


}
