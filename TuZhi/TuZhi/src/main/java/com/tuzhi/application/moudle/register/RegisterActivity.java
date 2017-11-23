package com.tuzhi.application.moudle.register;


import android.databinding.ViewDataBinding;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityRegisterBinding;
import com.tuzhi.application.moudle.applyfortrialcompletion.ApplyForTrialCompletionActivity;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 * @author wangpeng
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View {

    private ActivityRegisterBinding binding;
    private RegisterBean bean;
    private CountDownTimer countDownTimer;
    private boolean canClick = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityRegisterBinding) viewDataBinding;
        bean = new RegisterBean();
        bean.setAuthCodeInfo("发送验证码");
        binding.setActivity(this);
        binding.setData(bean);
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.getData().setAuthCodeInfo(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                canClick = true;
                binding.getData().setAuthCodeInfo("重新发送");
            }
        };
    }

    public void back() {
        onBackPressed();
    }

    public void commit(RegisterBean registerBean) {
        if (TextUtils.isEmpty(registerBean.getPhoneNumber())) {
            ToastUtilsKt.toast(this, "请输入手机号");
        } else if (TextUtils.isEmpty(registerBean.getAuthCode())) {
            ToastUtilsKt.toast(this, "请输入验证码");
        } else if (TextUtils.isEmpty(registerBean.getPassword())) {
            ToastUtilsKt.toast(this, "请输入密码");
        } else if (TextUtils.isEmpty(registerBean.getName())) {
            ToastUtilsKt.toast(this, "请输入姓名");
        } else if (TextUtils.isEmpty(registerBean.getCompany())) {
            ToastUtilsKt.toast(this, "请输入企业或团队名称");
        } else {
            mPresenter.register(registerBean);
        }
    }

    public void getAuthCode(String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtilsKt.toast(this, "请输入手机号");
        } else {
            if (canClick) {
                canClick = false;
                mPresenter.getAuthCode(phone);
            }
        }
    }

    @Override
    public void commitSuccess() {
        ActivitySkipUtilsKt.toActivity(this, ApplyForTrialCompletionActivity.class);
        onBackPressed();
    }

    @Override
    public void getAuthCodeSuccess() {
        countDownTimer.start();
    }

    @Override
    public void getAuthCodeErrno() {
        canClick = true;
        bean.setAuthCodeInfo("重新发送");
    }
}
