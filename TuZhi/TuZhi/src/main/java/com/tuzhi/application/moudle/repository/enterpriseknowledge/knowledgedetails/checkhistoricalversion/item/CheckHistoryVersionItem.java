package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemCheckHistoryVersionBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.bean.CheckHistoryVersionItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.recoverhistoryversion.mvp.RecoverHistoryVersionActivity;

/**
 * Created by wangpeng on 2017/8/10.
 */

public class CheckHistoryVersionItem extends BaseItem<CheckHistoryVersionItemBean> {
    public static final String TYPE = "CheckHistoryVersionItem";

    private ItemCheckHistoryVersionBinding binding;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_check_history_version;
    }

    @Override
    public void handleData(CheckHistoryVersionItemBean checkHistoryVersionItemBean, int i) {
        binding.setData(checkHistoryVersionItemBean);
        binding.executePendingBindings();
    }

    public void skip(CheckHistoryVersionItemBean checkHistoryVersionItemBean) {
        Intent intent = new Intent(context, RecoverHistoryVersionActivity.class);
        intent.putExtra(RecoverHistoryVersionActivity.ID, checkHistoryVersionItemBean.getId());
        intent.putExtra(RecoverHistoryVersionActivity.AID, checkHistoryVersionItemBean.getAid());
        context.startActivity(intent);
    }
}
