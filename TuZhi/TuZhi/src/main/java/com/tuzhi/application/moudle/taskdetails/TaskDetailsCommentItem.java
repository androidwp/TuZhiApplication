package com.tuzhi.application.moudle.taskdetails;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemTaskCommentBinding;
import com.tuzhi.application.item.GeneralItem;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author wangpeng
 * @date 2017/11/14
 */

public class TaskDetailsCommentItem extends GeneralItem<ItemBean> {

    public static final String TYPE = "TaskDetailsCommentItem";

    private ItemTaskCommentBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_task_comment;
    }

    @Override
    public void handleData(ItemBean bean, int i) {
        binding.setData(bean);
    }
}
