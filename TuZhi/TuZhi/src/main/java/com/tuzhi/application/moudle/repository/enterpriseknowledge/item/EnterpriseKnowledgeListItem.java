package com.tuzhi.application.moudle.repository.enterpriseknowledge.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemEnterpriseKnowledgeListBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.bean.EnterpriseKnowledgeListItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/2.
 */

public class EnterpriseKnowledgeListItem extends BaseItem<EnterpriseKnowledgeListItemBean> {

    public static final String TYPE = "EnterpriseKnowledgeListItem";
    private ItemEnterpriseKnowledgeListBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_enterprise_knowledge_list;
    }

    @Override
    public void handleData(EnterpriseKnowledgeListItemBean enterpriseKnowledgeListItemBean, int i) {
        binding.setData(enterpriseKnowledgeListItemBean);
        binding.setItem(this);
        binding.executePendingBindings();
    }

    public void skipKnowledgeDetailsActivity(String title,String id) {
        Intent intent=new Intent(context, KnowledgeDetailsActivity.class);
        intent.putExtra(KnowledgeDetailsActivity.ID,id);
        intent.putExtra(KnowledgeDetailsActivity.TITLE,title);
        context.startActivity(intent);

    }
}
