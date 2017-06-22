package com.tuzhi.application.moudle.mine.personalinformation.rename.mvp;

import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.UserInfoUtils;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RenamePresenter extends BasePresenterImpl<RenameContract.View> implements RenameContract.Presenter {

    private static final String URL = "user/updateStaffInfo";

    @Override
    public void commitName(final String name) {
        HttpInitBean userInfo = UserInfoUtils.getUserInfo(mView.getContext());
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("nickname", name);
        parameter.put("userImage", userInfo.getUserImage());
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                UserInfoUtils.changeUserInfo(mView.getContext(), "nickname", name);
                EventBus.getDefault().post(ConstantKt.getUPDATE_USER_INFO_EVENT());
                mView.back();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }
}
