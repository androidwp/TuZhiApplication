package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeDetailsListArticleBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/9.
 */

public class KnowledgeDetailsArticleItem extends BaseItem<KnowledgeDetailsListBean> {

    public static final String TYPE = "KnowledgeDetailsArticleItem";

    private ItemKnowledgeDetailsListArticleBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_details_list_article;
    }

    @Override
    public void handleData(KnowledgeDetailsListBean knowledgeDetailsListBean, int i) {
        if (!TextUtils.isEmpty(knowledgeDetailsListBean.getContent())) {
            binding.wv.setVisibility(View.VISIBLE);
            binding.wv.loadDataWithBaseURL(null, knowledgeDetailsListBean.getContent(), "text/html", "utf-8", null);
        } else {
            binding.wv.setVisibility(View.GONE);
        }

    }
}
