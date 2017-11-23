package com.tuzhi.application.moudle.search.searchpage.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemSearchTaskBinding;
import com.tuzhi.application.item.GeneralItem;
import com.tuzhi.application.moudle.search.searchpage.bean.SearchResultListBean;

/**
 * @author wangpeng
 * @date 2017/8/2
 */

public class SearchTaskItem extends GeneralItem<SearchResultListBean> {

    public static final String TYPE = "SearchTaskItem";

    private ItemSearchTaskBinding binding;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_search_task;
    }

    @Override
    public void handleData(SearchResultListBean searchResultListBean, int i) {
        binding.setData(searchResultListBean);
        binding.executePendingBindings();
        binding.iv.setImageResource(R.drawable.note);
    }

    @Override
    public void onItemClick(View view, Object data) {
        super.onItemClick(view, data);
    }
}
