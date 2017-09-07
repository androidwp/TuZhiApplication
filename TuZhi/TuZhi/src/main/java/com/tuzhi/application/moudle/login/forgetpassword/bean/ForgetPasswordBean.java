package com.tuzhi.application.moudle.login.forgetpassword.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;

/**
 * Created by wangpeng on 2017/9/4.
 */

public class ForgetPasswordBean extends BaseObservable {
    private String phoneHint = "请输入手机号";
    private String emailHint = "请输入邮箱账号";
    private String changePhone = "切换手机号";
    private String changeEmail = "切换邮箱账号";
    //获取验证码的信息
    private String authCodeInfo = "获取验证码";
    private boolean phoneStatus = true;
    private String phoneOrEmailText;
    private String authCodeText;
    private String passwordText;

    @Bindable
    public String getAuthCodeInfo() {
        return authCodeInfo;
    }

    public void setAuthCodeInfo(String authCodeInfo) {
        this.authCodeInfo = authCodeInfo;
        notifyPropertyChanged(BR.authCodeInfo);
    }

    public String getPhoneOrEmailText() {
        return phoneOrEmailText;
    }

    public void setPhoneOrEmailText(String phoneOrEmailText) {
        this.phoneOrEmailText = phoneOrEmailText;
    }

    public String getChangePhone() {
        return changePhone;
    }

    public String getChangeEmail() {
        return changeEmail;
    }

    public String getPhoneHint() {
        return phoneHint;
    }

    public void setPhoneHint(String phoneHint) {
        this.phoneHint = phoneHint;
    }

    public String getEmailHint() {
        return emailHint;
    }

    public void setEmailHint(String emailHint) {
        this.emailHint = emailHint;
    }

    @Bindable
    public boolean isPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(boolean phoneStatus) {
        this.phoneStatus = phoneStatus;
        notifyPropertyChanged(BR.phoneStatus);
    }


    public String getAuthCodeText() {
        return authCodeText;
    }

    public void setAuthCodeText(String authCodeText) {
        this.authCodeText = authCodeText;
    }

    public String getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(String passwordText) {
        this.passwordText = passwordText;
    }
}
