package com.tuzhi.application.moudle.repository.knowledgachannel.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeChannelBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelItemBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/1.
 */

public class KnowledgeChannelItem extends BaseItem<KnowledgeChannelItemBean> {
    public static final String TYPE = "KnowledgeChannelItem";

    private ItemKnowledgeChannelBinding binding;

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

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

    public void skip(View view, KnowledgeChannelItemBean knowledgeChannelItemBean) {
        if (clickListener!=null){
            view.setTag(knowledgeChannelItemBean);
            clickListener.onItemClick(view);
        }
    }
}
