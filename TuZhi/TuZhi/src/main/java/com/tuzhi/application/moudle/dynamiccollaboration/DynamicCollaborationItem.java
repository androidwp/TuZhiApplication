package com.tuzhi.application.moudle.dynamiccollaboration;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemDynamicCollaborationBinding;
import com.tuzhi.application.item.BaseItem;

/**
 * Created by wangpeng on 2017/10/26.
 */

public class DynamicCollaborationItem extends BaseItem<DynamicCollaborationItemBean> {

    public static final String TYPE = "DynamicCollaborationItem";
    private ItemDynamicCollaborationBinding binding;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_dynamic_collaboration;
    }

    @Override
    public void handleData(DynamicCollaborationItemBean dynamicCollaborationItemBean, int i) {
        binding.setData(dynamicCollaborationItemBean);
        binding.executePendingBindings();
    }
}
