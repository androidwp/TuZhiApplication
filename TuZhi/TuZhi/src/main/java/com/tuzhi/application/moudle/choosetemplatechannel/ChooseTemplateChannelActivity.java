package com.tuzhi.application.moudle.choosetemplatechannel;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityChooseTemplateChannelBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.chooseknowledgelib.ChooseKnowledgeLibActivity;
import com.tuzhi.application.moudle.chooselibtype.ChooseLibTypeItem;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.utils.ToastUtilsKt;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class ChooseTemplateChannelActivity extends MVPBaseActivity<ChooseTemplateChannelContract.View, ChooseTemplateChannelPresenter> implements ChooseTemplateChannelContract.View, ItemClickListener {

    public static final String TITLE = "title";
    public static final String ID = "id";
    private ActivityChooseTemplateChannelBinding binding;
    private ArrayList<ItemBean> data = new ArrayList<>();
    private ArrayList<ItemBean> chooseData = new ArrayList<>();
    private CommonRcvAdapter<ItemBean> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_template_channel;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityChooseTemplateChannelBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setLibName(getIntent().getStringExtra(TITLE));
        mPresenter.downloadData(getIntent().getStringExtra(ID));
        setAdapter();
    }

    private void setAdapter() {
        adapter = new CommonRcvAdapter<ItemBean>(data) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                ChooseLibTypeItem item = new ChooseLibTypeItem();
                item.setClickListener(ChooseTemplateChannelActivity.this);
                return item;
            }
        };
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }

    public void back() {
        onBackPressed();
    }

    public void sure() {
        StringBuilder ids = new StringBuilder();
        for (ItemBean chooseDatum : chooseData) {
            ids.append(",").append(chooseDatum.getId());
        }
        ids.deleteCharAt(0);
        mPresenter.createLib(getIntent().getStringExtra(ID), ids.toString());
    }

    public void chooseAll() {
        for (ItemBean datum : data) {
            datum.setFlag(true);
        }
        chooseData.clear();
        chooseData.addAll(data);
        binding.setChooseNumber("确认(" + chooseData.size() + ")");
    }

    @Override
    public void onItemClick(View view) {
        ItemBean itemBean = (ItemBean) view.getTag();
        itemBean.setFlag(!itemBean.isFlag());
        if (itemBean.isFlag()) {
            chooseData.add(itemBean);
        } else {
            chooseData.remove(itemBean);
        }
        if (chooseData.size() == 0) {
            binding.setChooseNumber("");
        } else {
            binding.setChooseNumber("确认(" + chooseData.size() + ")");
        }
    }

    @Override
    public void downloadSuccess(ArrayList<ItemBean> httpData) {
        data.addAll(httpData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void createLibSuccess() {
        EventBus.getDefault().post(ChooseKnowledgeLibActivity.EVENT_FINISH);
        EventBus.getDefault().post(RepositoryFragment.MESSAGE);
        ToastUtilsKt.toast(this, "创建成功");
        back();
    }
}
