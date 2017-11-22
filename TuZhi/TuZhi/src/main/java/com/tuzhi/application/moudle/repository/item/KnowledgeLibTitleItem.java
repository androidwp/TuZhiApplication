package com.tuzhi.application.moudle.repository.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemKnowledgeLibTitleBinding;
import com.tuzhi.application.item.GeneralItem;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author wangpeng
 * @date 2017/11/15
 */

public class KnowledgeLibTitleItem extends GeneralItem<ItemBean> {

    public static final String TYPE = "KnowledgeLibTitleItem";

    private ItemKnowledgeLibTitleBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_lib_title;
    }

    @Override
    public void handleData(ItemBean bean, int i) {
        binding.setData(bean);
        binding.executePendingBindings();
    }
}
