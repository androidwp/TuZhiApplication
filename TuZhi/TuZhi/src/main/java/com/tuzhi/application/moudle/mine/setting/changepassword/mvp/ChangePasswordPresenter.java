package com.tuzhi.application.moudle.mine.setting.changepassword.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangePasswordPresenter extends BasePresenterImpl<ChangePasswordContract.View> implements ChangePasswordContract.Presenter{

    private static final String URL="user/updatepwd";

    @Override
    public void commitPassword(String oldPassword, String newPassword) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("newpwd",newPassword);
        parameter.put("oldpwd",oldPassword);
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.back();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }
}
