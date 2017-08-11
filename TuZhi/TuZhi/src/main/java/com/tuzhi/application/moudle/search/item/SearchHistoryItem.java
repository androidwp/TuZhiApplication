package com.tuzhi.application.moudle.search.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.databinding.ItemSearchHistoryBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.search.bean.SearchHistoryBean;
import com.tuzhi.application.moudle.search.mvp.SearchFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wangpeng on 2017/8/1.
 */

public class SearchHistoryItem extends BaseItem<SearchHistoryBean> {

    public static final String TYPE = "SearchHistoryItem";

    private ItemSearchHistoryBinding binding;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_search_history;
    }

    @Override
    public void handleData(SearchHistoryBean searchHistoryBean, int i) {
        binding.setData(searchHistoryBean);
    }

    //点击历史记录进行搜索
    public void onSearch(String text) {
        EventBusBean busBean = new EventBusBean();
        busBean.setName(SearchFragment.NAME);
        busBean.setsContent(text);
        busBean.setEventType(0);
        EventBus.getDefault().post(busBean);
    }
}