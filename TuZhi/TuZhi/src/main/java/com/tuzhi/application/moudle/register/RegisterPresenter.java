package com.tuzhi.application.moudle.register;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter {

    private static final String URL_AUTH_CODE = "smsVerifyCode";
    private static final String URL_REGISTER = "register";

    @Override
    public void getAuthCode(String phone) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("type", "1");
        parameter.put("phone", phone);
        HttpUtilsKt.post(mView.getContext(), URL_AUTH_CODE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.getAuthCodeSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {
                mView.getAuthCodeErrno();
            }
        });
    }

    @Override
    public void register(RegisterBean bean) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("phone", bean.getPhoneNumber());
        parameter.put("verifyCode", bean.getAuthCode());
        parameter.put("password", bean.getPassword());
        parameter.put("nickname", bean.getName());
        parameter.put("companyName", bean.getCompany());
        HttpUtilsKt.post(mView.getContext(), URL_REGISTER, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.commitSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }
}
