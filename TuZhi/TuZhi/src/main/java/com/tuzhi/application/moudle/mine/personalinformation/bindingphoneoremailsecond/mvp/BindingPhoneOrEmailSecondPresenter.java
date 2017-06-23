package com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailsecond.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailfirst.mvp.BindingPhoneOrEmailFirstActivity;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 */

public class BindingPhoneOrEmailSecondPresenter extends BasePresenterImpl<BindingPhoneOrEmailSecondContract.View> implements BindingPhoneOrEmailSecondContract.Presenter {

    private static final String URL_SMS = "smsVerifyCode";
    private static final String URL_UPDATE = "user/updatePhoneOrEmail";

    //向后台请求短信验证码
    @Override
    public void sendNote(String type, String phoneOrEmail) {

        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("type", "4");
        if (type.equals(BindingPhoneOrEmailFirstActivity.PHONE))
            parameter.put("phone", phoneOrEmail);
        else
            parameter.put("email", phoneOrEmail);

        HttpUtilsKt.post(mView.getContext(), URL_SMS, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.sendSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });


    }

    //提交验证码
    @Override
    public void commitAuthCode(final String type, final String phoneOrEmail, final String code) {

        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("step", "1");
        if (type.equals(BindingPhoneOrEmailFirstActivity.PHONE))
            parameter.put("phone", phoneOrEmail);
        else
            parameter.put("email", phoneOrEmail);
        parameter.put("verifyCode", code);

        HttpUtilsKt.post(mView.getContext(), URL_UPDATE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                updatePhoneOrEmail(type, phoneOrEmail, code);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

    private void updatePhoneOrEmail(String type, String phoneOrEmail, String code) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("step", "2");
        if (type.equals(BindingPhoneOrEmailFirstActivity.PHONE))
            parameter.put("phone", phoneOrEmail);
        else
            parameter.put("email", phoneOrEmail);
        parameter.put("verifyCode", code);

        HttpUtilsKt.post(mView.getContext(), URL_UPDATE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.bindSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

}
