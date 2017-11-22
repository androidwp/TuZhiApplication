package com.tuzhi.application.moudle.chooselibicon;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityChooseLibIconBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.createknowledgelib.CreateKnowledgeLibActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChooseLibIconActivity extends MVPBaseActivity<ChooseLibIconContract.View, ChooseLibIconPresenter> implements ChooseLibIconContract.View, ItemClickListener {
    private ArrayList<ItemBean> itemBeans = new ArrayList<>();
    private ActivityChooseLibIconBinding binding;
    private CommonRcvAdapter<ItemBean> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_lib_icon;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityChooseLibIconBinding) viewDataBinding;
        binding.setActivity(this);
        setAdapter();
        mPresenter.downloadData();
    }

    private void setAdapter() {
        adapter = new CommonRcvAdapter<ItemBean>(itemBeans) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                ChooseLibIconItem item = new ChooseLibIconItem();
                item.setClickListener(ChooseLibIconActivity.this);
                return item;
            }
        };
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new GridLayoutManager(this, 4));
    }


    public void back() {
        onBackPressed();
    }

    @Override
    public void onItemClick(View view) {
        ItemBean itemBean = (ItemBean) view.getTag();
        EventBusBean eventBusBean = new EventBusBean();
        eventBusBean.setName(CreateKnowledgeLibActivity.name);
        eventBusBean.setEventType(CreateKnowledgeLibActivity.EVENT_TYPE_LIB_ICON);
        eventBusBean.setsContent(itemBean.getImage());
        EventBus.getDefault().post(eventBusBean);
        back();
    }

    @Override
    public void downloadDataSuccess(ArrayList<ItemBean> httpData) {
        itemBeans.addAll(httpData);
        adapter.notifyDataSetChanged();
    }
}
