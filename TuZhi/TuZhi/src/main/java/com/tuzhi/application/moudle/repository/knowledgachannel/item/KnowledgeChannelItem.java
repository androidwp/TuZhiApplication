package com.tuzhi.application.moudle.repository.knowledgachannel.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeChannelBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp.EnterpriseKnowledgeActivity;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelItemBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/1.
 */

public class KnowledgeChannelItem extends BaseItem<KnowledgeChannelItemBean> {
    public static final String TYPE = "KnowledgeChannelItem";

    private ItemKnowledgeChannelBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_channel;
    }

    @Override
    public void handleData(KnowledgeChannelItemBean knowledgeChannelItemBean, int i) {
        binding.setData(knowledgeChannelItemBean);
        binding.setItem(this);
        binding.executePendingBindings();//加一行，问题解决
    }

    public void skip(String klId, String kcId, String title) {
        Intent intent = new Intent(context, EnterpriseKnowledgeActivity.class);
        intent.putExtra(EnterpriseKnowledgeActivity.KLID, klId);
        intent.putExtra(EnterpriseKnowledgeActivity.KCID, kcId);
        intent.putExtra(EnterpriseKnowledgeActivity.TITLE, title);
        context.startActivity(intent);
    }

}
