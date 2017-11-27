package com.tuzhi.application.moudle.mytasks;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityMyTasksBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.GeneralEmptyFootViewItem;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.completedtasks.CompletedTasksActivity;
import com.tuzhi.application.moudle.taskdetails.TaskDetailsActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
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
 *
 * @author wangpeng
 */

public class MyTasksFragment extends MVPBaseFragment<MyTasksContract.View, MyTasksPresenter> implements MyTasksContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, ItemClickListener {

    public static final String EVENT_REFRESH = "MyTasksFragment_refresh";

    private ArrayList<MyTasksItemBean> mData = new ArrayList<>();
    private ActivityMyTasksBinding binding;
    private CommonRcvAdapter<MyTasksItemBean> adapter;
    private MyTasksItemBean myTestsItemBean;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
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
                    case GeneralEmptyFootViewItem.TYPE:
                        return new GeneralEmptyFootViewItem();
                    default:
                        MyTasksItem myTasksItem = new MyTasksItem();
                        myTasksItem.setClickListener(MyTasksFragment.this);
                        return myTasksItem;
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String text) {
        if (TextUtils.equals(text, EVENT_REFRESH)) {
            mPresenter.downloadData(0);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_tasks;
    }

    @Override
    public void downloadFinish(ArrayList<MyTasksItemBean> data, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, true);
    }


    @Override
    public void downloadError() {

    }

    @Override
    public void taskFinishSuccess() {
        mData.remove(myTestsItemBean.getPosition());
        adapter.notifyItemRemoved(myTestsItemBean.getPosition());
        adapter.notifyItemRangeChanged(myTestsItemBean.getPosition(), mData.size());
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
        switch (view.getId()) {
            case R.id.llTask:
                MyTasksItemBean bean = (MyTasksItemBean) view.getTag();
                ActivitySkipUtilsKt.toActivity(getContext(), TaskDetailsActivity.class, TaskDetailsActivity.ID, bean.getId());
                break;
            case R.id.cbFinishTask:
                myTestsItemBean = (MyTasksItemBean) view.getTag();
                if (myTestsItemBean.isCheckStatue()) {
                    mPresenter.taskFinish(myTestsItemBean.getId());
                }
                break;
            case R.id.llShipFinishTasks:
                Intent intent = new Intent(getContext(), CompletedTasksActivity.class);
                intent.putExtra(CompletedTasksActivity.ID, "0");
                intent.putExtra(CompletedTasksActivity.TYPE, "0");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
