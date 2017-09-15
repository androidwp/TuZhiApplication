package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.item;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemChooseColleagueBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;

/**
 * Created by wangpeng on 2017/8/10.
 */

public class ChooseColleagueItem extends BaseItem<ChooseColleagueItemBean> {

    public static final String TYPE = "ChooseColleagueItem";

    private ItemClickListener clickListener;

    private ItemChooseColleagueBinding binding;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_choose_colleague;
    }

    @Override
    public void handleData(ChooseColleagueItemBean checkHistoryVersionItemBean, int i) {
        binding.setData(checkHistoryVersionItemBean);
        binding.executePendingBindings();
    }

    public void itemClick(View view, ChooseColleagueItemBean ChooseColleagueItemBean) {
        if (clickListener != null) {
            view.setTag(ChooseColleagueItemBean);
            clickListener.onItemClick(view);
        }
    }
}
