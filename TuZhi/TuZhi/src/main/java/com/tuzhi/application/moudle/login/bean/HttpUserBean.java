package com.tuzhi.application.moudle.login.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;

/**
 * Created by wangpeng on 2017/6/21.
 */

public class HttpUserBean extends BaseObservable{
    
    private String resultMsg;
    private String phone;
    private String username;
    private String loginStatus;
    private String email;
    private String nickname;
    private String userId;
    private String resultCode;
    private String userImage;
    private String userType;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Bindable
    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
        notifyPropertyChanged(BR.userImage);
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
