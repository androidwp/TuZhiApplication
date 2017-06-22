package com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailsecond.mvp;


import android.databinding.ViewDataBinding;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityBindingPhoneEmailSecondBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailsecond.bean.BindingPhoneOrEmailSecondBean;
import com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailfirst.mvp.BindingPhoneOrEmailFirstActivity;
import com.tuzhi.application.utils.ToastUtilsKt;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BindingPhoneOrEmailSecondActivity extends MVPBaseActivity<BindingPhoneOrEmailSecondContract.View, BindingPhoneOrEmailSecondPresenter> implements BindingPhoneOrEmailSecondContract.View {

    public static final String TEXT = "TEXT";
    public boolean canClick = true;
    private CountDownTimer countDownTimer;
    private String text;
    private String type;
    private BindingPhoneOrEmailSecondBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_binding_phone_email_second;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        type = getIntent().getStringExtra(BindingPhoneOrEmailFirstActivity.TYPE);
        text = getIntent().getStringExtra(TEXT);
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                bean.setNoteText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                canClick = true;
                bean.setNoteText("重新发送");
            }
        };

        switch (type) {
            case BindingPhoneOrEmailFirstActivity.PHONE:
                bean = new BindingPhoneOrEmailSecondBean(BindingPhoneOrEmailFirstActivity.PHONE, "绑定手机号", "验证码已发到：" + text, "");
                countDownTimer.start();
                break;
            default:
                bean = new BindingPhoneOrEmailSecondBean(BindingPhoneOrEmailFirstActivity.EMAIL, "绑定邮箱", "验证码已发到：" + text, "重新发送");
                break;
        }
        ActivityBindingPhoneEmailSecondBinding binding = (ActivityBindingPhoneEmailSecondBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setData(bean);
    }

    public void resend() {
        if (canClick) {
            canClick = false;
            countDownTimer.start();
            mPresenter.sendNote(type, text);
        }
    }

    public void commitAuthCode(String authCode) {
        if (!TextUtils.isEmpty(authCode)) {
            mPresenter.commitAuthCode(authCode);
        }
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void sendSuccess() {
        ToastUtilsKt.toast(this, "发送成功");
    }


}
