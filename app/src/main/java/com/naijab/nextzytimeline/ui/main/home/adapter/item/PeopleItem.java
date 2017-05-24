package com.naijab.nextzytimeline.ui.main.home.adapter.item;

import android.os.Parcelable;

import com.naijab.nextzytimeline.base.adapter.BaseItem;
import com.naijab.nextzytimeline.ui.main.home.adapter.PeopleListAdapter;

public class PeopleItem extends BaseItem implements Parcelable {

    public PeopleItem(){
        super( PeopleListAdapter.CUSTOM_TYPE );
    }
}
