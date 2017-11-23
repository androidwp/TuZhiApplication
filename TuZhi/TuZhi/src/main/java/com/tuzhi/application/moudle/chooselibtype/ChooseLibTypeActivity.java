package com.tuzhi.application.moudle.chooselibtype;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityChooseLibTypeBinding;
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
 * @author wangpeng
 */

public class ChooseLibTypeActivity extends MVPBaseActivity<ChooseLibTypeContract.View, ChooseLibTypePresenter> implements ChooseLibTypeContract.View, ItemClickListener {

    public static final String ID = "id";
    private ArrayList<ItemBean> itemBeans = new ArrayList<>();
    private String[] titles = {"公共知识库", "部门知识库", "项目知识库"};
    private String[] ids = {"1", "2", "3"};
    private ActivityChooseLibTypeBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_lib_type;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityChooseLibTypeBinding) viewDataBinding;
        binding.setActivity(this);
        setData();
        setAdapter();
    }

    private void setAdapter() {
        CommonRcvAdapter<ItemBean> adapter = new CommonRcvAdapter<ItemBean>(itemBeans) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                ChooseLibTypeItem item = new ChooseLibTypeItem();
                item.setClickListener(ChooseLibTypeActivity.this);
                return item;
            }
        };
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData() {
        String id = getIntent().getStringExtra(ID);
        for (int i = 0; i < 3; i++) {
            ItemBean itemBean = new ItemBean(ChooseLibTypeItem.TYPE);
            itemBean.setTitle(titles[i]);
            itemBean.setId(ids[i]);
            itemBean.setFlag(id.equals(ids[i]));
            itemBeans.add(itemBean);
        }
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void onItemClick(View view) {
        ItemBean itemBean = (ItemBean) view.getTag();
        EventBusBean eventBusBean = new EventBusBean();
        eventBusBean.setName(CreateKnowledgeLibActivity.name);
        eventBusBean.setEventType(CreateKnowledgeLibActivity.EVENT_TYPE_LIB_TYPE);
        eventBusBean.setObject(itemBean);
        EventBus.getDefault().post(eventBusBean);
        back();
    }
}
