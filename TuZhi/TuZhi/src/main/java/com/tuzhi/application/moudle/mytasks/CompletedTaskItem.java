package com.tuzhi.application.moudle.mytasks;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemCompletedTaskBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.BaseItem;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author wangpeng
 * @date 2017/11/6
 */

public class CompletedTaskItem extends BaseItem<MyTasksItemBean> {

    private ItemClickListener clickListener;

    public static final String TYPE = "CompletedTasksItem";

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void bindView(@NotNull View view) {
        ItemCompletedTaskBinding binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_completed_task;
    }

    @Override
    public void handleData(MyTasksItemBean myTestsItemBean, int i) {

    }

    public void itemClick(View view) {
        if (clickListener != null) {
            clickListener.onItemClick(view);
        }
    }
}
