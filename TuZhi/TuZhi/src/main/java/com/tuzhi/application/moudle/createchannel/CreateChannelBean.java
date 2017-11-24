package com.tuzhi.application.moudle.createchannel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;

/**
 * @author wangpeng
 * @date 2017/11/10
 */

public class CreateChannelBean extends BaseObservable {
    private String kId;
    private String CId;
    private String channelName = "";
    private String channelSummery = "";
    private String type;
    private boolean openOrSecret = true;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getkId() {
        return kId;
    }

    public void setkId(String kId) {
        this.kId = kId;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
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
