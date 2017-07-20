package com.tuzhi.application.moudle.repository.bean;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/6/21.
 */

public class RepositoryListItemBean extends BaseListItemBean {
    private String id;
    private String title;
    private String text;

    public RepositoryListItemBean(String itemType) {
        super(itemType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
