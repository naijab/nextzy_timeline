package com.naijab.nextzytimeline.base.adapter.loadmore;

import android.view.ViewGroup;

import com.naijab.nextzytimeline.base.adapter.BaseItem;
import com.naijab.nextzytimeline.base.adapter.BaseItemType;
import com.naijab.nextzytimeline.base.adapter.BaseMvpListAdapter;
import com.naijab.nextzytimeline.base.adapter.BaseViewHolder;
import com.naijab.nextzytimeline.base.adapter.progress.ProgressHolder;
import java.util.List;

public abstract class LoadmoreAdapter<VH extends BaseViewHolder, P extends LoadmoreAdapterInterface.Presenter>
        extends BaseMvpListAdapter<VH, P>
        implements LoadmoreAdapterInterface.Adapter{

    private OnLoadMoreListener loadMoreListener;

    public void setOnLoadMoreListener( OnLoadMoreListener listener ){
        this.loadMoreListener = listener;
    }

    public void setItems( List<BaseItem> items, boolean isNextItemAvailable ){
        getPresenter().setItems( items, isNextItemAvailable );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public VH onCreateViewHolder( ViewGroup parent, int viewType ){
        if( viewType == BaseItemType.TYPE_PROGRESS ){
            return (VH) new ProgressHolder( parent );
        }
        return null;
    }

    @Override
    public void onBindViewHolder( VH holder, int position ){
        if( getItemViewType( position ) == BaseItemType.TYPE_PROGRESS ){
            if( loadMoreListener != null ){
                loadMoreListener.onLoadMore();
            }
        }
    }
}
