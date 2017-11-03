package com.tuzhi.application.moudle.completedtasks;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemCompletedTasksBinding;
import com.tuzhi.application.item.BaseItem;

/**
 * Created by wangpeng on 2017/10/26.
 */

public class CompletedTasksItem extends BaseItem<CompletedTasksItemBean> {

    public static final String TYPE = "CompletedTasksItem";
    private ItemCompletedTasksBinding binding;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_completed_tasks;
    }

    @Override
    public void handleData(CompletedTasksItemBean completedTasksItemBean, int i) {
        binding.setData(completedTasksItemBean);
    }
}
