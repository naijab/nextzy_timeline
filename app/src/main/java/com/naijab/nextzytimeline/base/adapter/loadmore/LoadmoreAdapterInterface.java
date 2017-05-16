package com.naijab.nextzytimeline.base.adapter.loadmore;

import com.naijab.nextzytimeline.base.adapter.BaseItem;
import com.naijab.nextzytimeline.base.adapter.BaseMvpListAdapterInterface;
import java.util.List;

public interface LoadmoreAdapterInterface{

    interface Adapter extends BaseMvpListAdapterInterface.Adapter{
    }

    interface Presenter<A extends Adapter>
            extends BaseMvpListAdapterInterface.Presenter<A>{
        void setItems(List<BaseItem> items, boolean isNextItemAvailable);
    }
}

