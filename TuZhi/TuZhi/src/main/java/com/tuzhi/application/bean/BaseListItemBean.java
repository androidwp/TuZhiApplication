package com.tuzhi.application.bean;

import android.databinding.BaseObservable;

/**
 * Created by wangpeng on 2017/6/1.
 */

public class BaseListItemBean extends BaseObservable implements Cloneable {
    private String itemType;

    public BaseListItemBean(String itemType) {
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public BaseListItemBean clone() {
        BaseListItemBean bean = null;
        try {
            bean = (BaseListItemBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
