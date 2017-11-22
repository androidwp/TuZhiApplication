package com.tuzhi.application.moudle.completedtasks;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityCompletedTasksBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.knowledgelibtask.KnowledgeLibTaskFragment;
import com.tuzhi.application.moudle.mytasks.MyTasksFragment;
import com.tuzhi.application.moudle.mytasks.MyTasksItem;
import com.tuzhi.application.moudle.mytasks.MyTasksItemBean;
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

public class CompletedTasksActivity extends MVPBaseActivity<CompletedTasksContract.View, CompletedTasksPresenter> implements CompletedTasksContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, ItemClickListener {

    public static final String EVENT_REFRESH = "CompletedTasksActivity_refresh";
    /**
     * 库id
     */
    public static final String ID = "id";
    /**
     * 我的完成  还是全部完成
     */
    public static final String TYPE = "type";
    private ArrayList<MyTasksItemBean> mData = new ArrayList<>();
    private ActivityCompletedTasksBinding binding;
    private MyTasksItemBean myTestsItemBean;
    private String id;
    private String type;
    private CommonRcvAdapter<MyTasksItemBean> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_completed_tasks;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        id = getIntent().getStringExtra(ID);
        type = getIntent().getStringExtra(TYPE);
        binding = (ActivityCompletedTasksBinding) viewDataBinding;
        binding.setActivity(this);
        binding.rrv.setLoadListener(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setTitle("暂无消息");
        binding.rrv.setDrawable(R.drawable.enptymessage);
        adapter = new CommonRcvAdapter<MyTasksItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                MyTasksItem item = new MyTasksItem();
                item.setClickListener(CompletedTasksActivity.this);
                return item;
            }

            @Override
            public Object getItemType(MyTasksItemBean listItemBean) {
                return listItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String text) {
        if (TextUtils.equals(text, EVENT_REFRESH)) {
            mPresenter.downloadData(id, type, 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void downloadFinish(ArrayList<MyTasksItemBean> data, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, false);
    }

    @Override
    public void downloadError() {
        binding.rrv.isShowRefreshView(false);
    }

    @Override
    public void taskFinishSuccess() {
        mData.remove(myTestsItemBean.getPosition());
        adapter.notifyItemRemoved(myTestsItemBean.getPosition());
        adapter.notifyItemRangeChanged(myTestsItemBean.getPosition(), mData.size());
        EventBus.getDefault().post(KnowledgeLibTaskFragment.EVENT_REFRESH);
        EventBus.getDefault().post(MyTasksFragment.EVENT_REFRESH);

    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downloadData(id, type, page);
    }

    @Override
    public void onRefresh() {
        mPresenter.downloadData(id, type, 0);
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.cbFinishTask:
                myTestsItemBean = (MyTasksItemBean) view.getTag();
                if (!myTestsItemBean.isCheckStatue()) {
                    mPresenter.taskFinish(myTestsItemBean.getId());
                }
                break;
            case R.id.llTask:
                MyTasksItemBean bean = (MyTasksItemBean) view.getTag();
                ActivitySkipUtilsKt.toActivity(getContext(), TaskDetailsActivity.class, TaskDetailsActivity.ID, bean.getId());
                break;
            default:
                break;
        }
    }
}
