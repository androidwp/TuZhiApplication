package com.tuzhi.application.moudle.message.read.mvp;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentReadBinding;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.message.read.bean.ReadListItemBean;
import com.tuzhi.application.moudle.message.read.item.ReadListItem;
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
 */

public class ReadFragment extends MVPBaseFragment<ReadContract.View, ReadPresenter> implements ReadContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String TYPE = "TYPE";
    public static final String TYPE_READ = "TYPE_READ";
    public static final String TYPE_UNREAD = "TYPE_UNREAD";
    public static final String REFRESH = "ReadFragment_refresh";

    private String type;
    private ArrayList<ReadListItemBean> mData = new ArrayList<>();
    private FragmentReadBinding binding;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        type = getArguments().getString(TYPE);
        binding = (FragmentReadBinding) viewDataBinding;
        binding.rrv.setLoadListener(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setTitle("暂无消息");
        binding.rrv.setDrawable(R.drawable.enptymessage);
        CommonRcvAdapter<ReadListItemBean> adapter = new CommonRcvAdapter<ReadListItemBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case GeneralLoadFootViewItem.TYPE:
                        GeneralLoadFootViewItem generalLoadFootViewItem = new GeneralLoadFootViewItem();
                        generalLoadFootViewItem.setColorId(R.color.colorWhite);
                        return generalLoadFootViewItem;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String text) {
        if (TextUtils.equals(text, REFRESH)) {
            mPresenter.downloadData(type, 0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
