package com.tuzhi.application.moudle.chooseknowledgelib;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemChooseKnowledgeLibBinding;
import com.tuzhi.application.item.GeneralItem;

import org.jetbrains.annotations.NotNull;

/**
 * @author wangpeng
 * @date 2017/11/9
 */

public class ChooseKnowledgeLibItem extends GeneralItem<ItemBean> {

    public static final String TYPE = "ChooseKnowledgeLibItem";

    private ItemChooseKnowledgeLibBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_choose_knowledge_lib;
    }

    @Override
    public void handleData(ItemBean bean, int i) {
        binding.setData(bean);
        binding.executePendingBindings();
    }

    @Override
    public void onItemClick(View view, Object data) {
        super.onItemClick(view, data);
    }
}
