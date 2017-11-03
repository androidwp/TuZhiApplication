package com.tuzhi.application.moudle.completedtasks;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCompletedTasksBinding;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.view.LoadMoreListener;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CompletedTasksActivity extends MVPBaseActivity<CompletedTasksContract.View, CompletedTasksPresenter> implements CompletedTasksContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<CompletedTasksItemBean> mData = new ArrayList<>();
    private ActivityCompletedTasksBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_completed_tasks;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityCompletedTasksBinding) viewDataBinding;
        binding.rrv.setLoadListener(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setTitle("暂无消息");
        binding.rrv.setDrawable(R.drawable.enptymessage);
        CommonRcvAdapter<CompletedTasksItemBean> adapter = new CommonRcvAdapter<CompletedTasksItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    default:
                        return new CompletedTasksItem();
                }
            }

            @Override
            public Object getItemType(CompletedTasksItemBean listItemBean) {
                return listItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);
    }

    @Override
    public void downloadFinish(ArrayList<CompletedTasksItemBean> data, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, false);
    }

    @Override
    public void downloadError() {

    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downloadData(page);
    }

    @Override
    public void onRefresh() {
        mPresenter.downloadData(0);
    }

    public void back(){
        onBackPressed();
    }
}
