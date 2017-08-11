package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.mvp;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCheckHistoryVersionBinding;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.bean.CheckHistoryVersionItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.item.CheckHistoryVersionItem;
import com.tuzhi.application.view.LoadMoreListener;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CheckHistoricalVersionActivity extends MVPBaseActivity<CheckHistoricalVersionContract.View, CheckHistoricalVersionPresenter> implements CheckHistoricalVersionContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener {
    public static final String ID = "ID";
    private ActivityCheckHistoryVersionBinding binding;
    private ArrayList<CheckHistoryVersionItemBean> data = new ArrayList<>();
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_history_version;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        id = getIntent().getStringExtra(ID);
        binding = (ActivityCheckHistoryVersionBinding) viewDataBinding;
        binding.setActivity(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.setLoadListener(this);
        CommonRcvAdapter<CheckHistoryVersionItemBean> adapter = new CommonRcvAdapter<CheckHistoryVersionItemBean>(data) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    default:
                        return new CheckHistoryVersionItem();
                }
            }

            @Override
            public Object getItemType(CheckHistoryVersionItemBean checkHistoryVersionItemBean) {
                return checkHistoryVersionItemBean.getItemType();
            }
        };

        binding.rrv.setAdapter(adapter);
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void onRefresh() {
        mPresenter.downloadData(id, 0);
    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downloadData(id, page);
    }

    @Override
    public void downloadFinish(ArrayList<CheckHistoryVersionItemBean> newData, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, data, newData, false);
    }

    @Override
    public void downloadFinish() {
        binding.rrv.isShowRefreshView(false);
    }
}
