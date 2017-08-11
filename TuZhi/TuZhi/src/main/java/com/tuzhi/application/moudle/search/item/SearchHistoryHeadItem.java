package com.tuzhi.application.moudle.search.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.databinding.ItemSearchHistoryHeadBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.search.bean.SearchHistoryBean;
import com.tuzhi.application.moudle.search.mvp.SearchFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wangpeng on 2017/8/1.
 */

public class SearchHistoryHeadItem extends BaseItem<SearchHistoryBean> {

    public static final String TYPE = "SearchHistoryHeadItem";
    private ItemSearchHistoryHeadBinding binding;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_search_history_head;
    }

    @Override
    public void handleData(SearchHistoryBean searchHistoryBean, int i) {
        binding.setData(searchHistoryBean);
    }

    //通知删除消息历史
    public void onDeleteHistory() {
        EventBusBean eventBusBean = new EventBusBean();
        eventBusBean.setName(SearchFragment.NAME);
        eventBusBean.setEventType(1);
        EventBus.getDefault().post(eventBusBean);
    }


}
