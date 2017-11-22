package com.tuzhi.application.moudle.chooselibicon;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemChooseLibIconBinding;
import com.tuzhi.application.item.GeneralItem;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/11/10.
 */

public class ChooseLibIconItem extends GeneralItem<ItemBean> {
    public static final String TYPE = "ChooseLibIconItem";
    private ItemChooseLibIconBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_choose_lib_icon;
    }

    @Override
    public void handleData(ItemBean itemBean, int i) {
        binding.setData(itemBean);
    }

    @Override
    public void onItemClick(View view, Object data) {
        super.onItemClick(view, data);
    }
}
