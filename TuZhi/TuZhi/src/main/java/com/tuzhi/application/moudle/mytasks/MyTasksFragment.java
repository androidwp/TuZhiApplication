package com.tuzhi.application.moudle.mytasks;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityMyTasksBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.completedtasks.CompletedTasksActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.view.LoadMoreListener;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyTasksFragment extends MVPBaseFragment<MyTasksContract.View, MyTasksPresenter> implements MyTasksContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, ItemClickListener {

    private ArrayList<MyTasksItemBean> mData = new ArrayList<>();
    private ActivityMyTasksBinding binding;
    private CommonRcvAdapter<MyTasksItemBean> adapter;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityMyTasksBinding) viewDataBinding;
        binding.rrv.setLoadListener(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setTitle("暂无任务");
        binding.rrv.setDrawable(R.drawable.enptymessage);
        adapter = new CommonRcvAdapter<MyTasksItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case CompletedTaskItem.TYPE:
                        CompletedTaskItem completedTaskItem = new CompletedTaskItem();
                        completedTaskItem.setClickListener(MyTasksFragment.this);
                        return completedTaskItem;
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    default:
                        MyTasksItem MyTasksItem = new MyTasksItem();
                        MyTasksItem.setClickListener(MyTasksFragment.this);
                        return MyTasksItem;
                }
            }

            @Override
            public Object getItemType(MyTasksItemBean listItemBean) {
                return listItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_tasks;
    }

    @Override
    public void downloadFinish(ArrayList<MyTasksItemBean> data, boolean haveNextPage, int page) {
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

    @Override
    public void onItemClick(View view) {
        if (view != null) {
            int position = (int) view.getTag();
            mData.remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, mData.size());
        } else {
            ActivitySkipUtilsKt.toActivity(getContext(), CompletedTasksActivity.class);
        }

    }
}
