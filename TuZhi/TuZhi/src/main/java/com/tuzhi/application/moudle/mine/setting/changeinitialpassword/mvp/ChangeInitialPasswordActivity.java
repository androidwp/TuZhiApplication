package com.tuzhi.application.moudle.mine.setting.changeinitialpassword.mvp;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;

import com.tuzhi.application.MainActivity;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityChangeInitialPasswordBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChangeInitialPasswordActivity extends MVPBaseActivity<ChangeInitialPasswordContract.View, ChangeInitialPasswordPresenter> implements ChangeInitialPasswordContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_initial_password;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityChangeInitialPasswordBinding binding = (ActivityChangeInitialPasswordBinding) viewDataBinding;
        binding.setActivity(this);
    }

    public void commitPassword(String password, String againPassword) {
        if (TextUtils.isEmpty(password)) {
            ToastUtilsKt.toast(this, "请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtilsKt.toast(this, "请确认新密码");
            return;
        }
        if (!TextUtils.equals(password, againPassword)) {
            ToastUtilsKt.toast(this, "请确保两次密码相同");
            return;
        }
        mPresenter.commitPassword(password, againPassword);
    }

    @Override
    public void changeSuccess() {
        ActivitySkipUtilsKt.toActivity(this, MainActivity.class);
        ToastUtilsKt.toast(this, "修改成功");
        onBackPressed();
    }
}
