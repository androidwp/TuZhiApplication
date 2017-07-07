package com.tuzhi.application.moudle.mine.personalinformation.rename.mvp;


import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityRenameBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RenameActivity extends MVPBaseActivity<RenameContract.View, RenamePresenter> implements RenameContract.View {

    public static final String NAME="name";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rename;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityRenameBinding binding = (ActivityRenameBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setName(getIntent().getStringExtra(NAME));
    }

    public void commitName(String name) {
        mPresenter.commitName(name);
    }

    public void back() {
        onBackPressed();
    }
}
