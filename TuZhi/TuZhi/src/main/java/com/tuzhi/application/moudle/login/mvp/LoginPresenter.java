package com.tuzhi.application.moudle.login.mvp;

import android.text.TextUtils;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.login.bean.HttpUserBean;
import com.tuzhi.application.moudle.login.bean.User;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;

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
        HttpUtilsKt.post(mView.getContext(), URL, parameter, HttpUserBean.class, new HttpCallBack<HttpUserBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpUserBean httpUserBean, @NotNull String text) {
                SharedPreferencesUtilsKt.saveLongCache(mView.getContext(), ConstantKt.getLOGIN_INFO(), text);
                SharedPreferencesUtilsKt.saveLongCache(mView.getContext(), ConstantKt.getUSER_TYPE(), httpUserBean.getUserType());
                SharedPreferencesUtilsKt.saveLongCache(mView.getContext(), ConstantKt.getUSER_ID(), httpUserBean.getUserId());
                SharedPreferencesUtilsKt.saveLongCache(mView.getContext(), ConstantKt.getLOGIN_STATUS(), httpUserBean.isLoginStatus());
                SharedPreferencesUtilsKt.saveLongCache(mView.getContext(), ConstantKt.getIMAGE_HEAD(), httpUserBean.getUserImage());
                if (TextUtils.equals(httpUserBean.isLoginStatus(), "true"))
                    mView.skip();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
