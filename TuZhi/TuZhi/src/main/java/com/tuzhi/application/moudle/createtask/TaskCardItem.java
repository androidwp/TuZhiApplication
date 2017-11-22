package com.tuzhi.application.moudle.createtask;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemTaskCardBinding;
import com.tuzhi.application.item.GeneralItem;

import org.jetbrains.annotations.NotNull;

/**
 * @author wangpeng
 * @date 2017/11/9
 */

public class TaskCardItem extends GeneralItem<ItemBean> {

    public static final String TYPE = "TaskCardItem";

    private ItemTaskCardBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_task_card;
    }

    @Override
    public void handleData(ItemBean itemBean, int i) {
        binding.setItem(this);
        binding.setData(itemBean);
        binding.setPosition(i);
        binding.executePendingBindings();
    }

    @Override
    public void onItemClick(View view, Object data) {
        super.onItemClick(view, data);
    }
}
