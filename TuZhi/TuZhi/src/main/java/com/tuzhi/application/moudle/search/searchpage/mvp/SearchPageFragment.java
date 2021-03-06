package com.tuzhi.application.moudle.search.searchpage.mvp;


import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.databinding.FragmentSearchNoteFileSpeakBinding;
import com.tuzhi.application.item.GeneralLoadFootViewItem;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.search.mvp.SearchFragment;
import com.tuzhi.application.moudle.search.searchpage.bean.SearchResultListBean;
import com.tuzhi.application.moudle.search.searchpage.item.SearchPageNoteItem;
import com.tuzhi.application.moudle.search.searchpage.item.SearchPageSpeakItem;
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

public class SearchPageFragment extends MVPBaseFragment<SearchPageContract.View, SearchPagePresenter> implements SearchPageContract.View, LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String TYPE = "TYPE";
    public static final String NAME = "SearchPageFragment";
    public static final String TYPE_NOTE = "TYPE_NOTE";
    public static final String TYPE_FILE = "TYPE_FILE";
    public static final String TYPE_SPEAK = "TYPE_SPEAK";
    private String type;
    private ArrayList<SearchResultListBean> mData = new ArrayList<>();
    private FragmentSearchNoteFileSpeakBinding binding;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        type = getArguments().getString(TYPE);
        binding = (FragmentSearchNoteFileSpeakBinding) viewDataBinding;
        binding.rrv.setLoadListener(this);
        binding.rrv.setOnRefreshListener(this);
        binding.rrv.setTitle("搜索内容为空");
        binding.rrv.setDrawable(R.drawable.enptysearch);
        if (!TextUtils.isEmpty(SearchFragment.searchText))
            binding.rrv.isShowRefreshView(true);
        CommonRcvAdapter<SearchResultListBean> adapter = new CommonRcvAdapter<SearchResultListBean>(mData) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String itemType = (String) o;
                switch (itemType) {
                    case SearchPageNoteItem.TYPE:
                        return new SearchPageNoteItem();
                    case GeneralLoadFootViewItem.TYPE:
                        return new GeneralLoadFootViewItem();
                    default:
                        return new SearchPageSpeakItem();
                }
            }

            @Override
            public Object getItemType(SearchResultListBean listItemBean) {
                return listItemBean.getItemType();
            }
        };
        binding.rrv.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMain(EventBusBean busBean) {
        if (busBean.getName().equals(NAME)) {
            binding.rrv.isShowRefreshView(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_note_file_speak;
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
    public void downloadFinish(ArrayList<SearchResultListBean> data, boolean haveNextPage, int page) {
        binding.rrv.downLoadFinish(page, haveNextPage, mData, data, false);
    }
}
