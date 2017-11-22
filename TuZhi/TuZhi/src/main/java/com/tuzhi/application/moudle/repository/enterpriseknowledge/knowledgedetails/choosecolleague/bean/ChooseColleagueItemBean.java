package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean;

import android.databinding.Bindable;

import com.tuzhi.application.BR;
import com.tuzhi.application.bean.BaseListItemBean;

/**
 *
 * @author wangpeng
 * @date 2017/8/10
 */

public class ChooseColleagueItemBean extends BaseListItemBean {
    private String id;
    private String portrait;
    private String nickName;
    private boolean chooseStatus;
    private String kcid;
    private String klid;
    private String title;
    private String text;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKcid() {
        return kcid;
    }

    public void setKcid(String kcid) {
        this.kcid = kcid;
    }

    public String getKlid() {
        return klid;
    }

    public void setKlid(String klid) {
        this.klid = klid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.text);
    }

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
