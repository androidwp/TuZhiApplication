package com.tuzhi.application.moudle.login.forgetpassword.mvp;


import android.databinding.ViewDataBinding;
import android.os.CountDownTimer;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityForgetPasswordBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.login.forgetpassword.bean.ForgetPasswordBean;
import com.tuzhi.application.utils.ToastUtilsKt;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ForgetPasswordActivity extends MVPBaseActivity<ForgetPasswordContract.View, ForgetPasswordPresenter> implements ForgetPasswordContract.View {

    private CountDownTimer countDownTimer;
    private boolean canClick = true;
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        final ActivityForgetPasswordBinding binding = (ActivityForgetPasswordBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setData(new ForgetPasswordBean());
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

    public void changePhoneOrEmail(ForgetPasswordBean bean) {
        bean.setPhoneStatus(!bean.isPhoneStatus());
    }

    public void getAuthCode(ForgetPasswordBean bean) {
        if (canClick) {
            canClick = false;
            if (bean.isPhoneStatus())
                mPresenter.getVerificationCode(PHONE, bean.getPhoneOrEmailText());
            else
                mPresenter.getVerificationCode(EMAIL, bean.getPhoneOrEmailText());

        }
    }

    public void commit(ForgetPasswordBean bean) {
        if (bean.isPhoneStatus()){
            mPresenter.changePassword(PHONE,bean.getPhoneOrEmailText(),bean.getAuthCodeText(),bean.getPasswordText());
        }else{
            mPresenter.changePassword(EMAIL,bean.getPhoneOrEmailText(),bean.getAuthCodeText(),bean.getPasswordText());
        }
    }

    @Override
    public void getVerificationCodeSuccess(String phoneOrEmail) {
        countDownTimer.start();
    }

    @Override
    public void getVerificationCodeError() {
        canClick = true;
    }

    @Override
    public void changePasswordSuccess() {
        ToastUtilsKt.toast(this,"修改成功");
        onBackPressed();
    }
}
