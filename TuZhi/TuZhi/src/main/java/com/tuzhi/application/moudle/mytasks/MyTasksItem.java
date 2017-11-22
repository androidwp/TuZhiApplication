package com.tuzhi.application.moudle.mytasks;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.CompoundButton;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemMyTestsBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.item.BaseItem;

/**
 * @author wangpeng
 * @date 2017/10/30
 */

public class MyTasksItem extends BaseItem<MyTasksItemBean> {

    public static final String TYPE = "MyTestsItem";

    public ItemClickListener clickListener;

    private ItemMyTestsBinding binding;

    private MyTasksItemBean myTestsItemBean;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_my_tests;
    }

    @Override
    public void handleData(MyTasksItemBean myTestsItemBean, int i) {
        this.myTestsItemBean = myTestsItemBean;
        binding.setData(myTestsItemBean);
        binding.executePendingBindings();
        myTestsItemBean.setPosition(i);
    }

    public void onItemClick(View view, MyTasksItemBean myTestsItemBean) {
        if (clickListener != null) {
            view.setTag(myTestsItemBean);
            clickListener.onItemClick(view);
        }
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (clickListener != null) {
            myTestsItemBean.setCheckStatue(isChecked);
            buttonView.setTag(myTestsItemBean);
            clickListener.onItemClick(buttonView);
        }
    }
}
