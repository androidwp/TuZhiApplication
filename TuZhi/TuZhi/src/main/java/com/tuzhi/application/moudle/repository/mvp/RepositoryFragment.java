package com.tuzhi.application.moudle.repository.mvp;


import android.Manifest;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.databinding.ActivityRepositoryBinding;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.repository.bean.RepositoryListItemBean;
import com.tuzhi.application.moudle.repository.crepository.mvp.CrepositoryActivity;
import com.tuzhi.application.moudle.repository.item.RepositoryListItem;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.UserInfoUtils;
import com.tuzhi.application.view.LoadMoreListener;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * 知识库页面
 */

public class RepositoryFragment extends MVPBaseFragment<RepositoryContract.View, RepositoryPresenter> implements RepositoryContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreListener {

    public static final String MESSAGE = "RepositoryActivity_refresh";
    private ActivityRepositoryBinding binding;
    private ArrayList<RepositoryListItemBean> mData = new ArrayList<>();


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
        if (TextUtils.equals(event, MESSAGE))
            onRefresh();
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        AndPermission.with(this).requestCode(100).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE).start();
        EventBus.getDefault().register(this);
        binding = (ActivityRepositoryBinding) viewDataBinding;
        binding.setActivity(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setLoadListener(this);
        binding.rrv.setTitle("知识库空空如也");
        binding.rrv.setInfo("点击上方的\"+\"号，创建知识库");
        HttpInitBean userInfo = UserInfoUtils.getUserInfo(getContext());
        binding.setData(userInfo);
        CommonRcvAdapter<RepositoryListItemBean> adapter = new CommonRcvAdapter<RepositoryListItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    default:
                        return new RepositoryListItem();
                }
            }

            @Override
            public Object getItemType(RepositoryListItemBean repositoryListItemBean) {
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
    public void downLoadFinish(ArrayList<RepositoryListItemBean> data, boolean haveNextPage, int page) {
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


    public void skipCreateRepositoryActivity() {
        Intent intent = new Intent(getContext(), CrepositoryActivity.class);
        intent.putExtra(CrepositoryActivity.TYPE, CrepositoryActivity.REPOSITORY);
        startActivityForResult(intent, ConstantKt.getCREATE_CODE());
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


}