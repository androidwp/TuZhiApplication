package com.tuzhi.application.moudle.repository.knowledgachannel.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemCreateChannelBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelItemBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/11/6.
 */

public class CreateChannelItem extends BaseItem<KnowledgeChannelItemBean> {
    public static final String TYPE = "CreateChannelItem";
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void bindView(@NotNull View view) {
        ItemCreateChannelBinding binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_create_channel;
    }

    @Override
    public void handleData(KnowledgeChannelItemBean knowledgeChannelItemBean, int i) {

    }

    public void createChannel() {
        if (clickListener != null) {
            clickListener.onItemClick(null);
        }
    }

}
