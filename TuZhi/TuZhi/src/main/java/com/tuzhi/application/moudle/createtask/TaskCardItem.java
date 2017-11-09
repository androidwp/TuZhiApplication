package com.tuzhi.application.moudle.createtask;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemTaskCardBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.BaseItem;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/11/9.
 */

public class TaskCardItem extends BaseItem<ItemBean> {

    public static final String TYPE = "TaskCardItem";

    private ItemClickListener clickListener;

    private ItemTaskCardBinding binding;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

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
        binding.setData(itemBean);
    }

    public void onItemClick(View view, ItemBean bean) {
        if (clickListener != null) {
            view.setTag(bean);
            clickListener.onItemClick(view);
        }
    }
}
