package com.tuzhi.application.moudle.repository.knowledgachannel.bean;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.tuzhi.application.bean.BaseListItemBean;

/**
 * @author wangpeng
 * @date 2017/6/21
 */

public class KnowledgeChannelItemBean extends BaseListItemBean {
    private String kcid;
    private String klid;
    private String title;
    private String text;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public KnowledgeChannelItemBean(String itemType) {
        super(itemType);
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
        notifyPropertyChanged(BR.text);
    }
}
