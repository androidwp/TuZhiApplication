package com.tuzhi.application.moudle.createchannel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;

/**
 * @author wangpeng
 * @date 2017/11/10
 */

public class CreateChannelBean extends BaseObservable {
    private String klId;
    private String channelName = "";
    private String channelSummery = "";
    private boolean openOrSecret = true;

    public String getKlId() {
        return klId;
    }

    public void setKlId(String klId) {
        this.klId = klId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelSummery() {
        return channelSummery;
    }

    public void setChannelSummery(String channelSummery) {
        this.channelSummery = channelSummery;
    }

    @Bindable
    public boolean isOpenOrSecret() {
        return openOrSecret;
    }

    public void setOpenOrSecret(boolean openOrSecret) {
        this.openOrSecret = openOrSecret;
        notifyPropertyChanged(BR.openOrSecret);
    }
}
