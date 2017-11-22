package com.tuzhi.application.moudle.repository.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityRepositoryBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.knowledgelib.KnowledgeLibActivity;
import com.tuzhi.application.moudle.repository.item.CommonKnowledgeLibItem;
import com.tuzhi.application.moudle.repository.item.KnowledgeLibTitleItem;
import com.tuzhi.application.moudle.repository.item.RepositoryListItem;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.view.LoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * 知识库页面
 *
 * @author wangpeng
 */

public class RepositoryFragment extends MVPBaseFragment<RepositoryContract.View, RepositoryPresenter> implements RepositoryContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, ItemClickListener {

    public static final String MESSAGE = "RepositoryActivity_refresh";
    private ActivityRepositoryBinding binding;
    private ArrayList<ItemBean> mData = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_repository;
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
        binding = (ActivityRepositoryBinding) viewDataBinding;
        binding.setActivity(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setLoadListener(this);
        binding.rrv.setTitle("知识库空空如也");
        binding.rrv.setInfo("点击上方的\"+\"号，创建知识库");
        CommonRcvAdapter<ItemBean> adapter = new CommonRcvAdapter<ItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    case CommonKnowledgeLibItem.TYPE:
                        CommonKnowledgeLibItem commonKnowledgeLibItem = new CommonKnowledgeLibItem();
                        commonKnowledgeLibItem.setClickListener(RepositoryFragment.this);
                        return commonKnowledgeLibItem;
                    case KnowledgeLibTitleItem.TYPE:
                        return new KnowledgeLibTitleItem();
                    default:
                        RepositoryListItem item = new RepositoryListItem();
                        item.setClickListener(RepositoryFragment.this);
                        return item;
                }
            }

            @Override
            public Object getItemType(ItemBean repositoryListItemBean) {
                return repositoryListItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        mPresenter.downLoadData(0);
    }

    @Override
    public void downLoadFinish(ArrayList<ItemBean> data, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, false);
    }

    @Override
    public void downloadFinish() {
        binding.rrv.isShowRefreshView(false);
    }


    @Override
    public void loadMoreListener(int page) {
        mPresenter.downLoadData(page);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstantKt.getKILL_ACTIVITY_CODE() && resultCode == ConstantKt.getKILL_ACTIVITY_CODE()) {
            getActivity().finish();
        } else if (requestCode == ConstantKt.getCREATE_CODE() && resultCode == ConstantKt.getCREATE_CODE()) {
            onRefresh();
        }
    }

    @Override
    public void onItemClick(View view) {
        ItemBean tag = (ItemBean) view.getTag();
        Intent intent = new Intent(getContext(), KnowledgeLibActivity.class);
        intent.putExtra(KnowledgeLibActivity.ID, tag.getId());
        intent.putExtra(KnowledgeLibActivity.TITLE, tag.getTitle());
        intent.putExtra(KnowledgeLibActivity.OPEN, tag.isFlag());
        getContext().startActivity(intent);
    }
}
