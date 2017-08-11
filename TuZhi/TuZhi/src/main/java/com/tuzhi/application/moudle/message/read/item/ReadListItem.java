package com.tuzhi.application.moudle.message.read.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemReadUnreadBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.message.read.bean.ReadListItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;

/**
 * Created by wangpeng on 2017/8/1.
 */

public class ReadListItem extends BaseItem<ReadListItemBean> {
    public static final String TYPE = "ReadListItem";

    private ItemReadUnreadBinding binding;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_read_unread;
    }

    @Override
    public void handleData(ReadListItemBean readListItemBean, int i) {
        binding.setItem(this);
        binding.setData(readListItemBean);
        binding.executePendingBindings();
    }

    public void skip(ReadListItemBean readListItemBean) {
        Intent intent = new Intent(context, KnowledgeDetailsActivity.class);
        intent.putExtra(KnowledgeDetailsActivity.ID, readListItemBean.getArticleId());
        intent.putExtra(KnowledgeDetailsActivity.TITLE, readListItemBean.getArticleTitle());
        context.startActivity(intent);
    }
}
