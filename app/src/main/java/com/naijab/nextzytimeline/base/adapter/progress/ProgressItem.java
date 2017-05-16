package com.naijab.nextzytimeline.base.adapter.progress;

import android.os.Parcel;
import com.naijab.nextzytimeline.base.adapter.BaseItem;
import com.naijab.nextzytimeline.base.adapter.BaseItemType;

public class ProgressItem extends BaseItem {
    public ProgressItem(){
        super( BaseItemType.TYPE_PROGRESS );
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeInt( this.type );
    }

    protected ProgressItem( Parcel in ){
        super( in );
        this.type = in.readInt();
    }

    public static final Creator<ProgressItem> CREATOR = new Creator<ProgressItem>(){
        @Override
        public ProgressItem createFromParcel( Parcel source ){
            return new ProgressItem( source );
        }

        @Override
        public ProgressItem[] newArray( int size ){
            return new ProgressItem[size];
        }
    };
}
