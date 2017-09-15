package com.tuzhi.application.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;

/**
 * Created by wangpeng on 2017/6/20.
 */

public class HttpInitBean extends BaseObservable {

    private String showAdfalg;
    private String phone;
    private String nickname;
    private String loginStatus;
    private String userImage;
    private String userType;
    private String resultMsg;
    private String username;
    private String email;
    private String userId;
    private String resultCode;
    private String updateReamrk;
    private String downloadUrl;
    private boolean forceUpdate;
    private boolean isUpdate;
    private boolean isFirstUse;

    public boolean isFirstUse() {
        return isFirstUse;
    }

    public void setFirstUse(boolean firstUse) {
        isFirstUse = firstUse;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String isShowAdfalg() {
        return showAdfalg;
    }

    public void setShowAdfalg(String showAdfalg) {
        this.showAdfalg = showAdfalg;
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        notifyPropertyChanged(BR.nickname);
    }

    public String isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
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

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
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

    public String getUpdateReamrk() {
        return updateReamrk;
    }

    public void setUpdateReamrk(String updateReamrk) {
        this.updateReamrk = updateReamrk;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public boolean isIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
}
