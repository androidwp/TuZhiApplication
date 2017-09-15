package com.tuzhi.application.moudle.login.forgetpassword.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ForgetPasswordPresenter extends BasePresenterImpl<ForgetPasswordContract.View> implements ForgetPasswordContract.Presenter {

    private static final String URL = "smsVerifyCode";

    private static final String URL_FORGET = "forgetpwd";


    @Override
    public void getVerificationCode(String type, final String phoneOrEmail) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("type", "4");
        if (type.equals(ForgetPasswordActivity.PHONE))
            parameter.put("phone", phoneOrEmail);
        else
            parameter.put("email", phoneOrEmail);

        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.getVerificationCodeSuccess(phoneOrEmail);
            }

            @Override
            public void onFailure(@NotNull String text) {
                mView.getVerificationCodeError();
            }
        });
    }

    @Override
    public void changePassword(String type, String phoneOrEmail, String verificationCode, String password) {
        checkVerificationCode(type, phoneOrEmail, verificationCode, password);
    }


    private void checkVerificationCode(final String type, final String phoneOrEmail, String verificationCode, final String password) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("step", "1");
        if (type.equals(ForgetPasswordActivity.PHONE))
            parameter.put("phone", phoneOrEmail);
        else
            parameter.put("email", phoneOrEmail);
        parameter.put("verifyCode", verificationCode);
        HttpUtilsKt.post(mView.getContext(), URL_FORGET, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                commitNewPassword(type, phoneOrEmail, password);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    private void commitNewPassword(String type, String phoneOrEmail, String password) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("step", "2");
        if (type.equals(ForgetPasswordActivity.PHONE))
            parameter.put("phone", phoneOrEmail);
        else
            parameter.put("email", phoneOrEmail);
        parameter.put("newpwdString", password);
        HttpUtilsKt.post(mView.getContext(), URL_FORGET, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.changePasswordSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

}
