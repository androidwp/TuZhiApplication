package com.tuzhi.application.moudle.register;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;

/**
 * Created by wangpeng on 2017/11/15.
 */

public class RegisterBean extends BaseObservable {
    private String phoneNumber;
    private String authCodeInfo;
    private String authCode;
    private String password;
    private String name;
    private String company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Bindable
    public String getAuthCodeInfo() {
        return authCodeInfo;
    }

    public void setAuthCodeInfo(String authCodeInfo) {
        this.authCodeInfo = authCodeInfo;
        notifyPropertyChanged(BR.authCodeInfo);
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
