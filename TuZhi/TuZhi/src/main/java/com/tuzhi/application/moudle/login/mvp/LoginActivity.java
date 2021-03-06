package com.tuzhi.application.moudle.login.mvp;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;

import com.tuzhi.application.MainActivity;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityLoginBinding;
import com.tuzhi.application.moudle.applyfortrial.ApplyForTrialActivity;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.login.bean.User;
import com.tuzhi.application.moudle.login.forgetpassword.mvp.ForgetPasswordActivity;
import com.tuzhi.application.moudle.mine.setting.changeinitialpassword.mvp.ChangeInitialPasswordActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityLoginBinding binding = (ActivityLoginBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setUser(new User("", ""));
    }


    public void commit(User user) {
        if (TextUtils.isEmpty(user.getUserName())) {
            ToastUtilsKt.toast(this, "请输入手机号或邮箱");
        } else if (TextUtils.isEmpty(user.getPassWord())) {
            ToastUtilsKt.toast(this, "请输入密码");
        } else {
            mPresenter.commitUser(user);
        }
    }

    public void forgetPassword() {
        ActivitySkipUtilsKt.toActivity(this, ForgetPasswordActivity.class);
    }

    public void applyForTrial() {
        ActivitySkipUtilsKt.toActivity(this, ApplyForTrialActivity.class);
    }


    @Override
    public void skip() {
        ActivitySkipUtilsKt.toActivity(this, MainActivity.class);
        onBackPressed();
    }

    @Override
    public void skipChangeInitPassword() {
        ActivitySkipUtilsKt.toActivity(this, ChangeInitialPasswordActivity.class);
        onBackPressed();
    }
}
