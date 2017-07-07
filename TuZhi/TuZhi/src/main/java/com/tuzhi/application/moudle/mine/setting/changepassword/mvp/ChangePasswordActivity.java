package com.tuzhi.application.moudle.mine.setting.changepassword.mvp;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityChangePasswordBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.utils.ToastUtilsKt;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChangePasswordActivity extends MVPBaseActivity<ChangePasswordContract.View, ChangePasswordPresenter> implements ChangePasswordContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityChangePasswordBinding binding = (ActivityChangePasswordBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setNewText("");
        binding.setOldText("");
    }

    public void back() {
        onBackPressed();
    }

    public void commitPassword(String oldPassword, String newPassword) {
        if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword)) {
            ToastUtilsKt.toast(this, "密码不能为空");
            return;
        }
        mPresenter.commitPassword(oldPassword, newPassword);
    }

}
