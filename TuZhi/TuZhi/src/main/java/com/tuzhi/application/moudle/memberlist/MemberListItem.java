package com.tuzhi.application.moudle.memberlist;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.databinding.ItemMemberListBinding;
import com.tuzhi.application.item.GeneralItem;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author wangpeng
 * @date 2017/11/13
 */

public class MemberListItem extends GeneralItem<ItemBean> {

    public static final String TYPE="MemberListItem";

    private ItemMemberListBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_member_list;
    }

    @Override
    public void handleData(ItemBean itemBean, int i) {
        binding.setData(itemBean);
    }

    @Override
    public void onItemClick(View view, Object data) {
        super.onItemClick(view, data);
    }

}
