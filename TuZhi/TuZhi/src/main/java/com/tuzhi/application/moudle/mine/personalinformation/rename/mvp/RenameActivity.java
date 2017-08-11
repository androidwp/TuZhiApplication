package com.tuzhi.application.moudle.mine.personalinformation.rename.mvp;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityRenameBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.utils.ToastUtilsKt;


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
        name = getIntent().getStringExtra(NAME);
        binding.setActivity(this);
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
        if (!TextUtils.isEmpty(name)) {
            mPresenter.commitName(name);
        } else {
            ToastUtilsKt.toast(this, "姓名不能为空");
        }
    }

    public void back() {
        onBackPressed();
    }
}
