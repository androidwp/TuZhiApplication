package com.tuzhi.application.moudle.repository.knowledgachannel.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityKnowledgeChannelBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.createchannel.CreateChannelActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp.EnterpriseKnowledgeActivity;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelItemBean;
import com.tuzhi.application.moudle.repository.knowledgachannel.item.CreateChannelItem;
import com.tuzhi.application.moudle.repository.knowledgachannel.item.KnowledgeChannelItem;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.view.LoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 *
 * @author wangpeng
 */

public class KnowledgeChannelActivity extends MVPBaseFragment<KnowledgeChannelContract.View, KnowledgeChannelPresenter> implements KnowledgeChannelContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, ItemClickListener {
    public static final String MESSAGE = "KnowledgeChannelActivity_refresh";
    public static final String ID = "id";
    public static final String TITLE = "TITLE";
    private String listId;
    private ArrayList<KnowledgeChannelItemBean> mData = new ArrayList<>();
    private ActivityKnowledgeChannelBinding binding;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_channel;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (TextUtils.equals(event, MESSAGE)) {
            onRefresh();
        }
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        binding = (ActivityKnowledgeChannelBinding) viewDataBinding;
        listId = getArguments().getString(ID);
        binding.setActivity(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.setLoadListener(this);
        binding.rrv.setTitle("该知识库下还没有频道哦");
        binding.rrv.setInfo("点击上方的\"+\"号，创建频道");
        CommonRcvAdapter<KnowledgeChannelItemBean> adapter = new CommonRcvAdapter<KnowledgeChannelItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case CreateChannelItem.TYPE:
                        CreateChannelItem createChannelItem = new CreateChannelItem();
                        createChannelItem.setClickListener(KnowledgeChannelActivity.this);
                        return createChannelItem;
                    case KnowledgeChannelItem.TYPE:
                        KnowledgeChannelItem knowledgeChannelItem = new KnowledgeChannelItem();
                        knowledgeChannelItem.setClickListener(KnowledgeChannelActivity.this);
                        return knowledgeChannelItem;
                    default:
                        return new GeneralLoadFootViewItem();
                }
            }

            @Override
            public Object getItemType(KnowledgeChannelItemBean enterpriseKnowledgeListItemBean) {
                return enterpriseKnowledgeListItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);
    }


    @Override
    public void onRefresh() {
        mPresenter.downLoadData(listId, 0);
    }

    @Override
    public void downloadFinish(int page, boolean haveNextPage, ArrayList<KnowledgeChannelItemBean> data) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, false);
    }

    @Override
    public void downloadFinishNothing() {
        binding.rrv.isShowRefreshView(false);
    }


    @Override
    public void loadMoreListener(int page) {
        mPresenter.downLoadData(listId, page);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantKt.getCREATE_CODE() && resultCode == ConstantKt.getCREATE_CODE()) {
            binding.rrv.isShowRefreshView(true);
        }
    }


    @Override
    public void onItemClick(View view) {
        if (view == null) {
            Intent intent = new Intent(getContext(), CreateChannelActivity.class);
            intent.putExtra(CreateChannelActivity.KID, listId);
            intent.putExtra(CreateChannelActivity.TYPE, CreateChannelActivity.TYPE_CREATE);
            startActivity(intent);
        } else {
            KnowledgeChannelItemBean knowledgeChannelItemBean = (KnowledgeChannelItemBean) view.getTag();
            Intent intent = new Intent(getContext(), EnterpriseKnowledgeActivity.class);
            intent.putExtra(EnterpriseKnowledgeActivity.KLID, knowledgeChannelItemBean.getKlid());
            intent.putExtra(EnterpriseKnowledgeActivity.KCID, knowledgeChannelItemBean.getKcid());
            startActivity(intent);
        }
    }
}
