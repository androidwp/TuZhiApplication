package com.tuzhi.application.moudle.repository.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemRepositoryListBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.bean.RepositoryListItemBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/1.
 */

public class RepositoryListItem extends BaseItem<RepositoryListItemBean> {
    public static final String TYPE = "RepositoryListItem";

    private ItemRepositoryListBinding binding;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

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

    public void skip(View view, RepositoryListItemBean bean) {
        if (itemClickListener!=null){
            view.setTag(bean);
            itemClickListener.onItemClick(view);
        }
    }
}
