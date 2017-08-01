package com.tuzhi.application.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemFootViewBinding;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/1.
 */

public class GeneralLoadFootViewItem extends BaseItem<Object> {
    public static final String TYPE = "GeneralLoadFootViewItem";
    private ItemFootViewBinding bind;
    private int colorId = R.color.colorGeneralBackground;

    @Override
    public void bindView(@NotNull View view) {
        bind = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_foot_view;
    }

    @Override
    public void handleData(Object o, int i) {
        bind.ll.setBackgroundResource(colorId);
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
