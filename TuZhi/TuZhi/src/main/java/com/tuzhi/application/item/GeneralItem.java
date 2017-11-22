package com.tuzhi.application.item;

import android.view.View;

import com.tuzhi.application.inter.ItemClickListener;

/**
 * Created by wangpeng on 2017/11/9.
 */

public abstract class GeneralItem<T> extends BaseItem<T> {
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ItemClickListener getClickListener() {
        return clickListener;
    }

    public void onItemClick(View view, Object data) {
        if (clickListener != null) {
            view.setTag(data);
            clickListener.onItemClick(view);
        }
    }

    public void onItemClick(View view) {
        if (clickListener != null) {
            clickListener.onItemClick(view);
        }
    }

    public void onItemClick(View view, Object... objects) {
        if (clickListener != null) {
            for (int i = 0; i < objects.length; i++) {
                view.setTag(i, objects[i]);
            }
            clickListener.onItemClick(view);
        }
    }
}
