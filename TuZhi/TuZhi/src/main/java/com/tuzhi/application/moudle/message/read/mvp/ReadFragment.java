package com.tuzhi.application.moudle.message.read.mvp;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentReadBinding;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.message.read.bean.ReadListItemBean;
import com.tuzhi.application.moudle.message.read.item.ReadListItem;
import com.tuzhi.application.view.LoadMoreListener;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ReadFragment extends MVPBaseFragment<ReadContract.View, ReadPresenter> implements ReadContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String TYPE = "TYPE";
    public static final String TYPE_READ = "TYPE_READ";
    public static final String TYPE_UNREAD = "TYPE_UNREAD";
    private String type;
    private ArrayList<ReadListItemBean> mData = new ArrayList<>();
    private FragmentReadBinding binding;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        type = getArguments().getString(TYPE);
        binding = (FragmentReadBinding) viewDataBinding;
        binding.rrv.setLoadListener(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isShowRefreshView(true);
        CommonRcvAdapter<ReadListItemBean> adapter = new CommonRcvAdapter<ReadListItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    default:
                        return new ReadListItem();
                }
            }

            @Override
            public Object getItemType(ReadListItemBean listItemBean) {
                return listItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read;
    }

    @Override
    public void loadMoreListener(int page) {
        mPresenter.downloadData(type, page);
    }

    @Override
    public void onRefresh() {
        mPresenter.downloadData(type, 0);
    }

    @Override
    public void downloadFinish(ArrayList<ReadListItemBean> data, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, false);
    }

    @Override
    public void downloadError() {
        binding.rrv.isShowRefreshView(false);
    }
}
