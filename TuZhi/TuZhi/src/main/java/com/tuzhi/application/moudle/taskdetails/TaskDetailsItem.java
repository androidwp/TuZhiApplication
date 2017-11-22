package com.tuzhi.application.moudle.taskdetails;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemTaskDetailsBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.GeneralItem;
import com.tuzhi.application.moudle.createtask.TaskCardItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * @author wangpeng
 * @date 2017/11/14
 */

public class TaskDetailsItem extends GeneralItem<ItemBean> implements ItemClickListener {

    public static final String TYPE = "TaskDetailsItem";

    private ItemTaskDetailsBinding binding;
    private CommonRcvAdapter<ItemBean> adapter;
    private ArrayList<ItemBean> data;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_task_details;
    }

    @Override
    public void handleData(ItemBean itemBean, int i) {
        data = itemBean.getTaskBean().getData();
        binding.setData(itemBean.getTaskBean());
        binding.setItemBean(itemBean);
        adapter = new CommonRcvAdapter<ItemBean>(data) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                TaskCardItem taskCardItem = new TaskCardItem();
                taskCardItem.setClickListener(getClickListener());
                return taskCardItem;
            }
        };
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(context));
        itemBean.getTaskBean().setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, Object data) {
        super.onItemClick(view, data);
    }

}
