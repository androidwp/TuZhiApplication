package com.tuzhi.application.moudle.search.searchpage.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemSearchPageSpeakBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.moudle.search.searchpage.bean.SearchResultListBean;
import com.tuzhi.application.utils.ToastUtilsKt;

/**
 *
 * @author wangpeng
 * @date 2017/8/2
 */

public class SearchPageSpeakItem extends BaseItem<SearchResultListBean> {

    public static final String TYPE = "SearchPageSpeakItem";
    private ItemSearchPageSpeakBinding binding;


    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_search_page_speak;
    }

    @Override
    public void handleData(SearchResultListBean searchResultListBean, int i) {
        binding.setData(searchResultListBean);
        binding.executePendingBindings();
    }

    public void skip(SearchResultListBean searchResultListBean) {
        if (searchResultListBean.isLimit()) {
            Intent intent = new Intent(context, KnowledgeDetailsActivity.class);
            intent.putExtra(KnowledgeDetailsActivity.ID, searchResultListBean.getAid());
            context.startActivity(intent);
        } else {
            ToastUtilsKt.toast(context, "无查看权限");
        }
    }
}
