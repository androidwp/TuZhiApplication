package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean;

import android.databinding.Bindable;

import com.tuzhi.application.BR;
import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/8/10.
 */

public class ChooseColleagueItemBean extends BaseListItemBean {
    private String id;
    private String portrait;
    private String nickName;
    private boolean chooseStatus;

    @Bindable
    public boolean isChooseStatus() {
        return chooseStatus;
    }

    public void setChooseStatus(boolean chooseStatus) {
        this.chooseStatus = chooseStatus;
        notifyPropertyChanged(BR.chooseStatus);
    }

    public ChooseColleagueItemBean(String itemType) {
        super(itemType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

}
