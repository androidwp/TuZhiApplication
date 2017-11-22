package com.tuzhi.application.moudle.repository.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemRepositoryListBinding;
import com.tuzhi.application.item.GeneralItem;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/1.
 */

public class RepositoryListItem extends GeneralItem<ItemBean> {
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
    public void handleData(ItemBean repositoryListItemBean, int i) {
        binding.setData(repositoryListItemBean);
        binding.setItem(this);
        binding.executePendingBindings();//加一行，问题解决
    }

    @Override
    public void onItemClick(View view, Object data) {
        super.onItemClick(view, data);
    }
}
