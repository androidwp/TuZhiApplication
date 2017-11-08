package com.tuzhi.application.moudle.knowledgelibtask;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.view.LoadMoreListener;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KnowledgeLibTaskFragment extends MVPBaseFragment<KnowledgeLibTaskContract.View, KnowledgeLibTaskPresenter> implements KnowledgeLibTaskContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, ItemClickListener {

    private ArrayList<MyTasksItemBean> mData = new ArrayList<>();
    private CommonRcvAdapter<MyTasksItemBean> adapter;
    private FragmentKnowledgeLibTaskBinding binding;
    private String id;
    private String title;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        id = getArguments().getString(KnowledgeLibActivity.ID);
        title = getArguments().getString(KnowledgeLibActivity.TITLE);
        binding = (FragmentKnowledgeLibTaskBinding) viewDataBinding;
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
                    case KnowledgeLibTaskBarItem.TYPE:
                        KnowledgeLibTaskBarItem item = new KnowledgeLibTaskBarItem();
                        item.setClickListener(KnowledgeLibTaskFragment.this);
                        return item;
                    default:
                        MyTasksItem MyTasksItem = new MyTasksItem();
                        MyTasksItem.setClickListener(KnowledgeLibTaskFragment.this);
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
        return R.layout.fragment_knowledge_lib_task;
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
        switch (view.getId()) {
            case R.id.cbFinishTask:
                int position = (int) view.getTag();
                mData.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, mData.size());
                break;
            case R.id.llShipFinishTasks:
                ActivitySkipUtilsKt.toActivity(getContext(), CompletedTasksActivity.class);
                break;
            case R.id.tvTasksChange:

                break;
            case R.id.tvCreateTask:
                Intent intent = new Intent(getContext(), CreateTaskActivity.class);
                intent.putExtra(CreateTaskActivity.TYPE, CreateTaskActivity.TYPE_LIB);
                intent.putExtra(CreateTaskActivity.LIB_ID, id);
                intent.putExtra(CreateTaskActivity.LIB_NAME, title);
                startActivity(intent);
                break;
        }
    }
}
