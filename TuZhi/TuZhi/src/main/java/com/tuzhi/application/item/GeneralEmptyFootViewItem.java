package com.tuzhi.application.item;

import android.view.View;

import com.tuzhi.application.R;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/1.
 */

public class GeneralEmptyFootViewItem extends BaseItem<Object> {
    public static final String TYPE = "GeneralEmptyFootViewItem";

    @Override
    public void bindView(@NotNull View view) {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_empty_foot_view;
    }

    @Override
    public void handleData(Object o, int i) {

    }
}
