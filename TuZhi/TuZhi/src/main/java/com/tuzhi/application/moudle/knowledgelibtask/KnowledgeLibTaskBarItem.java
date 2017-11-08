package com.tuzhi.application.moudle.knowledgelibtask;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeTaskBarBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.mytasks.MyTasksItemBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/11/7.
 */

public class KnowledgeLibTaskBarItem extends BaseItem<MyTasksItemBean> {

    public static final String TYPE = "KnowledgeLibTaskBarItem";

    private ItemKnowledgeTaskBarBinding barBinding;

    private ItemClickListener clickListener;


    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void bindView(@NotNull View view) {
        barBinding = DataBindingUtil.bind(view);
        barBinding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_task_bar;
    }

    @Override
    public void handleData(MyTasksItemBean myTasksItemBean, int i) {
        barBinding.setData(myTasksItemBean);
    }

    public void viewClick(View view, MyTasksItemBean myTasksItemBean) {
        if (clickListener != null) {
            if (view.getId() == R.id.tvTasksChange) {
                myTasksItemBean.setFlagAllTaskOrMyTask(!myTasksItemBean.isFlagAllTaskOrMyTask());
            }
            clickListener.onItemClick(view);
        }
    }
}
