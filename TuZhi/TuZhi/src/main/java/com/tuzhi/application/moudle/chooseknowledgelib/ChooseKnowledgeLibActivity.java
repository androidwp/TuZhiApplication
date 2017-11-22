package com.tuzhi.application.moudle.chooseknowledgelib;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityChooseKnowledgeLibBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.choosetemplatechannel.ChooseTemplateChannelActivity;
import com.tuzhi.application.moudle.createtask.CreateTaskActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class ChooseKnowledgeLibActivity extends MVPBaseActivity<ChooseKnowledgeLibContract.View, ChooseKnowledgeLibPresenter> implements ChooseKnowledgeLibContract.View, ItemClickListener {

    public static final String EVENT_FINISH = "ChooseKnowledgeLibActivity_finish";
    public static final String TYPE = "type";
    public static final String TYPE_CHOOSE_LIB = "0";
    public static final String TYPE_CHOOSE_TEMPLATE = "1";
    private ActivityChooseKnowledgeLibBinding binding;
    private ArrayList<ItemBean> data = new ArrayList<>();
    private CommonRcvAdapter<ItemBean> adapter;
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_knowledge_lib;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        type = getIntent().getStringExtra(TYPE);
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
        mPresenter.downLoadData(type);
    }

    public void back() {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String type) {
        if (TextUtils.equals(type, EVENT_FINISH)) {
            finish();
        }
    }

    @Override
    public void onItemClick(View view) {
        ItemBean bean = (ItemBean) view.getTag();
        if (type.equals(TYPE_CHOOSE_LIB)) {
            sendLib(bean);
            back();
        } else {
            Intent intent = new Intent(this, ChooseTemplateChannelActivity.class);
            intent.putExtra(ChooseTemplateChannelActivity.TITLE, bean.getTitle());
            intent.putExtra(ChooseTemplateChannelActivity.ID, bean.getId());
            startActivity(intent);
        }
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
