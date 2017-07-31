package com.tuzhi.application.moudle.message.read;


import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentReadBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.view.LoadMoreListener;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ReadFragment extends MVPBaseFragment<ReadContract.View, ReadPresenter> implements ReadContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        FragmentReadBinding binding = (FragmentReadBinding) viewDataBinding;
        binding.rrl.setLoadListener(this);
        binding.rrl.setOnRefreshListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read;
    }

    @Override
    public void loadMoreListener(int page) {

    }

    @Override
    public void onRefresh() {

    }
}
