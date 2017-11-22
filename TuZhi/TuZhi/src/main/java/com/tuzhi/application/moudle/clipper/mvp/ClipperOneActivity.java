package com.tuzhi.application.moudle.clipper.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ActivityClipperOneBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.clipper.clippertwo.mvp.ClipperTwoActivity;
import com.tuzhi.application.moudle.repository.item.RepositoryListItem;
import com.tuzhi.application.view.LoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ClipperOneActivity extends MVPBaseActivity<ClipperOneContract.View, ClipperOnePresenter> implements ClipperOneContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener, ItemClickListener {

    public static final String ARTICLE_URL = "ARTICLE_URL";
    public static final String EVENT_BACK = "ClipperOneActivity_back";
    private ArrayList<ItemBean> mData = new ArrayList<>();
    private ActivityClipperOneBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clipper_one;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        binding = (ActivityClipperOneBinding) viewDataBinding;
        binding.setActivity(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setLoadListener(this);
        CommonRcvAdapter<ItemBean> adapter = new CommonRcvAdapter<ItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    default:
                        RepositoryListItem item = new RepositoryListItem();
                        item.setClickListener(ClipperOneActivity.this);
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String text) {
        if (TextUtils.equals(text, EVENT_BACK)) {
            finish();
        }
    }

    @Override
    public void downLoadFinish(ArrayList<ItemBean> data, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, false);
    }

    @Override
    public void downloadFinish() {
        binding.rrv.isShowRefreshView(false);
    }

    public void back() {
        onBackPressed();
    }


    @Override
    public void onRefresh() {
        mPresenter.downLoadData(0);
    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downLoadData(page);
    }

    @Override
    public void onItemClick(View view) {
        ItemBean tag = (ItemBean) view.getTag();
        Intent intent = new Intent(this, ClipperTwoActivity.class);
        intent.putExtra(ClipperTwoActivity.ARTICLE_URL, getIntent().getStringExtra(ARTICLE_URL));
        intent.putExtra(ClipperTwoActivity.ID, tag.getId());
        getContext().startActivity(intent);
    }
}
