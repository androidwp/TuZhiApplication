package com.tuzhi.application.item;

import android.view.View;

import com.tuzhi.application.inter.ItemClickListener;

/**
 * Created by wangpeng on 2017/11/9.
 */

public abstract class GeneralItemBean<T> extends BaseItem<T> {
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void onItemClick(View view, T data) {
        if (clickListener != null) {
            view.setTag(data);
            clickListener.onItemClick(view);
        }
    }
}
