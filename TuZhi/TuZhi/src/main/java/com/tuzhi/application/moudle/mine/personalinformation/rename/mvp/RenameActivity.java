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

    public static final String NAME = "name";
    private ActivityRenameBinding binding;
    private String name;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rename;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityRenameBinding) viewDataBinding;
        binding.setActivity(this);
        name = getIntent().getStringExtra(NAME);
        binding.setName(name);
        binding.et.post(new Runnable() {
            @Override
            public void run() {
                binding.et.post(new Runnable() {
                    @Override
                    public void run() {
                        if (name.length() > 20) {
                            binding.et.setSelection(20);
                        } else {
                            binding.et.setSelection(name.length());
                        }
                    }
                });
            }
        });
    }

    public void commitName(String name) {
        mPresenter.commitName(name);
    }

    public void back() {
        onBackPressed();
    }
}
