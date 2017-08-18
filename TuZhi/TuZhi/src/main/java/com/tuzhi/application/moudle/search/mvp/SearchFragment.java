package com.tuzhi.application.moudle.search.mvp;


import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.databinding.FragmentSearchBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.search.bean.SearchHistoryBean;
import com.tuzhi.application.moudle.search.item.SearchHistoryHeadItem;
import com.tuzhi.application.moudle.search.item.SearchHistoryItem;
import com.tuzhi.application.moudle.search.searchpage.mvp.SearchPageFragment;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * MVPPlugin
 */

public class SearchFragment extends MVPBaseFragment<SearchContract.View, SearchPresenter> implements SearchContract.View, TextWatcher, TextView.OnEditorActionListener {
    public static final String NAME = "SearchFragment";
    private FragmentSearchBinding binding;
    private List<SearchHistoryBean> searchHistoryBeanList = new ArrayList<>();
    private CommonRcvAdapter<SearchHistoryBean> adapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    public static String searchText;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        binding = (FragmentSearchBinding) viewDataBinding;
        binding.setFragment(this);
        binding.setText("");
        binding.et.addTextChangedListener(this);
        binding.et.setOnEditorActionListener(this);
        initRV();
        initVP();
        dealHistory();
    }

    /**
     * 接收点击历史记录的消息
     *
     * @param busBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMain(final EventBusBean busBean) {
        if (TextUtils.equals(busBean.getName(), NAME)) {
            //事件等于0是搜索，1为删除缓存
            if (busBean.getEventType() == 0) {
                binding.et.setText(busBean.getsContent());
                search(busBean.getsContent());
                binding.et.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.et.setSelection(busBean.getsContent().length());
                    }
                });
            } else {
                SharedPreferencesUtilsKt.deleteSearchHistoryCache(getContext());
                dealHistory();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initVP() {
        titleList.add("笔记");
        titleList.add("文件");
        titleList.add("发言");
        fragmentList.add(getFragment(SearchPageFragment.TYPE_NOTE));
        fragmentList.add(getFragment(SearchPageFragment.TYPE_FILE));
        fragmentList.add(getFragment(SearchPageFragment.TYPE_SPEAK));
        binding.vp.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        binding.stl.setViewPager(binding.vp);
    }

    private void initRV() {
        adapter = new CommonRcvAdapter<SearchHistoryBean>(searchHistoryBeanList) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                String type = (String) o;
                switch (type) {
                    case SearchHistoryHeadItem.TYPE:
                        return new SearchHistoryHeadItem();
                    default:
                        return new SearchHistoryItem();
                }
            }

            @Override
            public Object getItemType(SearchHistoryBean searchHistoryBean) {
                return searchHistoryBean.getItemType();
            }
        };
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(adapter);
    }

    private Fragment getFragment(String type) {
        SearchPageFragment readFragment = new SearchPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SearchPageFragment.TYPE, type);
        readFragment.setArguments(bundle);
        return readFragment;
    }

    private void dealHistory() {
        //处理历史消息
        searchHistoryBeanList.clear();
        List<String> searchHistoryCache = SharedPreferencesUtilsKt.getSearchHistoryCache(getContext());
        List<String> historySplit = new ArrayList<>();
        if (searchHistoryCache.size() > 5) {
            historySplit.addAll(searchHistoryCache.subList(0, 5));
        } else {
            historySplit.addAll(searchHistoryCache);
        }
        SearchHistoryBean searchHistoryHeadBean = new SearchHistoryBean(SearchHistoryHeadItem.TYPE);
        searchHistoryHeadBean.setHistoryNumber(historySplit.size());
        searchHistoryBeanList.add(searchHistoryHeadBean);
        for (String s : historySplit) {
            if (!TextUtils.isEmpty(s)) {
                SearchHistoryBean searchHistoryBean = new SearchHistoryBean(SearchHistoryItem.TYPE);
                searchHistoryBean.setSearchContent(s);
                searchHistoryBeanList.add(searchHistoryBean);
            }
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    public void search(String text) {
        if (!TextUtils.isEmpty(text)) {
            SharedPreferencesUtilsKt.saveSearchHistoryCache(getContext(), text);
            searchText = text;
            binding.rv.setVisibility(View.GONE);
            EventBusBean busBean = new EventBusBean();
            busBean.setName(SearchPageFragment.NAME);
            busBean.setsContent(text);
            EventBus.getDefault().post(busBean);
            dealHistory();
        } else {
            ToastUtilsKt.toast(getContext(), "请输入搜索内容");
        }
    }

    public void deleteSearchText() {
        binding.et.setText("");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 0) {
            binding.rv.setVisibility(View.VISIBLE);
            binding.ivDelete.setVisibility(View.GONE);
        } else {
            binding.ivDelete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            search(v.getText().toString());
        }
        return false;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }

}
