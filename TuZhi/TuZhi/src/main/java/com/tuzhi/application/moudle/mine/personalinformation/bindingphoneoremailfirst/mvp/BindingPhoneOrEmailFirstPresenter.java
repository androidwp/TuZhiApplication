package com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailfirst.mvp;

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

public class BindingPhoneOrEmailFirstPresenter extends BasePresenterImpl<BindingPhoneOrEmailFirstContract.View> implements BindingPhoneOrEmailFirstContract.Presenter {

    private static final String URL = "smsVerifyCode";

    //提交检查号码正确性。获取验证码
    @Override
    public void commitPhoneOrEmail(String type, final String phoneOrEmail) {


        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("type", "4");
        if (type.equals(BindingPhoneOrEmailFirstActivity.PHONE))
            parameter.put("phone", phoneOrEmail);
        else
            parameter.put("email", phoneOrEmail);

        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.skipNextPage(phoneOrEmail);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });


    }
}
