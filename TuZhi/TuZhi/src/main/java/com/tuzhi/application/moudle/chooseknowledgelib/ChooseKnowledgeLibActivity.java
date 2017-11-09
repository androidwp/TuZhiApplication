package com.tuzhi.application.moudle.chooseknowledgelib;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityChooseKnowledgeLibBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.createtask.CreateTaskActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseKnowledgeLibActivity extends MVPBaseActivity<ChooseKnowledgeLibContract.View, ChooseKnowledgeLibPresenter> implements ChooseKnowledgeLibContract.View, ItemClickListener {

    private ActivityChooseKnowledgeLibBinding binding;
    private ArrayList<ItemBean> data = new ArrayList<>();
    private CommonRcvAdapter<ItemBean> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_knowledge_lib;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityChooseKnowledgeLibBinding) viewDataBinding;
        binding.setActivity(this);
        adapter = new CommonRcvAdapter<ItemBean>(data) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                ChooseKnowledgeLibItem item = new ChooseKnowledgeLibItem();
                item.setClickListener(ChooseKnowledgeLibActivity.this);
                return item;
            }
        };
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new GridLayoutManager(this, 2));
        mPresenter.downLoadData();
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void onItemClick(View view) {
        ItemBean bean = (ItemBean) view.getTag();
        sendLib(bean);
        back();
    }

    private void sendLib(ItemBean itemBean) {
        EventBusBean busBean = new EventBusBean();
        busBean.setName(CreateTaskActivity.EVENT_NAME);
        busBean.setEventType(CreateTaskActivity.EVENT_TYPE_LIB);
        busBean.setObject(itemBean);
        EventBus.getDefault().post(busBean);
    }

    @Override
    public void downLoadFinish(ArrayList<ItemBean> data) {
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
