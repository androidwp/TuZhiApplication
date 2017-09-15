package com.tuzhi.application.moudle.login.mvp;

import android.text.TextUtils;

import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.login.bean.User;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.UserInfoUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    private final String URL = "login";

    @Override
    public void commitUser(User user) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("loginname", user.getUserName());
        parameter.put("password", user.getPassWord());
        HttpUtilsKt.post(mView.getContext(), URL, parameter, HttpInitBean.class, new HttpCallBack<HttpInitBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpInitBean httpUserBean, @NotNull String text) {
                UserInfoUtils.saveUserInfo(mView.getContext(), text, httpUserBean);
                if (TextUtils.equals(httpUserBean.isLoginStatus(), "true"))
                    if (httpUserBean.isFirstUse()) {
                        mView.skipChangeInitPassword();
                    } else {
                        mView.skip();
                    }
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
