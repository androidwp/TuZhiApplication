package com.tuzhi.application.moudle.knowledgelibtask;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentKnowledgeLibTaskBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.completedtasks.CompletedTasksActivity;
import com.tuzhi.application.moudle.createtask.CreateTaskActivity;
import com.tuzhi.application.moudle.knowledgelib.KnowledgeLibActivity;
import com.tuzhi.application.moudle.mytasks.CompletedTaskItem;
import com.tuzhi.application.moudle.mytasks.MyTasksItem;
import com.tuzhi.application.moudle.mytasks.MyTasksItemBean;
import com.tuzhi.application.moudle.taskdetails.TaskDetailsActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
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

public class KnowledgeLibTaskFragment extends MVPBaseFragment<KnowledgeLibTaskContract.View, KnowledgeLibTaskPresenter> implements KnowledgeLibTaskContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, ItemClickListener {
    /**
     * 用来通知刷新的字段
     */
    public static final String ID = "ID";
    public static final String TITLE = "TITLE";
    public static final String EVENT_REFRESH = "KnowledgeLibTaskFragment_refresh";
    private ArrayList<MyTasksItemBean> mData = new ArrayList<>();
    private CommonRcvAdapter<MyTasksItemBean> adapter;
    private FragmentKnowledgeLibTaskBinding binding;
    private String id;
    private String title;
    private String flagType;
    private MyTasksItemBean myTestsItemBean;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        flagType = SharedPreferencesUtilsKt.getLongCache(getContext(), ConstantKt.getKeyTaskType(), ConstantKt.getValue_false());
        id = getArguments().getString(KnowledgeLibActivity.ID);
        title = getArguments().getString(KnowledgeLibActivity.TITLE);
        binding = (FragmentKnowledgeLibTaskBinding) viewDataBinding;
        binding.setFragment(this);
        binding.setFlag(TextUtils.equals(ConstantKt.getValue_true(), flagType));
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
                        completedTaskItem.setClickListener(KnowledgeLibTaskFragment.this);
                        return completedTaskItem;
                    default:
                        MyTasksItem myTasksItem = new MyTasksItem();
                        myTasksItem.setClickListener(KnowledgeLibTaskFragment.this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String event) {
        if (TextUtils.equals(event, EVENT_REFRESH)) {
            mPresenter.downloadData(id, flagType, 0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_lib_task;
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
    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downloadData(id, flagType, page);
    }

    @Override
    public void onRefresh() {
        mPresenter.downloadData(id, flagType, 0);
    }

    public void taskChange(boolean flag) {
        binding.setFlag(!flag);
        flagType = !flag ? ConstantKt.getValue_true() : ConstantKt.getValue_false();
        SharedPreferencesUtilsKt.saveLongCache(getContext(), ConstantKt.getKeyTaskType(), flagType);
        mPresenter.downloadData(id, flagType, 0);
    }

    public void shipCreateTask() {
        Intent intent = new Intent(getContext(), CreateTaskActivity.class);
        intent.putExtra(CreateTaskActivity.TYPE, CreateTaskActivity.TYPE_LIB);
        intent.putExtra(CreateTaskActivity.LIB_ID, id);
        intent.putExtra(CreateTaskActivity.LIB_NAME, title);
        startActivity(intent);
    }


    @Override
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.cbFinishTask:
                myTestsItemBean = (MyTasksItemBean) view.getTag();
                if (myTestsItemBean.isCheckStatue()) {
                    mPresenter.taskFinish(myTestsItemBean.getId());
                }
                break;
            case R.id.llShipFinishTasks:
                Intent intent = new Intent(getContext(), CompletedTasksActivity.class);
                intent.putExtra(CompletedTasksActivity.ID, id);
                intent.putExtra(CompletedTasksActivity.TYPE, flagType);
                startActivity(intent);
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
