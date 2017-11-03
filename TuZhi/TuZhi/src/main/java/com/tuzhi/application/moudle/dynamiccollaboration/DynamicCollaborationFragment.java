package com.tuzhi.application.moudle.dynamiccollaboration;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentDynamicCollaborationBinding;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.view.LoadMoreListener;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DynamicCollaborationFragment extends MVPBaseFragment<DynamicCollaborationContract.View, DynamicCollaborationPresenter> implements DynamicCollaborationContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<DynamicCollaborationItemBean> mData=new ArrayList<>();
    private FragmentDynamicCollaborationBinding binding;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (FragmentDynamicCollaborationBinding) viewDataBinding;
        binding.rrv.setLoadListener(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.isShowRefreshView(true);
        binding.rrv.setTitle("暂无消息");
        binding.rrv.setDrawable(R.drawable.enptymessage);
        CommonRcvAdapter<DynamicCollaborationItemBean> adapter = new CommonRcvAdapter<DynamicCollaborationItemBean>(mData) {
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
                        return new DynamicCollaborationItem();
                }
            }

            @Override
            public Object getItemType(DynamicCollaborationItemBean listItemBean) {
                return listItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic_collaboration;
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
    public void downloadFinish(ArrayList<DynamicCollaborationItemBean> data, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, false);
    }

    @Override
    public void downloadError() {

    }
}
