package com.tuzhi.application.moudle.memberlist;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityMemberListBinding;
import com.tuzhi.application.dialog.ChooseOpennessDialog;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp.ChooseColleagueActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * 知识库，频道 管理员的管理
 *
 * @author wangpeng
 */

public class MemberListActivity extends MVPBaseActivity<MemberListContract.View, MemberListPresenter> implements MemberListContract.View, ItemClickListener, OnDialogClickListener {

    public static final String REFRESH = "MemberListActivity_REFRESH";
    /**
     * 数据库或者频道的id
     */
    public static final String ID = "id";
    /**
     * 类类型
     */
    public static final String TYPE = "TYPE";
    /**
     * 知识库类型
     */
    public static final String TYPE_LIB = "1";
    /**
     * 频道类型
     */
    public static final String TYPE_CHANNEL = "2";

    private ActivityMemberListBinding binding;
    private ArrayList<ItemBean> data = new ArrayList<>();
    private CommonRcvAdapter<ItemBean> adapter;
    private String id;
    private ItemBean bean;
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_list;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        id = getIntent().getStringExtra(ID);
        type = getIntent().getStringExtra(TYPE);
        binding = (ActivityMemberListBinding) viewDataBinding;
        binding.setActivity(this);
        mPresenter.downloadData(type, id);
        setAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(String text) {
        if (TextUtils.equals(text, REFRESH)) {
            mPresenter.downloadData(type, id);
        }
    }

    private void setAdapter() {
        adapter = new CommonRcvAdapter<ItemBean>(data) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                MemberListItem memberListItem = new MemberListItem();
                memberListItem.setClickListener(MemberListActivity.this);
                return memberListItem;
            }
        };
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }

    public void back() {
        onBackPressed();
    }

    public void addMember() {
        Intent intent = new Intent(this, ChooseColleagueActivity.class);
        intent.putExtra(ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_ADD_MEMBER);
        intent.putExtra(ChooseColleagueActivity.LIB_ID, id);
        intent.putExtra(ChooseColleagueActivity.TYPE_ID, type);
        startActivity(intent);
    }

    public void removeMember() {
        Intent intent = new Intent(this, ChooseColleagueActivity.class);
        intent.putExtra(ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_REMOVE_MEMBER);
        intent.putExtra(ChooseColleagueActivity.LIB_ID, id);
        intent.putExtra(ChooseColleagueActivity.TYPE_ID, type);
        startActivity(intent);
    }

    @Override
    public void downloadSuccess(ArrayList<ItemBean> httpData) {
        data.clear();
        data.addAll(httpData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view) {
        bean = (ItemBean) view.getTag();
        if (bean.getIndex() != 0) {
            switch (view.getId()) {
                case R.id.tvChangeJurisdiction:
                    ChooseOpennessDialog dialog = new ChooseOpennessDialog(this, bean.getIndex() == 1, 2);
                    dialog.setClickListener(this);
                    dialog.show();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onDialogClick(View view) {
        switch (view.getId()) {
            case R.id.flOpen:
                bean.setIndex(1);
                break;
            case R.id.flSecret:
                bean.setIndex(2);
                break;
            default:
                break;
        }
    }
}
