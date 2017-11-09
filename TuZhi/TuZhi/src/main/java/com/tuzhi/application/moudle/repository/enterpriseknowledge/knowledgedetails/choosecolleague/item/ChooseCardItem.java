package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemChooseCardBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/11/9.
 */

public class ChooseCardItem extends BaseItem<ChooseColleagueItemBean> {

    public static final String TYPE = "ChooseCardItem";

    private ItemChooseCardBinding binding;

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
        return R.layout.item_choose_card;
    }

    @Override
    public void handleData(ChooseColleagueItemBean knowledgeChannelItemBean, int i) {
        binding.setData(knowledgeChannelItemBean);
        binding.setItem(this);
        binding.executePendingBindings();//加一行，问题解决
    }

    public void skip(View view, ChooseColleagueItemBean colleagueItemBean) {
        if (clickListener!=null){
            view.setTag(colleagueItemBean);
            clickListener.onItemClick(view);
        }
    }
}
