package com.tuzhi.application.moudle.repository.item;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemCommonKnowledgeLibBinding;
import com.tuzhi.application.item.GeneralItem;
import com.tuzhi.application.moudle.chooseknowledgelib.ChooseKnowledgeLibItem;

import org.jetbrains.annotations.NotNull;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 *
 * @author wangpeng
 * @date 2017/11/15
 */

public class CommonKnowledgeLibItem extends GeneralItem<ItemBean> {

    public static final String TYPE = "CommonKnowledgeLibItem";
    private ItemCommonKnowledgeLibBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_common_knowledge_lib;
    }

    @Override
    public void handleData(ItemBean bean, int i) {
        CommonRcvAdapter adapter = new CommonRcvAdapter<ItemBean>(bean.getItemBeans()) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                ChooseKnowledgeLibItem item = new ChooseKnowledgeLibItem();
                item.setClickListener(getClickListener());
                return item;
            }
        };
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new GridLayoutManager(context, 2));
    }
}
