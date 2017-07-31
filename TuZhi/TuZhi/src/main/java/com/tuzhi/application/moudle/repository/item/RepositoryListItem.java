package com.tuzhi.application.moudle.repository.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemRepositoryListBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.bean.RepositoryListItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp.EnterpriseKnowledgeActivity;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/1.
 */

public class RepositoryListItem extends BaseItem<RepositoryListItemBean> {
    public static final String TYPE = "RepositoryListItem";

    private ItemRepositoryListBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_repository_list;
    }

    @Override
    public void handleData(RepositoryListItemBean repositoryListItemBean, int i) {
        binding.setData(repositoryListItemBean);
        binding.setItem(this);
        binding.executePendingBindings();//加一行，问题解决
    }

    public void skip(String id, String title) {
        Intent intent = new Intent(context, EnterpriseKnowledgeActivity.class);
        intent.putExtra(EnterpriseKnowledgeActivity.ID, id);
        intent.putExtra(EnterpriseKnowledgeActivity.TITLE, title);
        context.startActivity(intent);
    }

}
