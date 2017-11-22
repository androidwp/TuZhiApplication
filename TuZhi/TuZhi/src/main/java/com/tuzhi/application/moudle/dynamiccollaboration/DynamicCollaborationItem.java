package com.tuzhi.application.moudle.dynamiccollaboration;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemDynamicCollaborationBinding;
import com.tuzhi.application.item.GeneralItem;

/**
 * @author wangpeng
 * @date 2017/10/26
 */

public class DynamicCollaborationItem extends GeneralItem<DynamicCollaborationItemBean> {

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
        binding.setItem(this);
        binding.executePendingBindings();
    }

    @Override
    public void onItemClick(View view, Object object) {
        super.onItemClick(view, object);
    }
}
